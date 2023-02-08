package org.loukili.transactionservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.loukili.transactionservice.model.TransactionType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
  private Double amount;
  private TransactionType transactionType;
  private String walletId;
}
