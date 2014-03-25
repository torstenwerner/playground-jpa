package de.wps.playground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface SomeRepository extends JpaRepository<SomeEntity, Long>, QueryDslPredicateExecutor<SomeEntity> {
}
