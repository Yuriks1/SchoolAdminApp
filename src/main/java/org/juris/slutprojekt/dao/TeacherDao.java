package org.juris.slutprojekt.dao;

import org.juris.slutprojekt.tables.Course;
import org.juris.slutprojekt.tables.Education;
import org.juris.slutprojekt.tables.Teacher;
import java.util.List;

public interface TeacherDao {

    void create(Teacher teacher, Education education);

    Teacher getById(int id);

    void update(Teacher teacher);

    void delete(Teacher teacher);

    void setCourse(List<Course> test, Teacher teacher);

    void deleteFromCourse(int teacherId, int courseId);

    List<Teacher> getByEducation(int educationId);

    List<Teacher> getAll();

}
