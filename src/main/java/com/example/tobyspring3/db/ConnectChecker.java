package com.example.tobyspring3.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectChecker {

    public void check() throws SQLException, ClassNotFoundException {

        Connection conn = getConnection();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show databases");

        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectChecker cc = new ConnectChecker();
        cc.check();

//        cc.add();

        cc.select();
    }

    public void add() throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();

        PreparedStatement pstmt = conn.prepareStatement("insert into users(name, password) values(?, ?)");
        //pstmt.setString(1, "1");
        pstmt.setString(1, "이강인");
        pstmt.setString(2, "12345678");
        pstmt.executeUpdate();
    }

    public void select() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        rs = st.getResultSet();
        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String password = rs.getString(3);
            System.out.printf("%s %s %s\n", id, name, password);
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        return conn;
    }
}
