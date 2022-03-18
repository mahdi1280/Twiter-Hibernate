package ir.maktab.twiter.service.users;

import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.repository.user.UsersRepository;

import java.sql.SQLException;

public class UsersService implements UsersServiceInterface{

    private final UsersRepository usersRepository=new UsersRepository();
    @Override
    public Users login(String username, String password) throws SQLException {
       return usersRepository.login(username,password);
    }

    @Override
    public Users save(Users users) throws SQLException {
        int save = usersRepository.save(users);
        return usersRepository.findById(save);
    }
}
