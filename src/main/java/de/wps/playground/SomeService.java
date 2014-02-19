package de.wps.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by torstenwerner on 18.02.14.
 */

@Service
public class SomeService {
    @Autowired
    private SomeRepository someRepository;

    @Autowired
    private RelatedRepository relatedRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SomeEntity createEntity() {
        final SomeEntity entity = new SomeEntity();
        return someRepository.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SomeEntity failTransaction() {
        final SomeEntity entity = new SomeEntity();
        someRepository.save(entity);
        if (true)
            throw new RuntimeException();
        return entity;
    }

    @Transactional(readOnly = true, timeout = 5)
    public SomeEntity getById(long id) {
        return someRepository.findOne(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RelatedEntity createRelated(String field) {
        final SomeEntity someEntity = someRepository.save(new SomeEntity(field));
        return relatedRepository.save(new RelatedEntity(someEntity));
    }
}
