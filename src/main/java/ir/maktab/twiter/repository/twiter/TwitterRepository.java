package ir.maktab.twiter.repository.twiter;

import ir.maktab.twiter.connection.MyConnection;
import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.entity.Users;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TwitterRepository implements TwitterRepositoryInterface{
    @Override
    public List<Twitter> findAll() throws SQLException {
        String sql="select * from twitter where deleted = false";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Twitter> twitters=new ArrayList<>();
        while (resultSet.next()){
            twitters.add(createTwitter(resultSet));
        }
        return twitters;
    }

    @Override
    public List<Twitter> search(String text) throws SQLException {
        String sql="select * from twitter where description like ? and deleted=false";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,"%"+text+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Twitter> twitters=new ArrayList<>();
        while (resultSet.next()){
            twitters.add(createTwitter(resultSet));
        }
        return twitters;
    }

    @Override
    public void save(Twitter twitter) throws SQLException {
        String sql="insert into twitter(description, users_id, deleted) values (?,?,?);";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,twitter.getDescription());
        preparedStatement.setInt(2,twitter.getUsers().getId());
        preparedStatement.setBoolean(3,false);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql="update twitter set deleted = true where id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private Twitter createTwitter(ResultSet resultSet) throws SQLException {
        return new Twitter(resultSet.getInt("id")
        ,resultSet.getString("description")
        , Date.valueOf(LocalDate.now())
        ,new Users(resultSet.getInt("users_id"))
        ,resultSet.getBoolean("deleted"));

    }
}
