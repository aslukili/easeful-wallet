package org.loukili.transactionservice.exception;

public class NotEnoughBalanceException extends RuntimeException {
  public NotEnoughBalanceException() {
    super("Not enough balance");
  }
}

