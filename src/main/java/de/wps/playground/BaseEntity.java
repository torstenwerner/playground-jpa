package de.wps.playground;

import javax.persistence.*;

/**
 * Created by torstenwerner on 24.02.14.
 */

@Entity(name = "base")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_ID", discriminatorType = DiscriminatorType.INTEGER)
public class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "TYPE_ID")
    private long typeId;

    @Column(name = "base_field")
    private String baseField;

    public long getTypeId() {
        return typeId;
    }
    public void setTypeId(long typeId) {
        this.typeId = typeId;
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
