package ir.maktab.twiter.service.users;

import ir.maktab.twiter.entity.Users;

import java.sql.SQLException;
import java.util.List;

public interface UsersServiceInterface {

    Users login(String username,String password) throws SQLException;

    Users save(Users users) throws SQLException;

    List<Users> findAll(Users users);

    void delete(int id) throws SQLException;
}
