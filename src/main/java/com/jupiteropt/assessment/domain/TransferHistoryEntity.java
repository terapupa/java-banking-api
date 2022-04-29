package com.jupiteropt.assessment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The data persistence entity.
 * The class represents the Transfer History data model used by JPA and Hibernate ORM to represent the MySQL schema.
 */

@Entity
@Table(name="transfer_history_entity")
public class TransferHistoryEntity {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "amount", nullable = false)
  private double amount;

  @Column(name = "account_from", nullable = false)
  private long accountFrom;

  @Column(name = "account_to", nullable = false)
  private long accountTo;

  public TransferHistoryEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public long getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(long accountFrom) {
    this.accountFrom = accountFrom;
  }

  public long getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(long accountTo) {
    this.accountTo = accountTo;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" +
        "id = " + id + ", " +
        "amount = " + amount + ", " +
        "accountFrom = " + accountFrom + ", " +
        "accountTo = " + accountTo + ")";
  }
}
