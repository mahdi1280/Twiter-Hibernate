package ir.maktab.twiter.entity.model;

import ir.maktab.twiter.entity.Comment;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CommentModel extends AbstractTableModel {
    private List<Comment > comments=new ArrayList<>();

    public CommentModel(List<Comment> comments){
        this.comments=comments;
    }

    @Override
    public int getRowCount() {
        return comments.size();
    }

    @Override
    public int getColumnCount() {
        return 4 ;
    }

    @Override
    public String getColumnName(int column) {
        if(column==0){
            return "id";
        }if(column==1){
            return "description";
        }if(column ==2){
            return "created date";
        }if(column==4){
            return "users";
        }
        return null;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Comment comment=comments.get(rowIndex);
       if(columnIndex==0){
           return comment.getId();
       }if(columnIndex==1){
            return comment.getDescription();
        }if(columnIndex ==2){
            return comment.getCreatedDate();
        }if(columnIndex==4){
            return comment.getUsers();
        }
       return null;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        fireTableDataChanged();
    }
}
