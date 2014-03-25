package de.wps.playground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface DerivedRepository extends JpaRepository<DerivedEntity, Long>, DerivedRepositoryCustom,
        QueryDslPredicateExecutor<DerivedEntity> {
}
