package kr.co.javaex.sec23.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void save(String fileName, Object data) {
        File file = new File(fileName);

        try {
            mapper.writeValue(file, data);
        } catch(IOException e) {
            System.out.println("파일 저장 중 오류 발생!");
            e.printStackTrace();
        }
    }

    public static <T> T load(String fileName, Class<T> clazz) {
        File file = new File(fileName);

        if(!file.exists())
            return null;

        try {
            return mapper.readValue(file, clazz);
        } catch(IOException e) {
            System.out.println("파일 읽기 중 오류 발생!");
            e.printStackTrace();
            return null;
        }
    }

}
