package br.com.movies.demo.controller;

import br.com.movies.demo.model.Award;
import br.com.movies.demo.model.DTO.AwardWinnerDTO;
import br.com.movies.demo.model.DTO.MinAndMaxIntervalDTO;
import br.com.movies.demo.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/awards")
public class AwardController {

    @Autowired
    private AwardService awardService;

    @GetMapping
    public ResponseEntity<List<Award>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(awardService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Award> getById(@PathVariable Long id) {
        Optional<Award> award = awardService.findById(id);
        return award.isPresent() ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(award.get()) :
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
    }

    @PostMapping
    public ResponseEntity<Award> create(@RequestBody Award award) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(awardService.create(award));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Award> update(@PathVariable Long id, @RequestBody Award award) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(awardService.update(id, award));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Award> delete(@PathVariable Long id) {
        awardService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

    @GetMapping(value = "/min-max-interval")
    public ResponseEntity<MinAndMaxIntervalDTO> getMinAndMaxInterval() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(awardService.getMinAndMaxInterval());
    }

}
