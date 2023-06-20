package LinkSQL.LaptopWebsite.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
    @Column
    private long price;
    @Column
    private int quantity;
}
