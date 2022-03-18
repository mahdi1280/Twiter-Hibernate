package ir.maktab.twiter.repository.user;

import ir.maktab.twiter.entity.Users;

import java.sql.SQLException;
import java.util.List;

public interface UserRepositoryInterFace {

    int save(Users users) throws SQLException;

    void modify(Users users) throws SQLException;

    void delete(int userId) throws SQLException;

    List<Users> findAll();

    List<Users> findAll(Users users);

    Users findById(int userId) throws SQLException;

    Users login(String username,String password) throws SQLException;
}
