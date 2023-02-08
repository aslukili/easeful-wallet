package org.loukili.walletservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.loukili.walletservice.dto.WalletRequest;
import org.loukili.walletservice.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class WalletServiceApplicationTests {
  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private WalletRepository walletRepository;

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }


  @Test
  void shouldCreateWallet() throws Exception {
    WalletRequest walletRequest = getWalletRequest();
    String walletRequestAsString = objectMapper.writeValueAsString(walletRequest);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/wallet")
      .contentType(MediaType.APPLICATION_JSON)
      .content(walletRequestAsString)
    ).andExpect(status().isCreated());
    Assertions.assertEquals(1, walletRepository.findAll().size());
  }

  private WalletRequest getWalletRequest() {
    return WalletRequest.builder()
      .ownerId("12345")
      .balance(12.4)
      .build();
  }


}
