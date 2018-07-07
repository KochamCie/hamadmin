package com.beelego.enums;

import com.beelego.global.exception.ApiException;
import sun.reflect.ConstructorAccessor;
import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: hama
 * @date: created in  2018/5/8
 * @description:
 */
public class DynamicEnum {
    private static ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();

    private static final String ENUM_CACHE_KEYWORD = "enumConstant";


    private static void setFailSafeFieldValue(Field field, Object target, Object value) throws NoSuchFieldException,
            IllegalAccessException {

        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        int modifiers = modifiersField.getInt(field);

        modifiers &= ~Modifier.FINAL;
        modifiersField.setInt(field, modifiers);

        FieldAccessor fa = reflectionFactory.newFieldAccessor(field, false);
        fa.set(target, value);
    }

    private static void cleanEnumCache(Class<?> enumClass, String fieldName) throws NoSuchFieldException,
            IllegalAccessException {
        for (Field field : Class.class.getDeclaredFields()) {
            if (field.getName().indexOf(fieldName) > -1) {
                AccessibleObject.setAccessible(new Field[]{field}, true);
                setFailSafeFieldValue(field, enumClass, null);
                break;
            }
        }
    }


    private static ConstructorAccessor getConstructorAccessor(Class<?> enumClass, Class<?>[] additionalParameterTypes)
            throws NoSuchMethodException {
        Class<?>[] parameterTypes = new Class[additionalParameterTypes.length + 2];
        parameterTypes[0] = String.class;
        parameterTypes[1] = int.class;
        System.arraycopy(additionalParameterTypes, 0, parameterTypes, 2, additionalParameterTypes.length);
        return reflectionFactory.newConstructorAccessor(enumClass.getDeclaredConstructor(parameterTypes));
    }

    private static Object makeEnum(Class<?> enumClass, String value, int ordinal, Class<?>[] additionalTypes,
                                   Object[] additionalValues) throws Exception {
        Object[] parms = new Object[additionalValues.length + 2];
        parms[0] = value;
        parms[1] = Integer.valueOf(ordinal);
        System.arraycopy(additionalValues, 0, parms, 2, additionalValues.length);
        return enumClass.cast(getConstructorAccessor(enumClass, additionalTypes).newInstance(parms));
    }

    /**
     * @param enumType
     * @param enumName
     * @param additionalTypes
     * @param additionalValues
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<?>> T addEnum(Class<T> enumType, String enumName, Class<?>[] additionalTypes, Object[] additionalValues) {
        Field valuesField = getValuesFiled(enumType);
        try {

            T[] previousValues = previousValues(enumType, valuesField);
            List<T> values = new ArrayList<T>(Arrays.asList(previousValues));

            T newValue = (T) makeEnum(enumType,
                    enumName,
                    values.size(),
                    additionalTypes,
                    additionalValues);

            values.add(newValue);

            setFailSafeFieldValue(valuesField, null, values.toArray((T[]) Array.newInstance(enumType, 0)));

            cleanEnumCache(enumType, ENUM_CACHE_KEYWORD);

            return newValue;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public static <T extends Enum<?>> T[] previousValues(Class<?> enumType, Field valuesField) {
        try {
            return (T[]) valuesField.get(enumType);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Field getValuesFiled(Class<?> enumType) {
        if (!Enum.class.isAssignableFrom(enumType)) {
            throw new RuntimeException("class " + enumType + " is not an instance of Enum");
        }

        Field valuesField = null;
        Field[] fields = enumType.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().contains("$VALUES")) {
                valuesField = field;
                break;
            }
        }
        AccessibleObject.setAccessible(new Field[]{valuesField}, true);
        return valuesField;
    }

    /**
     * @param clazz
     * @param enumName
     * @param <T>
     * @return
     */
    public static <T extends Enum<?>> T getEnum(Class<T> clazz, String enumName) {
        Field valuesField = getValuesFiled(clazz);
        T[] previousEnum = previousValues(clazz, valuesField);
        return Arrays.stream(previousEnum).filter(t -> enumName.equals(t.name()))
                .findFirst()
                .orElse(null);
    }


    public static <T extends Enum<?>> T getEnum(Class<T> clazz, String enumName, Object... addtionalValues) {

        //
        Constructor[] constructors = clazz.getDeclaredConstructors();
        int limit = Arrays.stream(constructors)
                .sorted((o1, o2) -> o2.getParameterCount() - o1.getParameterCount())
                .findFirst().orElse(constructors[0]).getParameterCount();
        if (addtionalValues.length + 2 > limit) {
            throw new ApiException(ErrorCodeEnum.ARGS_BEYOND_CONSTRUCTOR);
        }
        Class<?>[] additionalTypes = new Class[addtionalValues.length];
        for (int i = 0; i < addtionalValues.length; i++) {
            additionalTypes[i] = String.class;
        }
        addEnum(clazz, enumName, additionalTypes, addtionalValues);
        return getEnum(clazz, enumName);
    }


}
