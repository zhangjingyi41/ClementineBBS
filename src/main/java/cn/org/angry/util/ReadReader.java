package cn.org.angry.util;

import java.io.IOException;
import java.io.Reader;

/**
 * 提取request.reader中的json数据
 */
public class ReadReader {
    public static String read(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1){
            sb.append((char)c);
        }
        return sb.toString();
    }
}
