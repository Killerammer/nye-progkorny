package hu.nye.progkorny.travel.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;

/**
    Currently unused because of unimplemented feature.
 */

@Component
public class TravelFill {

    @Autowired
    public List<Travel> travelFill() {

        final List<Travel> dataBase = new ArrayList<>();

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

        return dataBase;
    }
}
