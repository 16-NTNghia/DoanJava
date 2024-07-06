package com.example.WebsiteBanNhacCu_DoAn.Validators;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Category;
import com.example.WebsiteBanNhacCu_DoAn.Validators.Annotations.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class ValidCategoryIdValidator implements
        ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category,
                           ConstraintValidatorContext context) {
        return category != null && category.getId() != null;
    }
}

