package de.wps.playground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by torstenwerner on 24.02.14.
 */

public interface DerivedRepository extends JpaRepository<DerivedEntity, Long>,
        QueryDslPredicateExecutor<DerivedEntity> {
}
