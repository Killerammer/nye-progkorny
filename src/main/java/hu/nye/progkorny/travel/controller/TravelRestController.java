package hu.nye.progkorny.travel.controller;

import java.util.List;

import hu.nye.progkorny.travel.model.Travel;
import hu.nye.progkorny.travel.model.exception.NotFoundException;
import hu.nye.progkorny.travel.service.TravelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/travel")
public class TravelRestController {

    private final TravelService travelService;

    public TravelRestController(final TravelService travelService) {
        this.travelService = travelService;
    }

    @GetMapping
    public List<Travel> getAllTravels() {
        return travelService.getAllTravels();
    }

    @GetMapping("/{id}")
    Travel getTravel(final @PathVariable Long id) {
        return travelService.getTravel(id);
    }

    @PostMapping
    Travel createTravel(final @RequestBody Travel travel) {
        return travelService.createTravel(travel);
    }

    @PutMapping("/{id}")
    Travel updateTravel(final @PathVariable Long id, final @RequestBody Travel travelChange) {
        return travelService.updateTravel(id, travelChange);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTravel(final @PathVariable Long id) {
        try {
            travelService.deleteTravel(id);
        } catch (NotFoundException e) {
            // We don't care about this now
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
