package com.example.WebsiteBanNhacCu_DoAn.Entities;

import com.example.WebsiteBanNhacCu_DoAn.Validators.Annotations.ValidCategoryId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import java.util.Objects;
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name", length = 50, nullable = false)
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    @NotBlank(message = "Name must not be blank")
    private String name;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "best_seller", nullable = false)
    private boolean bestSeller;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Column(name = "is_delete", nullable = false)
    private boolean isDelete;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ValidCategoryId
    @ToString.Exclude
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ItemInvoice> itemInvoices = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) !=
                Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(),
                product.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
