package com.webshop.users.service;

import com.webshop.users.domain.Role;

public interface RoleService {

    Role findRoleByName(String name);

}
