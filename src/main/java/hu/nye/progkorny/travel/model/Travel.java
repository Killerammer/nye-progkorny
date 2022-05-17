package hu.nye.progkorny.travel.model;

import java.util.Objects;

public class Travel {

    private Long id;
    private String iata;
    private String fullName;
    private String country;
    private String city;
    private Double latitude;
    private Double longitude;

    public Travel() {
    }

    public Travel(Long id, String iata, String fullName, String country, String city, Double latitude, Double longitude) {
        this.id = id;
        this.iata = iata;
        this.fullName = fullName;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Travel travel = (Travel) o;
        return Objects.equals(id, travel.id)
                && Objects.equals(iata, travel.iata)
                && Objects.equals(fullName, travel.fullName) && Objects.equals(country, travel.country)
                && Objects.equals(city, travel.city)
                && Objects.equals(latitude, travel.latitude)
                && Objects.equals(longitude, travel.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iata, fullName, country, city, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", iata='" + iata + '\'' +
                ", fullName='" + fullName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
