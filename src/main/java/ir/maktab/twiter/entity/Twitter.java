package ir.maktab.twiter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Twitter extends BaseEntity {

    @Column(length = 280)
    private String description;
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
    private boolean deleted;
    private int likes;

    public Twitter(int id) {
        super(id);
    }

    public Twitter(String description, Date createdDate, Users users) {
        this.description = description;
        this.createdDate = createdDate;
        this.users = users;
    }
}

