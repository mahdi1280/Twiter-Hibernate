package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.entity.model.CommentModel;
import ir.maktab.twiter.entity.model.UsersModel;
import ir.maktab.twiter.service.comment.CommentService;
import ir.maktab.twiter.service.users.UsersService;

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

    public UserFrame(Users users) throws SQLException {
        this.setTitle("User");
        usersModel=new UsersModel(usersService.findAll(users));
        table=new JTable(usersModel);
        scrollPane=new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(delete,BorderLayout.SOUTH);
        this.users=users;
        delete.addActionListener(this);
        this.pack();
        this.setModal(true);
        this.setVisible(true);
    }

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
    }
}
