package pl.dealership.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.dealership.model.OfferItem;

@Named("ajaxOffersBean")
@SessionScoped
public class AjaxOffersBean implements Serializable {

    @Inject
    private SessionBean sessionBean;

    private List<OfferItem> ajaxOffers;
    private int nextId = 4;

    private String brand;
    private String model;
    private int year = 2021;
    private double price = 72000.0;

    @PostConstruct
    public void init() {
        ajaxOffers = new ArrayList<>();
        ajaxOffers.add(new OfferItem(1, "Mercedes-Benz", "Klasa C W205", 2019, 98000.0));
        ajaxOffers.add(new OfferItem(2, "Ford", "Focus Mk4", 2020, 56000.0));
        ajaxOffers.add(new OfferItem(3, "Hyundai", "Tucson", 2021, 89000.0));
    }

    public void addAjaxOffer() {
        if (brand != null && !brand.trim().isEmpty() && model != null && !model.trim().isEmpty()) {
            ajaxOffers.add(new OfferItem(nextId++, brand.trim(), model.trim(), year, price));
            brand = "";
            model = "";
            year = 2021;
            price = 72000.0;
            sessionBean.registerAction();
        }
    }

    public void enableEdit(OfferItem item) {
        item.setEditable(true);
    }

    public void saveEdit(OfferItem item) {
        item.setEditable(false);
        sessionBean.registerAction();
    }

    public void deleteAjaxOffer(OfferItem item) {
        ajaxOffers.remove(item);
        sessionBean.registerAction();
    }

    public List<OfferItem> getAjaxOffers() { return ajaxOffers; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}