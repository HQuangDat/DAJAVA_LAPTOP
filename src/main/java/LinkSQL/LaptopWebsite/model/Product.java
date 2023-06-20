package LinkSQL.LaptopWebsite.model;

import LinkSQL.LaptopWebsite.Validator.annotation.ValidCategoryId;
import LinkSQL.LaptopWebsite.Validator.annotation.ValidUserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productname")
    @NotEmpty(message = "Product name must not be empty")
    @Size(max = 50, min = 1, message = "Title must be less than 50 characters")
    private String productname;

    @Column(name = "price")
    @NotNull(message = "Price is required")
    private Long price;

    @Column(name = "image")
    @NotNull(message = "Image is required")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ValidCategoryId
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;
}
