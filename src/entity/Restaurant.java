package entity;

public class Restaurant {
    private int id;

    private String state;

    private String rname;

    private int number;

    private int adminId;

    private String address;

    private String category;

    private int accept;

    public Restaurant(int id, String state, String rname, int number, int adminId, String address, String category, int accept) {
        this.id = id;
        this.state = state;
        this.rname = rname;
        this.number = number;
        this.adminId = adminId;
        this.address = address;
        this.category = category;
        this.accept = accept;
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

    public int getNumber() {
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
}
