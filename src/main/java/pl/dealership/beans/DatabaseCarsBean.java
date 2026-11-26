package pl.dealership.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.dealership.dao.CarDao;
import pl.dealership.to.CarTO;

@Named("databaseCarsBean")
@ViewScoped
public class DatabaseCarsBean implements Serializable {

    @Inject
    private CarDao carDao;

    @Inject
    private SessionBean sessionBean;

    private List<CarTO> cars;

    private String newBrand;
    private String newModel;
    private int newProductionYear = 2022;
    private int newMileage = 45000;
    private double newPrice = 69900.0;
    private String newFuelType = "Benzyna";

    @PostConstruct
    public void init() {
        refreshList();
    }

    public void refreshList() {
        cars = carDao.findAll();
    }

    public void addCar() {
        if (newBrand != null && !newBrand.trim().isEmpty() && newModel != null && !newModel.trim().isEmpty()) {
            CarTO newCar = new CarTO(
                    null,
                    newBrand.trim(),
                    newModel.trim(),
                    newProductionYear,
                    newMileage,
                    newPrice,
                    newFuelType != null && !newFuelType.trim().isEmpty() ? newFuelType.trim() : "Benzyna"
            );
            carDao.create(newCar);
            newBrand = "";
            newModel = "";
            newProductionYear = 2022;
            newMileage = 45000;
            newPrice = 69900.0;
            newFuelType = "Benzyna";
            refreshList();
            sessionBean.registerAction();
        }
    }

    public void enableEdit(CarTO car) {
        car.setEditable(true);
    }

    public void saveEdit(CarTO car) {
        carDao.update(car);
        car.setEditable(false);
        refreshList();
        sessionBean.registerAction();
    }

    public void deleteCar(CarTO car) {
        carDao.delete(car.getId());
        refreshList();
        sessionBean.registerAction();
    }

    public List<CarTO> getCars() { return cars; }

    public String getNewBrand() { return newBrand; }
    public void setNewBrand(String newBrand) { this.newBrand = newBrand; }

    public String getNewModel() { return newModel; }
    public void setNewModel(String newModel) { this.newModel = newModel; }

    public int getNewProductionYear() { return newProductionYear; }
    public void setNewProductionYear(int newProductionYear) { this.newProductionYear = newProductionYear; }

    public int getNewMileage() { return newMileage; }
    public void setNewMileage(int newMileage) { this.newMileage = newMileage; }

    public double getNewPrice() { return newPrice; }
    public void setNewPrice(double newPrice) { this.newPrice = newPrice; }

    public String getNewFuelType() { return newFuelType; }
    public void setNewFuelType(String newFuelType) { this.newFuelType = newFuelType; }
}