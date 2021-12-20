package org.juris.slutprojekt.menu;

import org.juris.slutprojekt.dao.CourseDao;
import org.juris.slutprojekt.dao.EducationDao;
import org.juris.slutprojekt.dao.StudentDao;
import org.juris.slutprojekt.impl.CourseImpl;
import org.juris.slutprojekt.impl.EducationImpl;
import org.juris.slutprojekt.impl.StudentImpl;
import org.juris.slutprojekt.tables.Course;
import org.juris.slutprojekt.tables.Education;
import org.juris.slutprojekt.tables.Student;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentMenu {

    StudentDao studentDao = new StudentImpl();
    EducationDao educationDao = new EducationImpl();
    CourseDao courseDao = new CourseImpl();
    Student student;
    Education education;
    Course course;


    private void choice() {

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().toLowerCase();
        command = command.replaceAll("\\s+", "");
        switch (command) {
            case "add" -> {
                System.out.println("Write the education id then the name of the student you want to add");
                idCheck(sc);
            }
            case "update" -> {
                System.out.println("Write the student id then the students updated name");
                getStudentId(sc).setName(name(sc), name(sc));
                studentDao.update(student);
            }
            case "id" -> {
                System.out.println("To search for specific student write the student id here: ");
                System.out.println(getStudentId(sc));
            }
            case "delete" -> {
                System.out.println("Write the id of the student you want to delete");
                getStudentId(sc);
                studentDao.delete(student);
            }
            case "all" -> studentDao.getAll().forEach(System.out::println);
            case "updateeducation" -> {
                System.out.println("Do edit a students education write student id then the education or null if you want to remove it");
                getStudentId(sc).setEducation(getEducationId(sc));
                studentDao.update(student);
            }
            case "getbyeducation" -> {
                System.out.println("to see a student specific education just write the id of the education");
                studentDao.getByEducation(sc.nextInt()).forEach(System.out::println);
            }

            case "c" -> {
                System.out.println("Commands: add, update, id, delete, all");
                System.out.println("get by education, update education");
                choice();
            }
            default -> {
                System.out.println("Try again, please");
                execute();
            }
        }
    }

    private void idCheck(Scanner sc) {
        Education idCheck = getEducationId(sc);
        if (idCheck == null)
            System.out.println("That ID does not exist");
        else
            studentDao.create(new Student(name(sc), name(sc)), idCheck);
    }

    private String name(Scanner sc) {
        return sc.next();
    }

    private Student getStudentId(Scanner sc) {
        return student = studentDao.getById(sc.nextInt());
    }

    private Course getCourseId(Scanner sc) {
        return course = courseDao.getById(sc.nextInt());
    }

    private Education getEducationId(Scanner sc) {
        return education = educationDao.getById(sc.nextInt());
    }

    private void printMenuOption() {
        System.out.println("Here you can search, add, remove, update anything related to the student table");
        System.out.println("Write \"c\" to get all the available commands");
    }

    public void execute() {

        printMenuOption();
        try {
            choice();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input, try again!");
        } catch (RollbackException e) {
            System.out.println("Already exists");
        } catch (IllegalArgumentException | PersistenceException | NullPointerException e) {
            System.out.println("That ID does not exist");
        }
    }
}
