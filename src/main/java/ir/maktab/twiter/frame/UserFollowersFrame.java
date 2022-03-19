package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.entity.model.UsersModel;
import ir.maktab.twiter.service.users.UsersService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserFollowersFrame extends JDialog {

    private final Users users;
    private final UsersModel usersModel;
    private final UsersService usersService=new UsersService();
    private final JTable table;
    private final JScrollPane scrollPane;

    public UserFollowersFrame(Users users) throws SQLException {
        this.setTitle("follower");
        usersModel=new UsersModel(usersService.login(users.getUsername(),users.getPassword()).getFollowers());
        table=new JTable(usersModel);
        scrollPane=new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
        this.users=users;
        this.pack();
        this.setModal(true);
        this.setVisible(true);
    }
}
