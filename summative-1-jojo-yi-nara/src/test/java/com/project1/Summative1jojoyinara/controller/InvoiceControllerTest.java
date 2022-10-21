package com.project1.Summative1jojoyinara.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.Summative1jojoyinara.model.Invoice;
import com.project1.Summative1jojoyinara.repository.InvoiceRepository;
import com.project1.Summative1jojoyinara.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

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
}