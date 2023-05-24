package com.example.tobyspring3.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        return null;
    }
}
