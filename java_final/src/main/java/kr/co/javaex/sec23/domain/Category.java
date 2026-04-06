package kr.co.javaex.sec23.domain;

public class Category {
    private int categoryId;
    private Integer categoryParentId;
    private String categoryName;
    private int categoryAsc;

    public Category() {}
    public Category(int categoryId, Integer categoryParentId, String categoryName, int categoryAsc) {
        this.categoryId = categoryId;
        this.categoryParentId = categoryParentId;
        this.categoryName = categoryName;
        this.categoryAsc = categoryAsc;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryParentId() {
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

    public int getCategoryAsc() {
        return categoryAsc;
    }

    public void setCategoryAsc(int categoryAsc) {
        this.categoryAsc = categoryAsc;
    }
}
