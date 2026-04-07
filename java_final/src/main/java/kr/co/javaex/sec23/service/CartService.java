package kr.co.javaex.sec23.service;

import kr.co.javaex.sec23.domain.Cart;
import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.repository.CartRepository;

import java.util.List;

public class CartService {
    private final CartRepository cartRepository = new CartRepository();

    public void addCartItem(Cart cart, User loginUser) throws IllegalArgumentException {
        if (loginUser == null)
            throw new IllegalArgumentException("로그인이 필요한 서비스입니다.");
        if (cart.getProductQuantity() <= 0)
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");

        cartRepository.save(cart);
    }

    public List<Cart> getMyCartList(User loginUser) {
        if (loginUser == null) return new java.util.ArrayList<>();
        return cartRepository.findByUserId(loginUser.getUserId());
    }

    public Cart getCart(int cartId) {
        return cartRepository.findByCartId(cartId);
    }

    public void updateCartQuantity(int cartId, int newQuantity, User loginUser) throws IllegalArgumentException {
        Cart cart = cartRepository.findByCartId(cartId);

        if (loginUser == null)
            throw new IllegalArgumentException("로그인이 필요한 서비스입니다.");
        if (cart == null || !cart.getUserId().equals(loginUser.getUserId()))
            throw new IllegalArgumentException("본인의 장바구니 항목만 수정할 수 있습니다.");
        if (newQuantity <= 0)
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");

        cart.setProductQuantity(newQuantity);
        cartRepository.update();
    }

    public void deleteCartItem(int cartId, User loginUser) throws IllegalArgumentException {
        Cart cart = cartRepository.findByCartId(cartId);

        if (loginUser == null)
            throw new IllegalArgumentException("로그인이 필요한 서비스입니다.");
        if (cart == null || !cart.getUserId().equals(loginUser.getUserId()))
            throw new IllegalArgumentException("본인의 장바구니 항목만 삭제할 수 있습니다.");

        cartRepository.delete(cartId);
    }

    public void clearMyCart(User loginUser) {
        if (loginUser == null) return;
        List<Cart> myCart = getMyCartList(loginUser);
        for (Cart cart : myCart) {
            cartRepository.delete(cart.getCartId());
        }
    }
}