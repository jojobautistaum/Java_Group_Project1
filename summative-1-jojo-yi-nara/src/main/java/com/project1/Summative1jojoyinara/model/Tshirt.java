package com.project1.Summative1jojoyinara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="t_shirt")
public class Tshirt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="t_shirt_id")
    private Integer tShirtId;
    private String size;
    private String color;
    private String description;
    @Min(value = 0)
    private Double price;
    @Min(value = 0)
    private Integer quantity;

    public Tshirt() {
    }

    public Tshirt(Integer tShirtId, String size, String color, String description, Double price, Integer quantity) {
        this.tShirtId = tShirtId;
        this.size = size;
        this.color = color;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer gettShirtId() {
        return tShirtId;
    }

    public void settShirtId(Integer tShirtId) {
        this.tShirtId = tShirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tshirt tshirt = (Tshirt) o;
        return Objects.equals(tShirtId, tshirt.tShirtId) && Objects.equals(size, tshirt.size) && Objects.equals(color, tshirt.color) && Objects.equals(description, tshirt.description) && Objects.equals(price, tshirt.price) && Objects.equals(quantity, tshirt.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tShirtId, size, color, description, price, quantity);
    }

    @Override
    public String toString() {
        return "Tshirt{" +
                "tShirtId=" + tShirtId +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
