package org.loukili.apigateway.service;

import org.loukili.apigateway.dto.WalletRequest;
import org.loukili.apigateway.dto.WalletResponse;
import org.loukili.apigateway.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletService {
  WalletResponse createWallet(WalletRequest walletRequest);
  List<WalletResponse> getAllWallets();

  Optional<Wallet> findWalletById(String walletId);

  WalletResponse updateWallet(String walletId, WalletRequest walletRequest);
}
