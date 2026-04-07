package kr.co.javaex.sec23.domain;

public class Product {
    private int productId;
    private String productName;
    private String productDesc;
    private int productPrice;
    private int productStock;
    private String productStatus;

    public Product() {}
    public Product(int productId, String productName, String productDesc, int productPrice, int productStock, String productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productStatus = productStatus;
    }
    public Product(int productId, String productName, String productDesc, int productPrice, int productStock) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productStock = productStock;
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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return String.format(
                "[상품 ID: %d] %s | 가격: %d원 | 재고: %d개 | 상태: %s\n - 상세설명: %s",
                productId, productName, productPrice, productStock, productStatus, productDesc
        );
    }
}
