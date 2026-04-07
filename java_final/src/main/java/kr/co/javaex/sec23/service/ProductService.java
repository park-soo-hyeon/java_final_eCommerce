package kr.co.javaex.sec23.service;

import kr.co.javaex.sec23.domain.Product;
import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.repository.ProductRepository;


public class ProductService {
    private final ProductRepository productRepository = new ProductRepository();

    public void registerProduct(Product newProduct, User loginUser) throws IllegalArgumentException {
        if (!loginUser.getUserCate().equals("관리자"))
            throw new IllegalArgumentException("관리자 권한이 없습니다.");

        productRepository.save(newProduct);
    }

    public Product getProduct(int productId) {
        Product product = productRepository.findByProductId(productId);

        if (product == null)
            return null;

        return product;
    }

    public void updateProductInfo(int productId, Product updatedProduct, User loginUser) throws IllegalArgumentException {
        Product product = productRepository.findByProductId(productId);

        if (!loginUser.getUserCate().equals("관리자"))
            throw new IllegalArgumentException("관리자 권한이 없습니다.");
        if (product == null)
            throw new IllegalArgumentException("상품이 존재하지 않습니다.");
        if (updatedProduct.getProductPrice() < 0)
            throw new IllegalArgumentException("가격은 0원 이상이여야 합니다.");
        if (updatedProduct.getProductStock() < 0)
            throw new IllegalArgumentException("재고는 0개 이상이여야 합니다.");

        product.setProductName(updatedProduct.getProductName());
        product.setProductDesc(updatedProduct.getProductDesc());
        product.setProductPrice(updatedProduct.getProductPrice());
        product.setProductStock(updatedProduct.getProductStock());

        productRepository.update();
    }

    public void changeProductStatus(int productId, String newStatus, User loginUser) throws IllegalArgumentException {
        Product product = productRepository.findByProductId(productId);

        if (!loginUser.getUserCate().equals("관리자"))
            throw new IllegalArgumentException("관리자 권한이 없습니다.");
        if (product == null)
            throw new IllegalArgumentException("상품이 존재하지 않습니다.");
        if (!newStatus.equals("정상") && !newStatus.equals("판매중지"))
            throw new IllegalArgumentException("상태는 '정상' 또는 '판매중지' 중에 입력해야 합니다.");

        product.setProductStatus(newStatus);

        productRepository.update();
    }

    public void updateStock(int productId, int newQuantity, User loginUser) throws IllegalArgumentException {
        Product product = productRepository.findByProductId(productId);

        if (!loginUser.getUserCate().equals("관리자"))
            throw new IllegalArgumentException("관리자 권한이 없습니다.");
        if (product == null)
            throw new IllegalArgumentException("상품이 존재하지 않습니다.");
        if (newQuantity < 0)
            throw new IllegalArgumentException("재고는 0개 이상이여야 합니다.");

        product.setProductStock(newQuantity);
        
        if (newQuantity == 0)
            product.setProductStatus("판매중지");
        else if (product.getProductStatus().equals("판매중지"))
            product.setProductStatus("정상");

        productRepository.update();
    }

    public void deleteProduct(int productId, User loginUser) throws IllegalArgumentException {
        Product product = productRepository.findByProductId(productId);

        if (!loginUser.getUserCate().equals("관리자"))
            throw new IllegalArgumentException("관리자 권한이 없습니다.");
        if (product == null)
            throw new IllegalArgumentException("삭제할 상품이 없습니다.");

        productRepository.delete(productId);
    }
}