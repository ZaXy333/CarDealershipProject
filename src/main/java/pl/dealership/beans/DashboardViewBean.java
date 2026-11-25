package pl.dealership.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("dashboardViewBean")
@ViewScoped
public class DashboardViewBean implements Serializable {

    @Inject
    private AppConfigBean appConfigBean;

    @Inject
    private SessionBean sessionBean;

    private double carPricePln = 60000.0;
    private int months = 36;
    private double monthlyInstallmentPln;
    private double monthlyInstallmentEur;

    @PostConstruct
    public void init() {
        recalculate();
    }

    public String calculate() {
        recalculate();
        sessionBean.registerAction();
        return "recalculate";
    }

    private void recalculate() {
        if (months <= 0) {
            months = 1;
        }
        double totalWithMargin = carPricePln * 1.08;
        monthlyInstallmentPln = Math.round((totalWithMargin / months) * 100.0) / 100.0;
        monthlyInstallmentEur = Math.round((monthlyInstallmentPln / appConfigBean.getEuroRate()) * 100.0) / 100.0;
    }

    public double getCarPricePln() { return carPricePln; }
    public void setCarPricePln(double carPricePln) { this.carPricePln = carPricePln; }

    public int getMonths() { return months; }
    public void setMonths(int months) { this.months = months; }

    public double getMonthlyInstallmentPln() { return monthlyInstallmentPln; }
    public double getMonthlyInstallmentEur() { return monthlyInstallmentEur; }
}