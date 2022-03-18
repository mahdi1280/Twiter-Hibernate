package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Twitter;
import ir.maktab.twiter.service.twitter.TwitterService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTwitFrame extends JDialog implements ActionListener {

    private final TwitterService twitterService=new TwitterService();
    private final Twitter twitter;

    private JTextArea textField=new JTextArea();

    private JButton create =new JButton("create");

    public UpdateTwitFrame(Twitter twitter){
        setFonts();
        create.addActionListener(this);
        this.twitter=twitter;
        textField.setText(twitter.getDescription());
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
                twitter.setDescription(textField.getText());
                twitterService.update(twitter);
                this.setVisible(false);
        }
    }
}
