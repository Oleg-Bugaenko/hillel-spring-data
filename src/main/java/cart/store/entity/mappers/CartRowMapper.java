package cart.store.entity.mappers;

import cart.store.entity.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartRowMapper implements RowMapper<List<Product>> {
    @Override
    public List<Product> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Product> products = new ArrayList<>();
        do {
            Product product = new Product();
            product.setId(rs.getInt("p.id"));
            product.setTitle(rs.getString("p.title"));
            product.setPrice(rs.getDouble("p.price"));
            products.add(product);
        } while (rs.next());
        return products;
    }
}
