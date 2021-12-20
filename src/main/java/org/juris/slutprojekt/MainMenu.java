package org.juris.slutprojekt;

import org.juris.slutprojekt.menu.CourseMenu;
import org.juris.slutprojekt.menu.EducationMenu;
import org.juris.slutprojekt.menu.StudentMenu;
import org.juris.slutprojekt.menu.TeacherMenu;
import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EducationMenu educationMenu = new EducationMenu();
    private static final CourseMenu courseMenu = new CourseMenu();
    private static final StudentMenu studentMenu = new StudentMenu();
    private static final TeacherMenu teacherMenu = new TeacherMenu();


    public static void main(String[] args) {

        while (true) {

            System.out.println("1. Education");
            System.out.println("2. Course");
            System.out.println("3. Student");
            System.out.println("4. Teacher");
            System.out.println("0. Exit");


            String choice = scanner.nextLine();

            switch (choice) {

                case "1" -> educationMenu.execute();
                case "2" -> courseMenu.execute();
                case "3" -> studentMenu.execute();
                case "4" -> teacherMenu.execute();
                case "0" -> System.exit(0);
                default -> System.out.println("Something went wrong, try again!");

            }
        }
    }
}