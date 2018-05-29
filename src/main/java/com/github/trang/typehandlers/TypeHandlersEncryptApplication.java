package com.github.trang.typehandlers;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.trang.typehandlers.domain.BaseCode;
import com.github.trang.typehandlers.mapper.BaseCodeMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@MapperScan("com.github.trang.typehandlers.mapper")
@Slf4j
public class TypeHandlersEncryptApplication implements CommandLineRunner {

    @Autowired
    private BaseCodeMapper baseCodeMapper;

    public static void main(String[] args) {
        SpringApplication.run(TypeHandlersEncryptApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BaseCode record1 = BaseCode.builder()
                .codeType("country")
                .codeValue("china")
                .remark("中国")
                .build();
        BaseCode record2 = BaseCode.builder()
                .codeType("city")
                .codeValue("bj")
                .remark("北京")
                .build();
        BaseCode record3 = BaseCode.builder()
                .codeType("city")
                .codeValue("tj")
                .remark("天津")
                .build();
        BaseCode record4 = BaseCode.builder()
                .codeType("city")
                .codeValue("cq")
                .remark("重庆")
                .build();
        List<BaseCode> baseCodes = Arrays.asList(record1, record2, record3, record4);
        baseCodes.forEach(baseCodeMapper::insert);
        log.info("application completed.");
    }

}