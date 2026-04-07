package kr.co.javaex.sec23.domain;

public class Cart {
    private int cartId;
    private String userId;
    private int productId;
    private int productQuantity;

    public Cart() {}
    public Cart(int cartId, String userId, int productId, int productQuantity) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
