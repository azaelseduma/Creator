package com.creator.service.user;

import com.creator.model.user.User;
import com.creator.repository.user.RoleRepository;
import com.creator.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByNameAndSurname(String name, String surname) {
        return userRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public boolean isExist(User user) {
        return getByEmail(user.getEmail()) == null ? getByUsername(user.getUsername()) != null : true;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
