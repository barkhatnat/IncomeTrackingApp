package ru.barkhatnat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@NoArgsConstructor

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    @Size(min = 1, max = 32)
    private String title;

    @Column(name = "category_type")
    @NotNull
    private Boolean categoryType;

    @OneToMany(mappedBy = "category")
    List<Operation> operations;

    public Category(String title, Boolean categoryType) {
        this.title = title;
        this.categoryType = categoryType;
    }
}
