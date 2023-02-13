package org.loukili.apigateway.controller;


import lombok.RequiredArgsConstructor;
import org.loukili.apigateway.model.User;
import org.loukili.apigateway.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
  private final UserService userService;

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public User addUser(@RequestBody User user) {
    return userService.addUser(user);
  }
}
