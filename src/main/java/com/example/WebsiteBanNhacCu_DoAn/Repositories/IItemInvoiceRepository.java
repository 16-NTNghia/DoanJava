package com.example.WebsiteBanNhacCu_DoAn.Repositories;
import com.example.WebsiteBanNhacCu_DoAn.Entities.ItemInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IItemInvoiceRepository extends
        JpaRepository<ItemInvoice, Long>{
}