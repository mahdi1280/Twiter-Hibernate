package ir.maktab.twiter.repository.twiter;

import ir.maktab.twiter.entity.Twitter;

import java.sql.SQLException;
import java.util.List;

public interface TwitterRepositoryInterface {

    List<Twitter> findAll() throws SQLException;

    List<Twitter> search(String text) throws SQLException;

    void save(Twitter twitter) throws SQLException;

    void delete(int id) throws SQLException;
}
