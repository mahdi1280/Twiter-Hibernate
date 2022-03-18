package ir.maktab.twiter.frame;

import ir.maktab.twiter.entity.Comment;
import ir.maktab.twiter.service.comment.CommentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateComment extends JDialog implements ActionListener {

    private final CommentService commentService=new CommentService();
    private final Comment comment;

    private JTextArea textField=new JTextArea();

    private JButton create =new JButton("create");

    public UpdateComment(Comment comment){
        setFonts();
        create.addActionListener(this);
        this.comment=comment;
        textField.setText(comment.getDescription());
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
            comment.setDescription(textField.getText());
            commentService.update(comment);
            this.setVisible(false);
        }
    }
}
