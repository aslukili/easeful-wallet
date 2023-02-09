package org.loukili.transactionservice.feignclients;


import feign.RequestLine;
import org.loukili.transactionservice.dto.WalletResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@FeignClient(name = "wallet-service")
public interface WalletClient {
  @GetMapping("/api/v1/wallets/{walletId}")
  @ResponseStatus(HttpStatus.OK)
  WalletResponse findWalletById(@PathVariable String walletId);
}
