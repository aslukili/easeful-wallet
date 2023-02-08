package org.loukili.transactionservice.controller;


import lombok.RequiredArgsConstructor;
import org.loukili.transactionservice.dto.TransactionRequest;
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
    return transactionService.withdraw(transactionRequest);
  }

  @PostMapping("/deposit")
  @ResponseStatus(HttpStatus.CREATED)
  public Transaction deposit(@RequestBody TransactionRequest transactionRequest) {
    return transactionService.deposit(transactionRequest);
  }

}
