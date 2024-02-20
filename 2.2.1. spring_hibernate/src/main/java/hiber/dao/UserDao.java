package hiber.dao;

import hiber.model.User;
import hiber.model.Car;


import java.util.List;

public interface UserDao {
   void add(User user);
   void  addCar(Car car);
   List<User> listUsers();
   List<Car> getCarOwner(String model, int series);
   void clearCars();
   void clearUser();

}
