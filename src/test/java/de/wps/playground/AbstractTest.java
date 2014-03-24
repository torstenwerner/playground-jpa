package de.wps.playground;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public abstract class AbstractTest {
    @Autowired
    protected SomeRepository someRepository;

    @Autowired
    protected RelatedRepository relatedRepository;

    @Autowired
    protected SomeService someService;
}
