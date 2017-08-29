package com.creator.model.user;

import com.creator.ApplicationTest;
import com.creator.service.user.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Azael on 2017/08/28.
 */
public class UserTest extends ApplicationTest {
    @Autowired
    private UserService userService;

    @Test
    public void userTestPack() {
        createUser();
        updateUser();
        deleteUser();
    }

    @Test
    public void createUser() {
        save("aseduma", "xbox360", "aseduma@gmail.com", "Azael", "Seduma", true);
        save("tseduma", "xbox360", "tseduma@gmail.com", "Thapelo", "Seduma", true);
    }

    @Test
    public void updateUser() {
        updateByUsername("aseduma", "xbox260", "aseduma@gmail.com", "Azael", "Seduma", true);
        updateByEmail("tseduma", "xbox367", "tseduma@gmail.com", "Thapelo", "Seduma", true);
    }

    @Test
    public void deleteUser() {
        deleteByUsername("aseduma");
        deleteByEmail("tseduma@gmail.com");
    }


    private void save(String username, String password, String email, String name, String surname, boolean active) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.setActive(active);
        userService.save(user);
        System.out.println(user.toString());
    }

    private void updateByUsername(String username, String password, String email, String name, String surname, boolean active) {
        User user = userService.getByUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.setActive(active);
        userService.save(user);
        System.out.println(user.toString());
    }

    private void deleteByUsername(String username) {
        User user = userService.getByUsername(username);
        userService.delete(user);
    }

    private void updateByEmail(String username, String password, String email, String name, String surname, boolean active) {
        User user = userService.getByEmail(email);
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.setActive(active);
        userService.save(user);
        System.out.println(user.toString());
    }

    private void deleteByEmail(String email) {
        User user = userService.getByEmail(email);
        userService.delete(user);
    }
}
