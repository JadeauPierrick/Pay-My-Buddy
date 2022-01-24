package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;

import java.util.Optional;

public interface UserService {

    public Iterable<User> getUsers();

    public User getUserById(Integer id) throws Exception;

    public User getUserByEmail(String email) throws Exception;

    public User addUser(User user) throws Exception;

    public void deleteUserById(Integer id);
}
