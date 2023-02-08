package org.loukili.discoveryserver.service;

import org.loukili.discoveryserver.dto.WalletRequest;
import org.loukili.discoveryserver.dto.WalletResponse;
import org.loukili.discoveryserver.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletService {
  WalletResponse createWallet(WalletRequest walletRequest);
  List<WalletResponse> getAllWallets();

  Optional<Wallet> findWalletById(String walletId);

  WalletResponse updateWallet(String walletId, WalletRequest walletRequest);
}
