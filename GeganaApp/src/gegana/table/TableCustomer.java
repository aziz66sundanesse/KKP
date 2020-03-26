package gegana.table;

import chumbucket.table.TableModel;
import gegana.entitas.Customer;
import java.util.ArrayList;

public class TableCustomer extends TableModel {
    
    private java.util.ArrayList<Customer> listCustomer = new ArrayList<>();
    
    private static final String[] HEADER = {"NO", "KODE CUSTOMER", "NAMA", "ALAMAT", "NO TELP"};

    public ArrayList<Customer> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(ArrayList<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }
    
    public Customer getCustomer(int index) {
        return listCustomer.get(index);
    }
    
    @Override
    public void clearAll() {
        listCustomer.removeAll(listCustomer);
    }

    @Override
    public void insert(Object object) {
        listCustomer.add((Customer) object);
        fireTableRowsInserted(getRowCount() -1, getRowCount() - 1);
    }

    @Override
    public void update(int index, Object object) {
        listCustomer.set(index, (Customer) object);
        fireTableRowsUpdated(index, index);
    }

    @Override
    public void delete(int index) {
        listCustomer.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return listCustomer.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public String getColumnName(int column) {
        return HEADER[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer brg = listCustomer.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return brg.getKode();
            case 2:
                return brg.getNama();
            case 3:
                return brg.getAlamat();
            case 4:
                return brg.getNoTelp();
            default:
                return null;
        }
    }
    
    public int getIndex(String kodeCustomer) {
        int x = -1;
        for(int i = 0; i < listCustomer.size(); i++) {
            String kode = listCustomer.get(i).getKode();
            if(kodeCustomer.equals(kode)) {
                x = i;
            }
        }
        return x;
    }
}
