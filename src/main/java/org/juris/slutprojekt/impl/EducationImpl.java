package org.juris.slutprojekt.impl;

import org.juris.slutprojekt.dao.EducationDao;
import org.juris.slutprojekt.tables.Education;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EducationImpl implements EducationDao {

    EntityManagerFactory emf;
    EntityManager em;

    public EducationImpl() {
        emf = Persistence.createEntityManagerFactory("jpa");
        em = emf.createEntityManager();
    }

    @Override
    public void create(Education education) {
        em.getTransaction().begin();
        em.persist(education);
        em.getTransaction().commit();
    }

    @Override
    public Education getById(int id) {
        return em.find(Education.class, id);
    }

    @Override
    public void update(Education education) {
        em.getTransaction().begin();
        em.merge(education);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Education education) {
        em.getTransaction().begin();
        em.remove(education);
        em.getTransaction().commit();
    }

    @Override
    public List<Education> getAll() {
        return em.createQuery("SELECT education FROM Education education", Education.class).getResultList();
    }
}
