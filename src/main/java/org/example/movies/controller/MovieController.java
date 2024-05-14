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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
