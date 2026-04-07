package kr.co.javaex.sec23.controller;

import kr.co.javaex.sec23.domain.Product;
import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.service.ProductService;

import java.util.Scanner;

public class ProductController {
    private final ProductService productService = new ProductService();
    private final Scanner scanner = new Scanner(System.in);

    public void productMenu(User loginUser) {
        if (loginUser == null || !loginUser.getUserCate().equals("관리자")) {
            System.out.println("접근 권한이 없습니다. 관리자만 이용 가능!");
            return;
        }

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n===== [ 상품 관리 메뉴 ] =====");
            System.out.println("1. 상품 등록");
            System.out.println("2. 상품 조회");
            System.out.println("3. 상품 수정");
            System.out.println("4. 상태 변경");
            System.out.println("5. 재고 수량 업데이트");
            System.out.println("6. 상품 삭제");
            System.out.println("0. 뒤로 가기 (메인 메뉴로)");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerProduct(loginUser);
                    break;
                case "2":
                    getProduct();
                    break;
                case "3":
                    updateProductInfo(loginUser);
                    break;
                case "4":
                    changeProductStatus(loginUser);
                    break;
                case "5":
                    updateStock(loginUser);
                    break;
                case "6":
                    deleteProduct(loginUser);
                    break;
                case "0":
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 0~6 사이의 숫자를 입력해 주세요.");
            }
        }
    }

    private void registerProduct(User loginUser) {
        System.out.println("\n=== 상품 등록 ===");
        try {
            System.out.print("상품 ID: ");
            int productId = Integer.parseInt(scanner.nextLine());

            System.out.print("상품명: ");
            String productName = scanner.nextLine();

            System.out.print("상세 설명: ");
            String productDesc = scanner.nextLine();

            System.out.print("가격: ");
            int productPrice = Integer.parseInt(scanner.nextLine());

            System.out.print("재고수량: ");
            int productStock = Integer.parseInt(scanner.nextLine());

            System.out.print("상태: ");
            String productStatus = scanner.nextLine();


            Product newProduct = new Product(productId, productName, productDesc, productPrice, productStock, productStatus);

            try {
                productService.registerProduct(newProduct, loginUser);
                System.out.println("상품 등록이 완료되었습니다!");
            } catch (IllegalArgumentException e) {
                System.out.println("상품 등록 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 상품 ID는 숫자로 입력해야 합니다.");
        }
    }

    private void getProduct() {
        System.out.println("\n=== 상품 조회 ===");
        System.out.print("상품 ID: ");

        try {
            int productId = Integer.parseInt(scanner.nextLine());

            Product selectList = productService.getProduct(productId);

            if (selectList == null) {
                System.out.println("해당 상품ID의 상품 리스트가 없습니다.");
                return;
            }

            System.out.println("조회 결과: " + selectList);
        } catch (NumberFormatException e) {
            System.out.println("오류: 상품 ID는 숫자로 입력해야 합니다.");
        }
    }

    private void updateProductInfo(User loginUser) {
        System.out.println("\n=== 상품 정보 수정 ===");
        try {
            System.out.print("상품 ID: ");
            int productId = Integer.parseInt(scanner.nextLine());

            Product oldProduct = productService.getProduct(productId);
            if (oldProduct == null) {
                System.out.println("해당 상품이 존재하지 않습니다.");
                return;
            }

            System.out.print("상품명: ");
            String productName = scanner.nextLine();
            if (productName.isEmpty())
                productName = oldProduct.getProductName();

            System.out.print("상세 설명: ");
            String productDesc = scanner.nextLine();
            if (productDesc.isEmpty())
                productDesc = oldProduct.getProductDesc();

            System.out.print("가격: ");
            String inputPrice = scanner.nextLine();
            int productPrice = inputPrice.isEmpty() ? oldProduct.getProductPrice() : Integer.parseInt(inputPrice);

            System.out.print("재고수량: ");
            String inputStock = scanner.nextLine();
            int productStock = inputStock.isEmpty() ? oldProduct.getProductStock() : Integer.parseInt(inputStock);

            Product updateProduct = new Product(productId, productName, productDesc, productPrice, productStock);

            try {
                productService.updateProductInfo(productId, updateProduct, loginUser);
            } catch (IllegalArgumentException e) {
                System.out.println("수정 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 상품 ID는 숫자로 입력해야 합니다.");
        }
    }

    private void changeProductStatus(User loginUser) {
        System.out.println("\n=== 상품 상태 변경 ===");
        try {
            System.out.print("상품 ID: ");
            int productId = Integer.parseInt(scanner.nextLine());

            System.out.print("상태: ");
            String productStatus = scanner.nextLine();

            try {
                productService.changeProductStatus(productId, productStatus, loginUser);
            } catch (IllegalArgumentException e) {
                System.out.println("수정 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 상품 ID는 숫자로 입력해야 합니다.");
        }
    }

    private void updateStock(User loginUser) {
        System.out.println("\n=== 상품 재고 관리 ===");
        try {
            System.out.print("상품 ID: ");
            int productId = Integer.parseInt(scanner.nextLine());

            System.out.print("재고수량: ");
            int productStock = Integer.parseInt(scanner.nextLine());

            try {
                productService.updateStock(productId, productStock, loginUser);
            } catch (IllegalArgumentException e) {
                System.out.println("수정 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 상품 ID는 숫자로 입력해야 합니다.");
        }
    }

    private void deleteProduct(User loginUser) {
        System.out.println("\n=== 상품 삭제 ===");
        try {
            System.out.print("상품 ID: ");
            int productId = Integer.parseInt(scanner.nextLine());

            try {
                productService.deleteProduct(productId, loginUser);
            } catch (IllegalArgumentException e) {
                System.out.println("삭제 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 상품 ID는 숫자로 입력해야 합니다.");
        }
    }
}