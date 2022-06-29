/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

/**
 *
 * @author programmer
 */
public class MysqlConnection {
    
    public static Connection getConnection() throws Exception{
        String dbRoot = "jdbc:mysql://";
        String hostname = "localhost:3306/";
        String dbName = "clinique";
        String dbUrl = dbRoot+hostname+dbName;
        
        String hostUsername = "root";
        String hostPassword = "";
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection MyConn = (Connection)DriverManager.getConnection(dbUrl,hostUsername,hostPassword);
        return MyConn;
    }
    
}
