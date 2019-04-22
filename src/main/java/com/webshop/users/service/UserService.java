package com.webshop.users.service;

import com.webshop.users.domain.User;
import com.webshop.users.dto.TokenRequestDto;
import com.webshop.users.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> findAllUsers(Pageable pageable);

    User findUserById(Long id);

    User saveUser(User user);

    User updateUser(User user, Long id);

    void deleteUserById(Long id);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    void changeUserStatusById(Long id);

}
