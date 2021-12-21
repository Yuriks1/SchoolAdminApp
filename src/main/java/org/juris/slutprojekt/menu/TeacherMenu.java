package org.juris.slutprojekt.menu;

import org.juris.slutprojekt.dao.CourseDao;
import org.juris.slutprojekt.dao.EducationDao;
import org.juris.slutprojekt.dao.TeacherDao;
import org.juris.slutprojekt.impl.CourseImpl;
import org.juris.slutprojekt.impl.EducationImpl;
import org.juris.slutprojekt.impl.TeacherImpl;
import org.juris.slutprojekt.tables.Course;
import org.juris.slutprojekt.tables.Education;
import org.juris.slutprojekt.tables.Teacher;
import javax.persistence.*;
import java.util.*;

public class TeacherMenu {

    TeacherDao teacherDao = new TeacherImpl();
    EducationDao educationDao = new EducationImpl();
    CourseDao courseDao = new CourseImpl();
    Teacher teacher;
    Education education;
    Course course;
    List<Course> courseSet = new ArrayList<>();


    private void choice() {

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().toLowerCase();
        command = command.replaceAll("\\s+", "");
        switch (command) {
            case "add" -> {
                System.out.println("Write the education ID then the name of the teacher you want to add");
                idCheck(sc);
            }
            case "update" -> {
                System.out.println("Write the teacher ID then the teacher updated name");
                getTeacherId(sc).setName(name(sc), name(sc));
                teacherDao.update(teacher);
            }
            case "id" -> {
                System.out.println("To search for specific teacher write the teacher ID here: ");
                System.out.println(getTeacherId(sc));
            }
            case "delete" -> {
                System.out.println("Write the ID of the teacher you want to delete");
                getTeacherId(sc);
                teacherDao.delete(teacher);
            }
            case "updateeducation" -> {
                System.out.println("To change a teachers education, write the teacher ID " +
                        "" +
                        "then the education or null if you want to remove it");
                getTeacherId(sc).setEducation(getEducationId(sc));
                teacherDao.update(teacher);
            }

            case "getbyeducation" ->{
                System.out.println("To see a teacher specific education write the ID of the education");
                teacherDao.getByEducation(sc.nextInt()).forEach(System.out::println);
            }
            case "addtocourse" -> {
                System.out.println("Write the course ID then the teacher ID please");
                courseSet.add(getCourseId(sc));
                teacherDao.setCourse(courseSet, getTeacherId(sc));
            }
            case "deletefromcourse" -> {
                System.out.println("Please write the ID of the course then ID of the teacher");
                teacherDao.deleteFromCourse(sc.nextInt(), sc.nextInt());
            }

            case "all" -> teacherDao.getAll().forEach(System.out::println);

            case "c" -> {
                System.out.println("Commands: add, update, id, delete, all, delete from course," +
                        " add to course, get by education,");
                System.out.println("update education");
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
            System.out.println("That id does not exist");
        else
            teacherDao.create(new Teacher(name(sc), name(sc)), idCheck);
    }

    private String name(Scanner sc) {
        return sc.next();
    }

    private Teacher getTeacherId(Scanner sc) {
        return teacher = teacherDao.getById(sc.nextInt());
    }

    private Course getCourseId(Scanner sc) {
        return course = courseDao.getById(sc.nextInt());
    }

    private Education getEducationId(Scanner sc) {
        return education = educationDao.getById(sc.nextInt());
    }


    public void execute() {
        System.out.println("Here you can search, add, remove, update !");
        System.out.println("write \"c\" to get all the available commands");
        try {
            choice();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input, try again!");
        } catch (RollbackException e) {
            System.out.println("Already exists");
        } catch (IllegalArgumentException | PersistenceException | NullPointerException | IllegalStateException e) {
            System.out.println("That ID does not exist");
        }
    }
}
