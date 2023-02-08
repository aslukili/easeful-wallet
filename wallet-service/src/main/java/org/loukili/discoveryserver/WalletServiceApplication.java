package org.loukili.discoveryserver;

import org.loukili.discoveryserver.model.Wallet;
import org.loukili.discoveryserver.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
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
