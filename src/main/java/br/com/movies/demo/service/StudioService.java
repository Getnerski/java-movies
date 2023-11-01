package br.com.movies.demo.service;

import br.com.movies.demo.model.Studio;
import br.com.movies.demo.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;

    public List<Studio> findAll() {
        return studioRepository.findAll();
    }

    public Optional<Studio> findById(Long id) {
        return studioRepository.findById(id);
    }

    public Optional<Studio> findByName(String studioName) {
        return studioRepository.findByName(studioName);
    }

    public Studio create(Studio studio) {
        return studioRepository.save(studio);
    }
}
