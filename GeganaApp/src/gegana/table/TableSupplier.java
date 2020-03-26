package gegana.table;

import chumbucket.table.TableModel;
import gegana.entitas.Supplier;
import java.util.ArrayList;

public class TableSupplier extends TableModel {
    
    private java.util.ArrayList<Supplier> listSupplier = new ArrayList<>();
    
    private static final String[] HEADER = {"NO", "KODE SUPPLIER", "NAMA", "ALAMAT", "NO TELP"};

    public ArrayList<Supplier> getListSupplier() {
        return listSupplier;
    }

    public void setListSupplier(ArrayList<Supplier> listSupplier) {
        this.listSupplier = listSupplier;
    }
    
    public Supplier getSupplier(int index) {
        return listSupplier.get(index);
    }
    
    @Override
    public void clearAll() {
        listSupplier.removeAll(listSupplier);
    }

    @Override
    public void insert(Object object) {
        listSupplier.add((Supplier) object);
        fireTableRowsInserted(getRowCount() -1, getRowCount() - 1);
    }

    @Override
    public void update(int index, Object object) {
        listSupplier.set(index, (Supplier) object);
        fireTableRowsUpdated(index, index);
    }

    @Override
    public void delete(int index) {
        listSupplier.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return listSupplier.size();
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
        Supplier brg = listSupplier.get(rowIndex);
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
    
    public int getIndex(String kodeSupplier) {
        int x = -1;
        for(int i = 0; i < listSupplier.size(); i++) {
            String kode = listSupplier.get(i).getKode();
            if(kodeSupplier.equals(kode)) {
                x = i;
            }
        }
        return x;
    }
}
