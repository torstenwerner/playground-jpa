package de.wps.playground;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by torstenwerner on 18.02.14.
 */

public interface SomeRepository extends CrudRepository<SomeEntity, Long>, QueryDslPredicateExecutor<SomeEntity> {
}
