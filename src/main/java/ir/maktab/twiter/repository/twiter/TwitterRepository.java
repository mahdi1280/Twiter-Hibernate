package ir.maktab.twiter.repository.twiter;

import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.session.MySessionFactory;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class TwitterRepository implements TwitterRepositoryInterface{

    @Override
    public List<Twitter> findAll() throws SQLException {
        Session session = MySessionFactory.openSession();
        return session.createQuery("select t from Twitter t where t.deleted = false",Twitter.class)
                .getResultList();
    }

    @Override
    public List<Twitter> search(String text) {
        Session session = MySessionFactory.openSession();
        return session.createQuery("select t from Twitter t where t.description like :description and t.deleted=false",Twitter.class)
                .setParameter("description",text)
                .getResultList();
    }

    @Override
    public void save(Twitter twitter) throws SQLException {
        Session session = MySessionFactory.openSession();
        session.beginTransaction();
        session.save(twitter);
        session.getTransaction().commit();
    }

    @Override
    public void delete(int id) throws SQLException {
        Session session = MySessionFactory.openSession();
        Comment comment = session.find(Comment.class, id);
        comment.setDeleted(true);
        session.beginTransaction();
        session.update(comment);
        session.getTransaction().commit();
    }

}
