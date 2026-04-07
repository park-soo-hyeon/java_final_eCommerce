package kr.co.javaex.sec23.controller;

import kr.co.javaex.sec23.domain.Category;
import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.service.CategoryService;

import java.util.Scanner;

public class CategoryController {
    private final CategoryService categoryService = new CategoryService();
    private final Scanner scanner = new Scanner(System.in);

    public void categoryMenu(User loginUser) {
        if (loginUser == null || !loginUser.getUserCate().equals("관리자")) {
            System.out.println("접근 권한이 없습니다. 관리자만 이용 가능합니다.");
            return;
        }

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n===== [ 카테고리 관리 메뉴 ] =====");
            System.out.println("1. 카테고리 생성");
            System.out.println("2. 카테고리 수정");
            System.out.println("3. 카테고리 삭제");
            System.out.println("0. 뒤로 가기 (메인 메뉴로)");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createCategory(loginUser);
                    break;
                case "2":
                    updateCategory(loginUser);
                    break;
                case "3":
                    deleteCategory(loginUser);
                    break;
                case "0":
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 0~3 사이의 숫자를 입력해 주세요.");
            }
        }
    }

    private void createCategory(User loginUser) {
        System.out.println("\n=== 카테고리 생성 ===");
        try {
            System.out.print("카테고리 ID: ");
            int categoryId = Integer.parseInt(scanner.nextLine());

            System.out.print("상위 카테고리 ID (대분류일 경우 엔터): ");
            String parentInput = scanner.nextLine();
            Integer parentId = parentInput.isEmpty() ? null : Integer.parseInt(parentInput);

            System.out.print("카테고리명: ");
            String categoryName = scanner.nextLine();

            Category newCategory = new Category(categoryId, parentId, categoryName);

            try {
                categoryService.createCategory(newCategory, loginUser);
                System.out.println("카테고리 생성이 완료되었습니다!");
            } catch (IllegalArgumentException e) {
                System.out.println("카테고리 생성 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자(ID, 순번)를 정확히 입력해야 합니다.");
        }
    }

    private void updateCategory(User loginUser) {
        System.out.println("\n=== 카테고리 수정 ===");
        try {
            System.out.print("수정할 카테고리 ID: ");
            int categoryId = Integer.parseInt(scanner.nextLine());

            Category oldCategory = categoryService.getCategory(categoryId);
            if (oldCategory == null) {
                System.out.println("해당 카테고리가 존재하지 않습니다.");
                return;
            }

            System.out.print("상위 카테고리 ID: ");
            String parentInput = scanner.nextLine();
            Integer parentId;

            if (parentInput.isEmpty()) {
                parentId = oldCategory.getCategoryParentId();
            } else if (parentInput.equalsIgnoreCase("null")) {
                parentId = null;
            } else {
                parentId = Integer.parseInt(parentInput);
            }

            System.out.print("카테고리명: ");
            String categoryName = scanner.nextLine();

            if (categoryName.isEmpty())
                categoryName = oldCategory.getCategoryName();

            Category updateCategory = new Category(categoryId, parentId, categoryName);

            try {
                categoryService.updateCategory(categoryId, updateCategory, loginUser);
                System.out.println("카테고리 수정이 완료되었습니다!");
            } catch (IllegalArgumentException e) {
                System.out.println("수정 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자를 입력해야 하는 항목입니다.");
        }
    }

    private void deleteCategory(User loginUser) {
        System.out.println("\n=== 카테고리 삭제 ===");
        try {
            System.out.print("삭제할 카테고리 ID: ");
            int categoryId = Integer.parseInt(scanner.nextLine());

            try {
                categoryService.deleteCategory(categoryId, loginUser);
                System.out.println("카테고리가 삭제되었습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println("삭제 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 카테고리 ID는 숫자로 입력해야 합니다.");
        }
    }
}