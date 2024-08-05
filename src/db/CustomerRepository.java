package db;

import entity.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {
    List<RestaurantDTO> selectAllRestaurant() throws SQLException;

    int insertReservation(Reservation reservation);

    ReservationDTO selectReservation(String phoneNumber);
}
