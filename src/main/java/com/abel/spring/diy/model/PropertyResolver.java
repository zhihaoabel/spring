package com.abel.spring.diy.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.util.*;
import java.util.function.Function;

public class PropertyResolver {

    Logger logger = LoggerFactory.getLogger(getClass());
    Map<String, String> properties = new HashMap<>();
    Map<Class<?>, Function<String, Object>> converters = new HashMap<>();

    public PropertyResolver(Properties props) {
        // 读取环境变量和Properties，放到Map<String, String>里
        readProperties(props);
        // 注册converters
        registerConverters();
    }

    private void readProperties(Properties props) {
        // 读取环境变量
        this.properties.putAll(System.getenv());
        // 读取配置文件
        Set<String> names = props.stringPropertyNames();
        for (String name : names) {
            this.properties.put(name, props.getProperty(name));
        }
        // 打印 property 的EntrySet
        if (logger.isDebugEnabled()) {
            List<String> keys = new ArrayList<>(this.properties.keySet());
            Collections.sort(keys);
            for (String key : keys) {
                logger.debug("PropertyResolver: {} = {}", key, this.properties.get(key));
            }
        }
    }

    void registerConverters() {
        converters.put(String.class, s -> s);
        converters.put(boolean.class, Boolean::parseBoolean);
        converters.put(Boolean.class, Boolean::valueOf);

        converters.put(byte.class, Byte::parseByte);
        converters.put(Byte.class, Byte::valueOf);

        converters.put(short.class, Short::parseShort);
        converters.put(Short.class, Short::valueOf);

        converters.put(int.class, Integer::parseInt);
        converters.put(Integer.class, Integer::valueOf);

        converters.put(long.class, Long::parseLong);
        converters.put(Long.class, Long::valueOf);

        converters.put(float.class, Float::parseFloat);
        converters.put(Float.class, Float::valueOf);

        converters.put(double.class, Double::parseDouble);
        converters.put(Double.class, Double::valueOf);

        converters.put(LocalDate.class, LocalDate::parse);
        converters.put(LocalTime.class, LocalTime::parse);
        converters.put(LocalDateTime.class, LocalDateTime::parse);
        converters.put(ZonedDateTime.class, ZonedDateTime::parse);
        converters.put(Duration.class, Duration::parse);
        converters.put(ZoneId.class, ZoneId::of);
    }
}
