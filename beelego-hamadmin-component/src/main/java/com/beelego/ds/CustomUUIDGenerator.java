package com.beelego.ds;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.boot.registry.classloading.spi.ClassLoadingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDGenerationStrategy;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.id.uuid.StandardRandomStrategy;
import org.hibernate.internal.CoreLogging;
import org.hibernate.internal.CoreMessageLogger;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;


/**
 * 本地化定制生成UUID
 *
 * @author : hama
 * @since : created in  2018/6/25
 */
public class CustomUUIDGenerator implements IdentifierGenerator, Configurable {

    public static final String UUID_GEN_STRATEGY = "uuid_gen_strategy";
    public static final String UUID_GEN_STRATEGY_CLASS = "uuid_gen_strategy_class";
    private static final CoreMessageLogger LOG = CoreLogging.messageLogger(UUIDGenerator.class);
    private UUIDGenerationStrategy strategy;
    private UUIDTypeDescriptor.ValueTransformer valueTransformer;
    private boolean lower;


    public CustomUUIDGenerator() {
    }

    public static CustomUUIDGenerator buildSessionFactoryUniqueIdentifierGenerator() {
        CustomUUIDGenerator generator = new CustomUUIDGenerator();
        generator.strategy = StandardRandomStrategy.INSTANCE;
        generator.valueTransformer = UUIDTypeDescriptor.ToStringTransformer.INSTANCE;
        generator.lower = false;
        return generator;
    }

    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        this.strategy = (UUIDGenerationStrategy) params.get("uuid_gen_strategy");
        if (this.strategy == null) {
            String strategyClassName = params.getProperty("uuid_gen_strategy_class");
            if (strategyClassName != null) {
                try {
                    ClassLoaderService cls = (ClassLoaderService) serviceRegistry.getService(ClassLoaderService.class);
                    Class strategyClass = cls.classForName(strategyClassName);

                    try {
                        this.strategy = (UUIDGenerationStrategy) strategyClass.newInstance();
                    } catch (Exception var8) {
                        LOG.unableToInstantiateUuidGenerationStrategy(var8);
                    }
                } catch (ClassLoadingException var9) {
                    LOG.unableToLocateUuidGenerationStrategy(strategyClassName);
                }
            }
        }

        if (this.strategy == null) {
            this.strategy = StandardRandomStrategy.INSTANCE;
        }

        if (UUID.class.isAssignableFrom(type.getReturnedClass())) {
            this.valueTransformer = UUIDTypeDescriptor.PassThroughTransformer.INSTANCE;
        } else if (String.class.isAssignableFrom(type.getReturnedClass())) {
            this.valueTransformer = UUIDTypeDescriptor.ToStringTransformer.INSTANCE;
        } else {
            if (!byte[].class.isAssignableFrom(type.getReturnedClass())) {
                throw new HibernateException("Unanticipated return type [" + type.getReturnedClass().getName() + "] for UUID conversion");
            }

            this.valueTransformer = UUIDTypeDescriptor.ToBytesTransformer.INSTANCE;
        }

        // 加载lower
        if (null != params.getProperty("lower")) {
            this.lower = Boolean.parseBoolean(params.getProperty("lower"));
        }
    }

    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        String uuid = this.valueTransformer.transform(this.strategy.generateUUID(session)).toString();
        return this.lower ? uuid : uuid.toUpperCase();
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String uuid = this.valueTransformer.transform(this.strategy.generateUUID(sharedSessionContractImplementor)).toString();
        return this.lower ? uuid : uuid.toUpperCase();
    }
}
