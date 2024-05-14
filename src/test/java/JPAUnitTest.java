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
    
    @Test
    public void should_find_movie_by_id() {
        Movie movie1 = new Movie("Movie#1", "Movie Description#1", true);
        entityManager.persist(movie1);
        
        Movie movie2 = new Movie("Movie#2", "Movie Description#2", false);
        entityManager.persist(movie2);
        
        Movie foundMovie = movieRepository.findById(movie2.getId()).get();
        
        assertThat(foundMovie).isEqualTo(movie2);
    }
    
    @Test
    public void should_find_published_movies() {
        Movie movie1 = new Movie("Movie#1", "Movie Description#1", true);
        entityManager.persist(movie1);
        
        Movie movie2 = new Movie("Movie#2", "Movie Description#2", false);
        entityManager.persist(movie2);
        
        Movie movie3 = new Movie("Movie#3", "Movie Description#3", true);
        entityManager.persist(movie3);
        
        Iterable movies = movieRepository.findByPublished(true, null);
        
        assertThat(movies).hasSize(2).contains(movie1, movie3);
    }
    
    @Test
    public void should_find_movies_by_title_containing_string() {
        Movie movie1 = new Movie("Spring boot Movie#1", "Movie Description#1", true);
        entityManager.persist(movie1);
        
        Movie movie2 = new Movie(" Java Movie#2", "Movie Description#2", false);
        entityManager.persist(movie2);
        
        Movie movie3 = new Movie("Spring Data JPA Movie#3", "Movie Description#3", true);
        entityManager.persist(movie3);
        
        Iterable movies = movieRepository.findByTitleContaining("ring", null);
        
        assertThat(movies).hasSize(2).contains(movie1, movie3);
    }
    
    @Test
    public void should_update_movie_by_id() {
        Movie movie1 = new Movie("Movie#1", "Movie Description#1", true);
        entityManager.persist(movie1);
        
        Movie movie2 = new Movie("Movie#2", "Movie Description#2", false);
        entityManager.persist(movie2);
        
        Movie updatedMovie = new Movie("Updated Movie#2", "Updated Movie Description#2", true);
        
        Movie movie = movieRepository.findById(movie2.getId()).get();
        movie.setTitle(updatedMovie.getTitle());
        movie.setDescription(updatedMovie.getDescription());
        movie.setPublished(updatedMovie.isPublished());
        movieRepository.save(movie);
        
        Movie checkMovie = movieRepository.findById(movie2.getId()).get();
        
        assertThat(checkMovie.getId()).isEqualTo(movie2.getId());
        assertThat(checkMovie.getTitle()).isEqualTo(updatedMovie.getTitle());
        assertThat(checkMovie.getDescription()).isEqualTo(updatedMovie.getDescription());
        assertThat(checkMovie.isPublished()).isEqualTo(updatedMovie.isPublished());
    }
    
    @Test
    public void should_delete_movie_by_id() {
        Movie movie1 = new Movie("Movie#1", "Movie Description#1", true);
        entityManager.persist(movie1);
        
        Movie movie2 = new Movie("Movie#2", "Movie Description#2", false);
        entityManager.persist(movie2);
        
        Movie movie3 = new Movie("Movie#3", "Movie Description#3", true);
        entityManager.persist(movie3);
        
        movieRepository.deleteById(movie2.getId());
        
        Iterable movies = movieRepository.findAll();
        
        assertThat(movies).hasSize(2).contains(movie1, movie3);
    }
    
    @Test
    public void should_delete_all_movies() {
        Movie movie1 = new Movie("Movie#1", "Movie Description#1", true);
        entityManager.persist(movie1);
        
        Movie movie2 = new Movie("Movie#2", "Movie Description#2", false);
        entityManager.persist(movie2);
        
        Movie movie3 = new Movie("Movie#3", "Movie Description#3", true);
        entityManager.persist(movie3);
        
        movieRepository.deleteAll();
        
        assertThat(movieRepository.findAll()).isEmpty();
    }
}
