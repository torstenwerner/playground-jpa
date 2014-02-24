package de.wps.playground;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "type")
public class TypeEntity {
    @Column(name = "NAME")
    public String name;

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
}
