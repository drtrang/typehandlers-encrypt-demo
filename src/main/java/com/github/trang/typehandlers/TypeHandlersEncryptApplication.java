package com.github.trang.typehandlers;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.trang.typehandlers.mapper")
public class TypeHandlersEncryptApplication {

    public static void main(String[] args) {
        SpringApplication.run(TypeHandlersEncryptApplication.class, args);
    }

}
