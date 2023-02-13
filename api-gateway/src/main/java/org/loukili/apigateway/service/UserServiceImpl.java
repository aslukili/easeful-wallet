package org.loukili.apigateway.service;

import lombok.RequiredArgsConstructor;
import org.loukili.apigateway.event.UserAddedEvent;
import org.loukili.apigateway.model.User;
import org.loukili.apigateway.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserRepository userRepository;
  private final KafkaTemplate<String, UserAddedEvent> kafkaTemplate;
  @Override
  public User addUser(User user) {
    userRepository.save(user);
    kafkaTemplate.send("walletTopic", new UserAddedEvent(user.getId()));
    return user;
  }
}
