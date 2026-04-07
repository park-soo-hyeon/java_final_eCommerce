package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.domain.Category;
import kr.co.javaex.sec23.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryRepository {
    public static final String FILE_NAME = "java_final/src/main/java/resources/categories.json";
    public static List<Category> categoryList = new ArrayList<>();
    private static boolean isLoaded = false;

    public CategoryRepository() {
        if (!isLoaded) {
            loadData();
            isLoaded = true;
        }
    }

    private void loadData() {
        Category[] categorys = FileUtil.load(FILE_NAME, Category[].class);

        if (categorys != null && categorys.length > 0) {
            categoryList = new ArrayList<>(Arrays.asList(categorys));
        } else {
            if (categoryList.isEmpty()) {
                categoryList.add(new Category(1, null, "전자제품"));
                categoryList.add(new Category(2, null, "의류"));

                categoryList.add(new Category(11, 1, "컴퓨터/노트북"));
                categoryList.add(new Category(12, 1, "스마트폰"));
                categoryList.add(new Category(21, 2, "남성의류"));

                update();
            }
        }
    }

    public void save(Category category) {
        categoryList.add(category);
        FileUtil.save(FILE_NAME, categoryList);
    }

    public void update() {
        FileUtil.save(FILE_NAME, categoryList);
    }

    public void delete(int categoryId) {
        Category categoryToRemove = findCategoryId(categoryId);

        if (categoryToRemove != null) {
            categoryList.remove(categoryToRemove);
            update();
        }
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
