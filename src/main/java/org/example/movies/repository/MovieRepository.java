package org.example.movies.repository;

import org.example.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: Pricilia Anna V
 * Date:5/14/2024
 * Time:10:14 PM
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByPublished(boolean published);
    List<Movie> findByTitleContaining(String title);
}
