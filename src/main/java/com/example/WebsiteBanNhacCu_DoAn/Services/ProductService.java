package com.example.WebsiteBanNhacCu_DoAn.Services;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Product;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.ICategoryRepository;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.IProductRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Product> getProducts() {
        return productRepository.findByIsDeleteFalse();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findByIdAndIsDeleteFalse(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void updateProducts(Product products) {
        productRepository.save(products);
    }

    public Product updateProduct(@NotNull Product product){
        Product existingProduct = productRepository.findByIdAndIsDeleteFalse(product.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " + product.getId() + " does not exist."));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setBestSeller(product.isBestSeller());
        existingProduct.setStockQuantity(product.getStockQuantity());
        existingProduct.setCategory(product.getCategory());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new IllegalStateException("Product with ID " + id + " does not exist."));
        existingProduct.setDelete(true); // Update the isDelete flag
        productRepository.save(existingProduct); // Save the updated product
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryIdAndIsDeleteFalse(categoryId);
    }
}

