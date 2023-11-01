package br.com.movies.demo.mapper;

import br.com.movies.demo.model.DTO.AwardWinnerDTO;
import br.com.movies.demo.model.projection.MinAndMaxIntervalProjection;

public class AwardWinnerMapper {

    public static AwardWinnerDTO toAwardWinnerDTO(MinAndMaxIntervalProjection projection) {
        return new AwardWinnerDTO(
                projection.getProducer(),
                projection.getDifference(),
                projection.getPreviousAwardYear(),
                projection.getFollowingAwardYear());
    }
}
