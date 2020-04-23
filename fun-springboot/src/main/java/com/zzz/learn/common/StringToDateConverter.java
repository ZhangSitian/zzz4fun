package com.zzz.learn.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 日期转换器，解决在post请求中日期类型参数自动转Date类型
 * @author yinjihuan
 *
 */
public class StringToDateConverter implements Converter<String, Date> {
    private static final String dateFormat1 = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat1 = "yyyy-MM-dd";
    private static final String dateFormat2 = "yyyy/MM/dd HH:mm:ss";
    private static final String shortDateFormat2 = "yyyy/MM/dd";
    @Override
    public Date convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        source = source.trim();
        try {
            SimpleDateFormat formatter;
            String dateFormat = "";
            String shortDateFormat = "";
            if (source.contains("-")) {
                dateFormat =  dateFormat1;
                shortDateFormat = shortDateFormat1;
            } else if (source.contains("/")) {
                dateFormat =  dateFormat2;
                shortDateFormat = shortDateFormat2;
            }
            if (source.contains(":")) {
                formatter = new SimpleDateFormat(dateFormat);
            } else {
                formatter = new SimpleDateFormat(shortDateFormat);
            }
            return formatter.parse(source);
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
    }
}
