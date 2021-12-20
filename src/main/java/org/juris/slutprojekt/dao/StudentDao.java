package org.juris.slutprojekt.dao;

import org.juris.slutprojekt.tables.Education;
import org.juris.slutprojekt.tables.Student;
import java.util.List;

public interface StudentDao {

    void create(Student student, Education education);

    Student getById(int id);

    void update(Student student);

    void delete(Student student);

    List<Student> getAll();

    List<Student> getByEducation(int educationId);

}
