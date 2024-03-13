package ru.barkhatnat.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @OneToMany(mappedBy = "account")
    private List<Operation> operations;

    public Account(Integer id, String title, BigDecimal balance, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.balance = balance;
        this.user = user;
        this.createdAt = createdAt;
    }
}
