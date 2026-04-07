package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.domain.Cart;
import kr.co.javaex.sec23.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartRepository {
    private static final String FILE_NAME = "java_final/src/main/java/resources/carts.json";
    private static List<Cart> cartList = new ArrayList<>();
    private static boolean isLoaded = false;

    public CartRepository() {
        if (!isLoaded) {
            loadData();
            isLoaded = true;
        }
    }

    private void loadData() {
        Cart[] carts = FileUtil.load(FILE_NAME, Cart[].class);

        if (carts != null && carts.length > 0) {
            cartList = new ArrayList<>(Arrays.asList(carts));
        } else {
            if (cartList.isEmpty()) {
                cartList.add(new Cart(1, "user1", 101, 1));
                update();
            }
        }
    }

    public void save(Cart cart) {
        cartList.add(cart);
        update();
    }

    public Cart findByCartId(int cartId) {
        for (Cart cart : cartList) {
            if (cart.getCartId() == cartId)
                return cart;
        }
        return null;
    }

    public List<Cart> findByUserId(String userId) {
        List<Cart> result = new ArrayList<>();
        for (Cart cart : cartList) {
            if (cart.getUserId().equals(userId))
                result.add(cart);
        }
        return result;
    }

    public void update() {
        FileUtil.save(FILE_NAME, cartList);
    }

    public void delete(int cartId) {
        Cart cartToRemove = findByCartId(cartId);
        if (cartToRemove != null) {
            cartList.remove(cartToRemove);
            update();
        }
    }

    public List<Cart> findAll() {
        return cartList;
    }
}