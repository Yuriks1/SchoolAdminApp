package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long locationId;
    private String street;
    private int houseNumber;
    private String zip;
    private String country;
    private String city;

    public Location() {
    }

    public Location(String street, int houseNumber, String zip, String country, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zip = zip;
        this.country = country;
        this.city = city;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long addressId) {
        this.locationId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

    @Override
    public String toString() {
        return " Location { " +
                "LocationID = " + locationId +
                ", Street = '" + street + '\'' +
                ", House Number = " + houseNumber +
                ", Zip = '" + zip + '\'' +
                ", Country = '" + country + '\'' +
                ", City = '" + city + '\'' +
                " }";
    }
}