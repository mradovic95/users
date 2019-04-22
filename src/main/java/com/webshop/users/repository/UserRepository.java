package com.webshop.users.repository;

import com.webshop.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameAndPasswordAndActive(String username, String password, Boolean active);

}
