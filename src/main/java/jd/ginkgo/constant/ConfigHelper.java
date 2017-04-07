package jd.ginkgo.constant;

import org.springframework.beans.factory.annotation.Value;

/**<p>
 *    解析properties文件
 * </p>
 * Created by hanxiaofei on 2017/4/7.
 */
public  class ConfigHelper {

    //------------------kafka topic
    @Value("${pro.trigger.topic}")
    public static String PRO_TRIGGER_TOPIC;
    @Value("${pro.hit.topic}")
    public static String PRO_HIT_TOPIC;
    @Value("${pro.paid.topic}")
    public static String PRO_PAID_TOPIC;
    @Value("${pro.search.topic}")
    public static String PRO_SEARCH_TOPIC;

    //-------------------seavice addr
    @Value("${pro.kafka.addr}")
    public static String BOOT_STRAP_SERVERS;
    @Value("${pro.zookeeper.addr}")
    public static String ZOOKEEPER_CONNECT;

    //--------------------统计相关
    @Value("${pro.4index.statistics}")
    public static String INDEX_STATISTICS;
}
