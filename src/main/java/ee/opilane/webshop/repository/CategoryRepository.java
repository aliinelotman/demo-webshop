package ee.opilane.webshop.repository;

import ee.opilane.webshop.model.database.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//.. extends–> sellest saab selle klassi "child" klass justkui
// hetkel suurim JpaRepository > aga ka CrudRepository > PagingAndSortingRepository jne
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
