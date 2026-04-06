package kr.co.javaex.sec23.domain;

public class Product {
    private int productId;
    private String productName;
    private String productDesc;
    private int productPrice;
    private int productStock;
    private String productCategory;
    private String productStatus;

    public Product() {}
    public Product(int productId, String productName, String productDesc, int productPrice, int productStock, String productCategory, String productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productCategory = productCategory;
        this.productStatus = productStatus;
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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
