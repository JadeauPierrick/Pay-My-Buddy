package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Connection;
import com.paymybuddy.application.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConnectionServiceImpl {

    @Autowired
    private ConnectionRepository connectionRepository;

    public Iterable<Connection> getConnections(){
        return connectionRepository.findAll();
    }

    public Optional<Connection> getConnectionById(Integer id){
        return connectionRepository.findById(id);
    }

    public Connection addConnection(Connection connection){
        return connectionRepository.save(connection);
    }

    public void deleteConnectionById(Integer id){
        connectionRepository.deleteById(id);
    }
}
