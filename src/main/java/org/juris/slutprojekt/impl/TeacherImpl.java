package org.juris.slutprojekt.impl;

import org.juris.slutprojekt.dao.TeacherDao;
import org.juris.slutprojekt.tables.Course;
import org.juris.slutprojekt.tables.Education;
import org.juris.slutprojekt.tables.Teacher;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TeacherImpl implements TeacherDao {

    EntityManagerFactory emf;
    EntityManager em;

    public TeacherImpl() {

        emf = Persistence.createEntityManagerFactory("jpa");
        em = emf.createEntityManager();

    }

    @Override
    public void create(Teacher teacher, Education education) {
        em.getTransaction().begin();
        teacher.setEducation(education);
        em.persist(teacher);
        em.getTransaction().commit();
    }

    @Override
    public Teacher getById(int id) {
        return em.find(Teacher.class, id);
    }

    @Override
    public void update(Teacher teacher) {
        em.getTransaction().begin();
        em.merge(teacher);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Teacher teacher) {
        em.getTransaction().begin();
        em.remove(teacher);
        em.getTransaction().commit();
    }

    @Override
    public void setCourse(List<Course> list, Teacher teacher) {
        em.getTransaction().begin();
        teacher.add(list);
        em.persist(teacher);
        teacher.clearList(list);
        em.getTransaction().commit();
    }

    @Override
    public void deleteFromCourse(int courseId, int teacherId) {
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM teacher_courses WHERE courses_id = ? AND teacher_id = ?")
                .setParameter(1, courseId)
                .setParameter(2, teacherId)
                .executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public List<Teacher> getByEducation(int educationId) {
        TypedQuery<Teacher> query = em.createQuery("SELECT teacher FROM Teacher teacher WHERE education.id = :education", Teacher.class);
        query.setParameter("education", educationId);
        return query.getResultList();
    }

    @Override
    public List<Teacher> getAll() {
        return em.createQuery("SELECT teacher FROM Teacher teacher", Teacher.class).getResultList();
    }
}
