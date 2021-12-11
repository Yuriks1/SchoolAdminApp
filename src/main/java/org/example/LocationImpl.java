package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class LocationImpl implements LocationDAO {
    EntityManagerFactory emf;
    EntityManager em;

    public LocationImpl() {
        emf = Persistence.createEntityManagerFactory("jpa");
        em = emf.createEntityManager();
    }

    @Override
    public void create(Location location) {
        em.getTransaction().begin();
        em.persist(location);
        em.getTransaction().commit();
    }

    @Override
    public List<Location> getAll() {
        return em.createQuery("SELECT address FROM Location address", Location.class).getResultList();
    }

    @Override
    public void update(Location location) {
        em.getTransaction().begin();
        em.merge(location);
        em.getTransaction().commit();
    }

    @Override
    public Location getById(long id) {
        return em.find(Location.class,id);
    }


    @Override
    public List<Location> selectWithSpecificCountry(String country) {
        TypedQuery<Location> query = em.createQuery("SELECT s FROM Location s WHERE s.country =: country",Location.class);
        query.setParameter("country",country);
        return query.getResultList();
    }

    @Override
    public void delete(Location location) {
        em.getTransaction().begin();
        em.remove(location);
        em.getTransaction().commit();
    }

    public List<Location> orderByCountry(){
        TypedQuery<Location>query = em.createQuery("SELECT s FROM Location s ORDER BY s.country DESC",Location.class);
        return query.getResultList();
    }

}
