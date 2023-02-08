package org.loukili.transactionservice.service;

import lombok.RequiredArgsConstructor;
import org.loukili.transactionservice.dto.TransactionRequest;
import org.loukili.transactionservice.model.Transaction;
import org.loukili.transactionservice.model.TransactionType;
import org.loukili.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
  private final TransactionRepository transactionRepository;

  @Override
  public Transaction withdraw(TransactionRequest transactionRequest) {
    // TODO: convert transactionRequest to transaction and persist it to the database
    Transaction transaction = Transaction.builder()
      .transactionType(TransactionType.WITHDRAW)
      .amount(transactionRequest.getAmount())
      .walletId(transactionRequest.getWalletId())
      .build();
    // TODO: to withdraw, make an api call to wallet service to check for enough balance
    return transactionRepository.save(transaction);
    // TODO: for a successful withdraw, update the balance in wallet entity
  }

  @Override
  public Transaction deposit(TransactionRequest transactionRequest) {
    Transaction transaction = Transaction.builder()
      .walletId(transactionRequest.getWalletId())
      .transactionType(TransactionType.DEPOSIT)
      .amount(transactionRequest.getAmount())
      .build();
    return transactionRepository.save(transaction);
    // TODO: for a successful deposit, update the balance in wallet entity
  }
}
