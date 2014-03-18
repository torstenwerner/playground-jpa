package de.wps.playground;

import javax.persistence.*;

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
    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    public SomeEntity() {
    }

    public SomeEntity(String field) {
        this.field = field;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public enum Type {
        IMMEDIATE, MONTH, QUARTER, HALFYEAR, YEAR;
    }
}
