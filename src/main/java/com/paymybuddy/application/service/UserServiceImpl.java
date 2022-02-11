package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Account;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.PasswordCallback;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) throws Exception{
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            return user.get();
        }else {
            throw new Exception("The user was not found");
        }
    }

    @Override
    public User getUserByEmail(String email) throws Exception{
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()){
            return user.get();
        }else {
            throw new Exception("The user was not found");
        }
    }

    @Override
    public User addUser(User user) throws Exception{
        Optional<User> currentUser = userRepository.findByEmail(user.getEmail());

        if (currentUser.isPresent()){
            throw new Exception("This email is already used");
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User newUser = userRepository.save(user);

            Account account = new Account();
            account.setBalance(0);

            newUser.addAccount(account);
            accountRepository.save(account);

            return newUser;
        }
    }

    @Override
    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }
}
