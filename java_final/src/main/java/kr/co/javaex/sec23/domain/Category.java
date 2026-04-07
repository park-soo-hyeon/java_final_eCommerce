package kr.co.javaex.sec23.domain;

public class Category {
    private int categoryId;
    private Integer categoryParentId;
    private String categoryName;

    public Category() {}
    public Category(int categoryId, Integer categoryParentId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryParentId = categoryParentId;
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Integer categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
