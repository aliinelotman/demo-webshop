package ee.opilane.webshop.repository;

import ee.opilane.webshop.model.database.Category;
import ee.opilane.webshop.model.database.Order;
import ee.opilane.webshop.model.database.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByPerson (Person person);
}