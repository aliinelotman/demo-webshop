package ee.opilane.webshop.repository;

import ee.opilane.webshop.model.database.Category;
import ee.opilane.webshop.model.database.Person;
import ee.opilane.webshop.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {

    Person findPersonByEmail(String email);
}