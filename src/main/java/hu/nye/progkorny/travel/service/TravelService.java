package hu.nye.progkorny.travel.service;

import hu.nye.progkorny.travel.model.Travel;

import java.util.List;

public interface TravelService {

    List<Travel> getAllTravels();

    Travel getTravel(Long id);

    Travel createTravel(Travel travel);

    Travel updateTravel(Long id, Travel travelChange);

    void deleteTravel(Long id);

    double calculateDistance(String iata1, String iata2);
}
