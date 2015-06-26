package de.wps.playground;

import javax.persistence.*;

@Entity(name = "related")
public class RelatedEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "some_id")
    private SomeEntity other;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "third_id")
    private ThirdEntity third;

    public RelatedEntity() {}

    public RelatedEntity(SomeEntity other, ThirdEntity third) {
        this.other = other;
        this.third = third;
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

    public ThirdEntity getThird() {
        return third;
    }

    public void setThird(ThirdEntity third) {
        this.third = third;
    }
}
