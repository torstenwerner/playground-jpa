package de.wps.playground;

import javax.persistence.*;

/**
 * Created by torstenwerner on 24.02.14.
 */

@Entity(name = "base")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_ID", discriminatorType = DiscriminatorType.INTEGER)
public class BaseEntity {
    @Column(name = "TYPE_ID")
    protected long typeId;
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TYPE_ID", insertable = false, updatable = false)
    private TypeEntity type;
    @Column(name = "base_field")
    private String baseField;
    public TypeEntity getType() {
        return type;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBaseField() {
        return baseField;
    }
    public void setBaseField(String baseField) {
        this.baseField = baseField;
    }
}
