package de.wps.playground;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ThirdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "thirds")
    private List<SomeEntity> somes = new ArrayList<>();

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
