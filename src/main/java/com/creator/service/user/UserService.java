package com.creator.service.user;

import com.creator.model.user.User;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
public interface UserService {
    void save(User user);

    User getByUsername(String username);

    List<User> getAll();

    User getByEmail(String email);

    User getByNameAndSurname(String name, String surname);

    boolean isExist(User user);

    void delete(User user);
}
