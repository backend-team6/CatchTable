package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String url="jdbc:mysql://127.0.0.1:3306/catch_table";
    public static final String user="devcourse";
    public static final String password="";

    public static Connection getConnection() throws SQLException{
        Connection conn=null;
        try {
            conn= DriverManager.getConnection(url, user,password);
        } catch (SQLException e) {
            System.out.println("커넥션 생성 오류");
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void close(AutoCloseable... closeables){ //개수 제한 없이 담아서 배열에 담아주는애
        for(AutoCloseable closeable : closeables){
            if(closeable != null){
                try{
                    closeable.close();
                }catch (Exception e){
                    System.out.println("close 하다가 에러나는거 아직까지 못보긴 했음.");
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
