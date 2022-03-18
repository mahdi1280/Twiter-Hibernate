package ir.maktab.twiter.entity.model;

import ir.maktab.twiter.entity.Users;

import javax.swing.table.AbstractTableModel;
import java.util.Collection;
import java.util.List;

public class UsersModel extends AbstractTableModel {

    private List<Users> usersCollections;

    public UsersModel(List<Users> users){
        this.usersCollections=users;
    }

    @Override
    public int getRowCount() {
        return usersCollections.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column){
        if(column==0){
            return "id";
        }
        return "user name";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Users users=usersCollections.get(rowIndex);
        if(columnIndex==0)
            return users.getId();
        return users.getUsername();
    }

    public List<Users> getUsersCollections() {
        return usersCollections;
    }

    public void setUsersCollections(List<Users> usersCollections) {
        this.usersCollections = usersCollections;
        fireTableDataChanged();
        fireTableDataChanged();
    }
}
