package org.loukili.walletservice.service;

import org.loukili.walletservice.dto.WalletRequest;
import org.loukili.walletservice.dto.WalletResponse;
import org.loukili.walletservice.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletService {
  WalletResponse createWallet(WalletRequest walletRequest);
  List<WalletResponse> getAllWallets();

  Optional<Wallet> findWalletById(String walletId);

  WalletResponse updateWallet(String walletId, WalletRequest walletRequest);
}
