package org.example;

import java.util.List;

public interface LocationDAO {

    void create(Location location);
    void update(Location location);
    void delete(Location location);
    List<Location> getAll();
    Location getById(long id);
    List<Location> selectWithSpecificCountry(String country);
    List<Location> orderByCountry();

}
