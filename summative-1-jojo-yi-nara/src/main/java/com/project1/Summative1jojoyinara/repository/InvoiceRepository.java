package com.project1.Summative1jojoyinara.repository;

import com.project1.Summative1jojoyinara.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findAllInvoiceByCustomerName(String customerName);
}
