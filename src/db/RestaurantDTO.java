package db;

public class RestaurantDTO {
    int id;
    String state;
    String rname;
    String number;
    String address;
    String category;
    int accept;

    public void setId(int id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", rname='" + rname + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", accept=" + accept +
                '}';
    }
}
