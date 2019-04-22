package com.webshop.users.service;

import com.webshop.users.domain.User;
import com.webshop.users.dto.TokenResponseDto;
import io.jsonwebtoken.Claims;

public interface TokenService {

    TokenResponseDto generate(User user);

    Claims parseToken(String jwt);

}
