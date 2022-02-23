package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Connection;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.ConnectionRepository;
import com.paymybuddy.application.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConnectionServiceTest {

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private User buddy;
    private Connection connection;

    @BeforeAll
    private void setUp(){
        connectionRepository.deleteAll();

        user = new User();
        user.setEmail("userconnection@email.com");
        user.setPassword("user");
        user.setFirstName("userTestFirstName");
        user.setLastName("userTestLastName");
        userRepository.save(user);

        buddy = new User();
        buddy.setEmail("buddyconnection@email.com");
        buddy.setPassword("buddy");
        buddy.setFirstName("buddyTestFirstName");
        buddy.setLastName("buddyTestLastName");
        userRepository.save(buddy);

        connection = new Connection();
        connection.setUser(user);
        connection.setBuddy(buddy);
    }

    @AfterAll
    private void tearDown(){
        userRepository.deleteById(user.getId());
        userRepository.deleteById(buddy.getId());
    }

    @Test
    @Order(1)
    public void addConnectionTest(){
        connectionService.addConnection(connection);
        assertThat(connection.getUser().getId()).isEqualTo(user.getId());
        assertThat(connection.getBuddy().getId()).isEqualTo(buddy.getId());
    }

    @Test
    @Order(2)
    public void getConnectionByIdTest(){
        Optional<Connection> con = connectionService.getConnectionById(connection.getId());
        assertThat(con.get().getId()).isEqualTo(connection.getId());
    }

    @Test
    @Order(3)
    public void deleteConnectionByIdTest(){
        connectionService.deleteConnectionById(connection.getId());
        List<Connection> connectionList = connectionService.getConnections();
        assertThat(connectionList.size()).isEqualTo(0);
    }
}
