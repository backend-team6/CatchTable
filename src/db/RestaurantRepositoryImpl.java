package db;

import entity.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantRepositoryImpl implements RestaurantRepository {
    private RestaurantRepositoryImpl(){ }
    public static RestaurantRepositoryImpl instance=new RestaurantRepositoryImpl();
    public static RestaurantRepositoryImpl getInstance(){
        return instance;
    }

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public String getRestaurantState(int id) throws SQLException {
        String result="";

        try{
            String SQL="SELECT STATE FROM RESTAURANT WHERE ID="+id;
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            rs=pstmt.executeQuery();
            while(rs.next()){
                result =rs.getString("STATE");
            }

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("getRestaurantState 오류 발생");
        }finally {
            DBUtil.close(pstmt,conn,rs);
        }
        return result;
    }

    public int updateRestaurantState(int id, String state) throws SQLException{
        int result=0;
        try{
            String SQL="UPDATE RESTAURANT SET STATE=(?) WHERE ID=(?)";
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1, state);
            pstmt.setInt(2,id);
            result=pstmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("updateRestaurantState");
        }finally {
            DBUtil.close(conn, pstmt);
        }
        return result;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try {
            conn = DBUtil.getConnection();
            String sql = "update Restaurant set restaurantId = ? where state = ?, rname=?,phone_number=?,admin_id=?,address=?,category=?,accept=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, restaurant.getId());
            pstmt.setString(2, restaurant.getState());
            pstmt.setString(3, restaurant.getRname());
            pstmt.setString(4, restaurant.getNumber());
            pstmt.setInt(5, restaurant.getAdminId());
            pstmt.setString(6, restaurant.getAddress());
            pstmt.setInt(7, restaurant.getAccept());

            int result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
