package org.loukili.apigateway.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loukili.apigateway.dto.WalletRequest;
import org.loukili.apigateway.dto.WalletResponse;
import org.loukili.apigateway.exception.WalletNotFoundException;
import org.loukili.apigateway.model.Wallet;
import org.loukili.apigateway.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService{
  private final WalletRepository walletRepository;

  @Override
  public WalletResponse createWallet(WalletRequest walletRequest) {
    Wallet wallet = Wallet.builder()
      .balance(walletRequest.getBalance())
      .ownerId(walletRequest.getOwnerId())
      .build();

    log.info("making a wallet");
    walletRepository.save(wallet);

    return mapToWalletResponse(wallet);
  }

  @Override
  public List<WalletResponse> getAllWallets() {
    List<Wallet> wallets = walletRepository.findAll();
    return wallets.stream().map(this::mapToWalletResponse).toList();
  }

  @Override
  public Optional<Wallet> findWalletById(String walletId) {
    return walletRepository.findById(walletId);
  }

  @Override
  public WalletResponse updateWallet(String walletId, WalletRequest walletRequest) {
    Optional<Wallet> existingWallet = walletRepository.findById(walletId);
    if (existingWallet.isPresent()) {
      Wallet wallet = existingWallet.get();
      wallet.setOwnerId(walletRequest.getOwnerId());
      wallet.setBalance(walletRequest.getBalance());
      wallet = walletRepository.save(wallet);
      return wallet.toResponse();
    } else {
      throw new WalletNotFoundException(walletId);
    }
  }

  private WalletResponse mapToWalletResponse(Wallet wallet) {
    return WalletResponse.builder()
      .id(wallet.getId())
      .balance(wallet.getBalance())
      .ownerId(wallet.getOwnerId())
      .build();
  }
}
