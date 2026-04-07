package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.domain.Product;
import kr.co.javaex.sec23.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    private static final String FILE_NAME = "java_final/src/main/java/resources/products.json";
    private static List<Product> productList = new ArrayList<>();
    private static boolean isLoaded = false;

    public ProductRepository() {
        if (!isLoaded) {
            loadData();
            isLoaded = true;
        }
    }

    private void loadData() {
        Product[] products = FileUtil.load(FILE_NAME, Product[].class);

        if(products != null && products.length > 0) {
            productList = new ArrayList<>(Arrays.asList(products));
        } else {
            if (productList.isEmpty()) {
                productList.add(new Product(101, "게이밍 노트북", "고성능 i9 게이밍 노트북", 1500000, 10, "정상"));
                productList.add(new Product(102, "사무용 노트북", "가벼운 1kg 사무용 노트북", 900000, 20, "정상"));
                productList.add(new Product(103, "무선 마우스", "인체공학 무소음 마우스", 25000, 50, "정상"));
                productList.add(new Product(104, "기계식 키보드", "적축 무선 기계식 키보드", 85000, 30, "정상"));
                productList.add(new Product(105, "27인치 모니터", "4K UHD 고화질 모니터", 350000, 15, "정상"));

                productList.add(new Product(201, "최신 스마트폰", "512GB 5G 스마트폰", 1200000, 20, "정상"));
                productList.add(new Product(202, "스마트폰 케이스", "투명 젤리 방충격 케이스", 15000, 100, "정상"));
                productList.add(new Product(203, "고속 충전기", "65W C타입 고속 충전기", 22000, 80, "정상"));
                productList.add(new Product(204, "무선 이어폰", "노이즈 캔슬링 무선 이어폰", 180000, 40, "정상"));
                productList.add(new Product(205, "보조배터리", "10000mAh 대용량 배터리", 30000, 60, "정상"));

                productList.add(new Product(301, "오버핏 반팔티", "순면 100% 무지 반팔티", 19000, 200, "정상"));
                productList.add(new Product(302, "와이드 데님 팬츠", "편안한 핏의 청바지", 39000, 100, "정상"));
                productList.add(new Product(303, "경량 패딩", "가을/겨울용 초경량 패딩", 59000, 50, "정상"));
                productList.add(new Product(304, "스포츠 바람막이", "방수 기능성 후드 바람막이", 45000, 70, "정상"));
                productList.add(new Product(305, "캐주얼 셔츠", "봄 가을용 옥스포드 셔츠", 32000, 90, "정상"));

                update();
            }
        }
    }

    public void save(Product product) {
        productList.add(product);
        FileUtil.save(FILE_NAME, productList);
    }

    public void update() {
        FileUtil.save(FILE_NAME, productList);
    }

    public void delete(int productId) {
        Product deleteProduct = findByProductId(productId);

        if (deleteProduct != null) {
            productList.remove(deleteProduct);
            update();
        }
    }

    public Product findByProductId(int productId) {
        for (Product product : productList) {
            if (product.getProductId() == productId)
                return product;
        }
        return null;
    }

    public List<Product> findAll() {
        return productList;
    }
}
