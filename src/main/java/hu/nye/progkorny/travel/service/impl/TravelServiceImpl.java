package hu.nye.progkorny.travel.service.impl;

import hu.nye.progkorny.travel.model.Travel;
import hu.nye.progkorny.travel.model.TravelFill;
import hu.nye.progkorny.travel.model.exception.NotFoundException;
import hu.nye.progkorny.travel.service.TravelService;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelServiceImpl implements TravelService {

    private final List<Travel> dataBase = new ArrayList<>();

    public TravelServiceImpl() {
        dataBase.add(new Travel(1L, "BUD", "Budapest Liszt Ferenc Nemzetközi Repülőtér",
                "Magyarország", "Budapest", 47.433333333333, 19.233333333333));
        dataBase.add(new Travel(2L, "DEB", "Debreceni Nemzetközi Repülőtér",
                "Magyarország", "Debrecen", 47.466666666667, 21.483333333333));
    }

    public TravelServiceImpl(final List<Travel> travels) {
        dataBase.addAll(travels);
    }

    @Override
    public List<Travel> getAllTravels() {
        return Collections.unmodifiableList(dataBase);
    }

    @Override
    public Travel getTravel(final Long id) {
        return dataBase.stream()
                .filter(travel -> travel.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Travel createTravel(final Travel travel) {
        travel.setId(getNextId());
        dataBase.add(travel);
        return travel;
    }

    @Override
    public Travel updateTravel(final Long id, final Travel travelChange) {
        final Travel travel = getTravel(id);
        travel.setIata(travelChange.getIata());
        travel.setFullName(travelChange.getFullName());
        travel.setCountry(travelChange.getCountry());
        travel.setCity(travelChange.getCity());
        travel.setLatitude(travelChange.getLatitude());
        travel.setLongitude(travelChange.getLongitude());
        return travel;
    }

    @Override
    public void deleteTravel(final Long id) {
        final Travel travel = getTravel(id);
        dataBase.remove(travel);
    }

    public double calculateDistance(String iata1, String iata2) {
        Travel travel1 = dataBase.stream()
                .filter(travel -> travel.getIata().equals(iata1))
                .findFirst()
                .orElseThrow(NotFoundException::new);
        Travel travel2 = dataBase.stream()
                .filter(travel -> travel.getIata().equals(iata2))
                .findFirst()
                .orElseThrow(NotFoundException::new);

        return distance(travel1.getLatitude(),travel2.getLatitude(),travel1.getLongitude(),travel2.getLongitude());
    }

    private long getNextId() {
        return getLastId() + 1L;
    }

    private long getLastId() {
        return dataBase.stream()
                .mapToLong(Travel::getId)
                .max()
                .orElse(0);
    }

    private static double distance(double lat1, double lat2, double lon1, double lon2)
    {

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;

        return(c * r);
    }
}
