package gegana.table;

import chumbucket.table.TableModel;
import gegana.entitas.BahanPembelian;
import java.util.ArrayList;

public class TableBahanPembelian extends TableModel {
    
    private java.util.ArrayList<BahanPembelian> listBahanPembelian = new ArrayList<>();
    
    private static final String[] HEADER = {"NO", "KODE BAHAN", "NAMA", "HARGA", "QTY", "TOTA"};

    public ArrayList<BahanPembelian> getListBahanPembelian() {
        return listBahanPembelian;
    }

    public void setListBahanPembelian(ArrayList<BahanPembelian> listBahanPembelian) {
        this.listBahanPembelian = listBahanPembelian;
    }
    
    public BahanPembelian getBahanPembelian(int index) {
        return listBahanPembelian.get(index);
    }
    
    @Override
    public void clearAll() {
        listBahanPembelian.removeAll(listBahanPembelian);
    }

    @Override
    public void insert(Object object) {
        listBahanPembelian.add((BahanPembelian) object);
        fireTableRowsInserted(getRowCount() -1, getRowCount() - 1);
    }

    @Override
    public void update(int index, Object object) {
        listBahanPembelian.set(index, (BahanPembelian) object);
        fireTableRowsUpdated(index, index);
    }

    @Override
    public void delete(int index) {
        listBahanPembelian.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return listBahanPembelian.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public String getColumnName(int column) {
        return HEADER[column];
    }
    
    public int getJumlahBahan() {
        int jumlah = 0;
        for (BahanPembelian bahanPembelian : listBahanPembelian) {
            jumlah += bahanPembelian.getJumlah();
        }
        return jumlah;
    }
    
    public double getTotalBelanja() {
        double jumlah = 0;
        for (BahanPembelian bahanPembelian : listBahanPembelian) {
            jumlah += bahanPembelian.getTotal();
        }
        return jumlah;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 3:
            case 4:
            case 5:
                return Double.class;
            default:
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BahanPembelian brg = listBahanPembelian.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return brg.getKode();
            case 2:
                return brg.getNama();
            case 3:
                return brg.getHarga();
            case 4:
                return brg.getJumlah();
            case 5:
                return brg.getTotal();
            default:
                return null;
        }
    }
    
    public int getIndex(String kodeBahanPembelian) {
        int x = -1;
        for(int i = 0; i < listBahanPembelian.size(); i++) {
            String kode = listBahanPembelian.get(i).getKode();
            if(kodeBahanPembelian.equals(kode)) {
                x = i;
            }
        }
        return x;
    }
}
