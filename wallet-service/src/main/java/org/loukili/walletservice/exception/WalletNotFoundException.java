package org.loukili.walletservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WalletNotFoundException extends RuntimeException {
  public WalletNotFoundException(String walletId) {
    super("Wallet with ID " + walletId + " was not found");
  }
}

