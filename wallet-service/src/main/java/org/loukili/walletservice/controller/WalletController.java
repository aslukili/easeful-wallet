package org.loukili.walletservice.controller;


import lombok.RequiredArgsConstructor;
import org.loukili.walletservice.dto.WalletRequest;
import org.loukili.walletservice.dto.WalletResponse;
import org.loukili.walletservice.model.Wallet;
import org.loukili.walletservice.service.WalletService;
import org.loukili.walletservice.service.WalletServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {
  private final WalletService walletService;

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public List<WalletResponse> getAllWallets(){
    return walletService.getAllWallets();
  }
  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  // TODO: convert Wallet to wallet response
  public WalletResponse createWallet(@RequestBody WalletRequest walletRequest) {
    return walletService.createWallet(walletRequest);
  }

  @GetMapping("/{walletId}")
  @ResponseStatus(HttpStatus.OK)
  public WalletResponse findWalletById(@PathVariable String walletId) {
    Optional<Wallet> wallet = walletService.findWalletById(walletId);
    if (wallet.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found with id: " + walletId);
    }
    return wallet.get().toResponse();
  }

  // TODO: update wallet (put mapping)
  @PutMapping("/{walletId}")
  @ResponseStatus(HttpStatus.OK)
  public WalletResponse updateWallet(@PathVariable String walletId, @RequestBody WalletRequest walletRequest) {
    return walletService.updateWallet(walletId, walletRequest);
  }
}
