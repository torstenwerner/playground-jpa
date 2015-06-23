package de.wps.playground;

import javax.persistence.*;

@Entity
public class ThirdEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;

    public ThirdEntity() {
    }

    public ThirdEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String field) {
        this.name = field;
    }
}
