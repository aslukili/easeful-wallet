package org.loukili.transactionservice.service;

import lombok.RequiredArgsConstructor;
import org.loukili.transactionservice.dto.TransactionRequest;
import org.loukili.transactionservice.dto.WalletRequest;
import org.loukili.transactionservice.dto.WalletResponse;
import org.loukili.transactionservice.exception.NotEnoughBalanceException;
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
    // to withdraw, make an api call to wallet service to check for enough balance
    WalletResponse walletResponse = walletClient.findWalletById(transaction.getWalletId());
    if (walletResponse.getBalance() > transaction.getAmount()){
      // for a successful withdrawal, update the balance in wallet entity and save the transaction
      walletClient.updateWallet(transaction.getWalletId(), WalletRequest.builder().balance(walletResponse.getBalance() - transaction.getAmount()).build());
      return transactionRepository.save(transaction);
    } else {
      throw new NotEnoughBalanceException();
    }
  }

  @Override
  public Transaction deposit(TransactionRequest transactionRequest) {
    Transaction transaction = Transaction.builder()
      .walletId(transactionRequest.getWalletId())
      .transactionType(TransactionType.DEPOSIT)
      .amount(transactionRequest.getAmount())
      .build();

    // for a successful deposit, update the balance in wallet entity
    WalletResponse walletResponse = walletClient.findWalletById(transaction.getWalletId());
    WalletRequest updatedWalletRequest = WalletRequest.builder()
      .balance(walletResponse.getBalance() + transaction.getAmount())
      .build();
    walletClient.updateWallet(transaction.getWalletId(), updatedWalletRequest);
    return transactionRepository.save(transaction);
  }
}
