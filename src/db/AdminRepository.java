package db;

import java.sql.SQLException;

public interface AdminRepository {


    boolean registerUser(String username, String password) throws SQLException;


    Integer loginUser(String username, String password) throws SQLException;
}