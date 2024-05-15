package org.example.movies;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class MoviesCrudRestApiApplicationTests {
    
    @Test
    void contextLoads() {
    }
    
    @Autowired
    private static JdbcTemplate jdbcTemplate;
    
    @BeforeClass
    public static void init() {
        // Assuming the use of an embedded database for testing
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS MOVIES (" +
                                 "ID BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                                 "TITLE VARCHAR(150), " +
                                 "DESCRIPTION VARCHAR(500), " +
                                 "PUBLISHED BOOLEAN)");
    }
    
}
