package entity;

public class Reservation {
    private int id;

    private int restaurantId;

    private int count;

    private String name;

    private String phoneNumber;

    public Reservation(int id, int restaurantId, int count, String name, String phoneNumber) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.count = count;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
