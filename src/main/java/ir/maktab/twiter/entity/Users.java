package ir.maktab.twiter.entity;

public class Users {

    private int id;
    private String username;
    private String password;
    private boolean deleted;

    public Users(int id) {
        this.id = id;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Users(int id, String username, String password, boolean deleted) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
