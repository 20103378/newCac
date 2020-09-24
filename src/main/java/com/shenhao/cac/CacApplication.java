package com.shenhao.cac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shenhao.cac.*.dao")
public class CacApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacApplication.class, args);
    }

}
