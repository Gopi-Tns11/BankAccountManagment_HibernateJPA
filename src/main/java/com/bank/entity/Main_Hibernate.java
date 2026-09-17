package com.bank.entity;

import java.math.BigDecimal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Main_Hibernate {
public static void main(String[] args) {
	
	 EntityManager em =
             JPAUtil.getEntityManager();

     EntityTransaction transaction =
             em.getTransaction();

     try {

         transaction.begin();

         // Create Customer
         Customer customer = Customer.builder()
                 .customerName("Gopi")
                 .email("gopi@gmail.com")
                 .phone("9876543210")
                 .build();

         // Create Savings Account
         Account savings = Account.builder()
                 .accountNumber("ACC10001")
                 .accountType(AccountType.SAVINGS)
                 .balance(new BigDecimal("50000.00"))
                 .customer(customer)
                 .build();

         // Create Current Account
         Account current = Account.builder()
                 .accountNumber("ACC10002")
                 .accountType(AccountType.CURRENT)
                 .balance(new BigDecimal("75000.00"))
                 .customer(customer)
                 .build();

         // Add accounts to customer
         customer.getAccounts().add(savings);
         customer.getAccounts().add(current);

         // Save customer
         em.persist(customer);

         transaction.commit();

         System.out.println(
             "Customer and accounts saved successfully."
         );

     } catch (Exception e) {

         if (transaction.isActive()) {
             transaction.rollback();
         }

         e.printStackTrace();

     } finally {

         em.close();
     }

     JPAUtil.close();
 }
}

