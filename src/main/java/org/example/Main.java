package org.example;


public class Main {

    public static void main( String[] args ) {

        LocationDAO locationDAO = new LocationImpl();

        locationDAO.create(new Location("Köpmansgatan", 23,"41113","Sweden","Göteborg"));
        locationDAO.create(new Location("Government street", 88,"12332","New Zealand","Auckland"));
        locationDAO.create(new Location("Ulvesund", 2,"54329","Sweden","Ljungskile"));
        locationDAO.create(new Location("Lowergate street", 18,"98278","New Zealand","Christchurch"));
        locationDAO.create(new Location("Östersund", 77,"09091","Sweden","Malmö"));
        locationDAO.create(new Location("Greenlown street", 5,"87401","New Zealand","Queenstown"));
        locationDAO.create(new Location("Gringston", 98,"12378","USA","Philadelphia"));
        locationDAO.create(new Location("Onster",55,"87327","USA","New York"));
        locationDAO.create(new Location("Apsinta",1,"46251","Latvia","Riga"));
        locationDAO.create(new Location("Gailisa",45,"43879","Latvia","Rezekne"));
        locationDAO.create(new Location("Inception",27,"19638","Spain","Barcelona"));
        locationDAO.create(new Location("Cabrion",9,"45028","Spain","Las Palmas"));


        System.out.println("All the information from Location!");
        System.out.println();
        locationDAO.getAll().forEach(System.out::println);

        System.out.println("========================");

        Location location = locationDAO.getById(1);
        location.setCountry("LaLaLand");
        locationDAO.update(location);
        System.out.println("City with locationID 1 changed to LaLaLand");

        System.out.println("========================");

        Location location2 = locationDAO.getById(2);
        locationDAO.delete(location2);
        System.out.println("Information with locationID 2 deleted!");

        System.out.println("========================");

        System.out.println("Everything about New Zealand in database!");
        System.out.println();
        locationDAO.selectWithSpecificCountry("New Zealand").forEach(System.out::println);

        System.out.println("========================");

        System.out.println("Countries ordered by name!");
        System.out.println();
        locationDAO.orderByCountry().forEach(System.out::println);

    }
}
