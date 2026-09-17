package com.bank.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Index;
@Entity
@Table(
    name = "bank_accounts",
    indexes = {
        @Index(
            name = "idx_account_number",
            columnList = "account_number"
        ),
        @Index(
            name = "idx_account_customer",
            columnList = "customer_id"
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long accountId;

	    @Column(
	        name = "account_number",
	        nullable = false,
	        unique = true,
	        length = 20
	    )
	    private String accountNumber;

	    @Enumerated(EnumType.STRING)
	    @Column(
	        name = "account_type",
	        nullable = false,
	        length = 20
	    )
	    private AccountType accountType;

	    @Column(
	        name = "balance",
	        nullable = false,
	        precision = 15,
	        scale = 2
	    )
	    private BigDecimal balance;

	    @Column(name = "created_at", nullable = false)
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(
	        name = "customer_id",
	        nullable = false
	    )
	    private Customer customer;

	    @PrePersist
	    protected void onCreate() {

	        createdAt = LocalDateTime.now();
	        updatedAt = LocalDateTime.now();
	    }

	    @PreUpdate
	    protected void onUpdate() {

	        updatedAt = LocalDateTime.now();
	    }

	    @Override
	    public boolean equals(Object o) {

	        if (this == o) {
	            return true;
	        }

	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }

	        Account account = (Account) o;

	        return accountId != null &&
	               accountId.equals(account.accountId);
	    }

	    @Override
	    public int hashCode() {

	        return getClass().hashCode();
	    }
}
