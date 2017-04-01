package com.halzfaller.app.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

class DatabaseWrapper {

    private static final String PERSISTENCE_UNIT = "test-db";

    protected EntityManager em;
    protected EntityManagerFactory emFactory;

    <T> List<T> getAll(String query, Class<T> clasz) {
        beginConnection();

        List<T> result = em.createQuery(query, clasz).getResultList();

        closeConnection();
        return result;
    }

    void create(Object entity) {
        beginConnection();

        em.persist(entity);

        closeConnection();
    }

    <T> T merge(T entity) {
        beginConnection();
        T newEntity = em.merge(entity);
        closeConnection();

        return newEntity;
    }

    void remove(Object entity) {
        beginConnection();

        em.remove(entity);

        closeConnection();
    }

    void beginConnection() {
        em = getEntityManager();

        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    void closeConnection() {
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
