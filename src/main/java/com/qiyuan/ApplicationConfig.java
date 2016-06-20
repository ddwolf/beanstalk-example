package com.qiyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by duxiutao duxiutao@gmail.com on 16/6/19.
 */
@RestController
@SpringBootApplication
@Controller
@Import({DBConfig.class, VelocityAutoConfiguration.class})
public class ApplicationConfig {
    @RequestMapping("/")
    String Home() {
        return "<h1>Hi 阿杜</h1>";
    }

    @RequestMapping("/title")
    ModelAndView title() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "你好世界");
        mav.setViewName("index");
        return mav;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class);
    }
}
