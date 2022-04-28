package com.jupiteropt.assessment.domain;

import java.io.Serializable;

public class TransferDto implements Serializable {
  protected final String name;
  protected final long accountFrom;
  protected final long accountTo;
  protected final double amount;

  public TransferDto(String name, long accountFrom, long accountTo, double amount) {
    this.name = name;
    this.accountFrom = accountFrom;
    this.accountTo = accountTo;
    this.amount = amount;
  }

  public long getAccountFrom() {
    return accountFrom;
  }

  public long getAccountTo() {
    return accountTo;
  }

  public String getName() {
    return name;
  }

  public double getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" +
        "name = " + name + ", " +
        "accountFrom = " + accountFrom + ", " +
        "accountTo = " + accountTo + ", " +
        "amount = " + amount + ")";
  }


}
