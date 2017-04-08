package jd.ginkgo;

import jd.ginkgo.commander.ConsumerHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by admin on 2017/4/6.
 */
@EnableAutoConfiguration
@SpringBootApplication
@PropertySource({"classpath:common.properties"})
@ComponentScan(basePackages = "jd.ginkgo")
public class Application {
        public static void main(String[] args) throws Exception {
            SpringApplication.run(Application.class, args);
            //启动cep处理器,自动重启服务
            try {
                new ConsumerHandler().main();
            }catch (Exception e){
                new ConsumerHandler().main();
            }
    }
}
