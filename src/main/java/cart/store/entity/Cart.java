package cart.store.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private Integer id;
    private List<Product> products = new ArrayList<>();
}
