package org.loukili.apigateway.repository;

import org.loukili.apigateway.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
