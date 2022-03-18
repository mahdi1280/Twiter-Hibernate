package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.entity.model.TwitterModel;
import ir.maktab.twiter.service.twitter.TwitterService;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TwitterFrame extends JFrame implements ActionListener {

    private boolean flag=true;
    private final Users users;
    private final JPanel panel = new JPanel();
    private final JTable jTable;
    private final TwitterModel twitterModel;
    private final TwitterService twitterService = new TwitterService();
    private final JScrollPane scrollPane;
    private final JButton createTwit = new JButton("createTwit");
    private final JButton createComment = new JButton("createComment");
    private final JButton deleteTwit = new JButton("delete twit");
    private final JButton showComments = new JButton("showComments");
    private final JTextField search = new JTextField();
    private final JButton usersBtn = new JButton("Users");
    private final JButton updateTwitBtn = new JButton("update Twit");
    private final JButton like=new JButton("like");
    public TwitterFrame(Users users) throws SQLException {
        setFonts();
        createTwit.addActionListener(this);
        this.users = users;
        createComment.addActionListener(this);
        deleteTwit.addActionListener(this);
        showComments.addActionListener(this);
        twitterModel = new TwitterModel(twitterService.findAll());
        jTable = new JTable(twitterModel);
        scrollPane = new JScrollPane(jTable);
        panel.setLayout(new GridLayout(2, 5, 5, 5));
        panel.add(createTwit);
        search.addActionListener(this);
        panel.add(createComment);
        panel.add(deleteTwit);
        panel.add(like);
        like.addActionListener(this);
        panel.add(showComments);
        panel.add(search);
        panel.add(usersBtn);
        panel.add(updateTwitBtn);
        usersBtn.addActionListener(this);
        updateTwitBtn.addActionListener(this);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    private void setFonts() {
        createTwit.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 25));
        createTwit.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 25));
        deleteTwit.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 25));
        showComments.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 25));
        search.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 25));
        createComment.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 25));
        usersBtn.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 25));
        updateTwitBtn.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 25));

    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createTwit) {
            CreateTwit createTwit = new CreateTwit(users);
            try {
                twitterModel.setTwitters(twitterService.findAll());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == deleteTwit) {
            if (jTable.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "not selected");
            } else {
                int selectedRow = jTable.getSelectedRow();

                if (twitterModel.getTwitters().get(selectedRow).getUsers().getId() != users.getId()) {
                    JOptionPane.showMessageDialog(this, "in twitt male shoma nist");
                    return;
                }
                try {
                    twitterService.delete(twitterModel.getTwitters().get(selectedRow).getId());
                    JOptionPane.showMessageDialog(this, "success");
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
        if (e.getSource() == createComment) {
            if (jTable.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "not selected");
                return;
            }
            new CreateCommentFrame(users, twitterModel.getTwitters().get(jTable.getSelectedRow()));
        }
        if (e.getSource() == showComments) {
            if (jTable.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "not selected");
                return;
            }
            try {
                new CommentsFrame(twitterModel.getTwitters().get(jTable.getSelectedRow()), users);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (search == e.getSource()) {
            try {
                twitterModel.setTwitters(twitterService.search(search.getText()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (usersBtn == e.getSource()) {
            new UserFrame(users);
        }

        if (updateTwitBtn == e.getSource()) {
            if (jTable.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "not selected");
            } else {
                if (twitterModel.getTwitters().get(jTable.getSelectedRow()).getUsers().getId() != users.getId())
                    JOptionPane.showMessageDialog(this, "this twit not yours");
                else {
                    new UpdateTwitFrame(twitterModel.getTwitters().get(jTable.getSelectedRow()));
                    twitterModel.setTwitters(twitterService.findAll());
                }
            }
        }
        if(like== e.getSource()){
            if (jTable.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "not selected");
            }else{
                if (flag)
                 twitterService.like(twitterModel.getTwitters().get(jTable.getSelectedRow()));
                else{
                    twitterService.desLike(twitterModel.getTwitters().get(jTable.getSelectedRow()));
                }
                flag=!flag;
                twitterModel.setTwitters(twitterService.findAll());
            }
        }
    }
}
