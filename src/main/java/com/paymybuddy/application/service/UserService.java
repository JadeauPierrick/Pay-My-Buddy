package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers();

    public User getUserById(Integer id) throws Exception;

    public User getUserByEmail(String email) throws Exception;

    public User addUser(User user) throws Exception;

    public void deleteUserById(Integer id);
}
