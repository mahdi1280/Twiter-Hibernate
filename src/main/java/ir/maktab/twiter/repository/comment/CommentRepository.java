package ir.maktab.twiter.repository.comment;

import ir.maktab.twiter.connection.MyConnection;
import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.entity.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository implements CommentRepositoryInterface{
    @Override
    public void save(Comment comment) throws SQLException {
        String sql="insert into comment (description, users_id, created_date, twitter_id, deleted)\n" +
                "values (?,?,?,?,?);";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,comment.getDescription());
        preparedStatement.setInt(2,comment.getUsers().getId());
        preparedStatement.setDate(3,comment.getCreatedDate());
        preparedStatement.setInt(4,comment.getTwitter().getId());
        preparedStatement.setBoolean(5,comment.isDeleted());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public List<Comment> findAll(Twitter twitter) throws SQLException {
        String sql="select * from comment where twitter_id = ? and deleted = false ";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,twitter.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Comment> comments=new ArrayList<>();
        while (resultSet.next())
            comments.add(createComment(resultSet));
        return comments;
    }

    @Override
    public void deleted(int id) throws SQLException {
        String sql="update comment set deleted=true where id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private Comment createComment(ResultSet resultSet) throws SQLException {

        return new Comment(resultSet.getInt("id"),resultSet.getString("description"),
                resultSet.getDate("created_date")
        ,new Twitter(resultSet.getInt("twitter_id"))
        ,new Users(resultSet.getInt("users_id"))
        ,resultSet.getBoolean("deleted"));
    }
}
