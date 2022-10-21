package com.project1.Summative1jojoyinara.controller;

import com.project1.Summative1jojoyinara.exception.ResponseStatusException;
import com.project1.Summative1jojoyinara.model.Invoice;
import com.project1.Summative1jojoyinara.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;


    @GetMapping()
    public List<Invoice> getInvoices(){
        return invoiceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable Integer id){
        Optional<Invoice> returnVal = invoiceRepository.findById(id);
        if(returnVal.isPresent()){
            return returnVal.get();
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invoiceId '" + id + "' does not exist");
        }
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    @GetMapping("/customerName/{customerName}")
    public List<Invoice> getInvoicesByTitle(@PathVariable String customerName){
        return invoiceRepository.findAllInvoiceByCustomerName(customerName);
    }


}


