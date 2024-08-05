package db;

import entity.Restaurant;
import java.sql.SQLException;
import java.util.List;

public interface RestaurantRepository {

    String getRestaurantState(int id) throws SQLException;
    int updateRestaurantState(int id, String state) throws SQLException;
    void updateRestaurant(Restaurant restaurant) throws SQLException;
    List<Restaurant> showAllRestaurant(int id) throws SQLException;
    void saveRestaurant(Restaurant restaurant);
}
