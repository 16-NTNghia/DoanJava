package com.example.WebsiteBanNhacCu_DoAn.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long productId;
    private String productName;
    private int price;
    private String description;
    private int quantity;
}
