package com.project1.Summative1jojoyinara.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD
import com.project1.Summative1jojoyinara.model.Invoice;
import com.project1.Summative1jojoyinara.repository.InvoiceRepository;
import com.project1.Summative1jojoyinara.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
=======
import com.project1.Summative1jojoyinara.model.Game;
import com.project1.Summative1jojoyinara.model.Invoice;
import com.project1.Summative1jojoyinara.repository.InvoiceRepository;
import org.junit.Before;
>>>>>>> 51eff225dea4aa48787edf2f3532aec94be95273
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.Optional;

=======

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.contains;
>>>>>>> 51eff225dea4aa48787edf2f3532aec94be95273
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
<<<<<<< HEAD
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
=======
>>>>>>> 51eff225dea4aa48787edf2f3532aec94be95273

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
<<<<<<< HEAD

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    @MockBean
    private InvoiceRepository invoiceRepository;

    private ObjectMapper mapper = new ObjectMapper();

    private Invoice invoice;
    private List<Invoice> invoiceList;
    private String outputJson;

    @Before
    public void setUp() throws Exception {
        invoice = new Invoice(45,"Jeff", "123 Main Street", "Tampa", "FL", "12345", "t_shirt", 18, 10.00, 5, 50.00, 3.00, 1.98, 54.98);
        invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
    }

    @Test
    public void shouldReturnAllInvoices() throws Exception {
        doReturn(invoiceList).when(invoiceRepository).findAll();
        outputJson = mapper.writeValueAsString(invoiceList);

        mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnOneInvoiceById() throws Exception  {
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(45);
        outputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(get("/invoice/45"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnAllInvoicesByCustomerName() throws Exception  {
        doReturn(invoiceList).when(invoiceRepository).findAllInvoiceByCustomerName("Jeff");
        outputJson = mapper.writeValueAsString(invoiceList);

        mockMvc.perform(get("/invoice/customerName/Jeff"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
=======
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InvoiceRepository invoiceRepository;
    private ObjectMapper mapper = new ObjectMapper();
    private Invoice invoice1;
    private Invoice invoice2;
    private Invoice invoice3;
    private Invoice invoice4;
    private Invoice invoice5;
    private String invoice1Json;
    private String invoice2Json;
    private List<Invoice> allInvoices = new ArrayList<>();
    private List<Invoice> allInvoices2 = new ArrayList<>();
    private String allInvoicesJson;
    private String allInvoices2Json;
    @Before
    public void setup() throws Exception {
        invoice1 = new Invoice(1,"Dan","s1", "c1", "s1","123", "game",1, 12.99, 12, 155.88, 9.35, 16.98,182.21 );
        invoice1Json = mapper.writeValueAsString(invoice1);

        invoice2 = new Invoice(2,"Nara","s2", "c2", "s2","123", "game",1, 12.99, 12, 155.88, 9.35, 16.98,182.21 );
        invoice2Json = mapper.writeValueAsString(invoice2);
        invoice3 = new Invoice(3,"Yi","s3", "c3", "s3","123", "game",1, 12.99, 12, 155.88, 9.35, 16.98,182.21 );
        invoice4 = new Invoice(4,"Jojo","s8", "c8", "s8","123", "game",1, 12.99, 12, 155.88, 9.35, 16.98,182.21 );
        invoice5 = new Invoice(5,"Dan","s8", "c8", "s8","123", "game",1, 12.99, 12, 155.88, 9.35, 16.98,182.21 );


        allInvoices.add(invoice1);
        allInvoices.add(invoice2);
        allInvoices.add(invoice3);
        allInvoices.add(invoice4);
        allInvoices.add(invoice5);

        allInvoices2.add(invoice1);
        allInvoices2.add(invoice5);
        allInvoicesJson = mapper.writeValueAsString(allInvoices);
        allInvoices2Json = mapper.writeValueAsString(allInvoices2);
    }


>>>>>>> 51eff225dea4aa48787edf2f3532aec94be95273
}