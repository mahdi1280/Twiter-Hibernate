package ir.maktab.twiter.service.twitter;

import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.repository.twiter.TwitterRepository;

import java.sql.SQLException;
import java.util.List;


public class TwitterService implements TwitterServiceInterface{

    private TwitterRepository twitterRepository=new TwitterRepository();
    @Override
    public List<Twitter> findAll() throws SQLException {
        return twitterRepository.findAll();
    }

    @Override
    public List<Twitter> search(String text) throws SQLException {
        return twitterRepository.search(text);
    }

    @Override
    public void save(Twitter twitter) throws SQLException {
        twitterRepository.save(twitter);
    }

    @Override
    public void delete(int id) throws SQLException {
        twitterRepository.delete(id);
    }
}
