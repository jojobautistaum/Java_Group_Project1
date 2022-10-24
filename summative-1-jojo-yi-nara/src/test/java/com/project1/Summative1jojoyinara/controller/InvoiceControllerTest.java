package com.project1.Summative1jojoyinara.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.project1.Summative1jojoyinara.model.Invoice;
import com.project1.Summative1jojoyinara.model.Tshirt;
import com.project1.Summative1jojoyinara.repository.InvoiceRepository;
import com.project1.Summative1jojoyinara.service.ServiceLayer;
import com.project1.Summative1jojoyinara.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
    private String inputJson;
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

    @Test
    public void shouldReturnNewInvoiceAfterSaveInvoice() throws Exception {
        Tshirt tshirt = new Tshirt(18, "XS", "white", "Plain", 10.00, 400);
        Tshirt quantityChangedtTshirt = new Tshirt(18, "XS", "white", "Plain", 10.00, 395);

        InvoiceViewModel inputInvoiceViewModel = new InvoiceViewModel();
        inputInvoiceViewModel.setCustomerName("Jeff");
        inputInvoiceViewModel.setStreet("123 Main Street");
        inputInvoiceViewModel.setCity("Tampa");
        inputInvoiceViewModel.setState("FL");
        inputInvoiceViewModel.setZipcode("12345");
        inputInvoiceViewModel.setItemType("t_shirt");
        inputInvoiceViewModel.setItemId(18);
        inputInvoiceViewModel.setItemDetail(tshirt);
        inputInvoiceViewModel.setQuantity(5);
        inputInvoiceViewModel.setUnitPrice(0.00);
        inputInvoiceViewModel.setSubtotal(0.00);
        inputInvoiceViewModel.setProcessingFee(0.00);
        inputInvoiceViewModel.setSalesTax(0.00);
        inputInvoiceViewModel.setTotal(0.00);

        InvoiceViewModel expectedOutput = new InvoiceViewModel();
        expectedOutput.setId(2);
        expectedOutput.setCustomerName("Jeff");
        expectedOutput.setStreet("123 Main Street");
        expectedOutput.setCity("Tampa");
        expectedOutput.setState("FL");
        expectedOutput.setZipcode("12345");
        expectedOutput.setItemType("t_shirt");
        expectedOutput.setItemId(18);
        expectedOutput.setItemDetail(quantityChangedtTshirt);
        expectedOutput.setUnitPrice(10.00);
        expectedOutput.setQuantity(5);
        expectedOutput.setSubtotal(50.00);
        expectedOutput.setProcessingFee(1.98);
        expectedOutput.setSalesTax(3.00);
        expectedOutput.setTotal(54.98);
        
        doReturn(expectedOutput).when(serviceLayer).saveInvoices(inputInvoiceViewModel);

        inputJson = mapper.writeValueAsString(inputInvoiceViewModel);
        outputJson = mapper.writeValueAsString(expectedOutput);

        mockMvc.perform(post("/invoice")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

}