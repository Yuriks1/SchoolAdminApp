package org.juris.slutprojekt.impl;

import org.juris.slutprojekt.tables.Course;
import org.juris.slutprojekt.tables.Education;
import org.juris.slutprojekt.dao.CourseDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseImpl implements CourseDao {

    EntityManagerFactory emf;
    EntityManager em;

    public CourseImpl() {

        emf = Persistence.createEntityManagerFactory("jpa");
        em = emf.createEntityManager();

    }

    @Override
    public void create(Course course, Education education) {
        em.getTransaction().begin();
        course.setEducation(education);
        em.persist(course);
        em.getTransaction().commit();
    }

    @Override
    public Course getById(int id) {
        return em.find(Course.class, id);
    }

    @Override
    public void update(Course course) {
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();

    }

    @Override
    public void delete(Course course) {
        em.getTransaction().begin();
        em.remove(course);
        em.getTransaction().commit();
    }

    @Override
    public List<Course> getAll() {
        return em.createQuery("SELECT course FROM Course course", Course.class).getResultList();
    }

    @Override
    public List<Course> getByEducation(int educationId) {
        TypedQuery<Course> query = em.createQuery("SELECT course From Course course WHERE education.id = :education", Course.class);
        query.setParameter("education", educationId);
        return query.getResultList();
    }
}
