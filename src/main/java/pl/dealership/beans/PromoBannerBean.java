package pl.dealership.beans;

import java.io.Serializable;

public class PromoBannerBean implements Serializable {

    private String promoTitle;
    private int discountPercent;

    public String getPromoTitle() { return promoTitle; }
    public void setPromoTitle(String promoTitle) { this.promoTitle = promoTitle; }

    public int getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(int discountPercent) { this.discountPercent = discountPercent; }
}