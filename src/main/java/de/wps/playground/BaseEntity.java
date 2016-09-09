package de.wps.playground;

import javax.persistence.*;

@Entity(name = "base")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_id", discriminatorType = DiscriminatorType.INTEGER)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
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
