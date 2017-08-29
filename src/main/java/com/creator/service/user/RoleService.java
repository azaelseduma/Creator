package com.creator.service.user;

import com.creator.model.user.Role;

import java.util.List;

/**
 * Created by Azael on 2017/08/28.
 */
public interface RoleService {
    void save(Role role);

    List<Role> getAll();

    void delete(Role role);

    Role getByRole(String role);
}
