package org.loukili.transactionservice.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.loukili.transactionservice.dto.TransactionRequest;
import org.loukili.transactionservice.dto.TransactionResponse;
import org.loukili.transactionservice.model.Transaction;
import org.loukili.transactionservice.model.TransactionType;
import org.loukili.transactionservice.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {
  private final TransactionService transactionService;

  @PostMapping("/withdraw")
  @ResponseStatus(HttpStatus.CREATED)
  // TODO convert Transaction to transaction response
  public Transaction withdraw(@RequestBody TransactionRequest transactionRequest) {
    // TODO make sure the amount to draw is not a negative number
    return transactionService.withdraw(transactionRequest);
  }

  @PostMapping("/deposit")
  @ResponseStatus(HttpStatus.CREATED)
  @CircuitBreaker(name = "wallet", fallbackMethod = "fallBackForDeposit")
  public TransactionResponse deposit(@RequestBody TransactionRequest transactionRequest) {
    Transaction transaction = transactionService.deposit(transactionRequest);
    return TransactionResponse.builder()
      .message("Transaction was successful")
      .id(transaction.getId())
      .amount(transaction.getAmount())
      .transactionType(transaction.getTransactionType())
      .walletId(transaction.getWalletId())
      .build();
  }

  public TransactionResponse fallBackForDeposit(TransactionRequest transactionRequest, Throwable throwable){
    return TransactionResponse.builder()
      .message("An error occurred, please try again later")
      .build();
  }

}
