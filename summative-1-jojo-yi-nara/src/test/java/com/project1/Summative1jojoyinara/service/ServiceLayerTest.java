package com.project1.Summative1jojoyinara.service;

import com.project1.Summative1jojoyinara.model.*;
import com.project1.Summative1jojoyinara.repository.*;
import com.project1.Summative1jojoyinara.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TshirtRepository tshirtRepository;
    private InvoiceRepository invoiceRepository;
    private SalesTaxRateRepository salesTaxRateRepository;
    private ProcessingFeeRepository processingFeeRepository;
    private ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        setUpGameRepositoryMock();
        setUpConsoleRepositoryMock();
        setUpTshirtRepositoryMock();
        setUpInvoiceRepositoryMock();
        setUpSalesTaxRepositoryMock();
        setUpProcessingFeeRepositoryMock();

        serviceLayer = new ServiceLayer(gameRepository, consoleRepository, tshirtRepository, invoiceRepository, salesTaxRateRepository, processingFeeRepository);
    }

    public void setUpGameRepositoryMock() {
        gameRepository = mock(GameRepository.class);
        Game game = new Game(4, "Zelda", "rated E", "Nintendo Switch", 50.00, "Nintendo", 10);
        Game game2 = new Game(null,"Zelda", "rated E", "Nintendo Switch", 50.00, "Nintendo", 10);
        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        doReturn(game).when(gameRepository).save(game2);
        doReturn(Optional.of(game)).when(gameRepository).findById(4);
        doReturn(gameList).when(gameRepository).findAll();
    }

    public void setUpConsoleRepositoryMock() {
        consoleRepository = mock(ConsoleRepository.class);
        Console console = new Console(10, "Xbox", "Sony", "3T", "Intel", 300.00, 50);
        Console console2 = new Console(null,"Xbox", "Sony", "3T", "Intel", 300.00, 50);
        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console);

        doReturn(console).when(consoleRepository).save(console2);
        doReturn(Optional.of(console)).when(consoleRepository).findById(10);
        doReturn(consoleList).when(consoleRepository).findAll();
    }

    public void setUpTshirtRepositoryMock() {
        tshirtRepository = mock(TshirtRepository.class);
        Tshirt tshirt = new Tshirt(18, "XS", "white", "Plain", 10.00, 400);
        Tshirt tshirt2 = new Tshirt(null, "XS", "white", "Plain", 10.00, 400);
        List<Tshirt> tshirtList = new ArrayList<>();
        tshirtList.add(tshirt);

        doReturn(tshirt).when(tshirtRepository).save(tshirt2);
        doReturn(Optional.of(tshirt)).when(tshirtRepository).findById(18);
        doReturn(tshirtList).when(tshirtRepository).findAll();
    }

    public void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);
        Invoice invoice = new Invoice(2,"Jeff", "123 Main Street", "Tampa", "FL", "12345", "t_shirt", 18, 10.00, 5, 50.00, 3.00, 1.98, 54.98);
        Invoice invoice2 = new Invoice(null,"Jeff", "123 Main Street", "Tampa", "FL", "12345", "t_shirt", 18, 10.00, 5, 50.00, 3.00, 1.98, 54.98);
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceRepository).save(invoice2);
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(2);
        doReturn(invoiceList).when(invoiceRepository).findAll();
    }

    public void setUpSalesTaxRepositoryMock() {
        salesTaxRateRepository = mock(SalesTaxRateRepository.class);
        SalesTaxRate salesTaxRate = new SalesTaxRate("FL", 0.06);

        List<SalesTaxRate> taxList = new ArrayList<>();
        taxList.add(salesTaxRate);

        doReturn(taxList).when(salesTaxRateRepository).findAll();
        doReturn(salesTaxRate).when(salesTaxRateRepository).findByState("FL");
    }

    public void setUpProcessingFeeRepositoryMock() {
        processingFeeRepository = mock(ProcessingFeeRepository.class);
        ProcessingFee processingFee = new ProcessingFee("t_shirt", 1.98);

        List<ProcessingFee> feeList = new ArrayList<>();
        feeList.add(processingFee);

        doReturn(feeList).when(processingFeeRepository).findAll();
        doReturn(processingFee).when(processingFeeRepository).findByProductType("t_shirt");
    }

    @Test
    public void shouldSaveInvoice() throws Exception {
        // Arrange
        Tshirt tshirt = new Tshirt(18, "XS", "white", "Plain", 10.00, 400);
        Tshirt quantityChangedtTshirt = new Tshirt(18, "XS", "white", "Plain", 10.00, 400);

        InvoiceViewModel inputInvoiceViewModel = new InvoiceViewModel();
        inputInvoiceViewModel.setCustomerName("Jeff");
        inputInvoiceViewModel.setStreet("123 Main Street");
        inputInvoiceViewModel.setCity("Tampa");
        inputInvoiceViewModel.setState("FL");
        inputInvoiceViewModel.setZipcode("12345");
        inputInvoiceViewModel.setItemType("t_shirt");
        inputInvoiceViewModel.setItemId(18);
        inputInvoiceViewModel.setGame(null);
        inputInvoiceViewModel.setConsole(null);
        inputInvoiceViewModel.setTshirt(tshirt);
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
        expectedOutput.setGame(null);
        expectedOutput.setConsole(null);
        expectedOutput.setTshirt(quantityChangedtTshirt);
        expectedOutput.setUnitPrice(10.00);
        expectedOutput.setQuantity(5);
        expectedOutput.setSubtotal(50.00);
        expectedOutput.setProcessingFee(1.98);
        expectedOutput.setSalesTax(3.00);
        expectedOutput.setTotal(54.98);

        // Act
        InvoiceViewModel actualInvoiceViewModel = serviceLayer.saveInvoices(inputInvoiceViewModel);

        // Assert
        assertEquals(expectedOutput, actualInvoiceViewModel);
    }
}