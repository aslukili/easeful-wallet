package org.loukili.transactionservice.service;

import lombok.RequiredArgsConstructor;
import org.loukili.transactionservice.dto.TransactionRequest;
import org.loukili.transactionservice.dto.WalletResponse;
import org.loukili.transactionservice.feignclients.WalletClient;
import org.loukili.transactionservice.model.Transaction;
import org.loukili.transactionservice.model.TransactionType;
import org.loukili.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
  private final TransactionRepository transactionRepository;
  private final WalletClient walletClient;

  @Override
  public Transaction withdraw(TransactionRequest transactionRequest) {
    Transaction transaction = Transaction.builder()
      .transactionType(TransactionType.WITHDRAW)
      .amount(transactionRequest.getAmount())
      .walletId(transactionRequest.getWalletId())
      .build();
    // TODO: to withdraw, make an api call to wallet service to check for enough balance
    WalletResponse walletResponse = walletClient.findWalletById(transaction.getWalletId());
    // TODO: for a successful withdraw, update the balance in wallet entity
    System.out.println(walletResponse.getBalance());

    return transactionRepository.save(transaction);
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
