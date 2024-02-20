package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void addCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<Car> getCarOwner(String model, int series) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("select c.user from Car c where (model = :mod and series = :ser)")
              .setParameter("mod", model)
              .setParameter("ser", series);
      return query.getResultList();
   }

   @Override
   public void clearCars() {
      try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            // Выполняем HQL-запрос для удаления всех записей из таблицы
            //session.createQuery("DELETE FROM User").executeUpdate();
         session.createQuery("DELETE FROM Car").executeUpdate();
            transaction.commit();
         } catch (HibernateException e) {
            e.printStackTrace(); // Обработка ошибок (логирование, выброс исключения и т.д.)
         }
   }

   @Override
   public void clearUser() {
      try (Session session = sessionFactory.openSession()) {
         Transaction transaction = session.beginTransaction();
         // Выполняем HQL-запрос для удаления всех записей из таблицы
         session.createQuery("DELETE FROM User").executeUpdate();
         //session.createQuery("DELETE FROM Car").executeUpdate();
         transaction.commit();
      } catch (HibernateException e) {
         e.printStackTrace(); // Обработка ошибок (логирование, выброс исключения и т.д.)
      }
   }


}


