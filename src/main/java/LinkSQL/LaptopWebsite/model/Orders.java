package LinkSQL.LaptopWebsite.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date order_date;
    @Column
    private Boolean isPaid;
    @Column(name = "total_amount")
    private long totalAmount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrdersDetail> orderDetails;
}
