package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.domain.Product;
import kr.co.javaex.sec23.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    private static final String FILE_NAME = "products.json";
    private List<Product> productList = new ArrayList<>();

    public ProductRepository() {
        loadData();
    }

    private void loadData() {
        Product[] products = FileUtil.load(FILE_NAME, Product[].class);

        if(products != null)
            productList = new ArrayList<>(Arrays.asList(products));
    }

    public void save(Product product) {
        productList.add(product);
        FileUtil.save(FILE_NAME, productList);
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
