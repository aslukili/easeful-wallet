package org.loukili.discoveryserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {
  private String id;
  private String ownerId;
  private Double balance;
}
