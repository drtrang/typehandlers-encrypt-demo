package com.github.trang.typehandlers.test;

import java.util.List;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.trang.typehandlers.TypeHandlersEncryptApplication;
import com.github.trang.typehandlers.domain.BaseCode;
import com.github.trang.typehandlers.mapper.BaseCodeMapper;
import com.github.trang.typehandlers.util.EncryptUtil;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TypeHandlersEncryptApplication.class)
@Slf4j
public class TypeHandlersEncryptApplicationTests {

    @Autowired
    private BaseCodeMapper mapper;

    @Test
    public void encrypt() {
        System.out.println(EncryptUtil.encrypt("000"));
    }

    @Test
    public void sql() {
        BaseCode code = new BaseCode();
        code.setCodeType("sql");
        code.setCodeValue("update");
        code.setIsEncrypt(false);
        mapper.insertSelective(code);
    }

    @Test
    public void all() {
        List<BaseCode> baseCodes = mapper.selectByExample(null);
        baseCodes.stream().map(new Gson()::toJson).forEach(log::info);
    }

    @Test
    public void once() {
        long start = System.currentTimeMillis();
        List<BaseCode> baseCodes = mapper.selectByExample(null);
        log.info("size: {}", baseCodes.size());
        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);
    }

    @Test
    public void ten_times() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            List<BaseCode> baseCodes = mapper.selectByExample(null);
            log.info("times:{}, size: {}", i, baseCodes.size());
        }
        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);
    }

    @Test
    public void hundred_times() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            List<BaseCode> baseCodes = mapper.selectByExample(null);
            log.info("times:{}, size: {}", i, baseCodes.size());
        }
        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);
    }

}