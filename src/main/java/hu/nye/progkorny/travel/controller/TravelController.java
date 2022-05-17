package hu.nye.progkorny.travel.controller;

import java.util.List;

import hu.nye.progkorny.travel.model.Travel;
import hu.nye.progkorny.travel.model.exception.NotFoundException;
import hu.nye.progkorny.travel.service.TravelService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/travel")
public class TravelController {

    private static final String TRAVEL_LIST_TEMPLATE_NAME = "travel/list";
    private static final String TRAVEL_EDIT_TEMPLATE_NAME = "travel/edit";
    private static final String TRAVEL_ATTRIBUTE_NAME = "travel";

    private final TravelService travelService;

    public TravelController(final TravelService travelService) {
        this.travelService = travelService;
    }

    @GetMapping
    public String getAllTravel(final Model model) {
        final List<Travel> travels = travelService.getAllTravels();
        model.addAttribute("travels", travels);
        return TRAVEL_LIST_TEMPLATE_NAME;
    }

    @GetMapping("/{id}")
    public String getTravel(final Model model, final @PathVariable Long id) {
        final Travel travel = travelService.getTravel(id);
        model.addAttribute(TRAVEL_ATTRIBUTE_NAME, travel);
        return TRAVEL_EDIT_TEMPLATE_NAME;
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateTravel(final Model model,
                               final @RequestParam(value = "id", required = false) Long id,
                               final Travel travelChanges) {
        final Travel travel = travelService.updateTravel(id, travelChanges);
        model.addAttribute(TRAVEL_ATTRIBUTE_NAME, travel);
        return TRAVEL_EDIT_TEMPLATE_NAME;
    }

    @GetMapping("/create")
    public String createTravelForm(final Model model) {
        return "travel/create";
    }

    @PostMapping("/create")
    public String createTravel(final Model model, final Travel travel) {
        final Travel savedTravel = travelService.createTravel(travel);
        model.addAttribute(TRAVEL_ATTRIBUTE_NAME, savedTravel);
        return TRAVEL_EDIT_TEMPLATE_NAME;
    }

    @GetMapping("/{id}/delete")
    public String deleteTravel(final Model model, final @PathVariable("id") Long id) {
        try {
            travelService.deleteTravel(id);
        } catch (NotFoundException e) {
            // We don't care about this now
        }
        final List<Travel> travels = travelService.getAllTravels();
        model.addAttribute("travels", travels);
        return TRAVEL_LIST_TEMPLATE_NAME;
    }
}
