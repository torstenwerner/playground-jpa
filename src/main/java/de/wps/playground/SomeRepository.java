package de.wps.playground;

import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import static de.wps.playground.QSomeEntity.someEntity;

@Repository
public interface SomeRepository extends JpaRepository<SomeEntity, Long>, QueryDslPredicateExecutor<SomeEntity> {
    /**
     * some very complex query using ManyToMany
     */
    default Page<SomeEntity> findAllManyMany(String field, Pageable pageable) {
        BooleanExpression expression = someEntity.thirds.any().somes.any().field.eq(field);
        return findAll(expression, pageable);
    }

}
