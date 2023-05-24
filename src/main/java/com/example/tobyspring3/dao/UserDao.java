package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;

public class UserDao {

    ConnectionMaker connectionMaker;
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }


    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection conn = connectionMaker.makeConnection();

        PreparedStatement psmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        psmt.setString(1, user.getId());
        psmt.setString(2, user.getName());
        psmt.setString(3, user.getPassword());

        psmt.executeUpdate();
        psmt.close();
        conn.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next(); // ctrl + enter

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();

        pstmt.close();
        conn.close();

        return user;
    }

    /*public Connection getConnection() throws ClassNotFoundException, SQLException;
    {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        return conn;
    }*/


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker cm = new DConnectionMaker();
        UserDao userDao = new UserDao(cm);
        User user = new User();
        user.setId("8");
        user.setName("슈퍼콘");
        user.setPassword("1234");
        userDao.add(user);

        User selectedUser = userDao.get("8");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());

    }
}
