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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String title) {
        log.info("Get all movies");
        try {
            List<Movie> movies = new ArrayList<>();
            
            if (title == null) {
                movieRepository.findAll().forEach(movies::add);
            } else {
                movieRepository.findByTitleContaining(title).forEach(movies::add);
            }
            
            if (movies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(movies, HttpStatus.OK);
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
}
