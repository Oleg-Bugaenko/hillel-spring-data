package cart.store.repository;

import cart.store.entity.Product;
import cart.store.entity.mappers.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDao implements ProductRepo{

    private final JdbcTemplate jdbcTemplate;
    private final ProductRowMapper productRowMapper;


    @Override
    public void deleteById(Integer id) {
        String sqlQuestion = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sqlQuestion, id);
    }

    @Override
    public void add(Product product) {
        String sqlQuestion = "INSERT INTO product (title, price) " +
                "VALUES (?, ?)";
        jdbcTemplate.update(sqlQuestion, product.getTitle(), product.getPrice());
    }

    @Override
    public Product findById(Integer id) {
        String sqlQuestion = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuestion, productRowMapper, id);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products;
        String sqlQuestion = "SELECT * FROM product";
        products = jdbcTemplate.query(sqlQuestion, productRowMapper);
        return products;
    }
}
