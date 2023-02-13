package org.loukili.transactionservice.feignclients;


import org.loukili.transactionservice.dto.WalletRequest;
import org.loukili.transactionservice.dto.WalletResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "wallet-service")
public interface WalletClient {
  @GetMapping("/api/v1/wallets/{walletId}")
  @ResponseStatus(HttpStatus.OK)
  WalletResponse findWalletById(@PathVariable String walletId);

  @PutMapping("/api/v1/wallets/{walletId}")
  @ResponseStatus(HttpStatus.OK)
  WalletResponse updateWallet(@PathVariable String walletId, @RequestBody WalletRequest walletRequest);
}
