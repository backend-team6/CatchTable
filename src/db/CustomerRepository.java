package db;

import entity.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {
    List<RastaurantDTO> selectAllRastaurant() throws SQLException;

    int insertReservation(Reservation reservation);

    ReservationDTO selectReservation(String phoneNumber);
}
