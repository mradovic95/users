package com.webshop.users.controller;

import com.webshop.users.domain.User;
import com.webshop.users.dto.TokenRequestDto;
import com.webshop.users.dto.TokenResponseDto;
import com.webshop.users.secutiry.CheckSecurity;
import com.webshop.users.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Get all users")
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Page<User>> getAllUsers(@RequestHeader("Authorization") String authorization
            , Pageable pageable) {
        return new ResponseEntity<>(userService.findAllUsers(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getAllUsers(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Save user")
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update user status")
    @PutMapping("/{id}/change-status")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Void> changeUserStatus(@RequestHeader("Authorization") String authorization, @PathVariable Long id) {
        userService.changeUserStatusById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Update user")
    @PutMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<User> updateUser(@RequestHeader("Authorization") String authorization
            , @RequestBody @Valid User user, @PathVariable Long id) {
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/{id}")
    @CheckSecurity(roles = "ROLE_ADMIN")
    public ResponseEntity deleteUser(@RequestHeader("Authorization") String authorization, @PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Login user")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }

}
