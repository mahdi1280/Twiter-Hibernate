package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.entity.model.CommentModel;
import ir.maktab.twiter.service.comment.CommentService;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CommentsFrame extends JDialog implements ActionListener {

    private boolean flag=true;
    private final Twitter twitter;
    private final Users users;
    private final CommentModel commentModel;
    private final CommentService commentService=new CommentService();
    private final JTable table;
    private final JScrollPane scrollPane;
    private final JButton delete=new JButton("delete");
    private final JButton updateComment=new JButton("update comment btn");
    private final JPanel panel = new JPanel();
    private final JButton likes = new JButton("likes");
    private final JButton createComment=new JButton("create comment");
    private final JButton showComment = new JButton("show Comment");
    private final Comment comment;
    public CommentsFrame(Twitter twitter,Users users,Comment comment) throws SQLException {
        this.comment=comment;
        if(comment==null)
            commentModel=new CommentModel(commentService.findAll(twitter));
        else
            commentModel=new CommentModel(commentService.findById(comment.getId()).getComments());
        table=new JTable(commentModel);
        scrollPane=new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(1, 5, 5, 5) );
        panel.add(delete);
        panel.add(updateComment);
        updateComment.addActionListener(this);
        panel.add(likes);
        panel.add(createComment);
        panel.add(showComment);
        showComment.addActionListener(this);
        createComment.addActionListener(this);
        likes.addActionListener(this);
        this.add(panel,BorderLayout.SOUTH);
        this.users=users;
        this.twitter = twitter;
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
        if(updateComment == e.getSource()){
            if(table.getSelectedRow()<0) {
                JOptionPane.showMessageDialog(this, "not selected");
                return;
            }
            else{
                Comment comment = commentModel.getComments().get(table.getSelectedRow());
                if(comment.getUsers().getId() != users.getId()){
                    JOptionPane.showMessageDialog(this,"in comment male shoma nist");
                    return;
                }
                try {
                    new UpdateComment(comment);
                    commentModel.setComments(commentService.findAll(twitter));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if(likes == e.getSource()){
            if(table.getSelectedRow()<0) {
                JOptionPane.showMessageDialog(this, "not selected");
                return;
            }else{
                if(flag){
                    commentService.like(commentModel.getComments().get(table.getSelectedRow()));
                }else{
                    commentService.desLike(commentModel.getComments().get(table.getSelectedRow()));
                }
                flag=!flag;
                commentModel.setComments(commentService.findAll(twitter));
            }
        }
        if(e.getSource()== createComment){
            if(table.getSelectedRow()<0) {
                JOptionPane.showMessageDialog(this, "not selected");
                return;
            }
            new CreateCommentFrame(users,twitter,commentModel.getComments().get(table.getSelectedRow()));
            commentModel.setComments(commentService.findAll(twitter));
        }
        if(e.getSource() == showComment){
            if(table.getSelectedRow()<0) {
                JOptionPane.showMessageDialog(this, "not selected");
                return;
            }

            new CommentsFrame(twitter,users,commentModel.getComments().get(table.getSelectedRow()));
        }

    }
}
