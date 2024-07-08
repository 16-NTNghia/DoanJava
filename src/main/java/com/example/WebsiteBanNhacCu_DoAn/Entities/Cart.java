package com.example.WebsiteBanNhacCu_DoAn.Entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Cart {
    private Product product;
    private int quantity;
}

