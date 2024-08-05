package db;

public class ReservationDTO {
    int id; //예약번호
    String rname; //식당이름
    int count;
    String name; //예약자 이름
    String phoneNumber;

    public void setId(int id) {
        this.id = id;
    }

    public void setRname(String rname) {
        this.rname = rname;
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

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", rname='" + rname + '\'' +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
