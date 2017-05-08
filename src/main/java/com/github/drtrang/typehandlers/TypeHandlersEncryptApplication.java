package com.github.drtrang.typehandlers;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.drtrang.typehandlers.mapper")
public class TypeHandlersEncryptApplication {

    public static void main(String[] args) {
        SpringApplication.run(TypeHandlersEncryptApplication.class, args);
    }
}