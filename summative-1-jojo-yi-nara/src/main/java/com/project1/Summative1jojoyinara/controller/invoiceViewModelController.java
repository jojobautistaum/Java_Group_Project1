package com.project1.Summative1jojoyinara.controller;

import com.project1.Summative1jojoyinara.model.Invoice;
import com.project1.Summative1jojoyinara.repository.InvoiceRepository;
import com.project1.Summative1jojoyinara.service.ServiceLayer;
import com.project1.Summative1jojoyinara.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class invoiceViewModelController {

    @Autowired
    private ServiceLayer serviceLayer;

//    @GetMapping()
//    public List<InvoiceViewModel> getAllInvoices(){
//        return serviceLayer.getAllInvoices();
//    }

//    @GetMapping("/{id}")
//    public InvoiceViewModel getInvoiceById(@PathVariable Integer id){
//       return serviceLayer.findInvoices(id);
//    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody InvoiceViewModel invoiceViewModel){
        return serviceLayer.saveInvoices(invoiceViewModel);
    }

}
