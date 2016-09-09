package de.wps.playground;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String field;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "RELATED",
            joinColumns = {@JoinColumn(name = "SOME_ID")},
            inverseJoinColumns = {@JoinColumn(name = "THIRD_ID")})
    private List<ThirdEntity> thirds = new ArrayList<>();

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

    public List<ThirdEntity> getThirds() {
        return thirds;
    }

    public enum Type {
        IMMEDIATE, MONTH, QUARTER, HALFYEAR, YEAR;
    }

    @PrePersist
    @PreUpdate
    private void checkConstraint() {
        if ("INVALID".equals(field)) {
            throw new RuntimeException("field is invalid");
        }
    }
}
