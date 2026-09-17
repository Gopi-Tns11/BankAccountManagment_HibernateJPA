package com.bank.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


	@Entity
	@Table(name = "bank_customers", 
	indexes = { @Index(name = "idx_customer_email", columnList = "email"),
			@Index(name = "idx_customer_phone", columnList = "phone") })
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class Customer {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long customerId;

		@Column(name = "customer_name", nullable = false, length = 100)
		private String customerName;

		@Column(name = "email", nullable = false, unique = true, length = 100)
		private String email;

		@Column(name = "phone", length = 15)
		private String phone;

		@Column(name = "created_at", nullable = false)
		private LocalDateTime createdAt;

		@Column(name = "updated_at")
		private LocalDateTime updatedAt;

		@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
		@Builder.Default
		private List<Account> accounts = new ArrayList<>();

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

			Customer customer = (Customer) o;

			return customerId != null && customerId.equals(customer.customerId);
		}

		@Override
		public int hashCode() {

			return getClass().hashCode();
		}
		

	
	}


