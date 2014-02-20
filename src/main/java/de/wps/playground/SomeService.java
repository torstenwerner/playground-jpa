package de.wps.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Transactional
    public SomeEntity createEntity() {
        final SomeEntity entity = new SomeEntity();
        return someRepository.save(entity);
    }

    @Transactional
    public SomeEntity failTransaction() {
        final SomeEntity entity = new SomeEntity();
        someRepository.save(entity);
        if (true)
            throw new RuntimeException();
        return entity;
    }

    @Transactional(readOnly = true)
    public SomeEntity getById(long id) {
        return someRepository.findOne(id);
    }

    @Transactional
    public RelatedEntity saveRelated(RelatedEntity relatedEntity) {
        return relatedRepository.findOne(relatedRepository.save(relatedEntity).getId());
    }

    @Transactional
    public RelatedEntity createBoth(String field) {
        final SomeEntity someEntity = someRepository.save(new SomeEntity(field));
        return relatedRepository.findOne(relatedRepository.save(new RelatedEntity(someEntity)).getId());
    }
}
