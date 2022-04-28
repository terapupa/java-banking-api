package com.jupiteropt.assessment.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_entity")
public class AccountEntity {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "account_id", nullable = false)
  private Long accountId;

  @Column(name = "balance", nullable = false)
  private double balance;

  @ManyToOne
  @JoinColumn(name = "customer_entity_id")
  private CustomerEntity customerEntity;

  public CustomerEntity getCustomerEntity() {
    return customerEntity;
  }

  public void setCustomerEntity(CustomerEntity customerEntity) {
    this.customerEntity = customerEntity;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }
}
