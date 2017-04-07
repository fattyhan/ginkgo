package jd.ginkgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by admin on 2017/4/6.
 */
@SpringBootApplication
@PropertySource({"classpath:common.properties"})
public class Application {
        public static void main(String[] args) throws Exception {
            SpringApplication.run(Application.class, args);
    }
}
