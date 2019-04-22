package com.webshop.users.service.impl;

import com.webshop.users.domain.Role;
import com.webshop.users.exception.NotFoundException;
import com.webshop.users.repository.RoleRepository;
import com.webshop.users.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(() -> new NotFoundException(String.format(
                "Role with name: %s not found", name)));
    }

}
