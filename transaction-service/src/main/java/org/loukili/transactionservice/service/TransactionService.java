package org.loukili.transactionservice.service;

import org.loukili.transactionservice.dto.TransactionRequest;
import org.loukili.transactionservice.dto.TransactionResponse;
import org.loukili.transactionservice.exception.NotEnoughBalanceException;
import org.loukili.transactionservice.model.Transaction;

public interface TransactionService {
    Transaction withdraw(TransactionRequest transactionRequest);
    Transaction deposit(TransactionRequest transactionRequest);
}
