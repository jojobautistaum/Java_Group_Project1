package com.project1.Summative1jojoyinara.service;

import com.project1.Summative1jojoyinara.model.*;
import com.project1.Summative1jojoyinara.repository.*;
import com.project1.Summative1jojoyinara.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ServiceLayer {
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TshirtRepository tshirtRepository;
    private InvoiceRepository invoiceRepository;
    private SalesTaxRateRepository salesTaxRateRepository;
    private ProcessingFeeRepository processingFeeRepository;

    @Autowired
    public ServiceLayer(GameRepository gameRepository, ConsoleRepository consoleRepository, TshirtRepository tshirtRepository, InvoiceRepository invoiceRepository, SalesTaxRateRepository salesTaxRateRepository, ProcessingFeeRepository processingFeeRepository) {
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tshirtRepository = tshirtRepository;
        this.invoiceRepository = invoiceRepository;
        this.salesTaxRateRepository = salesTaxRateRepository;
        this.processingFeeRepository = processingFeeRepository;
    }

    @Transactional
    public InvoiceViewModel saveInvoices(InvoiceViewModel invoiceViewModel){
        Invoice a = new Invoice();
        a.setCustomerName(invoiceViewModel.getCustomerName());
        a.setStreet(invoiceViewModel.getStreet());
        a.setCity(invoiceViewModel.getCity());
        a.setState(invoiceViewModel.getState());
        a.setZipcode(invoiceViewModel.getZipcode());
        a.setItemType(invoiceViewModel.getItemType());
        a.setItemId(invoiceViewModel.getItemId());

        if(a.getItemType().equals("game")) {
            Optional<Game> game = gameRepository.findById(a.getItemId());
            if(game.isPresent()) {
                if (game.get().getQuantity() >= invoiceViewModel.getQuantity()){
                    a.setQuantity(invoiceViewModel.getQuantity());
                } else{
                    a.setQuantity(game.get().getQuantity());
                }
                a.setUnitPrice(game.get().getPrice());
            }

        } else if(a.getItemType().equals("console")) {
            Optional<Console> console = consoleRepository.findById(a.getItemId());
            if(console.isPresent()) {
                if (console.get().getQuantity() >= invoiceViewModel.getQuantity()){
                    a.setQuantity(invoiceViewModel.getQuantity());
                } else{
                    a.setQuantity(console.get().getQuantity());
                }
                a.setUnitPrice(console.get().getPrice());
            }
        } else if(a.getItemType().equals("t-shirt")) {
            Optional<Tshirt> tshirt = tshirtRepository.findById(a.getItemId());
            if(tshirt.isPresent()) {
                if (tshirt.get().getQuantity() >= invoiceViewModel.getQuantity()){
                    a.setQuantity(invoiceViewModel.getQuantity());
                } else{
                    a.setQuantity(tshirt.get().getQuantity());
                }
                a.setUnitPrice(tshirt.get().getPrice());
            }
        } else {
            System.out.println("Product type does not exist");
            System.out.println(a);
        }

        a.setSubtotal(a.getUnitPrice() * a.getQuantity());
        SalesTaxRate tax = salesTaxRateRepository.findByState(a.getState());
        a.setTax(tax.getRate() * a.getSubtotal());
        ProcessingFee fee = processingFeeRepository.findByProductType(a.getItemType());
        if (a.getQuantity() > 10) {
            Double additionalFee = 15.49;
            a.setProcessingFee(fee.getFee() + additionalFee);
        } else {
            a.setProcessingFee(fee.getFee());
        }
        a.setTotal(a.getSubtotal() + a.getTax() + a.getProcessingFee());

        a = invoiceRepository.save(a);

        return buildInvoiceViewModel(a);
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        InvoiceViewModel returnVal = new InvoiceViewModel();
        returnVal.setId(invoice.getInvoiceId());
        returnVal.setCustomerName(invoice.getCustomerName());
        returnVal.setStreet(invoice.getStreet());
        returnVal.setCity(invoice.getCity());
        returnVal.setState(invoice.getState());
        returnVal.setZipcode(invoice.getZipcode());
        returnVal.setItemType(invoice.getItemType());
        returnVal.setItemId(invoice.getItemId());

        Integer itemId = invoice.getItemId();
        if (invoice.getItemType().equals("game")) {
            Optional<Game> game = gameRepository.findById(itemId);
            if (game.isPresent()) {
                returnVal.setGame(game.get());
            }
        } else if (invoice.getItemType().equals("console")) {
            Optional<Console> console = consoleRepository.findById(itemId);
            if (console.isPresent()) {
                returnVal.setConsole(console.get());
            }
        } else if (invoice.getItemType().equals("t-shirt")) {
            Optional<Tshirt> tshirt = tshirtRepository.findById(itemId);
            if (tshirt.isPresent()) {
                returnVal.setTshirt(tshirt.get());
            }
        }
        returnVal.setUnitPrice(invoice.getUnitPrice());
        returnVal.setQuantity(invoice.getQuantity());
        returnVal.setSubtotal(invoice.getSubtotal());
        returnVal.setSalesTax(invoice.getTax());
        returnVal.setProcessingFee(invoice.getProcessingFee());
        returnVal.setTotal(invoice.getTotal());

        return returnVal;
    }
}
