package ir.maktab.twiter.entity.model;

import ir.maktab.twiter.entity.Twitter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TwitterModel extends AbstractTableModel {
    private List<Twitter> twitters=new ArrayList<>();

    public TwitterModel(List<Twitter> twitters) {
        this.twitters = twitters;
    }

    @Override
    public int getRowCount() {
        return twitters.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }


    @Override
    public String getColumnName(int column) {
        if(column==0){
            return "id";
        }if(column==1){
            return "description";
        }if(column==2){
            return "created date";
        }if(column==3){
            return "user";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Twitter twitter=twitters.get(rowIndex);
        if(columnIndex==0){
            return twitter.getId();
        }if(columnIndex==1){
            return twitter.getDescription();
        }if(columnIndex==2){
            return twitter.getCreatedDate();
        }if(columnIndex==3){
            return twitter.getUsers().getId();
        }
        return null;
    }

    public void setTwitters(List<Twitter> twitters) {
        this.twitters = twitters;
        fireTableDataChanged();
    }

    public List<Twitter> getTwitters() {
        return twitters;
    }
}
