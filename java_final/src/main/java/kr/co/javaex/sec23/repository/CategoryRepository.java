package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.domain.Category;
import kr.co.javaex.sec23.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryRepository {
    public static final String FILE_NAME = "categories.json";
    public List<Category> categoryList = new ArrayList<>();

    public CategoryRepository() {
        loadData();
    }

    private void loadData() {
        Category[] categorys = FileUtil.load(FILE_NAME, Category[].class);

        if (categorys != null)
            categoryList = new ArrayList<>(Arrays.asList(categorys));
    }

    public void save(Category category) {
        categoryList.add(category);
        FileUtil.save(FILE_NAME, categoryList);
    }

    public Category findCategoryId(int categoryId) {
        for(Category category : categoryList) {
            if (category.getCategoryId() == categoryId)
                return category;
        }
        return null;
    }

    public List<Category> findAll() {
        return categoryList;
    }
}
