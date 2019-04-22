package com.webshop.users.service.impl;

import com.webshop.users.domain.Role;
import com.webshop.users.domain.User;
import com.webshop.users.dto.TokenRequestDto;
import com.webshop.users.dto.TokenResponseDto;
import com.webshop.users.exception.NotFoundException;
import com.webshop.users.repository.UserRepository;
import com.webshop.users.service.RoleService;
import com.webshop.users.service.TokenService;
import com.webshop.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RoleService roleService;

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(
                "User with id: %s not found", id)));
    }

    @Override
    public User saveUser(User user) {
        Role role = roleService.findRoleByName(user.getRole().getName());
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long id) {
        findUserById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {

        User user = userRepository.findUserByUsernameAndPasswordAndActive(tokenRequestDto.getUsername(), tokenRequestDto.getPassword(), true)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "User with username: %s and password: %s not found", tokenRequestDto.getUsername(), tokenRequestDto.getPassword())));
        return tokenService.generate(user);
    }

    @Override
    public void changeUserStatusById(Long id) {
        User user = findUserById(id);
        user.setActive(Boolean.valueOf(!user.getActive().booleanValue()));
        userRepository.save(user);
    }


}
