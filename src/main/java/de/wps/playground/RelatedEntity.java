package de.wps.playground;

import javax.persistence.*;

/**
 * Created by torstenwerner on 19.02.14.
 */

@Entity(name = "related")
public class RelatedEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "other_id")
    private SomeEntity other;

    public RelatedEntity() {}

    public RelatedEntity(SomeEntity other) {
        this.other = other;
    }

    public SomeEntity getOther() {
        return other;
    }
    public void setOther(SomeEntity other) {
        this.other = other;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
