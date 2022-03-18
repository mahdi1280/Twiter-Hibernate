package ir.maktab.twiter.repository.user;

import ir.maktab.twiter.connection.MyConnection;
import ir.maktab.twiter.customexception.NotFoundException;
import ir.maktab.twiter.entity.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsersRepository implements UserRepositoryInterFace{

    @Override
    public int save(Users users) throws SQLException {
        String sql="insert into users (username, password, deleted)\n" +
                "values (?,?,?);";
        PreparedStatement preparedStatement = MyConnection.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,users.getUsername());
        preparedStatement.setString(2,users.getPassword());
        preparedStatement.setBoolean(3,false);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    public void modify(Users users) throws SQLException {
        String sql="update users set username=?,password=?,deleted=? where id=?;";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,users.getUsername());
        preparedStatement.setString(2,users.getPassword());
        preparedStatement.setBoolean(3,users.isDeleted());
        preparedStatement.setInt(4,users.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void delete(int userId) throws SQLException {
        String sql="update users set deleted=? where id=?;";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(2,userId);
        preparedStatement.setBoolean(1,true);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public Users findById(int userId) throws SQLException {
        String sql="select * from users where id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return createUser(resultSet);
        throw new NotFoundException("user not found");
    }

    @Override
    public Users login(String username, String password) throws SQLException {
        String sql="select * from users where username = ? and password =? ";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return createUser(resultSet);
        throw new NotFoundException("user not found");
    }

    private Users createUser(ResultSet resultSet) throws SQLException {
        return new Users(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getBoolean("deleted")
                );
    }
}
