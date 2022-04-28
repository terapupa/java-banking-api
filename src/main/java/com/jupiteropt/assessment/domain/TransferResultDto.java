package com.jupiteropt.assessment.domain;

public class TransferResultDto extends TransferDto {
  private final String result;

  public TransferResultDto(String result, String name, long accountFrom, long accountTo, double amount) {
    super(name, accountFrom, accountTo, amount);
    this.result = result;
  }

  public String getResult() {
    return result;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" +
        "result = " + result + ", " +
        "name = " + name + ", " +
        "accountFrom = " + accountFrom + ", " +
        "accountTo = " + accountTo + ", " +
        "amount = " + amount + ")";
  }
}
