package com.example.WebsiteBanNhacCu_DoAn.Services;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Category;
import com.example.WebsiteBanNhacCu_DoAn.Entities.Product;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.ICategoryRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;

    public List<Category> getCategories() {
        return categoryRepository.findByIsDeleteFalse();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findByIdAndIsDeleteFalse(id);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(@NotNull Category category){
        Category existingCategory = categoryRepository.findByIdAndIsDeleteFalse(category.getId())
                .orElseThrow(() -> new IllegalStateException("Category with ID " + category.getId() + " does not exist."));
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findByIdAndIsDeleteFalse(id)
                .orElseThrow(() -> new IllegalStateException("Category with ID " + id + " does not exist."));
        existingCategory.setDelete(true);// Update the isDelete flag
        List<Product> products = productService.getProductsByCategoryId(id);
        // Đánh dấu isDelete của các product liên quan là true
        for (Product product : products) {
            product.setDelete(true);
            productService.updateProducts(product); // Cần gọi lại service để cập nhật product
        }
        categoryRepository.save(existingCategory); // Save the updated category
    }
}
