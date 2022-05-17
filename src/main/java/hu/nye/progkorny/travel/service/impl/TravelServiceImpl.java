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

    private long getNextId() {
        return getLastId() + 1L;
    }

    private long getLastId() {
        return dataBase.stream()
                .mapToLong(Travel::getId)
                .max()
                .orElse(0);
    }
}
