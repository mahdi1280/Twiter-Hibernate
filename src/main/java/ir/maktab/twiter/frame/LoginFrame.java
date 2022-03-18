package ir.maktab.twiter.frame;

import ir.maktab.twiter.customexception.NotFoundException;
import ir.maktab.twiter.entity.Users;
import ir.maktab.twiter.service.users.UsersService;
import ir.maktab.twiter.service.users.UsersServiceInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginFrame extends JFrame implements ActionListener {

    private final UsersServiceInterface usersServiceInterface=new UsersService();
    private JLabel usernameLbl=new JLabel("username: ");
    private JLabel passwordLbl=new JLabel("password: ");

    private JTextField usernameTxt = new JTextField();
    private JTextField passwordTxt = new JTextField();

    private JButton login = new JButton("login");
    private JButton create = new JButton("create");

    public LoginFrame(){
        this.setLayout(new GridLayout(3,2,4,4));
        setFont();
        login.addActionListener(this);
        create.addActionListener(this);
        this.add(usernameLbl);
        this.add(usernameTxt);
        this.add(passwordLbl);
        this.add(passwordTxt);
        this.add(login);
        this.add(create);
        this.pack();
        this.setVisible(true);
    }

    private void setFont() {
        usernameLbl.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
        passwordLbl.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
        usernameTxt.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
        passwordTxt.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
        login.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
        create.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login){
            try {
                Users login = usersServiceInterface.login(usernameTxt.getText(), passwordTxt.getText());
                JOptionPane.showMessageDialog(this,"welcome");
                TwitterFrame twitterFrame=new TwitterFrame(login);
                this.setVisible(false);
            } catch (SQLException| NotFoundException ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
            }
        }
        if(e.getSource() == create){
            try {
                Users save = usersServiceInterface.save(createUser());
                JOptionPane.showMessageDialog(this,"welcom");
                TwitterFrame twitterFrame=new TwitterFrame(save);
                this.setVisible(false);
            } catch (SQLException ex) {

                ex.printStackTrace();
            }
        }
    }

    private Users createUser() {
        return new Users(usernameTxt.getText(),passwordTxt.getText());
    }
}
