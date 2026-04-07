package kr.co.javaex.sec23.service;

import kr.co.javaex.sec23.domain.Order;
import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository = new OrderRepository();

    public void createOrder(Order order, User loginUser) throws IllegalArgumentException {
        if (loginUser == null)
            throw new IllegalArgumentException("로그인이 필요한 서비스입니다.");
        if (order.getProductQuantity() <= 0)
            throw new IllegalArgumentException("주문 수량은 1개 이상이어야 합니다.");

        orderRepository.save(order);
    }
}