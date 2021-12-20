package org.juris.slutprojekt.dao;

import org.juris.slutprojekt.tables.Course;
import org.juris.slutprojekt.tables.Education;
import java.util.List;

public interface CourseDao {

    void create(Course course, Education education);

    Course getById(int id);

    void update(Course course);

    void delete(Course course);

    List<Course> getAll();

    List<Course> getByEducation(int EducationId);

}
