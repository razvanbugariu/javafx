package com.halzfaller.app.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DatabaseWrapper {

    private static final String PERSISTENCE_UNIT = "test-db";

    private EntityManager em;
    private EntityManagerFactory emFactory;

    public <T> List<T> findAll(String query, Class<T> clasz) {
        beginConnection();

        List<T> result = em.createQuery(query, clasz).getResultList();

        closeConn();
        return result;
    }

    public void save(Object entity) {
        beginConnection();

        em.persist(entity);

        closeConn();
    }

    public <T> T update(T entity) {
        beginConnection();
        T newEntity = em.merge(entity);
        closeConn();

        return newEntity;
    }

    private void beginConnection() {
        em = getEntityManager();

        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void closeConn() {
        if (em != null) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().commit();
            }
            em.close();
        }
        if (emFactory != null) {
            emFactory.close();
        }
    }

    private EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = createEntityManager();
        }
        return em;
    }

    private EntityManager createEntityManager() {
        if (emFactory == null || !emFactory.isOpen()) {
            emFactory = createEntityManagerFactory();
        }

        return emFactory.createEntityManager();
    }

    private EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }
}
