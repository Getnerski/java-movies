package br.com.movies.demo.service;

import br.com.movies.demo.model.Award;
import br.com.movies.demo.model.Movie;
import br.com.movies.demo.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie update(Long id, Movie movie) {
        Optional<Movie> old = movieRepository.findById(id);

        if (old.isPresent()) {
            Movie updated = old.get();
            updated.setTitle(movie.getTitle());
            updated.setYear(movie.getYear());
            updated.setProducers(movie.getProducers());
            return movieRepository.save(updated);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void delete(Long id) {
        if(findById(id).isPresent()) {
            movieRepository.deleteById(id);
        }
    }
}

