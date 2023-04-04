package cart.store.repository;

import cart.store.entity.Cart;
import cart.store.entity.Product;
import cart.store.entity.mappers.CartRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartDao implements CartRepo {

    private final JdbcTemplate jdbcTemplate;
    private final CartRowMapper cartRowMapper;

    @Override
    public Cart add(Cart cart) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sqlQuestion = "INSERT INTO cart (id) VALUES (DEFAULT)";
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
            return statement;
        }, keyHolder);
        Number key = keyHolder.getKey();
        if (key != null) cart.setId(key.intValue());
        return cart;
    }

    @Override
    public void deleteById(Integer id) {
        String sqlQuestion = "DELETE FROM cart WHERE id = ?";
        jdbcTemplate.update(sqlQuestion, id);
    }

    @Override
    public Cart findById(Integer id) {
        try {
            List<Product> products;
            String sqlQuestion;
            sqlQuestion = "select p.id, p.title, p.price from product AS p " +
                    "join cart_product cp on p.id = cp.product_id " +
                    "where cp.cart_id = ?;";
            products = jdbcTemplate.queryForObject(sqlQuestion, cartRowMapper, id);
            Cart cart = new Cart();
            cart.setId(id);
            cart.setProducts(products);
            return cart;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Cart addProductToCart(Cart cart, Product... products) {
        if (cart != null) {
            String sqlQuestion = "INSERT INTO cart_product (cart_id, product_id) " +
                    "VALUES (?, ?)";
            for (int i = 0; i < products.length; i++) {
                jdbcTemplate.update(sqlQuestion, cart.getId(), products[i].getId());
                cart.getProducts().add(products[i]);
            }
        }
        return cart;
    }
}
