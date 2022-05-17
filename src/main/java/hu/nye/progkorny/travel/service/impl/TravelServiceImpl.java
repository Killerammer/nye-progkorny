package hu.nye.progkorny.travel.service.impl;

import hu.nye.progkorny.travel.model.Travel;
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

    @Autowired
    public TravelServiceImpl() {
        dataBase.add(new Travel(1L, "BUD", "Budapest Liszt Ferenc Nemzetközi Repülőtér",
                "Magyarország", "Budapest", 47.433333333333, 19.233333333333));
        dataBase.add(new Travel(2L, "DEB", "Debreceni Nemzetközi Repülőtér",
                "Magyarország", "Debrecen", 47.466666666667, 21.483333333333));
        dataBase.add(new Travel(3L, "MCQ", "Miskolci Repülőtér",
                "Magyarország", "Miskolc", 48.05, 20.833333333333));
        dataBase.add(new Travel(4L, "CLJ", "Aeroportul International Avram lancu Cluj",
                "Románia", "Kolozsvár", 46.783333333333, 23.683333333333));
        dataBase.add(new Travel(5L, "OTP", "Aeroportul International Henri Coanda Bucuresti",
                "Románia", "Bukarest", 44.5, 26.083333333333));
        dataBase.add(new Travel(6L, "BER", "Flughafen Berlin Brandenburg Willy Brandt",
                "Németország", "Berlin", 52.3666666667, 13.5033333333));
        dataBase.add(new Travel(7L, "CGN", "Flughafen Köln/Bonn Konrad Adenauer",
                "Németország", "Köln", 50.866666666667, 7.15));
        dataBase.add(new Travel(8L, "CDG", "Aéroport de Paris-Charles-de-Geulle",
                "Franciaország", "Párizs", 49.016666666667, 2.55));
        dataBase.add(new Travel(9L, "LHR", "London Heathrow Airport",
                "Egyesült Királyság", "London", 51.466666666667, -0.45));
        dataBase.add(new Travel(10L, "SVO", "Международный аэропорт Шереметьево имени А. С. Пушкина",
                "Oroszország", "Moszkva", 55.969444444444, 37.416666666667));
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
