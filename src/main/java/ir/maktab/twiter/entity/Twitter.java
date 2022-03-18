package ir.maktab.twiter.entity;

import java.sql.Date;

public class Twitter {

    private int id;
    private String description;
    private Date createdDate;
    private Users users;
    private boolean deleted;

    public Twitter(int id) {
        this.id = id;
    }

    public Twitter(String description, Date createdDate, Users users) {
        this.description = description;
        this.createdDate = createdDate;
        this.users = users;
    }

    public Twitter(int id, String description, Date createdDate, Users users, boolean deleted) {
        this.id = id;
        this.description = description;
        this.createdDate = createdDate;
        this.users = users;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

