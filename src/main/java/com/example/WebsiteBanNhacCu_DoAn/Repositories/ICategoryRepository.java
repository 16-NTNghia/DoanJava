package com.example.WebsiteBanNhacCu_DoAn.Repositories;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends
        JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.isDelete = false")
    List<Category> findByIsDeleteFalse();

    @Query("SELECT c FROM Category c WHERE c.id = :id AND c.isDelete = false")
    Optional<Category> findByIdAndIsDeleteFalse(@Param("id") Long id);
}

