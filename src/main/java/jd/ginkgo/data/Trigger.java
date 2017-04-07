package jd.ginkgo.data;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class Trigger extends  BaseData{
    private String orderID;
    private String userPin;

    public Trigger(String orderID, String userPin) {
        this.orderID = orderID;
        this.userPin = userPin;
    }

    /**
     * 将输入源抓取的数据解析为对象
     * @param json
     * @return
     * @throws IOException
     */
    public static Trigger parseTrigger(String json) throws IOException {
        return JSON.parseObject(json,Trigger.class);
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }
}
