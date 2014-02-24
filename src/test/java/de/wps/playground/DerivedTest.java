package de.wps.playground;

import com.mysema.query.types.expr.BooleanExpression;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by torstenwerner on 24.02.14.
 */

public class DerivedTest extends BaseTest {
    @Autowired
    private DerivedRepository derivedRepository;

    @Test
    public void testCreate() throws Exception {
        final DerivedEntity entity01 = new DerivedEntity();
        entity01.setBaseField("basefield");
        entity01.setDerivedField("derivedfield");
        derivedRepository.save(entity01);
        assertThat(entity01.getId(), notNullValue());

        assertThat(derivedRepository.count(), is(1L));
        final DerivedEntity entity02 = derivedRepository.findAll().iterator().next();
        assertThat(entity02.getDerivedField(), is("derivedfield"));
        assertThat(entity02.getBaseField(), is("basefield"));

        final QDerivedEntity qDerivedEntity = QDerivedEntity.derivedEntity;
        final BooleanExpression isMatching = qDerivedEntity.derivedField.eq("derivedfield");
        assertThat(derivedRepository.count(isMatching), is(1L));
        final DerivedEntity entity03 = derivedRepository.findAll(isMatching).iterator().next();
        assertThat(entity03.getBaseField(), is("basefield"));
    }
}
