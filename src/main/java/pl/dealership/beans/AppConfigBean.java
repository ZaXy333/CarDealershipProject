package pl.dealership.beans;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("appConfigBean")
@ApplicationScoped
public class AppConfigBean implements Serializable {

    private String dealershipName = "AutoElite Katowice";
    private double euroRate = 4.30;
    private int totalViews = 1;

    public void incrementViews() {
        totalViews++;
    }

    public String getDealershipName() { return dealershipName; }
    public void setDealershipName(String dealershipName) { this.dealershipName = dealershipName; }

    public double getEuroRate() { return euroRate; }
    public void setEuroRate(double euroRate) { this.euroRate = euroRate; }

    public int getTotalViews() { return totalViews; }
}