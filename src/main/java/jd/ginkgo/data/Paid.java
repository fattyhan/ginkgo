package jd.ginkgo.data;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * 支付完成表明消费成功，可以累加用户的消费额
 *
 * Created by hanxiaofei on 2017/4/7.
 */
public class Paid extends BaseData {
    private String userPin;
    private String orderId;
    private Double orderAmount;
    private int productItem;

    public Paid(String userPin, String orderId, Double orderAmount, int productItem) {
        this.userPin = userPin;
        this.orderId = orderId;
        this.orderAmount = orderAmount;
        this.productItem = productItem;
    }

    public static Paid parsePaid(String json) throws IOException {
        return JSON.parseObject(json,Paid.class);
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getProductItem() {
        return productItem;
    }

    public void setProductItem(int productItem) {
        this.productItem = productItem;
    }
}
