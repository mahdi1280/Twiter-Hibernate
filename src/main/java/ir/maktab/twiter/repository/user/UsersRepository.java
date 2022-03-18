package ir.maktab.twiter.repository.user;

import ir.maktab.twiter.customexception.NotFoundException;
import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.session.MySessionFactory;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class UsersRepository implements UserRepositoryInterFace{

    @Override
    public int save(Users users) throws SQLException {
        users.setDeleted(false);
        Session session = MySessionFactory.openSession();
        session.beginTransaction();
        session.save(users);
        session.getTransaction().commit();
        return users.getId();
    }

    @Override
    public void modify(Users users) {
        users.setDeleted(false);
        Session session = MySessionFactory.openSession();
        Users users1 = session.find(Users.class, users.getId());
        users1.setUsername(users.getUsername());
        users1.setPassword(users.getPassword());
        users1.setDeleted(users.isDeleted());
        session.beginTransaction();
        session.update(users1);
        session.getTransaction().commit();
    }

    @Override
    public void delete(int userId) throws SQLException {
        Session session = MySessionFactory.openSession();
        Users users = session.find(Users.class, userId);
        users.setDeleted(true);
        session.beginTransaction();
        session.update(users);
        session.getTransaction().commit();
    }

    @Override
    public List<Users> findAll() {
        Session session = MySessionFactory.openSession();
        return session.createQuery("select u from Users u where u.deleted=false",Users.class)
                .getResultList();
    }

    @Override
    public List<Users> findAll(Users users) {
        Session session = MySessionFactory.openSession();
        return session.createQuery("select u from Users u where u.deleted=false and u.id<>:userId",Users.class)
                .setParameter("userId",users.getId())
                .getResultList();
    }

    @Override
    public Users findById(int userId){
        Session session = MySessionFactory.openSession();
        Users users = session.find(Users.class, userId);
        if(users==null)
            throw new NotFoundException("user not found");
        return users;
    }

    @Override
    public Users login(String username, String password) {
        Session session = MySessionFactory.openSession();
        List<Users> resultList = session.createQuery("select u from Users u where u.username =:username and u.password =:password and u.deleted=false", Users.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        if(resultList==null || resultList.isEmpty())
            throw new NotFoundException("user not found");
        return resultList.get(0);
    }

}
