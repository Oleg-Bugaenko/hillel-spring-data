package cart.store.repository;

import cart.store.entity.Cart;
import cart.store.entity.Product;

public interface CartRepo {
    Cart add(Cart cart);
    void deleteById(Integer id);
    Cart findById(Integer id);
    Cart addProductToCart(Cart cart, Product... products);
}
