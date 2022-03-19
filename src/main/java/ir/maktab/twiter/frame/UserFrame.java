package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.entity.model.CommentModel;
import ir.maktab.twiter.entity.model.UsersModel;
import ir.maktab.twiter.service.comment.CommentService;
import ir.maktab.twiter.service.users.UsersService;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserFrame extends JDialog implements ActionListener {

    private final Users users;
    private final UsersModel usersModel;
    private final UsersService usersService=new UsersService();
    private final JTable table;
    private final JScrollPane scrollPane;
    private final JButton delete=new JButton("delete");
    private final JButton followBtn=new JButton("follow");
    private final JPanel panel=new JPanel();
    private final JButton showFowler = new JButton(" show followers");
    public UserFrame(Users users) throws SQLException {
        this.setTitle("User");
        usersModel=new UsersModel(usersService.findAll(users));
        table=new JTable(usersModel);
        scrollPane=new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(1,3,5,5));
        panel.add(delete);
        panel.add(followBtn);
        panel.add(showFowler);
        followBtn.addActionListener(this);
        this.add(panel,BorderLayout.SOUTH);
        this.users=users;
        showFowler.addActionListener(this);
        delete.addActionListener(this);
        this.pack();
        this.setModal(true);
        this.setVisible(true);
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==delete){
            if(table.getSelectedRow()<0){
                JOptionPane.showMessageDialog(this,"not selected");
                return;
            }else{
                Users users = usersModel.getUsersCollections().get(table.getSelectedRow());
                try {
                    usersService.delete(users.getId());
                    usersModel.setUsersCollections(usersService.findAll(users));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if(e.getSource() == followBtn){
            if(table.getSelectedRow()<0){
                JOptionPane.showMessageDialog(this,"not selected");
                return;
            }
            else{
                Users users = usersModel.getUsersCollections().get(table.getSelectedRow());
                usersService.update(this.users,users);
            }
        }

        if(e.getSource() == showFowler){
            if(table.getSelectedRow()<0){
                JOptionPane.showMessageDialog(this,"not selected");
                return;
            }
            else {
                Users users = usersModel.getUsersCollections().get(table.getSelectedRow());
                new UserFollowersFrame(users);
            }
        }
    }
}
