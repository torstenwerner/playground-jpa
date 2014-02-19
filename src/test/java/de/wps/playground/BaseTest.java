package de.wps.playground;

import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.CollectionExpression;
import com.mysema.query.types.Predicate;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by torstenwerner on 18.02.14.
 */

public class BaseTest extends AbstractTest {
    @Test
    public void testNothing() throws Exception {
    }

    @Test
    public void testSomeEntity() throws Exception {
        assertThat(someRepository.count(), is(0L));
        final SomeEntity entity = someService.createEntity();
        assertThat(entity.getId(), notNullValue());
        assertThat(someRepository.count(), is(1L));
    }

    @Test
    public void testNested() throws Exception {
        assertThat(someRepository.count(), is(0L));
        final SomeEntity entity01 = new SomeEntity();
        someRepository.save(entity01);
        someService.getById(entity01.getId());

        entity01.setField("someValue");
        someRepository.save(entity01);
        assertThat(entity01.getField(), is("someValue"));
        final SomeEntity entity02 = someService.getById(entity01.getId());
        assertThat(entity02.getField(), is(entity01.getField()));
    }

    @Test
    public void testRelated() throws Exception {
        assertThat(someRepository.count(), is(0L));
        final RelatedEntity relatedEntity = someService.createRelated("myfield");
        assertThat(relatedEntity.getOther().getField(), is("myfield"));

        final QSomeEntity qSomeEntity = QSomeEntity.someEntity;
        final CollectionExpression myfields = new JPASubQuery().from(qSomeEntity).
                where(qSomeEntity.field.eq("myfield")).list();

        final QRelatedEntity qRelatedEntity = QRelatedEntity.relatedEntity;
        Predicate isMyfield = qRelatedEntity.other.in(myfields);

        final List<RelatedEntity> results = (List<RelatedEntity>) relatedRepository.findAll(isMyfield);
        assertThat(results, hasSize(1));
        assertThat(results.get(0).getOther().getField(), is("myfield"));
    }
}
