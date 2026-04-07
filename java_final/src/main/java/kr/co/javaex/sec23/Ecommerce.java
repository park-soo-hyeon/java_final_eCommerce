package kr.co.javaex.sec23;

import kr.co.javaex.sec23.controller.*;
import kr.co.javaex.sec23.domain.User;

import java.util.Scanner;

public class Ecommerce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserController userController = new UserController();
        CategoryController categoryController = new CategoryController();
        ProductController productController = new ProductController();
        CartController cartController = new CartController();
        OrderController orderController = new OrderController();

        boolean isRunning = true;

        System.out.println("===========================================");
        System.out.println("          Java e-Commerce System           ");
        System.out.println("===========================================");

        while (isRunning) {
            User loginUser = userController.getLoggedInUser();
            String loginStatus = (loginUser == null) ? "로그아웃 상태" : "[" + loginUser.getName() + "님 로그인 중]";

            System.out.println("\n===== [ 메인 메뉴 ] =====");
            System.out.println("현재 상태: " + loginStatus);
            System.out.println("1. 회원 관리 (가입/로그인/수정)");
            System.out.println("2. 장바구니 메뉴");
            System.out.println("3. 주문 메뉴");
            System.out.println("4. 카테고리 관리 (관리자 전용)");
            System.out.println("5. 상품 관리 (관리자 전용)");
            System.out.println("0. 프로그램 종료");
            System.out.print("이동할 메뉴 번호를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userController.userMenu();
                    break;
                case "2":
                    cartController.cartMenu(loginUser);
                    break;
                case "3":
                    orderController.orderMenu(loginUser);
                    break;
                case "4":
                    categoryController.categoryMenu(loginUser);
                    break;
                case "5":
                    productController.productMenu(loginUser);
                    break;
                case "0":
                    System.out.println("e-Commerce 프로그램을 종료합니다. 감사합니다!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 0~5 사이의 숫자를 입력해 주세요.");
            }
        }
        scanner.close();
    }
}