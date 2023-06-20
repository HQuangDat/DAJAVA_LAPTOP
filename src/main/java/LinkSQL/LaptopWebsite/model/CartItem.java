package LinkSQL.LaptopWebsite.model;

import lombok.Data;


@Data
public class CartItem {
    //Product info
    private Long id;
    private String name;
    private String image;
    private Long price;
    private Category category;
    //Quantity
    private int quantity;
}
