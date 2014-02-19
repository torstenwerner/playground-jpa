package de.wps.playground;

import org.junit.Test;

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
        final RelatedEntity relatedEntity = someService.createRelated("myfield");
        assertThat(relatedEntity.getOther().getField(), is("myfield"));
    }
}
