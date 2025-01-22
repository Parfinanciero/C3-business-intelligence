package com.BI.model;

import com.BI.Utils.TypeTransaction;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "cash_flow")
public class CashFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private double amount;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Categories categories;

}
