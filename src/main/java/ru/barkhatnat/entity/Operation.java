package ru.barkhatnat.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name="operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private BigDecimal amount;
    @Column(name = "date_purchase")
    private Timestamp datePurchase;
    @Column(name = "operation_type")
    private Boolean operationType;
    @Column
    private String note;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name ="category_id")
    private Category category;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name ="account_id")
    private Account account;
    private Timestamp createdAt;

    public Operation(BigDecimal amount, Timestamp datePurchase, Boolean operationType, String note, Timestamp createdAt) {
        this.amount = amount;
        this.datePurchase = datePurchase;
        this.operationType = operationType;
        this.note = note;
        this.createdAt = createdAt;
    }
}
