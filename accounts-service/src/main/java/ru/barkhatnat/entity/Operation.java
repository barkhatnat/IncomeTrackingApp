package ru.barkhatnat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    @DecimalMin(value = "-9999999999.99")
    @DecimalMax(value = "9999999999.99")
    private BigDecimal amount;

    @Column(name = "date_purchase")
    @NotNull
    private Timestamp datePurchase;

    @Column
    @Size(min = 1, max = 512)
    private String note;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Operation(BigDecimal amount, Timestamp datePurchase, Category category, Account account, String note, Timestamp createdAt) {
        this.amount = amount;
        this.datePurchase = datePurchase;
        this.category = category;
        this.account = account;
        this.note = note;
        this.createdAt = createdAt;
    }
}
