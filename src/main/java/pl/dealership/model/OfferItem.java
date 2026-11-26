package pl.dealership.model;

import java.io.Serializable;

public class OfferItem implements Serializable {

    private int id;
    private String brand;
    private String model;
    private int year;
    private double price;
    private boolean editable;

    public OfferItem() {
    }

    public OfferItem(int id, String brand, String model, int year, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.editable = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isEditable() { return editable; }
    public void setEditable(boolean editable) { this.editable = editable; }
}