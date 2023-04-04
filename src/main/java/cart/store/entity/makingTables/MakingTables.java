package cart.store.entity.makingTables;

import cart.store.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MakingTables {
    private final JdbcTemplate jdbcTemplate;

    public void createDatabase() {
        try {
            String text = Files.lines(Paths.get("src/main/resources/create_table.sql")).collect(Collectors.joining());
            String[] sqlQuestion = text.split(";");
            for (int i = 0; i < sqlQuestion.length; i++) {
                jdbcTemplate.execute(sqlQuestion[i]);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
