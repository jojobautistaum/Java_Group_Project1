package com.project1.Summative1jojoyinara.repository;

import com.project1.Summative1jojoyinara.model.ProcessingFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingFeeRepository extends JpaRepository<ProcessingFee, String > {
    ProcessingFee findByProductType(String product);
}
