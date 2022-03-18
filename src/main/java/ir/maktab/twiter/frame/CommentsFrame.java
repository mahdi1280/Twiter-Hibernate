package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.entity.model.CommentModel;
import ir.maktab.twiter.service.comment.CommentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CommentsFrame extends JDialog implements ActionListener {

    private Twitter twitter;
    private Users users;
    private CommentModel commentModel;
    private CommentService commentService=new CommentService();
    private JTable table;
    private JScrollPane scrollPane;
    private JButton delete=new JButton("delete");
    public CommentsFrame(Twitter twitter,Users users) throws SQLException {
        commentModel=new CommentModel(commentService.findAll(twitter));
        table=new JTable(commentModel);
        scrollPane=new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(delete,BorderLayout.SOUTH);
        this.users=users;
        this.twitter = twitter;
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
                Comment comment = commentModel.getComments().get(table.getSelectedRow());
                if(comment.getUsers().getId() != users.getId()){
                    JOptionPane.showMessageDialog(this,"in comment male shoma nist");
                    return;
                }

                try {
                    commentService.delete(comment.getId());
                    commentModel.setComments(commentService.findAll(twitter));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
