package br.com.movies.demo.controller;

import br.com.movies.demo.model.Movie;
import br.com.movies.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movieService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.findById(id);
        return movie.isPresent() ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(movie.get()) :
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody Movie movie) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieService.create(movie));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie movie) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movieService.update(id, movie));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Movie> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

}
