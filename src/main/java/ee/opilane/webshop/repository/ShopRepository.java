package ee.opilane.webshop.repository;

import ee.opilane.webshop.model.database.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository  extends JpaRepository<Shop, Long> {
}
