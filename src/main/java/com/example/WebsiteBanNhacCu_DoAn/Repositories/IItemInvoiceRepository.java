package com.example.WebsiteBanNhacCu_DoAn.Repositories;
import com.example.WebsiteBanNhacCu_DoAn.Entities.ItemInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemInvoiceRepository extends
        JpaRepository<ItemInvoice, Long>{
    @Query("SELECT i from ItemInvoice i WHERE i.invoice.id = ?1")
    List<ItemInvoice> findByInvoiceID(Long id);
}