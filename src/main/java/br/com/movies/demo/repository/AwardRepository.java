package br.com.movies.demo.repository;

import br.com.movies.demo.model.Award;
import br.com.movies.demo.model.projection.MinAndMaxIntervalProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AwardRepository extends JpaRepository<Award, Long> {

    @Query(value = "SELECT u.name AS producer, " +
            "MIN(a1.AWARD_YEAR) AS previousAwardYear, " +
            "MAX(a1.AWARD_YEAR) AS followingAwardYear, " +
            "MAX(a1.AWARD_YEAR) - MIN(a1.AWARD_YEAR) AS difference " +
            "FROM users u " +
            "JOIN movie_producer mp ON u.id = mp.user_id " +
            "JOIN movies m ON mp.movie_id = m.id " +
            "JOIN awards a1 ON m.id = a1.winner_id " +
            "WHERE u.id IN (" +
            "    SELECT u.id " +
            "    FROM users u " +
            "    JOIN movie_producer mp ON u.id = mp.user_id " +
            "    JOIN movies m ON mp.movie_id = m.id " +
            "    JOIN awards a1 ON m.id = a1.winner_id " +
            "    GROUP BY u.id " +
            "    HAVING COUNT(u.id) >= 2" +
            ") " +
            "GROUP BY u.name " +
            "ORDER BY difference DESC, previousAwardYear ASC",
            nativeQuery = true)
    List<MinAndMaxIntervalProjection> findMinAndMaxInterval();

}