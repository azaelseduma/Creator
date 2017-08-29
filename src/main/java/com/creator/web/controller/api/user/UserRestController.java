package com.creator.web.controller.api.user;

import com.creator.model.user.User;
import com.creator.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Azael on 2017/08/28.
 */
@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    /**
     * @return
     */
    @RequestMapping(value = "/api/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        /*List<UserDto> userDtos = new ArrayList<>();
        for(User user : users){
            userDtos.add(new UserDto(user.getUsername(),user.getPassword(),user.getEmail(),user.getName(),user.getSurname(),user.isActive(),user.getDateCreated(), user.getRoles()));
        }*/
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/{email}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getByEmail(email);
        if (user == null) {
            System.out.println("User with email " + email + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getByUsername(username);
        if (user == null) {
            System.out.println("User with username " + username + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * @param name
     * @param surname
     * @return
     */
    @RequestMapping(value = "/api/user/{name}/{surname}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByNameAndSurname(@PathVariable("name") String name, @PathVariable("surname") String surname) {
        User user = userService.getByNameAndSurname(name, surname);
        if (user == null) {
            System.out.println("User with name " + name + " and surname " + surname + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * @param user
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/api/user/save", method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

        System.out.println("Creating User " + user.getEmail());

        if (userService.isExist(user)) {
            System.out.println("A User with email " + user.getEmail() + " already exist");
            System.out.println("A User with username " + user.getUsername() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{username}").buildAndExpand(user.getEmail()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * @param email
     * @param user
     * @return
     */
    @RequestMapping(value = "/api/user/update/{email}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUserByEmail(@PathVariable("email") String email, @RequestBody User user) {
        System.out.println("Updating User " + email);

        User currentUser = userService.getByEmail(email);

        if (currentUser == null) {
            System.out.println("User with email " + email + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        currentUser.setPassword(user.getPassword());
        currentUser.setActive(user.isActive());
        currentUser.setUsername(user.getUsername());

        userService.save(currentUser);

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/update/{username}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUserByUsername(@PathVariable("username") String username, @RequestBody User user) {
        System.out.println("Updating User " + username);

        User currentUser = userService.getByUsername(username);

        if (currentUser == null) {
            System.out.println("User with username " + username + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setSurname(user.getSurname());
        currentUser.setPassword(user.getPassword());
        currentUser.setActive(user.isActive());

        userService.save(currentUser);

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    /**
     * @param email
     * @return
     */
    @RequestMapping(value = "/api/user/delete/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUserByEmail(@PathVariable("email") String email) {
        System.out.println("Fetching & Deleting User with email " + email);

        User user = userService.getByEmail(email);
        if (user == null) {
            System.out.println("Unable to delete. User with email " + email + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/api/user/delete/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUserByUsername(@PathVariable("username") String username) {
        System.out.println("Fetching & Deleting User with username " + username);

        User user = userService.getByEmail(username);
        if (user == null) {
            System.out.println("Unable to delete. User with username " + username + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
