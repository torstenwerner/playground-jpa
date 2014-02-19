package de.wps.playground;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by torstenwerner on 18.02.14.
 */

@Entity
public class SomeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String field;

    public SomeEntity() {}

    public SomeEntity(String field) {
        this.field = field;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
}
