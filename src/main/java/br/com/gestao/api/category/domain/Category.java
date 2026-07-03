package br.com.gestao.api.category.domain;

import br.com.gestao.api.common.domain.AuditableEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "category")
public class Category extends AuditableEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 200)
    private String description;

    protected Category() {

    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public UUID getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {return description;}

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
