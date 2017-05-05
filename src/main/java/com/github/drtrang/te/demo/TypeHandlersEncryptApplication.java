package com.github.drtrang.te.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.drtrang.te.demo.mapper")
public class TypeHandlersEncryptApplication {

    public static void main(String[] args) {
        SpringApplication.run(TypeHandlersEncryptApplication.class, args);
    }
}