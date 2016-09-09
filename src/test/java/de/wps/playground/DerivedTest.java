package de.wps.playground;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DerivedTest extends BaseTest {
    @Autowired
    private DerivedRepository derivedRepository;

    @Test
    public void testCreate() throws Exception {
        final DerivedEntity entity01 = new DerivedEntity();
        entity01.setBaseField("basefield");
        entity01.setDerivedField("derivedfield");
        derivedRepository.saveAndRefresh(entity01);
        assertThat(entity01.getId(), notNullValue());

        final DerivedEntity entity02 = derivedRepository.findOne(entity01.getId());
        assertThat(entity02.getType().getName(), is("DERIVED"));

        assertThat(derivedRepository.count(), is(1L));
        final DerivedEntity entity03 = derivedRepository.findAll().iterator().next();
        assertThat(entity03.getDerivedField(), is("derivedfield"));
        assertThat(entity03.getBaseField(), is("basefield"));
        assertThat(entity03.getType().getName(), is("DERIVED"));

        final QDerivedEntity qDerivedEntity = QDerivedEntity.derivedEntity;
        final BooleanExpression isMatching01 = qDerivedEntity.derivedField.eq("derivedfield");
        assertThat(derivedRepository.count(isMatching01), is(1L));
        final DerivedEntity entity04 = derivedRepository.findAll(isMatching01).iterator().next();
        assertThat(entity04.getBaseField(), is("basefield"));

        final BooleanExpression isMatching02 = qDerivedEntity.type.name.eq("DERIVED");
        assertThat(derivedRepository.count(isMatching02), is(1L));
        final DerivedEntity entity05 = derivedRepository.findAll(isMatching02).iterator().next();
        assertThat(entity05.getBaseField(), is("basefield"));
    }
}
