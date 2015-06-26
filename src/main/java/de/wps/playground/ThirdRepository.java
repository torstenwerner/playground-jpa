package de.wps.playground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdRepository extends JpaRepository<ThirdEntity, Long>, QueryDslPredicateExecutor<ThirdEntity> {
}
