package db;

import entity.Restaurant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RestaurantRepositoryImpl implements RestaurantRepository {
    private RestaurantRepositoryImpl(){ }
    public static RestaurantRepositoryImpl instance=new RestaurantRepositoryImpl();
    public static RestaurantRepositoryImpl getInstance(){
        return instance;
    }

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
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

    @Override
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
            String sql = "update Restaurant set state = ?, rname=?,phone_number=?,admin_id=?,address=?,category=?,accept=? where id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, restaurant.getState());
            pstmt.setString(2, restaurant.getRname());
            pstmt.setString(3, restaurant.getNumber());
            pstmt.setInt(4, restaurant.getAdminId());
            pstmt.setString(5, restaurant.getAddress());
            pstmt.setString(6,restaurant.getCategory());
            pstmt.setInt(7, restaurant.getAccept());
            pstmt.setInt(8, restaurant.getId());

            int result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("RestaurantRepository.updateRestaurant SQLException 발생");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Restaurant> showAllRestaurant(int id) throws SQLException{
        List<Restaurant>restaurantList=new LinkedList<>();

        try{
            String SQL="SELECT * FROM RESTAURANT WHERE admin_id="+id;
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(SQL);
            rs= pstmt.executeQuery();
            while(rs.next()){
                int rid=rs.getInt("id");
                String state=rs.getString("state");
                String rname=rs.getString("rname");
                String number=rs.getString("phone_number");
                int adminId=rs.getInt("admin_id");
                String addr=rs.getString("address");
                String category=rs.getString("category");
                int accept=rs.getInt("accept");
                restaurantList.add(new Restaurant(rid, state, rname, number, adminId, addr, category,accept));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("showAllRestaurant 오류");
        }finally {
            DBUtil.close(conn, pstmt,rs);
        }

        return restaurantList;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into restaurant(state,rname, phone_number,admin_id,address,category, accept) values (?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, restaurant.getState());
            pstmt.setString(2, restaurant.getRname());
            pstmt.setString(3, restaurant.getNumber());
            pstmt.setInt(4, restaurant.getAdminId());
            pstmt.setString(5, restaurant.getAddress());
            pstmt.setString(6, restaurant.getCategory());
            pstmt.setInt(7, restaurant.getAccept());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL 오류");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
