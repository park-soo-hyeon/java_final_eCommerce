package kr.co.javaex.sec23.controller;

import kr.co.javaex.sec23.domain.Cart;
import kr.co.javaex.sec23.domain.Product;
import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.service.CartService;
import kr.co.javaex.sec23.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class CartController {
    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();
    private final Scanner scanner = new Scanner(System.in);

    public void cartMenu(User loginUser) {
        if (loginUser == null) {
            System.out.println("로그인 후 이용해 주세요.");
            return;
        }

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n===== [ 장바구니 메뉴 ] =====");
            System.out.println("1. 장바구니 담기");
            System.out.println("2. 장바구니 목록 조회");
            System.out.println("3. 수량 변경");
            System.out.println("4. 선택 상품 삭제");
            System.out.println("5. 장바구니 비우기");
            System.out.println("0. 뒤로 가기");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addCartItem(loginUser);
                    break;
                case "2":
                    getCartList(loginUser);
                    break;
                case "3":
                    updateCartQuantity(loginUser);
                    break;
                case "4":
                    deleteCartItem(loginUser);
                    break;
                case "5":
                    cartService.clearMyCart(loginUser);
                    System.out.println("장바구니를 모두 비웠습니다.");
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 0~5 사이의 숫자를 입력해 주세요.");
            }
        }
    }

    private void addCartItem(User loginUser) {
        System.out.println("\n=== 장바구니 담기 ===");
        try {
            System.out.print("장바구니 ID: ");
            int cartId = Integer.parseInt(scanner.nextLine());

            System.out.print("담을 상품 ID: ");
            int productId = Integer.parseInt(scanner.nextLine());

            if (productService.getProduct(productId) == null) {
                System.out.println("존재하지 않는 상품입니다.");
                return;
            }

            System.out.print("수량: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            Cart cart = new Cart(cartId, loginUser.getUserId(), productId, quantity);

            try {
                cartService.addCartItem(cart, loginUser);
                System.out.println("장바구니에 상품을 담았습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println("담기 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자(ID, 수량)를 정확히 입력하세요.");
        }
    }

    private void getCartList(User loginUser) {
        System.out.println("\n=== 장바구니 목록 ===");
        List<Cart> myCart = cartService.getMyCartList(loginUser);

        if (myCart.isEmpty()) {
            System.out.println("장바구니가 비어있습니다.");
        } else {
            int totalPrice = 0;

            for (Cart cart : myCart) {
                Product product = productService.getProduct(cart.getProductId());

                if (product != null) {
                    int itemTotal = product.getProductPrice() * cart.getProductQuantity();
                    totalPrice += itemTotal;

                    System.out.printf("[장바구니 ID: %d] %s | 최신 가격: %d원 | 수량: %d개 | 합계: %d원\n",
                            cart.getCartId(), product.getProductName(), product.getProductPrice(), cart.getProductQuantity(), itemTotal);
                } else {
                    System.out.printf("[장바구니 ID: %d] 현재 판매가 중단되었거나 삭제된 상품입니다.\n", cart.getCartId());
                }
            }
            System.out.println("------------------------------------------------");
            System.out.println("총 결제 예상 금액: " + totalPrice + "원");
        }
    }

    private void updateCartQuantity(User loginUser) {
        System.out.println("\n=== 수량 변경 ===");
        try {
            System.out.print("장바구니 ID: ");
            int cartId = Integer.parseInt(scanner.nextLine());

            Cart oldCart = cartService.getCart(cartId);
            if (oldCart == null || !oldCart.getUserId().equals(loginUser.getUserId())) {
                System.out.println("해당 장바구니 항목을 찾을 수 없습니다.");
                return;
            }

            System.out.print("새로운 수량: ");
            String inputQuantity = scanner.nextLine();
            int newQuantity = inputQuantity.isEmpty() ? oldCart.getProductQuantity() : Integer.parseInt(inputQuantity);

            try {
                cartService.updateCartQuantity(cartId, newQuantity, loginUser);
                System.out.println("수량이 변경되었습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println("변경 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자를 정확히 입력하세요.");
        }
    }

    private void deleteCartItem(User loginUser) {
        System.out.println("\n=== 선택 상품 삭제 ===");
        try {
            System.out.print("삭제할 장바구니 ID: ");
            int cartId = Integer.parseInt(scanner.nextLine());

            try {
                cartService.deleteCartItem(cartId, loginUser);
                System.out.println("항목이 삭제되었습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println("삭제 실패: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자를 입력하세요.");
        }
    }
}