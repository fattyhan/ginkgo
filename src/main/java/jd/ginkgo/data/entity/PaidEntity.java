package jd.ginkgo.data.entity;

/**
 * Created by hanxiaofei on 2017/4/7.
 */
public class PaidEntity extends Entity {
    private String userPin;
    private String orderId;
    private Double orderAmount;
    private int productItem;

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
