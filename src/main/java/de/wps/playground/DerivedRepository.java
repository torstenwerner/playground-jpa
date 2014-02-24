package de.wps.playground;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by torstenwerner on 24.02.14.
 */

public interface DerivedRepository extends CrudRepository<DerivedEntity, Long>,
        QueryDslPredicateExecutor<DerivedEntity> {
}
