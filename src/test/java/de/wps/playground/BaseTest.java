package de.wps.playground;

import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.types.CollectionExpression;
import com.mysema.query.types.Predicate;
import org.junit.Test;

import java.util.List;

import static de.wps.playground.SomeEntity.Type.YEAR;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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
        assertThat(entity.getType(), is(YEAR));
        assertThat(entity.getType().ordinal(), is(4));
        assertThat(entity.getType().name(), is("YEAR"));
        assertThat(entity.getField(), is("Ernie"));
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
        final RelatedEntity relatedEntity = someService.createBoth("myfield");
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

    @Test
    public void testRelatedOnly() throws Exception {
        final SomeEntity someEntity = someRepository.save(new SomeEntity("myfield"));
        final RelatedEntity relatedEntity = someService.saveRelated(new RelatedEntity(someEntity));
        assertThat(relatedEntity.getOther(), notNullValue());
        assertThat(relatedEntity.getOther().getField(), is("myfield"));
    }
}
