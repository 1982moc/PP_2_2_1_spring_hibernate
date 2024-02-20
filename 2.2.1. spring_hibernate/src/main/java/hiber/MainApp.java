package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Aaa", "111", "1@mail.11");
      User user2 = new User("Bbb", "222", "2@mail.22");
      User user3 = new User("Ccc", "333", "3@mail.33");
      User user4 = new User("Ddd", "444", "4@mail.44");
      Car car1 = new Car("BMW", 1);
      Car car2 = new Car("AUDI", 2);
      Car car3 = new Car("VOLVO", 3);
      Car car4 = new Car("MERCEDES", 4);

      car1.setUser(user1);
      car2.setUser(user2);
      car3.setUser(user3);
      car4.setUser(user4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      userService.addCar(car1);
      userService.addCar(car2);
      userService.addCar(car3);
      userService.addCar(car4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
         if (user.getCars() != null) {
            System.out.println("Car = " + user.getCars().getModel());
            System.out.println("Series = " + user.getCars().getSeries());
         }
         System.out.println();



      }
      List<Car> owner = userService.getCarOwner("MERCEDES", 4);
      for (int i = 0; i < owner.size();i++) {
         System.out.println(owner.get(i));
      }
      userService.clearCars();
      userService.clearUser();

      context.close();
   }
}
