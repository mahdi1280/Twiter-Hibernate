package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.entity.model.TwitterModel;
import ir.maktab.twiter.service.twitter.TwitterService;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TwitterFrame extends JFrame implements ActionListener {

    private Users users;
    private JPanel panel=new JPanel();
    private JTable jTable;
    private TwitterModel twitterModel;
    private TwitterService twitterService=new TwitterService();
    private JScrollPane scrollPane;
    private JButton createTwit=new JButton("createTwit");
    private JButton createComment=new JButton("createComment");
    private JButton deleteTwit=new JButton("delete twit");
    private JButton showComments=new JButton("showComments");
    private JTextField search=new JTextField();
    public TwitterFrame(Users users) throws SQLException {
        setFonts();
        createTwit.addActionListener(this);
        this.users=users;
        createComment.addActionListener(this);
        deleteTwit.addActionListener(this);
        showComments.addActionListener(this);
        twitterModel=new TwitterModel(twitterService.findAll());
        jTable=new JTable(twitterModel);
        scrollPane=new JScrollPane(jTable);
        panel.setLayout(new GridLayout(1,5,5,5));
        panel.add(createTwit);
        search.addActionListener(this);
        panel.add(createComment);
        panel.add(deleteTwit);
        panel.add(showComments);
        panel.add(search);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    private void setFonts() {
        createTwit.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
                createTwit.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
                deleteTwit.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
                showComments.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
                search.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
createComment.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==createTwit){
            CreateTwit createTwit=new CreateTwit(users);
            try {
                twitterModel.setTwitters(twitterService.findAll());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==deleteTwit){
            if(jTable.getSelectedRow()<0){
                JOptionPane.showMessageDialog(this,"not selected");
            }else{
                int selectedRow = jTable.getSelectedRow();

                if(twitterModel.getTwitters().get(selectedRow).getUsers().getId() != users.getId()) {
                    JOptionPane.showMessageDialog(this, "in twitt male shoma nist");
                    return;
                }
                try {
                    twitterService.delete(twitterModel.getTwitters().get(selectedRow).getId());
                    JOptionPane.showMessageDialog(this,"success");
                    try {
                        twitterModel.setTwitters(twitterService.findAll());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if(e.getSource()==createComment){
            if(jTable.getSelectedRow()<0) {
                JOptionPane.showMessageDialog(this, "not selected");
                return;
            }
                CreateCommentFrame createCommentFrame=new CreateCommentFrame(users,twitterModel.getTwitters().get(jTable.getSelectedRow()));

        }
        if(e.getSource()==showComments){
            if(jTable.getSelectedRow()<0) {
                JOptionPane.showMessageDialog(this, "not selected");
                return;
            }
            try {
                CommentsFrame commentsFrame=new CommentsFrame(twitterModel.getTwitters().get(jTable.getSelectedRow()),users);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if(search == e.getSource()){
            try {
                twitterModel.setTwitters(twitterService.search(search.getText()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
