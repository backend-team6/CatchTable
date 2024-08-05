package db;

import entity.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private CustomerRepositoryImpl() {
    }

    public static CustomerRepositoryImpl instance = new CustomerRepositoryImpl();

    public static CustomerRepository getInstance() {
        return instance;
    }

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;


    @Override
    public List<RestaurantDTO> selectAllRestaurant() throws SQLException {
        List<RestaurantDTO> list = new ArrayList<>();
        conn = DBUtil.getConnection();

        try {
            String sql = "SELECT ID, STATE, RNAME, phone_number, ADDRESS, CATEGORY, ACCEPT FROM restaurant";

            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(makeRestaurantDTO(rs));
            }
        } catch (SQLException ex) {
            System.out.println("select error");
            throw ex;
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return list;
    }

    @Override
    public int insertReservation(Reservation reservation) {
        int result = 0;
        try {
            String sql = "INSERT INTO reservation(restaurant_id, count, name, phoneNumber) VALUES (?, ?, ?, ?)";
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservation.getRestaurantId());
            pstmt.setInt(2, reservation.getCount());
            pstmt.setString(3, reservation.getName());
            pstmt.setString(4, reservation.getPhoneNumber());
            result = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("insert error");
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return result;
    }

    @Override
    public ReservationDTO selectReservation(String phoneNumber) {
        ReservationDTO reservationDTO = null;

        try {
            String sql = "select a.id, b.rname, a.name from reservation a join restaurant b on a.restaurant_id = b.id where a.phoneNumber = " + phoneNumber;
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                reservationDTO = makeReservationDTO(rs, phoneNumber);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("select error");
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return reservationDTO;
    }

    private RestaurantDTO makeRestaurantDTO(ResultSet rs) throws SQLException {
        RestaurantDTO reservationDTO = new RestaurantDTO();
        reservationDTO.setId(rs.getInt("id"));
        reservationDTO.setState(rs.getString("state"));
        reservationDTO.setRname(rs.getString("rname"));
        reservationDTO.setNumber(rs.getString("phone_number"));
        reservationDTO.setAddress(rs.getString("address"));
        reservationDTO.setCategory(rs.getString("category"));
        reservationDTO.setAccept(rs.getInt("accept"));
        return reservationDTO;
    }

    private ReservationDTO makeReservationDTO(ResultSet rs, String phoneNumber) throws SQLException {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(rs.getInt("a.id"));
        reservationDTO.setRname(rs.getString("b.rname"));
        reservationDTO.setName(rs.getString("a.name"));
        reservationDTO.setPhoneNumber(phoneNumber);
        return reservationDTO;
    }
}
