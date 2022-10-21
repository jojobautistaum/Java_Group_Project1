package com.project1.Summative1jojoyinara.repository;

import com.project1.Summative1jojoyinara.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsoleRepository extends JpaRepository<Console, Integer> {
    List<Console> findConsoleByManufacturer(String manufacturer);
}
