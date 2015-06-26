package de.wps.playground;

import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.types.CollectionExpression;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static de.wps.playground.SomeEntity.Type.YEAR;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class BaseTest extends AbstractTest {
    @Test
    public void testNothing() throws Exception {
    }

    @PersistenceContext
    private EntityManager entityManager;

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
        final ThirdEntity thirdEntity = new ThirdEntity("myname");
        final RelatedEntity relatedEntity = someService.saveRelated(new RelatedEntity(someEntity, thirdEntity));
        assertThat(relatedEntity.getOther(), notNullValue());
        assertThat(relatedEntity.getOther().getField(), is("myfield"));
    }

    @Test
    public void testManyToMany() throws Exception {
        ThirdEntity thirdEntity = new ThirdEntity("myname");

        final SomeEntity someEntity = new SomeEntity("myfield");
        someEntity.getThirds().add(thirdEntity);
        someRepository.save(someEntity);
        assertThat(someEntity.getId(), notNullValue());
        assertThat(someEntity.getThirds(), hasSize(1));
        assertThat(someEntity.getField(), is("myfield"));

        assertThat(someRepository.findOne(someEntity.getId()), notNullValue());
        assertThat(someRepository.findAll(), hasSize(1));

        BooleanExpression expr1 = QSomeEntity.someEntity.thirds.any().name.eq("myname");
        Page<SomeEntity> entities1 = someRepository.findAll(expr1, new PageRequest(0, 100));
        assertThat(entities1.getNumberOfElements(), is(1));
        assertThat(entities1.getContent().get(0).getField(), is("myfield"));

        BooleanExpression expr2 = QThirdEntity.thirdEntity.somes.any().field.eq("myfield");
        Page<ThirdEntity> entities2 = thirdRepository.findAll(expr2, new PageRequest(0, 100));
        assertThat(entities2.getNumberOfElements(), is(1));

        Page<SomeEntity> entities3 = someRepository.findAllManyMany("myfield", new PageRequest(0, 100));
        assertThat(entities3.getNumberOfElements(), is(1));
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidInsert() throws Exception {
        someRepository.saveAndFlush(new SomeEntity("INVALID"));
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidUpdate() throws Exception {
        final SomeEntity someEntity = someRepository.save(new SomeEntity("myfield"));
        someEntity.setField("INVALID");
        someRepository.saveAndFlush(someEntity);
    }
}
