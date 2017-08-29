package com.creator.model.user;

import com.creator.ApplicationTest;
import com.creator.service.user.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Azael on 2017/08/28.
 */
public class RoleTest extends ApplicationTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void createUserRole() {
        save("USER", "User");
    }

    @Test
    public void deleteUserRole() {
        delete("USER");
    }

    @Test
    public void createAdminRole() {
        save("ADMIN", "Administrator");
    }

    @Test
    public void deleteAdminRole() {
        delete("ADMIN");
    }

    private void save(String strRole, String description) {
        Role role = new Role();
        role.setRole(strRole);
        role.setDescription(description);
        roleService.save(role);
        System.out.println(role.toString());
    }

    private void delete(String strRole) {
        Role role = roleService.getByRole(strRole);
        roleService.delete(role);
    }
}
