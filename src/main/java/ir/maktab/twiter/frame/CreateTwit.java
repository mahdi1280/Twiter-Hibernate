package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.service.twitter.TwitterService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CreateTwit extends JDialog implements ActionListener {

    private TwitterService twitterService=new TwitterService();
    private Users users;

    private JTextArea textField=new JTextArea();

    private JButton create =new JButton("create");

    public CreateTwit(Users users){
        setFonts();
        create.addActionListener(this);
        this.users=users;
        this.add(textField, BorderLayout.CENTER);
        this.add(create, BorderLayout.SOUTH);
        this.pack();
        this.setModal(true);
        this.setVisible(true);
    }

    private void setFonts() {
        textField.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
        create.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(textField.getText().length()>280 || textField.getText().length()==0){
            JOptionPane.showMessageDialog(this,"message not valid");
        }else {
            try {
                twitterService.save(new Twitter(textField.getText(), Date.valueOf(LocalDate.now()),users));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            this.setVisible(false);
        }
    }
}
