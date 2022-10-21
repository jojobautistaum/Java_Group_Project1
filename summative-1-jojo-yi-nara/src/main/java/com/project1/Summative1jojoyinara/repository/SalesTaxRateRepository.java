package com.project1.Summative1jojoyinara.repository;

import com.project1.Summative1jojoyinara.model.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesTaxRateRepository extends JpaRepository<SalesTaxRate, String > {
    SalesTaxRate findByState(String state);
}
