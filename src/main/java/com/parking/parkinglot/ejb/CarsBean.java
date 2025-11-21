package com.parking.parkinglot.ejb;
import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    @PersistenceContext
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> dtos = new ArrayList<>();
        for (Car car : cars) {
            CarDto carDto = new CarDto(car.getParkingSpot(),car.getId(),car.getLicensePlate(),car.getOwner().getUsername());
            dtos.add(carDto);
        }
        return dtos;
    }

    public List<CarDto> findAllCars(){
        LOG.info("findAllCars");
        try {
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        }catch(Exception ex){
            throw new EJBException(ex);
        }
    }

    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        LOG.info("createCar");

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

        entityManager.persist(car);
    }

    public CarDto findById(Long id) {
        LOG.info("findById");

        Car car = entityManager.find(Car.class, id);
        List<Car> carAsList = new ArrayList<>();
        carAsList.add(car);
        List<CarDto> carAsDtoList = copyCarsToDto(carAsList);
        return carAsDtoList.getFirst();
    }

    public void updateCar(Long id, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("updateCar");

        Car car = entityManager.find(Car.class, id);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user = entityManager.find(User.class, userId);
        car.setOwner(user);
    }

    public void deleteCarsByIds(Collection<Long> ids) {
        LOG.info("deleteCarsByIds");

        for(Long id : ids){
            Car car = entityManager.find(Car.class, id);
            entityManager.remove(car);
        }
    }
}
