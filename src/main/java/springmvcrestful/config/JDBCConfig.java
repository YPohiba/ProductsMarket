package springmvcrestful.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by Yevhen Pohiba on 09.12.2017.
 */
@Configuration
public class JDBCConfig {

    @Bean
    public DriverManagerDataSource getSQLDriverManagerDatasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/test_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        return dataSource;
    }

}
