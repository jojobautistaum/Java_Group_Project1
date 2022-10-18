package com.project1.Summative1jojoyinara.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game {
    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;

    private String title;

    @Column(name = "esrb_rating")
    private String esrbRating;
    private String description;

    @Min(value = 0, message = "the price cannot be negative")
    private double price;

    private String studio;

    @Min(value = 0, message = "the quantity cannot be negative")
    private Integer quantity;

    public Game() {
    }

    public Game(Integer gameId, String title, String esrbRating, String description, double price, String studio, Integer quantity) {
        this.gameId = gameId;
        this.title = title;
        this.esrbRating = esrbRating;
        this.description = description;
        this.price = price;
        this.studio = studio;
        this.quantity = quantity;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
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
        Game game = (Game) o;
        return Double.compare(game.price, price) == 0 && Objects.equals(gameId, game.gameId) && Objects.equals(title, game.title) && Objects.equals(esrbRating, game.esrbRating) && Objects.equals(description, game.description) && Objects.equals(studio, game.studio) && Objects.equals(quantity, game.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, title, esrbRating, description, price, studio, quantity);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
