package com.example.WebsiteBanNhacCu_DoAn.Repositories;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends
        PagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> {
    default List<Product> findAllProducts(Integer pageNo,
                                       Integer pageSize,
                                       String sortBy) {
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))
                .getContent();
    }

//    @Query("""
//             SELECT b FROM Product b
//             WHERE b.title LIKE %?1%
//             OR b.author LIKE %?1%
//             OR b.category.name LIKE %?1%
//             """)
//    List<Product> searchProduct(String keyword);

    @Query(""" 
             SELECT b FROM Product b
             WHERE b.category.name LIKE %?1%
             """)
    List<Product> searchProductByCategory(String keyword);
    List<Product> findByNameContainingIgnoreCase(String title);
}