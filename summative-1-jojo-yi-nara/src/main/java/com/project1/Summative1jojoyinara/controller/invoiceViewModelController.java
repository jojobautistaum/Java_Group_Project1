package com.project1.Summative1jojoyinara.controller;

import com.project1.Summative1jojoyinara.service.ServiceLayer;
import com.project1.Summative1jojoyinara.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class invoiceViewModelController {

    @Autowired
    private ServiceLayer serviceLayer;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody @Valid InvoiceViewModel invoiceViewModel){
        return serviceLayer.saveInvoices(invoiceViewModel);
    }

}