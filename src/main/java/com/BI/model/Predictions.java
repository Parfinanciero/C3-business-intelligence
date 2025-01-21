package com.BI.model;

import com.BI.Utils.Status;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "predictions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Predictions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDate startDate;

    private LocalDate endDate;

    private String resultData;

    @Enumerated(EnumType.STRING)
    private Status status;


}
