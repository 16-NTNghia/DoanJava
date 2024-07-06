package com.example.WebsiteBanNhacCu_DoAn.Services;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Category;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CategoryService{
    private final ICategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    public Category findById(Long id){
        return  categoryRepository.findById(id).orElse(null);
    }
    public void updateCategory(Category category) {
        Category existingCategory = categoryRepository
                .findById(category.getId())
                .orElse(null);
        Objects.requireNonNull(existingCategory)
                .setName(category.getName());
        categoryRepository.save(existingCategory);
    }
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
