package ir.maktab.twiter.entity;

import javax.persistence.Entity;
import java.sql.Date;

public class Comment {

    private int id;
    private String description;
    private Date createdDate;
    private Twitter twitter;
    private Users users;
    private boolean deleted;

    public Comment(int id, String description, Date createdDate, Twitter twitter, Users users, boolean deleted) {
        this.id = id;
        this.description = description;
        this.createdDate = createdDate;
        this.twitter = twitter;
        this.users = users;
        this.deleted = deleted;
    }

    public Comment(String description, Date createdDate, Twitter twitter, Users users) {
        this.description = description;
        this.createdDate = createdDate;
        this.twitter = twitter;
        this.users = users;
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

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
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
