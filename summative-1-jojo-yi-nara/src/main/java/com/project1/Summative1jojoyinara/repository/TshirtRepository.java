package com.project1.Summative1jojoyinara.repository;

import com.project1.Summative1jojoyinara.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TshirtRepository extends JpaRepository<Tshirt, Integer> {
    List<Tshirt> findTShirtByColor(String color);
    List<Tshirt> findTShirtBySize(String size);
}
