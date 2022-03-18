package ir.maktab.twiter.service.comment;

import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.repository.comment.CommentRepository;

import java.sql.SQLException;
import java.util.List;

public class CommentService implements CommentServiceInterface{


    private CommentRepository commentRepository=new CommentRepository();
    @Override
    public void save(Comment comment) throws SQLException {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll(Twitter twitter) throws SQLException {
        return commentRepository.findAll(twitter);
    }

    @Override
    public void delete(int id) throws SQLException {
        commentRepository.deleted(id);
    }

    @Override
    public void update(Comment comment) {
        commentRepository.update(comment);
    }

    @Override
    public void like(Comment comment) {
        commentRepository.like(comment);
    }

    @Override
    public void desLike(Comment comment) {
        commentRepository.desLike(comment);
    }
}
