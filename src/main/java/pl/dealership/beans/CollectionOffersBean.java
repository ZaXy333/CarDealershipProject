package pl.dealership.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.dealership.model.OfferItem;

@Named("collectionOffersBean")
@SessionScoped
public class CollectionOffersBean implements Serializable {

    @Inject
    private SessionBean sessionBean;

    private List<OfferItem> offers;
    private int nextId = 4;

    private String newBrand;
    private String newModel;
    private int newYear = 2020;
    private double newPrice = 45000.0;

    @PostConstruct
    public void init() {
        offers = new ArrayList<>();
        offers.add(new OfferItem(1, "Audi", "A6 C7", 2016, 68000.0));
        offers.add(new OfferItem(2, "Volkswagen", "Golf VII", 2018, 52000.0));
        offers.add(new OfferItem(3, "Mazda", "6 GJ", 2017, 59500.0));
    }

    public String addOffer() {
        if (newBrand != null && !newBrand.trim().isEmpty() && newModel != null && !newModel.trim().isEmpty()) {
            offers.add(new OfferItem(nextId++, newBrand.trim(), newModel.trim(), newYear, newPrice));
            newBrand = "";
            newModel = "";
            newYear = 2020;
            newPrice = 45000.0;
            sessionBean.registerAction();
        }
        return null;
    }

    public String enableEdit(OfferItem item) {
        item.setEditable(true);
        return null;
    }

    public String saveEdit(OfferItem item) {
        item.setEditable(false);
        sessionBean.registerAction();
        return null;
    }

    public String deleteOffer(OfferItem item) {
        offers.remove(item);
        sessionBean.registerAction();
        return null;
    }

    public List<OfferItem> getOffers() { return offers; }

    public String getNewBrand() { return newBrand; }
    public void setNewBrand(String newBrand) { this.newBrand = newBrand; }

    public String getNewModel() { return newModel; }
    public void setNewModel(String newModel) { this.newModel = newModel; }

    public int getNewYear() { return newYear; }
    public void setNewYear(int newYear) { this.newYear = newYear; }

    public double getNewPrice() { return newPrice; }
    public void setNewPrice(double newPrice) { this.newPrice = newPrice; }
}