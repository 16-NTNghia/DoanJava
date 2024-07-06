package com.example.WebsiteBanNhacCu_DoAn.Services;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Product;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE,
        rollbackFor = {Exception.class, Throwable.class})
public class ProductService {
    private final IProductRepository productRepository;
    public List<Product> getAllProducts(Integer pageNo,
                                        Integer pageSize,
                                        String sortBy) {
        return productRepository.findAllProducts(pageNo, pageSize, sortBy);
    }
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> productList(String tiile){
        return productRepository.findByNameContainingIgnoreCase(tiile);
    }
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Product product){
        Product editProduct =productRepository.findById(product.getId()).orElse(null);
        Objects.requireNonNull(editProduct).setName(product.getName());
        editProduct.setDescription(product.getDescription());
        editProduct.setPrice(product.getPrice());
        editProduct.setCategory(product.getCategory());
        productRepository.save(editProduct);
    }
//    public List<Product> searchProduct(String keyword) {
//        return productRepository.searchProduct(keyword);
//    }
    public List<Product> searchProductByCategory(String keyword) {
        return productRepository.searchProductByCategory(keyword);
    }
}

