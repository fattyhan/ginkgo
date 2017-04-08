package jd.ginkgo.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**<p>
 *    解析properties文件
 * </p>
 * Created by hanxiaofei on 2017/4/7.
 */
@Component
public  class ConfigHelper {

    //------------------kafka topic
    public static String PRO_TRIGGER_TOPIC;
    public static String PRO_HIT_TOPIC;
    public static String PRO_PAID_TOPIC;
    public static String PRO_SEARCH_TOPIC;

    //-------------------seavice addr
    public static String BOOT_STRAP_SERVERS;
    public static String ZOOKEEPER_CONNECT;

    //--------------------统计相关
    public static String INDEX_STATISTICS;


    @Autowired
    public ConfigHelper(Environment env) {
        ConfigHelper.INDEX_STATISTICS = env.getRequiredProperty("pro.4index.statistics").toLowerCase();
        ConfigHelper.ZOOKEEPER_CONNECT = env.getRequiredProperty("pro.zookeeper.addr").toLowerCase();
        ConfigHelper.BOOT_STRAP_SERVERS = env.getRequiredProperty("pro.kafka.addr").toLowerCase();
        ConfigHelper.PRO_SEARCH_TOPIC = env.getRequiredProperty("pro.paid.topic").toLowerCase();
        ConfigHelper.PRO_PAID_TOPIC = env.getRequiredProperty("pro.4index.statistics").toLowerCase();
        ConfigHelper.PRO_HIT_TOPIC = env.getRequiredProperty("pro.hit.topic").toLowerCase();
        ConfigHelper.PRO_TRIGGER_TOPIC = env.getRequiredProperty("pro.trigger.topic").toLowerCase();

    }
}
