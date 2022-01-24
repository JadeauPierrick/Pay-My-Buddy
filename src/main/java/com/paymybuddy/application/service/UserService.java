package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;

import java.util.Optional;

public interface UserService {

    public Iterable<User> getUsers();

    public Optional<User> getUserById(Integer id);

    public User addUser(User user);

    public void deleteUserById(Integer id);
}
