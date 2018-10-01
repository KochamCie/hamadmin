package com.beelego.rest.github;

import java.io.*;

/**
 * @author : hama
 * @since : created in  2018/8/17
 */
public class NoteAPI {

    private static String path = "F:\\learningspace\\mybatis\\src\\main\\java\\";

    public static void main(String[] args) {

        File file = new File(path);
        System.out.println(file.isDirectory());
        show(file);


    }

    public static void show(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                show(file1);
            }
        } else {
            System.out.println(file.getAbsolutePath());
            if (!"F:\\learningspace\\mybatis\\src\\main\\java\\org\\apache\\ibatis\\annotations\\Arg.java".equals(file.getAbsolutePath())) {
                return;
            }
            BufferedReader reader = null;
            try {
                System.out.println("以行为单位读取文件内容，一次读一整行：");
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                StringBuffer sb = new StringBuffer();
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    // 显示行号
                    System.out.println("line " + line + ": " + tempString);
                    line++;
                    sb.append(tempString);
                    sb.append("\n");
                }
                reader.close();
                System.out.println(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }


        }
    }


}
