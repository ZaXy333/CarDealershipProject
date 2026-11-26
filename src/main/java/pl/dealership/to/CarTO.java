package pl.dealership.to;

import java.io.Serializable;

public class CarTO implements Serializable {

    private Long id;
    private String brand;
    private String model;
    private int productionYear;
    private int mileage;
    private double price;
    private String fuelType;
    private boolean editable;

    public CarTO() {
    }

    public CarTO(Long id, String brand, String model, int productionYear, int mileage, double price, String fuelType) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.mileage = mileage;
        this.price = price;
        this.fuelType = fuelType;
        this.editable = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getProductionYear() { return productionYear; }
    public void setProductionYear(int productionYear) { this.productionYear = productionYear; }

    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { this.mileage = mileage; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    public boolean isEditable() { return editable; }
    public void setEditable(boolean editable) { this.editable = editable; }
}