package ru.barkhatnat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq", allocationSize = 1)
    private Integer id;

    @Column
    @NotNull
    @Size(min = 1, max = 32)
    private String title;

    @Column(name = "category_type")
    @NotNull
    private Boolean categoryType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "category")
    List<Operation> operations;

    public Category(String title, Boolean categoryType, User user) {
        this.title = title;
        this.categoryType = categoryType;
        this.user = user;
    }
}
