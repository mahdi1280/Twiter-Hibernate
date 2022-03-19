package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.service.comment.CommentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CreateCommentFrame extends JDialog implements ActionListener {

    private final Comment comment;
    private Users users;
    private Twitter twitter;
    private final CommentService commentService = new CommentService();
    private final JTextArea jTextArea = new JTextArea();
    private final JButton create = new JButton("create");

    public CreateCommentFrame(Users users, Twitter twitter, Comment comment) {
        this.comment = comment;
        setFonts();
        create.addActionListener(this);
        this.users = users;
        this.twitter = twitter;
        this.add(jTextArea, BorderLayout.CENTER);
        this.add(create, BorderLayout.SOUTH);
        this.pack();
        this.setModal(true);
        this.setVisible(true);
    }

    private void setFonts() {
        create.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
        jTextArea.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==create){
            if(jTextArea.getText().length()<=0)
                JOptionPane.showMessageDialog(this,"is empty");
            else{
                try {
                    Comment comment;
                    if (this.comment != null) {
                        comment = new Comment(jTextArea.getText(), Date.valueOf(LocalDate.now()), twitter, users);
                        comment.getComments().add(this.comment);
                    }
                    else{
                        comment =  new Comment(jTextArea.getText(), Date.valueOf(LocalDate.now()), twitter, users);
                    }
                    commentService.save(comment);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                this.setVisible(false);
            }
        }
    }
}
