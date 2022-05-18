package hu.nye.progkorny.travel.service.impl;

import hu.nye.progkorny.travel.model.Travel;
import hu.nye.progkorny.travel.model.TravelFill;
import hu.nye.progkorny.travel.model.exception.NotFoundException;
import hu.nye.progkorny.travel.service.TravelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TravelServiceImplTest {

    private static final Travel BUDAPEST_TRAVEL = new Travel(1L, "BUD", "Budapest Liszt Ferenc Nemzetközi Repülőtér",
            "Magyarország", "Budapest", 47.433333333333, 19.233333333333);
    private static final Travel BERLIN_TRAVEL = new Travel(2L, "BER", "Flughafen Berlin Brandenburg Willy Brandt",
            "Németország", "Berlin", 52.3666666667, 13.5033333333);
    private static final List<Travel> TRAVELS = List.of(
            BUDAPEST_TRAVEL,
            BERLIN_TRAVEL
    );

    public static final long UNKNOWN_TRAVEL_ID = -1L;
    public static final String MOSCOW_TRAVEL_FULLNAME = "Международный аэропорт Шереметьево имени А. С. Пушкина";

    private TravelService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TravelServiceImpl(TRAVELS);
    }

    @Test
    void getAllTravelsShouldReturnAllAirports() {
        // when
        final List<Travel> actual = underTest.getAllTravels();
        // then
        assertThat(actual).isEqualTo(TRAVELS);
    }

    @Test
    void getTravelShouldReturnAirportWhenGivenIdOfExistingAirport() {
        // when
        final Travel actual = underTest.getTravel(BERLIN_TRAVEL.getId());
        // then
        assertThat(actual).isEqualTo(BERLIN_TRAVEL);
    }

    @Test
    void getTravelShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingAirport() {
        // when then
        assertThrows(NotFoundException.class, () -> underTest.getTravel(UNKNOWN_TRAVEL_ID));
    }

    @Test
    void createTravelShouldReturnAirportWhenDelegateIt() {
        // given
        final Travel moscowTravel = new Travel(null, "SVO", MOSCOW_TRAVEL_FULLNAME,
                "Oroszország", "Moszkva", 55.969444444444, 37.416666666667);
        final Travel expectedMoscowTravel = new Travel(3L, "SVO", MOSCOW_TRAVEL_FULLNAME,
                "Oroszország", "Moszkva", 55.969444444444, 37.416666666667);
        // when
        final Travel actual = underTest.createTravel(moscowTravel);
        // then
        assertThat(actual).isEqualTo(expectedMoscowTravel);
    }

    @Test
    void updateTravelShouldReturnUpdatedAirportWhenGivenIdOfExistingAirport() {
        // given
        final Travel moscowTravel = new Travel(null, "SVO", MOSCOW_TRAVEL_FULLNAME,
                "Oroszország", "Moszkva", 55.969444444444, 37.416666666667);
        final Travel expectedTravel = new Travel(BERLIN_TRAVEL.getId(), "SVO", MOSCOW_TRAVEL_FULLNAME,
                "Oroszország", "Moszkva", 55.969444444444, 37.416666666667);
        // when
        final Travel actual = underTest.updateTravel(BERLIN_TRAVEL.getId(), moscowTravel);
        // then
        assertThat(actual).isEqualTo(expectedTravel);
    }

    @Test
    void updateTravelShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingAirport() {
        // given
        final Travel moscowTravel = new Travel(null, "SVO", MOSCOW_TRAVEL_FULLNAME,
                "Oroszország", "Moszkva", 55.969444444444, 37.416666666667);
        // when then
        assertThrows(NotFoundException.class, () -> underTest.updateTravel(UNKNOWN_TRAVEL_ID, moscowTravel));
    }

    @Test
    void deleteTravelShouldDeleteAirportWhenGivenIdOfAirport() {
        // given
        final List<Travel> expectedTravels = List.of(BERLIN_TRAVEL);
        // when
        underTest.deleteTravel(BUDAPEST_TRAVEL.getId());
        final List<Travel> actual = underTest.getAllTravels();
        // then
        assertThat(actual).isEqualTo(expectedTravels);
    }


    //Átadunk neki 2 iata-t ami megfelel a kettő példának teszthez
    //VIsszaadja e az azokhoz helyes ID-t
    @Test
    void calculateDistanceShouldReturnTheCorrectDistanceIfGivenTwoIatas() {
        //given TODO Számolj gyereket
        double expected = 0;
        //when
        double actual = underTest.calculateDistance(BERLIN_TRAVEL.getIata(),BUDAPEST_TRAVEL.getIata());
        //then
        assertThat(actual).isEqualTo(expected);
    }

    //Visszaadja e az azok között lévő helyes távolságot
}
