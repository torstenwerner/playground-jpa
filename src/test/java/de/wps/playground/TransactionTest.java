package de.wps.playground;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by torstenwerner on 18.02.14.
 */

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
    public void testFail() throws Exception {
        assertThat(someRepository.count(), is(0L));
        assertThat(runFailingMethod(), is(true));
        assertThat(someRepository.count(), is(0L));
    }
}
