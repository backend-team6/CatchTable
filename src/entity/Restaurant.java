package entity;

public class Restaurant {
    private int id;

    private String state;

    private String rname;

    private String number;

    private int adminId;

    private String address;

    private String category;

    private int accept;

    public Restaurant(int id, String state, String rname, String number, int adminId, String address, String category, int accept) {
        this.id = id;
        this.state = state;
        this.rname = rname;
        this.number = number;
        this.adminId = adminId;
        this.address = address;
        this.category = category;
        this.accept = accept;
    }

    public Restaurant() {

    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getRname() {
        return rname;
    }

    public String getNumber() {
        return number;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public int getAccept() {
        return accept;
    }

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

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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
        return "Restaurant{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", rname='" + rname + '\'' +
                ", number='" + number + '\'' +
                ", adminId=" + adminId +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", accept=" + accept +
                '}';
    }
}
