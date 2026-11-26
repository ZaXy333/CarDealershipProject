package pl.dealership.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.dealership.entity.Car;
import pl.dealership.to.CarTO;

@Stateless
public class CarDao {

    @PersistenceContext(unitName = "CarDealershipPU")
    private EntityManager em;

    public List<CarTO> findAll() {
        List<Car> entities = em.createQuery("SELECT c FROM Car c ORDER BY c.id ASC", Car.class).getResultList();
        List<CarTO> result = new ArrayList<>();
        for (Car c : entities) {
            result.add(toTransferObject(c));
        }
        return result;
    }

    public void create(CarTO to) {
        Car entity = new Car();
        copyToEntity(to, entity);
        em.persist(entity);
    }

    public void update(CarTO to) {
        if (to.getId() != null) {
            Car entity = em.find(Car.class, to.getId());
            if (entity != null) {
                copyToEntity(to, entity);
                em.merge(entity);
            }
        }
    }

    public void delete(Long id) {
        if (id != null) {
            Car entity = em.find(Car.class, id);
            if (entity != null) {
                em.remove(entity);
            }
        }
    }

    private CarTO toTransferObject(Car c) {
        return new CarTO(
                c.getId(),
                c.getBrand(),
                c.getModel(),
                c.getProductionYear(),
                c.getMileage(),
                c.getPrice(),
                c.getFuelType()
        );
    }

    private void copyToEntity(CarTO to, Car entity) {
        entity.setBrand(to.getBrand());
        entity.setModel(to.getModel());
        entity.setProductionYear(to.getProductionYear());
        entity.setMileage(to.getMileage());
        entity.setPrice(to.getPrice());
        entity.setFuelType(to.getFuelType());
    }
}