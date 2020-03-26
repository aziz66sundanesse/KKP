package gegana.table;

import chumbucket.table.TableModel;
import gegana.entitas.Bahan;
import java.util.ArrayList;

public class TableBahan extends TableModel {
    
    private java.util.ArrayList<Bahan> listBahan = new ArrayList<>();
    
    private static final String[] HEADER = {"NO", "KODE BAHAN", "NAMA", "JUMLAH STOCK", "HARGA"};

    public ArrayList<Bahan> getListBahan() {
        return listBahan;
    }

    public void setListBahan(ArrayList<Bahan> listBahan) {
        this.listBahan = listBahan;
    }
    
    public Bahan getBahan(int index) {
        return listBahan.get(index);
    }
    
    @Override
    public void clearAll() {
        listBahan.removeAll(listBahan);
    }

    @Override
    public void insert(Object object) {
        listBahan.add((Bahan) object);
        fireTableRowsInserted(getRowCount() -1, getRowCount() - 1);
    }

    @Override
    public void update(int index, Object object) {
        listBahan.set(index, (Bahan) object);
        fireTableRowsUpdated(index, index);
    }

    @Override
    public void delete(int index) {
        listBahan.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return listBahan.size();
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
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 3:
                return Integer.class;
            case 4:
                return Double.class;
            default:
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Bahan brg = listBahan.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return brg.getKode();
            case 2:
                return brg.getNama();
            case 3:
                return brg.getJumlah();
            case 4:
                return brg.getHarga();
            default:
                return null;
        }
    }
    
    public int getIndex(String kodeBahan) {
        int x = -1;
        for(int i = 0; i < listBahan.size(); i++) {
            String kode = listBahan.get(i).getKode();
            if(kodeBahan.equals(kode)) {
                x = i;
            }
        }
        return x;
    }
}
