package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id){
        return userRepository.findById(id);
    }

    @Override
    public User addUser(User user){
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }
}
