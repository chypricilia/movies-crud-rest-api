/**
 * Author: Pricilia Anna V
 * Date:5/14/2024
 * Time:11:02 PM
 */

import org.example.movies.model.Movie;
import org.example.movies.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: Pricilia Anna V
 * Date:5/14/2024
 * Time:11:02 PM
 */
@DataJpaTest
public class JPAUnitTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    MovieRepository movieRepository;
    
    @Test
    public void should_find_no_movies_if_repository_is_empty() {
        Iterable movies = movieRepository.findAll();
        
        assertThat(movies).isEmpty();
    }

}
