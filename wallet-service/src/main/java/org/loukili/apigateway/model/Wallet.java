package org.loukili.apigateway.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.loukili.apigateway.dto.WalletResponse;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document(value = "wallet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Wallet {
  @Id
  private String id;
  private String ownerId;
  private Double balance;
  @CreatedDate
  private Timestamp createdAt;
  @LastModifiedDate
  private Timestamp updatedAt;

  public WalletResponse toResponse() {
    return WalletResponse.builder()
      .id(this.id)
      .ownerId(this.ownerId)
      .balance(this.balance)
      .build();
  }
}
