/**
 * Author: Pricilia Anna V
 * Date:5/14/2024
 * Time:10:18 PM
 */

package org.example.movies.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.movies.model.Movie;
import org.example.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Author: Pricilia Anna V
 * Date:5/14/2024
 * Time:10:18 PM
 */

@RestController
@Slf4j
@RequestMapping("/api")
public class MovieController {
    
    @Autowired
    MovieRepository movieRepository;
    
    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies(@RequestParam(required = false) String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        log.info("Get all movies");
        try {
            List<Movie> movies = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            
            Page<Movie> moviePage;
            
            if (title == null) {
                moviePage = movieRepository.findAll(paging);
            } else {
                moviePage = movieRepository.findByTitleContaining(title, paging);
            }
            
            movies = moviePage.getContent();
            
            Map<String, Object> response = new HashMap<>();
            response.put("movies", movies);
            response.put("currentPage", moviePage.getNumber());
            response.put("totalItems", moviePage.getTotalElements());
            response.put("totalPages", moviePage.getTotalPages());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        log.info("Get movie by id");
        Optional<Movie> movieData = movieRepository.findById(id);
        
        if (movieData.isPresent()) {
            return new ResponseEntity<>(movieData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        log.info("Add new movie");
        try {
            Movie _movie = movieRepository.save(new Movie(movie.getTitle(), movie.getDescription(), false));
            return new ResponseEntity<>(_movie, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
        log.info("Update movie");
        Optional<Movie> movieData = movieRepository.findById(id);
        
        if (movieData.isPresent()) {
            Movie _movie = movieData.get();
            _movie.setTitle(movie.getTitle());
            _movie.setDescription(movie.getDescription());
            _movie.setPublished(movie.isPublished());
            return new ResponseEntity<>(movieRepository.save(_movie), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") Long id) {
        log.info("Delete movie");
        try {
            movieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/movies")
    public ResponseEntity<HttpStatus> deleteAllMovies() {
        log.info("Delete all movies");
        try {
            movieRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/movies/published")
    public ResponseEntity<?> findByPublished(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        log.info("Get all published movies");
        try {
            List<Movie> movies = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            
            Page<Movie> moviePage = movieRepository.findByPublished(true, paging);
            
            Map<String, Object> response = new HashMap<>();
            response.put("movies", movies);
            response.put("currentPage", moviePage.getNumber());
            response.put("totalItems", moviePage.getTotalElements());
            response.put("totalPages", moviePage.getTotalPages());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
