package br.com.movies.demo.service;

import br.com.movies.demo.mapper.AwardWinnerMapper;
import br.com.movies.demo.model.Award;
import br.com.movies.demo.model.DTO.AwardWinnerDTO;
import br.com.movies.demo.model.DTO.MinAndMaxIntervalDTO;
import br.com.movies.demo.model.projection.MinAndMaxIntervalProjection;
import br.com.movies.demo.repository.AwardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AwardService {

    @Autowired
    private AwardRepository awardRepository;

    public List<Award> findAll() {
        return awardRepository.findAll();
    }

    public Optional<Award> findById(Long id) {
        return awardRepository.findById(id);
    }

    public Award create(Award award) {
        return awardRepository.save(award);
    }

    public Award update(Long id, Award award) {
        Optional<Award> old = awardRepository.findById(id);

        if (old.isPresent()) {
            Award updated = old.get();
            updated.setYear(award.getYear());
            updated.setWinner(award.getWinner());
            return awardRepository.save(updated);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void delete(Long id) {
        if(findById(id).isPresent()) {
            awardRepository.deleteById(id);
        }
    }

    public MinAndMaxIntervalDTO getMinAndMaxInterval() {
        List<MinAndMaxIntervalProjection> projections = awardRepository.findMinAndMaxInterval();
        MinAndMaxIntervalDTO minAndMaxIntervalDTO = new MinAndMaxIntervalDTO();

        if (!projections.isEmpty()) {
            double maxDifference = projections.get(0).getDifference();
            double minDifference = projections.get(projections.size() - 1).getDifference();

            List<AwardWinnerDTO> minList = projections.stream()
                    .filter(projection -> projection.getDifference() == minDifference)
                    .map(AwardWinnerMapper::toAwardWinnerDTO)
                    .collect(Collectors.toList());

            List<AwardWinnerDTO> maxList = projections.stream()
                    .filter(projection -> projection.getDifference() == maxDifference)
                    .map(AwardWinnerMapper::toAwardWinnerDTO)
                    .collect(Collectors.toList());

            minAndMaxIntervalDTO.setMin(minList);
            minAndMaxIntervalDTO.setMax(maxList);
        }

        return minAndMaxIntervalDTO;
    }

}
