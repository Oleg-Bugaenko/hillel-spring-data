package cart.store.repository;

import cart.store.entity.Product;

import java.util.List;

public interface ProductRepo {
    void deleteById(Integer id);
    void add(Product product);
    Product findById(Integer id);
    List<Product> getAll();


}
