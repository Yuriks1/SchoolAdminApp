package org.juris.slutprojekt.menu;

import org.juris.slutprojekt.dao.CourseDao;
import org.juris.slutprojekt.dao.EducationDao;
import org.juris.slutprojekt.impl.CourseImpl;
import org.juris.slutprojekt.impl.EducationImpl;
import org.juris.slutprojekt.tables.Course;
import org.juris.slutprojekt.tables.Education;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseMenu {

    EducationDao educationDao = new EducationImpl();
    CourseDao courseDao = new CourseImpl();
    Education education;
    Course course;

    private void choice() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().toLowerCase();
        command = command.replaceAll("\\s+", "");
        switch (command) {
            case "add" -> {
                System.out.println("Write the education id then the name of the course you want to add");
                idCheck(sc);
            }
            case "update" -> {
                System.out.println("Write the id of the course you want to update and then the updated name");
                getCourseId(sc).setName(name(sc));
                courseDao.update(course);
            }
            case "id" -> {
                System.out.println("to search for specific education just write the id here: ");
                System.out.println(getCourseId(sc));
            }
            case "delete" -> {
                System.out.println("Write the id of the course you want to delete");
                getCourseId(sc);
                courseDao.delete(course);
            }
            case "all" -> courseDao.getAll().forEach(System.out::println);
            case "updateeducation" -> {
                System.out.println("To edit a course education write student id then the education or null if you want to remove it");
                getCourseId(sc).setEducation(getEducationId(sc));
                courseDao.update(course);
            }
            case "getbyeducation" -> {
                System.out.println("to see all courses connected to a specfic education, just write the education id");
                courseDao.getByEducation(sc.nextInt()).forEach(System.out::println);
            }
            case "c" -> {
                System.out.println("Commands: add, update, id, delete, all, get by education");
                choice();
            }
            default -> {
                System.out.println("Try again");
                execute();
            }
        }

    }

    private void idCheck(Scanner sc) {
        Education idCheck = getEducationId(sc);
        if (idCheck == null)
            System.out.println("That id doesn't exist");
        else
            courseDao.create(new Course(name(sc)), idCheck);
    }

    private String name(Scanner sc) {
        return sc.next();
    }

    private Education getEducationId(Scanner sc) {
        return education = educationDao.getById(sc.nextInt());
    }

    private Course getCourseId(Scanner sc) {
        return course = courseDao.getById(sc.nextInt());
    }

    private void printMenuOption() {
        System.out.println("Here you can search, add, remove, update anything related to the course table");
        System.out.println("write \"c\" to get all the available commands");
    }


    public void execute() {
        printMenuOption();
        try {
            choice();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input, try again");
        } catch (RollbackException e) {
            System.out.println("Already exists");
        } catch (IllegalArgumentException | PersistenceException | NullPointerException e) {
            System.out.println("That ID does not exist");
        }
    }
}
