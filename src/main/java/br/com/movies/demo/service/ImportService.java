package br.com.movies.demo.service;

import br.com.movies.demo.model.Award;
import br.com.movies.demo.model.Movie;
import br.com.movies.demo.model.Studio;
import br.com.movies.demo.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImportService {

    @Value("${database.path}")
    private String databasePath;

    @Autowired
    private MovieService movieService;

    @Autowired
    private AwardService awardService;

    @Autowired
    private StudioService studioService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void importDataFromCSV() {
        String path = databasePath;
        List<String> data = getCSVLines(path);
        int index = 0;
        for (String line : data) {
            if (index > 0) {
                persistenceData(line);
            }
            index++;
        }
    }

    @Transactional
    private List<String> getCSVLines(String filePath) {
        List<String> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(filePath)))) {
            data = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Transactional
    private Movie persistenceData(String line) {
        String[] values = line.split(";");
        Movie movie = new Movie();
        Award award = new Award();

        if (values.length > 0) {
            movie.setYear(Integer.parseInt(values[0]));
        }
        if (values.length > 1) {
            movie.setTitle(values[1]);
        }
        if (values.length > 2) {
            movie.setStudios(persistenceStudios(values[2]));
        }
        if (values.length > 3) {
            movie.setProducers(persistenceUsers(values[3]));
        }
        movieService.create(movie);
        if (values.length > 4) {
            award.setYear(movie.getYear());
            award.setWinner(movie);
            awardService.create(award);
        }

        return movie;
    }

    @Transactional
    private List<Studio> persistenceStudios(String stringStudios) {
        String[] arrStudios = stringStudios.split(", ");
        return Arrays.stream(arrStudios)
                .map(studioName -> {
                    Optional<Studio> studio = studioService.findByName(studioName);
                    return studio.isPresent() ? studio.get() : studioService.create(new Studio(studioName));
                })
                .collect(Collectors.toList());
    }

    @Transactional
    private List<User> persistenceUsers(String stringUsers) {
        String[] arrUsers = stringUsers.split(", and| and |, ");
        return Arrays.stream(arrUsers)
                .map(userName -> {
                    Optional<User> user = userService.findByName(userName);
                    return user.isPresent() ? user.get() : userService.create(new User(userName));
                })
                .collect(Collectors.toList());
    }
}
