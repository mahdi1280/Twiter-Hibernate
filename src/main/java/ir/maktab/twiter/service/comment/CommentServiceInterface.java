package ir.maktab.twiter.service.comment;

import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.entity.Twitter;

import java.sql.SQLException;
import java.util.List;

public interface CommentServiceInterface {
    void save(Comment comment) throws SQLException;

    List<Comment> findAll(Twitter twitter) throws SQLException;

    void delete(int id) throws SQLException;

    void update(Comment comment);

    void like(Comment comment);

    void desLike(Comment comment);

    Comment findById(int id);
}
