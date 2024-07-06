package com.example.WebsiteBanNhacCu_DoAn.Repositories;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice,
        Long>{
}