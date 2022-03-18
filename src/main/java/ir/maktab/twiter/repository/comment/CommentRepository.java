package ir.maktab.twiter.repository.comment;

import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.session.MySessionFactory;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class CommentRepository implements CommentRepositoryInterface{

    @Override
    public void save(Comment comment) throws SQLException {
        Session session = MySessionFactory.openSession();
        session.beginTransaction();
        session.save(comment);
        session.getTransaction().commit();
    }

    @Override
    public List<Comment> findAll(Twitter twitter) throws SQLException {
        Session session = MySessionFactory.openSession();
        return session.createQuery("select c from Comment c where c.twitter.id=:twiterId and c.deleted=false",Comment.class)
                .setParameter("twiterId",twitter.getId())
                .getResultList();
    }

    @Override
    public void deleted(int id) throws SQLException {
        Session session = MySessionFactory.openSession();
        Comment comment = session.find(Comment.class, id);
        comment.setDeleted(true);
        session.beginTransaction();
        session.update(comment);
        session.getTransaction().commit();
    }

    @Override
    public void update(Comment comment) {
        Session session = MySessionFactory.openSession();
        Comment comment1 = session.find(Comment.class, comment.getId());
        comment1.setDescription(comment.getDescription());
        session.beginTransaction();
        session.update(comment1);
        session.getTransaction().commit();
    }

}
