package com.example.WebsiteBanNhacCu_DoAn.Viewmodels;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder
public record ProductPostVm(String name, String description, int price,
                            Long categoryId) {
    public static ProductPostVm from(@NotNull Product product) {
        return new ProductPostVm(product.getName(), product.getDescription(),
                product.getPrice(), product.getCategory().getId());
    }
}