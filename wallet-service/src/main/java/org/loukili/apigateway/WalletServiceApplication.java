package org.loukili.apigateway;

import org.loukili.apigateway.model.Wallet;
import org.loukili.apigateway.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class WalletServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(WalletServiceApplication.class, args);
  }

  @Bean
  public CommandLineRunner loadData(WalletRepository walletRepository) {
    return args -> {
      Wallet wallet = Wallet.builder()
        .balance(24.4)
        .ownerId("123")
        .build();
      walletRepository.save(wallet);
    };
  }
}
