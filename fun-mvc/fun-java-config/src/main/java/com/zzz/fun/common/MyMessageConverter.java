package com.zzz.fun.common;

import com.zzz.fun.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    public MyMessageConverter(){
        super(new MediaType("application","x-zzz", StandardCharsets.UTF_8));
    }

    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(),StandardCharsets.UTF_8);
        String [] tempArr = temp.split("-");
        return new DemoObj(new Long(tempArr[0]),tempArr[1]);
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello:"+ demoObj.getId()+"-" +demoObj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
