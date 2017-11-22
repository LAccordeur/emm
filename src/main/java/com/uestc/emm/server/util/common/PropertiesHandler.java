package com.uestc.emm.server.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Properties资源文件Handler   拦截器中不适用（其调用周期处于servlet中与filter所属时间所属线程不一样） 由于该类初始化的线程与拦截器所在线程不一致，故在拦截器里调用get回报空指针异常
 *
 */
public class PropertiesHandler {


    public static final ThreadLocal<Map<String, Boolean>> holder = new ThreadLocal<Map<String, Boolean>>();
    protected static Logger logger = LoggerFactory.getLogger(PropertiesHandler.class);
    private static Properties props = new Properties();

    static {
        try {
            props.load(PropertiesHandler.class.getResourceAsStream("/props/system.properties"));
            putProperties(props);
            System.out.println("加载系统配置文件");
        } catch (IOException e) {
            logger.error("加载配置文件失败", e);
        }
    }

    /**
     * 在项目启动的时候将配置的数据加到holder中
     */
    public static void putProperties(Properties properties) {
        Map<String, Boolean> props = new HashMap<String, Boolean>(properties.size());
        Set<String> propKeys = properties.stringPropertyNames();
        for (String str : propKeys) {
            props.put(str, Boolean.valueOf(properties.getProperty(str)));
        }
        holder.set(props);
    }

    /**
     * 从holer中获取数据源字符串
     */
    public static String getProperties(String key) {
        Map<String, Boolean> keyValue = holder.get();
        for (Map.Entry<String, Boolean> entry : keyValue.entrySet()) {
            if (key.equals(entry.getKey())) {
                return String.valueOf(entry.getValue());
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Boolean flag = Boolean.valueOf(PropertiesHandler.getProperties("IS_OPEN_AUTH_TOKEN"));
        System.out.println(flag);
    }
}
