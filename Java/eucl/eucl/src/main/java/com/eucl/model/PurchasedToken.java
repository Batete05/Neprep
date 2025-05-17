package com.eucl.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NamedStoredProcedureQuery(
        name = "PurchasedToken.findExpiringTokens",
        procedureName = "find_expiring_tokens",
        resultClasses = PurchasedToken.class
)
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchased_tokens")
public class PurchasedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meterNumber", referencedColumnName = "meterNumber")
    private Meter meter;

    private String token;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TokenStatus tokenStatus;

    private Integer tokenValueDays;

    @CreationTimestamp
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private Integer amount;


    public String getToken() {
        if (token == null || token.length() != 16) return token;
        return token.replaceAll("(.{4})(?=.)", "$1-");
    }
}