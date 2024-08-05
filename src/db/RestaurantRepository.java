package db;

import entity.Restaurant;

import java.sql.SQLException;

public interface RestaurantRepository {

    String getRestaurantState(int id) throws SQLException;

    int updateRestaurantState(int id, String state) throws SQLException;

    void updateRestaurant(Restaurant restaurant) throws SQLException;
}
