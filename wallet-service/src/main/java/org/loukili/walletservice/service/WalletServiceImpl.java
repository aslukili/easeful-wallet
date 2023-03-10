package org.loukili.walletservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loukili.walletservice.dto.WalletRequest;
import org.loukili.walletservice.dto.WalletResponse;
import org.loukili.walletservice.exception.WalletNotFoundException;
import org.loukili.walletservice.model.Wallet;
import org.loukili.walletservice.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService{
  private final WalletRepository walletRepository;

  @Override
  public WalletResponse createWallet(WalletRequest walletRequest) {
    Wallet wallet = Wallet.builder()
      .balance(0.0)
      .ownerId(walletRequest.getUserId())
      .build();

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
      wallet.setOwnerId(walletRequest.getUserId());
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
