package com.project1.Summative1jojoyinara.service;

import com.project1.Summative1jojoyinara.exception.ResponseStatusException;
import com.project1.Summative1jojoyinara.model.*;
import com.project1.Summative1jojoyinara.repository.*;
import com.project1.Summative1jojoyinara.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        Optional<SalesTaxRate> stateCode = Optional.ofNullable(salesTaxRateRepository.findByState(invoiceViewModel.getState()));
        if (stateCode.isPresent()) {
            a.setState(invoiceViewModel.getState());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown '" + invoiceViewModel.getState() + "' state code.");
        }

        a.setZipcode(invoiceViewModel.getZipcode());
        a.setItemType(invoiceViewModel.getItemType());
        a.setItemId(invoiceViewModel.getItemId());

        if(a.getItemType().equalsIgnoreCase("game")) {
            // set it to the text as it appears in the DB
            a.setItemType("game");
            Optional<Game> game = gameRepository.findById(a.getItemId());
            if(game.isPresent()) {
                if (game.get().getQuantity() >= invoiceViewModel.getQuantity()){
                    a.setQuantity(invoiceViewModel.getQuantity());
                } else{
                    a.setQuantity(game.get().getQuantity());
                }
                game.get().setQuantity(game.get().getQuantity() - a.getQuantity());
                a.setUnitPrice(game.get().getPrice());
            }
        } else if(a.getItemType().equalsIgnoreCase("console")) {
            // set it to the text as it appears in the DB
            a.setItemType("console");
            Optional<Console> console = consoleRepository.findById(a.getItemId());
            if(console.isPresent()) {
                if (console.get().getQuantity() >= invoiceViewModel.getQuantity()){
                    a.setQuantity(invoiceViewModel.getQuantity());
                } else{
                    a.setQuantity(console.get().getQuantity());
                }
                console.get().setQuantity(console.get().getQuantity() - a.getQuantity());
                a.setUnitPrice(console.get().getPrice());
            }
        } else if(a.getItemType().equalsIgnoreCase("t-shirt") || a.getItemType().equalsIgnoreCase("t_shirt")
                || a.getItemType().equalsIgnoreCase("tshirt") || a.getItemType().equalsIgnoreCase("shirt")) {
            // set it to the text as it appears in the DB
            a.setItemType("t_shirt");
            Optional<Tshirt> tshirt = tshirtRepository.findById(a.getItemId());
            if(tshirt.isPresent()) {
                if (tshirt.get().getQuantity() >= invoiceViewModel.getQuantity()){
                    a.setQuantity(invoiceViewModel.getQuantity());
                } else{
                    a.setQuantity(tshirt.get().getQuantity());
                }
                tshirt.get().setQuantity(tshirt.get().getQuantity() - a.getQuantity());
                a.setUnitPrice(tshirt.get().getPrice());
            }
        } else {
<<<<<<< HEAD
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid itemType: '" + a.getItemType() + "'. Please use one of the following values for itemType: 'game', 'console' or 't-shirt'");
        }
        // Calculating Subtotal, Tax, ProcessingFee and Total
        a.setSubtotal(a.getUnitPrice() * a.getQuantity());
        Optional<SalesTaxRate> tax = Optional.ofNullable(salesTaxRateRepository.findByState(a.getState()));
        a.setTax(tax.get().getRate() * a.getSubtotal());
=======
            System.out.println("Product type does not exist");
        }
        a.setSubtotal(a.getUnitPrice() * a.getQuantity());
        SalesTaxRate tax = salesTaxRateRepository.findByState(a.getState());
        a.setTax(tax.getRate() * a.getSubtotal());
>>>>>>> 651a157ff7551be4c914cc19335e898c570d6307
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
                returnVal.setItemDetail(game.get());
            }

        } else if (invoice.getItemType().equals("console")) {
            Optional<Console> console = consoleRepository.findById(itemId);
            if (console.isPresent()) {
                returnVal.setItemDetail(console.get());
                System.out.println(returnVal);
            }
        } else if (invoice.getItemType().equals("t_shirt")) {
            Optional<Tshirt> tshirt = tshirtRepository.findById(itemId);
            if (tshirt.isPresent()) {
                returnVal.setItemDetail(tshirt.get());
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
