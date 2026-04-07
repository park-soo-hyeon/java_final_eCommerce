package kr.co.javaex.sec23.domain;

public class Order {
    private int orderId;
    private String userId;
    private int productId;
    private String productName;
    private int productPrice;
    private int productQuantity;

    public Order() {}

    // Controller에서 넘겨주는 7개의 값을 모두 받을 수 있도록 생성자 수정
    public Order(int orderId, String userId, int productId, String productName, int productPrice, int productQuantity) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    // 만약을 위해 데이터를 예쁘게 출력해주는 toString() 추가
    @Override
    public String toString() {
        return String.format(
                "[주문 번호: %d] %s (상품 ID: %d) | 결제금액: %d원 | 수량: %d개",
                orderId, productName, productPrice * productQuantity, productId, productQuantity
        );
    }
}