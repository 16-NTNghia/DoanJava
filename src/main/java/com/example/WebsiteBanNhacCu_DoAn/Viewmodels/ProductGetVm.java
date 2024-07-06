package com.example.WebsiteBanNhacCu_DoAn.Viewmodels;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder
public record ProductGetVm(Long id, String name, String description, int
        price, String category) {
    public static ProductGetVm from(@NotNull Product product) {
        return ProductGetVm.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .build();
    }
}

