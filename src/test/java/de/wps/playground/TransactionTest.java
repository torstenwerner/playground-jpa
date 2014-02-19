package de.wps.playground;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by torstenwerner on 18.02.14.
 */

@TransactionConfiguration(defaultRollback = true)
public class TransactionTest extends AbstractTest {
    @Test
    public void testTransaction() throws Exception {
        assertThat(someRepository.count(), is(0L));
    }

    private boolean runFailingMethod() {
        try {
            someService.failTransaction();
        } catch (RuntimeException e) {
            return true;
        }
        return false;
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testFail() throws Exception {
        assertThat(someRepository.count(), is(0L));
        assertThat(runFailingMethod(), is(true));
        assertThat(someRepository.count(), is(0L));
    }
}
