package ir.maktab.twiter.entity;

import antlr.collections.impl.LList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity{

    private String description;
    private Date createdDate;
    private boolean deleted;
    private int likes;
    @ManyToOne(fetch = FetchType.LAZY)
    private Twitter twitter;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Comment> comments=new ArrayList<>();

    public Comment(String description, Date createdDate, Twitter twitter, Users users) {
        this.description = description;
        this.createdDate = createdDate;
        this.twitter = twitter;
        this.users = users;
    }
}
