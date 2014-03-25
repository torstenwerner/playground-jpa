package de.wps.playground;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DerivedRepositoryImpl implements DerivedRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveAndRefresh(DerivedEntity entity) {
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.refresh(entity);
    }
}
