//package com.project1.Summative1jojoyinara.service;
//
//import com.project1.Summative1jojoyinara.repository.ConsoleRepository;
//import com.project1.Summative1jojoyinara.repository.GameRepository;
//import com.project1.Summative1jojoyinara.repository.InvoiceRepository;
//import com.project1.Summative1jojoyinara.repository.TshirtRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ServiceLayer {
//    private GameRepository gameRepository;
//    private ConsoleRepository consoleRepository;
//    private TshirtRepository tshirtRepository;
//    private InvoiceRepository invoiceRepository;
//
//    @Autowired
//    public ServiceLayer(GameRepository gameRepository){
//        this.gameRepository = gameRepository
//    }
//    @Autowired
//    public ServiceLayer(ConsoleRepository consoleRepository){
//        this.consoleRepository = consoleRepository;
//    }
//    @Autowired
//    public ServiceLayer(TshirtRepository tshirtRepository){
//        this.tshirtRepository = tshirtRepository
//    }
//
//
//    public List<com.project1.Summative1jojoyinara.model.Invoice> findAllInvoices() {
//
//        List<com.project1.Summative1jojoyinara.model.Invoice> invoiceList = invoiceRepository.findAll();
//
//        return invoiceList;
//    }
//
//
//
//}
