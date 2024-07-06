package com.example.WebsiteBanNhacCu_DoAn.Viewmodels;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder
public record CategoryGetVm(Long id, String name) {
    public static CategoryGetVm from(@NotNull Category category) {
        return CategoryGetVm.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
