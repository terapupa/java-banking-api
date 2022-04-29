package com.jupiteropt.assessment.domain;

import java.io.Serializable;

public class CreateAccountDto  implements Serializable {
  private final String firstName;
  private final String lastName;
  private final long account;
  private final double initialDeposit;
  private final String result;

  public CreateAccountDto(String firstName, String lastName, long account, double initialDeposit, String result) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.account = account;
    this.initialDeposit = initialDeposit;
    this.result = result;
  }

  public String getFirstName() {
    return firstName;
  }

  public long getAccount() {
    return account;
  }

  public double getInitialDeposit() {
    return initialDeposit;
  }

  public String getResult() {
    return result;
  }

  public String getLastName() {
    return lastName;
  }
}
