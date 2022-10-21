package com.project1.Summative1jojoyinara.controller;

import com.project1.Summative1jojoyinara.model.Invoice;
import com.project1.Summative1jojoyinara.repository.InvoiceRepository;
import com.project1.Summative1jojoyinara.service.ServiceLayer;
import com.project1.Summative1jojoyinara.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private InvoiceRepository invoiceRepository;
    private ServiceLayer serviceLayer;

    @Autowired
    public InvoiceController(InvoiceRepository invoiceRepository, ServiceLayer serviceLayer) {
        this.invoiceRepository = invoiceRepository;
        this.serviceLayer = serviceLayer;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody @Valid InvoiceViewModel invoiceViewModel){
        return serviceLayer.saveInvoices(invoiceViewModel);
    }

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
            return null;
        }
    }

    @GetMapping("/customerName/{customerName}")
    public List<Invoice> getInvoicesByCustomerName(@PathVariable String customerName){
        return invoiceRepository.findAllInvoiceByCustomerName(customerName);
    }
}


