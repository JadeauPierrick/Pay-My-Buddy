package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Connection;
import com.paymybuddy.application.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectionServiceImpl implements ConnectionService{

    @Autowired
    private ConnectionRepository connectionRepository;

    @Override
    public List<Connection> getConnections(){
        return connectionRepository.findAll();
    }

    @Override
    public Optional<Connection> getConnectionById(Integer id){
        return connectionRepository.findById(id);
    }

    @Override
    public Connection addConnection(Connection connection){
        return connectionRepository.save(connection);
    }

    @Override
    public void deleteConnectionById(Integer id){
        connectionRepository.deleteById(id);
    }
}
