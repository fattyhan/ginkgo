package jd.ginkgo;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import jd.ginkgo.commander.ConsumerHandler;
import jd.ginkgo.db.HazelcastMapHelper;
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
    private static Config config = new Config();
    private static HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
        public static void main(String[] args) throws Exception {
            SpringApplication.run(Application.class, args);
            //启动Hazel
            new HazelcastMapHelper(h);
            //启动cep处理器,自动重启服务
            try {
                new ConsumerHandler().main();
            }catch (Exception e){
                new ConsumerHandler().main();
            }
    }
}
