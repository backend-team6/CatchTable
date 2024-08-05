package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {
    private AdminRepositoryImpl() { }
    public static AdminRepositoryImpl instance = new AdminRepositoryImpl();
    public static AdminRepositoryImpl getInstance() {
        return instance;
    }

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public boolean registerUser(String username, String password) throws SQLException {
        boolean result = false;
        try {
            String SQL = "INSERT INTO ADMIN (login_id, PASSWORD) VALUES (?, ?)";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int rowsAffected = pstmt.executeUpdate();
            result = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("registerUser 오류 발생");
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return result;
    }

    public Integer loginUser(String username, String password) throws SQLException {
        int result = -1;
        try {
            String SQL = "SELECT admin_id FROM ADMIN WHERE login_id = ? AND PASSWORD = ?";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("loginUser 오류 발생");
        } finally {
            DBUtil.close(pstmt, conn, rs);
        }
        return result;
    }
}