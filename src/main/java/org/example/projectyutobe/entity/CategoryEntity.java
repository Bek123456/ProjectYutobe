package org.example.projectyutobe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;
}
