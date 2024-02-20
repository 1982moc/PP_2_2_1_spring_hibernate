package hiber.service;

import hiber.model.User;
import hiber.model.Car;
import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void addCar(Car car);
    List<Car> getCarOwner(String model, int series);
    void clearCars();
    void clearUser();



}
