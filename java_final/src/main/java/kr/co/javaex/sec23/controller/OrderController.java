package kr.co.javaex.sec23.controller;

import kr.co.javaex.sec23.domain.Cart;
import kr.co.javaex.sec23.domain.Order;
import kr.co.javaex.sec23.domain.Product;
import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.service.CartService;
import kr.co.javaex.sec23.service.OrderService;
import kr.co.javaex.sec23.service.ProductService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private final OrderService orderService = new OrderService();
    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();
    private final Scanner scanner = new Scanner(System.in);

    public void orderMenu(User loginUser) {
        if (loginUser == null) {
            System.out.println("로그인 후 이용해 주세요.");
            return;
        }

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n===== [ 주문 메뉴 ] =====");
            System.out.println("1. 장바구니 선택 상품 주문");
            System.out.println("2. 장바구니 전체 상품 주문");
            System.out.println("0. 뒤로 가기 (메인 메뉴로)");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    directOrder(loginUser);
                    break;
                case "2":
                    orderFromCart(loginUser);
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 0~2 사이의 숫자를 입력해 주세요.");
            }
        }
    }

    private void directOrder(User loginUser) {
        System.out.println("\n장바구니 선택 상품 주문");
        try {
            System.out.print("주문 번호: ");
            int orderId = Integer.parseInt(scanner.nextLine());

            System.out.print("주문할 상품 ID: ");
            int productId = Integer.parseInt(scanner.nextLine());

            Product product = productService.getProduct(productId);
            if (product == null) {
                System.out.println("존재하지 않는 상품입니다. 주문할 수 없습니다.");
                return;
            }

            System.out.print("수량: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            Order order = new Order(orderId, loginUser.getUserId(), productId, product.getProductName(), product.getProductPrice(), quantity);

            try {
                orderService.createOrder(order, loginUser);
                System.out.println("주문이 완료되었습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println("주문 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자(ID, 수량)를 정확히 입력하세요.");
        }
    }

    private void orderFromCart(User loginUser) {
        System.out.println("\n=== 장바구니 전체 주문 ===");
        List<Cart> myCart = cartService.getMyCartList(loginUser);

        if (myCart.isEmpty()) {
            System.out.println("장바구니가 비어있어 주문할 수 없습니다.");
            return;
        }

        int successCount = 0;

        for (Cart cart : myCart) {
            try {
                Product product = productService.getProduct(cart.getProductId());
                if (product == null) {
                    System.out.println("상품 ID [" + cart.getProductId() + "]는 더 이상 존재하지 않아 주문이 취소됩니다.");
                    continue;
                }

                Order order = new Order(cart.getCartId(), loginUser.getUserId(), cart.getProductId(),
                        product.getProductName(), product.getProductPrice(), cart.getProductQuantity());
                orderService.createOrder(order, loginUser);
                successCount++;
            } catch (Exception e) {
                System.out.println("장바구니 항목 주문 실패 (상품 ID: " + cart.getProductId() + ") - " + e.getMessage());
            }
        }

        if (successCount > 0) {
            cartService.clearMyCart(loginUser);
            System.out.println(successCount + "건의 장바구니 상품 주문이 완료되었습니다.");
        }
    }
}