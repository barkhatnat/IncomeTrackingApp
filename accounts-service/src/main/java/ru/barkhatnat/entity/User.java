package ru.barkhatnat.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    @NotNull
    @Size(min = 1, max = 64)
    private String username;

    @Column
    @NotNull
    @Size(min = 1, max = 256)
    private String password;

    @Column
    @NotNull
    @Size(min = 1, max = 128)
    private String email;

    @CreationTimestamp
    @Column(name = "created_at")
    @NotNull
    private Timestamp createdAt;

    @Column
    @NotNull
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    public User(String username, String password, String email, Timestamp createdAt, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.role = role;
    }
}
