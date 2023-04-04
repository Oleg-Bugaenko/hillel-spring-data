package cart.store.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class Product {
    private Integer id;
    private String title;
    private Double price;

    public Product() {}
    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

}
