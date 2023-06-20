package LinkSQL.LaptopWebsite.repository;

import LinkSQL.LaptopWebsite.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productname like %:key%")
    Page<Product> searchProducts(@Param("key") String key, Pageable pageable);
}
