package com.example.exampracticeapp.Repository.DBRepositories.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DataBaseAccess {
    private Connection connection;
    private String url;
    private String password;
    private String usernameDB;
    public DataBaseAccess(String url, String password, String usernameDB) {
        this.url = url;
        this.password = password;
        this.usernameDB = usernameDB;
    }
    public void createConnection() throws SQLException
    {
            connection=DriverManager.getConnection(url,usernameDB,password);
    }
    public PreparedStatement createStatement(String statement) throws SQLException
    {
        if(connection.isClosed())
        {createConnection();}
        return connection.prepareStatement(statement);
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernameDB() {
        return usernameDB;
    }

    public void setUsernameDB(String usernameDB) {
        this.usernameDB = usernameDB;
    }



}
