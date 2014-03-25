package de.wps.playground;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DerivedRepositoryCustom {
    public void saveAndRefresh(DerivedEntity entity);
}
