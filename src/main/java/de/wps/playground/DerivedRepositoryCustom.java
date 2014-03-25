package de.wps.playground;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * idea from http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 */
public interface DerivedRepositoryCustom {
    public void saveAndRefresh(DerivedEntity entity);
}
