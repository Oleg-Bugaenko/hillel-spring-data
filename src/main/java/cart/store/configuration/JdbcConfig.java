package cart.store.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:jdbcMysqlProperty.properties")
public class JdbcConfig {
    @Value("${jdbc.mysql.url}")
    private String mysqlUrl;

    @Value("${jdbc.mysql.username}")
    private String mysqlUsername;

    @Value("${jdbc.mysql.password}")
    private String userPassword;

    @Bean
    public JdbcTemplate mySqlJdbcTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(mysqlUrl);
        dataSource.setUsername(mysqlUsername);
        dataSource.setPassword(userPassword);
        return new JdbcTemplate(dataSource);
    }


}
