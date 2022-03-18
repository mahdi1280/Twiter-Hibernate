package ir.maktab.twiter.service.users;

import ir.maktab.twiter.entity.Users;

import java.sql.SQLException;

public interface UsersServiceInterface {

    Users login(String username,String password) throws SQLException;

    Users save(Users users) throws SQLException;
}
