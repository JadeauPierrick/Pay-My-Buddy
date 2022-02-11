package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Connection;

import java.util.List;
import java.util.Optional;

public interface ConnectionService {

    public List<Connection> getConnections();

    public Optional<Connection> getConnectionById(Integer id);

    public Connection addConnection(Connection connection);

    public void deleteConnectionById(Integer id);
}
