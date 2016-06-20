package com.qiyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by duxiutao duxiutao@gmail.com on 16/6/19.
 */
@RestController
@SpringBootApplication
@Import(DBConfig.class)
public class ApplicationConfig {
    @RequestMapping("/")
    String Home(){
        return "Hi";
    }
    public static void main(String[] args){
        SpringApplication.run(ApplicationConfig.class);
    }
}
