package LinkSQL.LaptopWebsite.repository;

import LinkSQL.LaptopWebsite.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Orders,Long> {
}
