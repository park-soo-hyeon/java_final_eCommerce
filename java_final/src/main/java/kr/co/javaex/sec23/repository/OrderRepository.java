package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.domain.Order;
import kr.co.javaex.sec23.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderRepository {
    private static final String FILE_NAME = "java_final/resource/orders.json";
    private static List<Order> orderList = new ArrayList<>();

    public OrderRepository() {
        loadData();
    }

    private void loadData() {
        Order[] orders = FileUtil.load(FILE_NAME, Order[].class);

        if(orders != null)
            orderList = new ArrayList<>(Arrays.asList(orders));

        if (orderList.isEmpty()) {
            orderList.add(new Order(1, "user1", 101, "게이밍 노트북", 1500000, 1));
            update();
        }
    }

    public void save(Order order) {
        orderList.add(order);
        FileUtil.save(FILE_NAME, orderList);
    }

    public void update() {
        FileUtil.save(FILE_NAME, orderList);
    }

    public Order findByOrderId(int orderId) {
        for(Order order : orderList) {
            if (order.getOrderId() == orderId)
                return order;
        }
        return null;
    }

    public List<Order> findByUserId(String userId) {
        List<Order> result = new ArrayList<>();

        for(Order order : orderList) {
            if (order.getUserId().equals(userId))
                result.add(order);
        }
        return result;
    }

    public List<Order> findAll() {
        return orderList;
    }
}
