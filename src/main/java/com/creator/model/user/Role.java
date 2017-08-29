package com.creator.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Azael on 2017/08/10.
 */
@Entity
@Table(name = "role", catalog = "app")
public class Role {

    @Id
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
