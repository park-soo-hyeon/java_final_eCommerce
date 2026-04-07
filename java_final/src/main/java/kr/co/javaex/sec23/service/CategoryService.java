package kr.co.javaex.sec23.service;

import kr.co.javaex.sec23.domain.Category;
import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.repository.CategoryRepository;

public class CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepository();

    public void createCategory(Category category, User loginUser) throws IllegalArgumentException {
        if (!loginUser.getUserStatus().equals("관리자"))
            throw new IllegalArgumentException("관리자 권한이 없습니다.");

        categoryRepository.save(category);
    }

    public Category getCategory(int categoryId) {
        return categoryRepository.findCategoryId(categoryId);
    }

    public void updateCategory(int categoryId, Category updatedCategory, User loginUser) throws IllegalArgumentException {
        Category category = categoryRepository.findCategoryId(categoryId);

        if (!loginUser.getUserStatus().equals("관리자"))
            throw new IllegalArgumentException("관리자 권한이 없습니다.");
        if (category == null)
            throw new IllegalArgumentException("존재하지 않는 카테고리입니다.");

        category.setCategoryParentId(updatedCategory.getCategoryParentId());
        category.setCategoryName(updatedCategory.getCategoryName());

        categoryRepository.update();
    }

    public void deleteCategory(int categoryId, User loginUser) throws IllegalArgumentException {
        Category category = categoryRepository.findCategoryId(categoryId);

        if (!loginUser.getUserStatus().equals("관리자"))
            throw new IllegalArgumentException("관리자 권한이 없습니다.");
        if (category == null)
            throw new IllegalArgumentException("삭제할 카테고리가 없습니다.");

        categoryRepository.delete(categoryId);
    }
}