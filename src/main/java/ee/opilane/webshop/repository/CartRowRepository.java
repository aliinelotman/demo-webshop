package ee.opilane.webshop.repository;

import ee.opilane.webshop.model.database.CartRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRowRepository extends JpaRepository<CartRow, Long> {
}
