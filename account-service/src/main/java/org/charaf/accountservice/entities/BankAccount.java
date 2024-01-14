package org.charaf.accountservice.entities;

import ch.qos.logback.core.boolex.EvaluationException;
import jakarta.persistence.*;
import lombok.*;
import org.charaf.accountservice.enums.AccountType;
import org.charaf.accountservice.model.Customer;

import java.time.LocalDate;

@Setter @Getter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class BankAccount {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String accountId;
    private Double balance;
    private LocalDate createdAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Long customerId;
    @Transient
    private Customer customer;



}
