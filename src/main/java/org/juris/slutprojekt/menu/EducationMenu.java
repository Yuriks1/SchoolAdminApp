package org.juris.slutprojekt.menu;

import org.juris.slutprojekt.dao.EducationDao;
import org.juris.slutprojekt.impl.EducationImpl;
import org.juris.slutprojekt.tables.Education;
import javax.persistence.RollbackException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EducationMenu {

    EducationDao dao = new EducationImpl();
    Education education;

    private void choice() {

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().toLowerCase();
        command = command.replaceAll("\\s+", "");
        switch (command) {
            case "add" -> {
                System.out.println("Write the name of the education you want to create");
                dao.create(new Education(name(sc)));
            }
            case "update" -> {
                System.out.println("Write the ID of the education you want to update and the updated name");
                getId(sc).setName(name(sc));
                dao.update(education);
            }
            case "id" -> {
                System.out.println("to search for specific education just write the ID here: ");
                System.out.println(getId(sc));
            }
            case "delete" -> {
                System.out.println("Write the ID of the education you want to delete");
                getId(sc);
                dao.delete(education);
            }
            case "all" -> dao.getAll().forEach(System.out::println);
            case "c" -> {
                System.out.println("Commands: add, update, id, delete, all");
                choice();
            }
            default -> {
                System.out.println("Try again, please!");
                execute();
            }
        }
    }

    private String name(Scanner sc) {
        return sc.next();
    }

    private Education getId(Scanner sc) {
        return education = dao.getById(sc.nextInt());
    }



    public void execute() {

        System.out.println("Here you can search, add, remove, update !");
        System.out.println("Write \"c\" to get all the available commands!");
        try {
            choice();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input, try again");
        } catch (RollbackException e) {
            System.out.println("Already exists");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("That ID does not exist");
        }
    }
}
