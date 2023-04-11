package cn.org.angry.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * JackJSON工具类
 */
public class JSONUtil {
    private static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        // 忽略未知的json字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static <T> T toObject(String content, Class<T> clazz){
        try {
            return objectMapper.readValue(content, clazz);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static String toString(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
