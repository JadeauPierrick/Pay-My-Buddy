package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private User user;

    @BeforeAll
    private void setUp() {
        userRepository.deleteAll();
        user = new User();
        user.setEmail("user@email.com");
        user.setPassword("user");
        user.setFirstName("userTestFirstName");
        user.setLastName("userTestLastName");
        userRepository.save(user);
    }

    @Test
    @Order(1)
    public void getUsersTest(){
        List<User> userList = userService.getUsers();
        assertThat(userList.size()).isEqualTo(1);
    }

    @Test
    @Order(2)
    public void getUserByEmailTest() throws Exception {
        User userTest = userService.getUserByEmail(user.getEmail());
        assertThat(userTest.getFirstName()).isEqualTo("userTestFirstName");
        assertThat(userTest.getLastName()).isEqualTo("userTestLastName");
    }

    @Test
    @Order(3)
    public void deleteUserById(){
        userService.deleteUserById(user.getId());
        List<User> userList = userService.getUsers();
        assertThat(userList.size()).isEqualTo(0);
    }
}
