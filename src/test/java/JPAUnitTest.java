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
    
    @Test
    public void should_store_a_movie() {
        Movie movie = movieRepository.save(new Movie("Movie Title", "Movie Description", true));
        
        assertThat(movie).hasFieldOrPropertyWithValue("title", "Movie Title");
        assertThat(movie).hasFieldOrPropertyWithValue("description", "Movie Description");
        assertThat(movie).hasFieldOrPropertyWithValue("published", true);
    }
    
    
    @Test
    public void should_find_all_movies() {
        Movie movie1 = new Movie("Movie Title 1", "Movie Description 1", true);
        entityManager.persist(movie1);
        
        Movie movie2 = new Movie("Movie Title 2", "Movie Description 2", true);
        entityManager.persist(movie2);
        
        Movie movie3 = new Movie("Movie Title 3", "Movie Description 3", true);
        entityManager.persist(movie3);
        
        Iterable movies = movieRepository.findAll();
        
        assertThat(movies).hasSize(3).contains(movie1, movie2, movie3);
    }
}
