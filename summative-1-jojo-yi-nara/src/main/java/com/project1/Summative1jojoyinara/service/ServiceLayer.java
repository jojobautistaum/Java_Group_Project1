package com.project1.Summative1jojoyinara.service;

import com.project1.Summative1jojoyinara.model.Game;
import com.project1.Summative1jojoyinara.model.Invoice;
import com.project1.Summative1jojoyinara.repository.ConsoleRepository;
import com.project1.Summative1jojoyinara.repository.GameRepository;
import com.project1.Summative1jojoyinara.repository.InvoiceRepository;
import com.project1.Summative1jojoyinara.repository.TshirtRepository;
import com.project1.Summative1jojoyinara.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServiceLayer {
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TshirtRepository tshirtRepository;
    private InvoiceRepository invoiceRepository;

    @Autowired
    public ServiceLayer(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }
    public ServiceLayer(ConsoleRepository consoleRepository){
        this.consoleRepository = consoleRepository;
    }
    public ServiceLayer(TshirtRepository tshirtRepository){
        this.tshirtRepository = tshirtRepository;
    }

//
//    public List<com.project1.Summative1jojoyinara.model.Invoice> findAllInvoices() {
//
//        List<com.project1.Summative1jojoyinara.model.Invoice> invoiceList = invoiceRepository.findAll();
//
//        return invoiceList;
//    }
//    public String getItemType(String itemType){
//
//    }
    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel){
        Invoice a = new Invoice();
        a.setCustomerName(invoiceViewModel.getCustomerName());
        a.setStreet(invoiceViewModel.getStreet());
        a.setCity(invoiceViewModel.getCity());
        a.setState(invoiceViewModel.getState());
        a.setZipcode(invoiceViewModel.getZipcode());

        if(invoiceViewModel.getGame().getGameId() != null ) {
            a.setItemType("game");
            a.setItemId(invoiceViewModel.getGame().getGameId());
            if (invoiceViewModel.getGame().getQuantity() >= invoiceViewModel.getQuantity()){
                a.setQuantity(invoiceViewModel.getQuantity());
            } else{
               a.setQuantity(invoiceViewModel.getGame().getQuantity());
            }
        } else if(invoiceViewModel.getConsole().getConsoleId() != null) {
            a.setItemType("console");
            a.setItemId(invoiceViewModel.getConsole().getConsoleId());
            if (invoiceViewModel.getConsole().getQuantity() >= invoiceViewModel.getQuantity()){
                a.setQuantity(invoiceViewModel.getQuantity());
            } else{
                a.setQuantity(invoiceViewModel.getConsole().getQuantity());
            }
        } else if(invoiceViewModel.getTshirt().gettShirtId() != null) {
            a.setItemType("t_shirt");
            a.setItemId(invoiceViewModel.getTshirt().gettShirtId());
            if (invoiceViewModel.getTshirt().getQuantity() >= invoiceViewModel.getQuantity()){
                a.setQuantity(invoiceViewModel.getQuantity());
            } else{
                a.setQuantity(invoiceViewModel.getTshirt().getQuantity());
            }

        } else {
            System.out.println("Product type does not exist");
        }
        a.setSubtotal(0.0);
        a.setTax(0.0);
        a.setProcessingFee(0.0);
        a.setTotal(0.0);


//        Game game = new Game;
//        game.setGameId(invoiceViewModel.getGame().getGameId());

            return invoiceViewModel;

    }


}
