package ir.maktab.twiter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity{

    private String description;
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Twitter twitter;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
    private boolean deleted;
    private int likes;

    public Comment(String description, Date createdDate, Twitter twitter, Users users) {
        this.description = description;
        this.createdDate = createdDate;
        this.twitter = twitter;
        this.users = users;
    }
}
