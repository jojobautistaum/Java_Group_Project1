package com.project1.Summative1jojoyinara.controller;

import com.project1.Summative1jojoyinara.exception.ResponseStatusException;
import com.project1.Summative1jojoyinara.model.Game;
import com.project1.Summative1jojoyinara.model.SalesTaxRate;
import com.project1.Summative1jojoyinara.repository.SalesTaxRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    SalesTaxRateRepository repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<SalesTaxRate> readAll() {
        return repo.findAll();
    }

    @GetMapping("/{state}")
    @ResponseStatus(HttpStatus.OK)
    public SalesTaxRate readOne(@PathVariable String state) {
        return repo.findByState(state);
    }
}
