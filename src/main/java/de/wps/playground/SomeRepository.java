package de.wps.playground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by torstenwerner on 18.02.14.
 */

public interface SomeRepository extends JpaRepository<SomeEntity, Long>, QueryDslPredicateExecutor<SomeEntity> {
}
