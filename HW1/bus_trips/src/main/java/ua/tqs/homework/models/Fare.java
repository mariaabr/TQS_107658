package ua.tqs.homework.models;

public class Fare {
    private Double price;
    private String currency;

    public Fare(Double price) {
        
        this.price = price;
    }

    public Fare(Double price, String currency) {
        
        this.price = price;
        this.currency = currency;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "{" +
            " price='" + getPrice() + "'" +
            ", currency='" + getCurrency() + "'" +
            "}";
    }

}