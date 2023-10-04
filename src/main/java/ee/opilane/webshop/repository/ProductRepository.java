package ee.opilane.webshop.repository;

import ee.opilane.webshop.model.database.Category;
import ee.opilane.webshop.model.database.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//.. extends–> sellest saab selle klassi "child" klass justkui
// hetkel suurim JpaRepository > aga ka CrudRepository > PagingAndSortingRepository jne
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByActiveAndStockGreaterThan(boolean active, int stock, Pageable pageable);

    Page<Product> findAllByCategoryAndActiveEquals(Category category, boolean active, Pageable pageable);

    List<Product> findAllByOrderById();

}
