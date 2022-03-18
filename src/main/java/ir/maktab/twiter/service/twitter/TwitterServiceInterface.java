package ir.maktab.twiter.service.twitter;

import ir.maktab.twiter.entity.Twitter;

import java.sql.SQLException;
import java.util.List;

public interface TwitterServiceInterface {

    List<Twitter> findAll() throws SQLException;

    List<Twitter> search(String text) throws SQLException;
    void save(Twitter twitter) throws SQLException;

    void delete(int id) throws SQLException;

    void update(Twitter twitter);
}
