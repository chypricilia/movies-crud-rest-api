/**
 * Author: Pricilia Anna V
 * Date:5/14/2024
 * Time:9:47 PM
 */

package org.example.movies.model;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Author: Pricilia Anna V
 * Date:5/14/2024
 * Time:9:47 PM
 */

@Entity
@Getter
@Setter
@Table(name = "MOVIES")
public class Movie implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title", length = 150)
    private String title;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "published")
    private boolean published;
    
    public Movie(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }
    
    public Movie() {
    
    }
    
    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
    }
}
