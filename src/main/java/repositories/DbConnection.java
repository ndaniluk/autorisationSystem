package repositories;

import models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    public static List<User> userDB = new ArrayList<User>(); //TODO zamien na baze

    public static void connect() {
        Connection con = null;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            //Creating the connection with HSQLDB
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "SA", "");
            if (con != null) {
                System.out.println("Connection created successfully");

            } else {
                System.out.println("Problem with creating connection");
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}

//    CREATE TABLE users (
//        id INT NOT NULL,
//        username VARCHAR(50) NOT NULL,
//    password VARCHAR(50) NOT NULL,
//    isPremium BOOLEAN NOT NULL,
//    email VARCHAR(50) NOT NULL,
//    PRIMARY KEY (id)
//);