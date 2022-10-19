package com.project1.Summative1jojoyinara.controller;

import com.project1.Summative1jojoyinara.model.ProcessingFee;
import com.project1.Summative1jojoyinara.repository.ProcessingFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class FeeController {

    @Autowired
    ProcessingFeeRepository repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessingFee> readAll() {
        return repo.findAll();
    }

    @GetMapping("/{product}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessingFee readOne(@PathVariable String product) {
        return repo.findByProductType(product);
    }
}
