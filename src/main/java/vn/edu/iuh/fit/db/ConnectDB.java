package vn.edu.iuh.fit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static ConnectDB instance;
    private Connection connect;
    private ConnectDB() {
        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mydb","root","sapassword");
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
    }
}
public synchronized static ConnectDB getInstance(){
    if(instance == null){
        instance = new ConnectDB();
    }
    return instance;
}
public Connection getConnection(){
    return connect;
}
}
