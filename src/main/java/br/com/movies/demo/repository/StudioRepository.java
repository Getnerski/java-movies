package br.com.movies.demo.repository;

import br.com.movies.demo.model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudioRepository extends JpaRepository<Studio, Long> {

    Optional<Studio> findByName(String studioName);
}