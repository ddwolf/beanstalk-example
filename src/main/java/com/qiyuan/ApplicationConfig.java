package com.qiyuan;

import com.surftools.BeanstalkClient.Job;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

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
        mav.addObject("title", "你好世界"+s);
        mav.setViewName("index");
        return mav;
    }
    @RequestMapping("/put")
    long put(@RequestParam String data){
        return BeanStalkHelper.getClient().put(1, 0, 1000, data.getBytes());
    }
    @RequestMapping("/get")
    String get(){
        Job job = BeanStalkHelper.getClient().reserve(null);
//        BeanStalkHelper.getClient().delete(job.getJobId());
        return job.getJobId()+":"+new String(job.getData());
    }
    public static class Car {
        public void ss(String s){};
        public Car() {
            System.out.println("[initializing car object]");
        }
        public String toString(){
            return "CAR::::";

        };
    }

    @Component
    public static class Service implements FactoryBean<Car> {
        public Service() {

        }
        Object S = new String("Hello");
        public Car getObject(){
            return new Car();
        }

        @Override
        public Class<?> getObjectType() {
            return Car.class;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }
        public String toString(){
            return "this is a service[FactoryBean]";
        }
    }

    @Autowired
//    @Qualifier("Service")
    Car s;
    public static void main(String[] args) throws Exception{
        Method m = Car.class.getDeclaredMethod("ss", String.class);
        System.out.println(m.getParameterTypes()[0].getName());
        System.out.println(AnnotatedElementUtils.getMetaAnnotationTypes(ApplicationConfig.class, "org.springframework.web.bind.annotation.RestController"));
        SpringApplication.run(ApplicationConfig.class);
    }
}
