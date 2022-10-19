package com.project1.Summative1jojoyinara.viewmodel;

import com.project1.Summative1jojoyinara.model.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class InvoiceViewModel {

    private Integer id;

    private String customerName;

    private String street;

    private String city;

    private String state;

    private String zipcode;


    private Game game;

    private Console console;

    private Tshirt tshirt;

    private Integer quantity;

    private Double subtotal;

    private ProcessingFee processingFee;

    private SalesTaxRate salesTaxRate;

    private Double total;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Tshirt getTshirt() {
        return tshirt;
    }

    public void setTshirt(Tshirt tshirt) {
        this.tshirt = tshirt;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public ProcessingFee getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(ProcessingFee processingFee) {
        this.processingFee = processingFee;
    }

    public SalesTaxRate getSalesTaxRate() {
        return salesTaxRate;
    }

    public void setSalesTaxRate(SalesTaxRate salesTaxRate) {
        this.salesTaxRate = salesTaxRate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return Objects.equals(id, that.id) && Objects.equals(customerName, that.customerName) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zipcode, that.zipcode) && Objects.equals(game, that.game) && Objects.equals(console, that.console) && Objects.equals(tshirt, that.tshirt) && Objects.equals(quantity, that.quantity) && Objects.equals(subtotal, that.subtotal) && Objects.equals(processingFee, that.processingFee) && Objects.equals(salesTaxRate, that.salesTaxRate) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, street, city, state, zipcode, game, console, tshirt, quantity, subtotal, processingFee, salesTaxRate, total);
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", game=" + game +
                ", console=" + console +
                ", tshirt=" + tshirt +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", processingFee=" + processingFee +
                ", salesTaxRate=" + salesTaxRate +
                ", total=" + total +
                '}';
    }
}
