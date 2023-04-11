package cn.org.angry.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Properties;

public class DBUtil {
    private volatile static JdbcTemplate template;
    private DBUtil(){}
    public static JdbcTemplate getInstance(){
        if(template==null){
            synchronized (DBUtil.class){
                if(template==null){
                    Properties properties = new Properties();
                    try {
                        properties.load(DBUtil.class.getClassLoader().getResourceAsStream("database-druid.properties"));
                        template = new JdbcTemplate(DruidDataSourceFactory.createDataSource(properties));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return template;
    }
}
