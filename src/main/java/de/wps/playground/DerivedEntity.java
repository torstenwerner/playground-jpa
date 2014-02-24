package de.wps.playground;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Created by torstenwerner on 24.02.14.
 */

@Entity(name = "derived")
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "BASE_ID")
public class DerivedEntity extends BaseEntity {
    @Column(name = "derived_field")
    private String derivedField;

    public DerivedEntity() {
        setTypeId(1);
    }

    public String getDerivedField() {
        return derivedField;
    }
    public void setDerivedField(String derivedField) {
        this.derivedField = derivedField;
    }
}
