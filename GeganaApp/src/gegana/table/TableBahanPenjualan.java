package gegana.table;

import chumbucket.table.TableModel;
import gegana.entitas.BahanPenjualan;
import gegana.entitas.BahanPenjualan;
import java.util.ArrayList;

public class TableBahanPenjualan extends TableModel {
    
    private java.util.ArrayList<BahanPenjualan> listBahanPenjualan = new ArrayList<>();
    
    private static final String[] HEADER = {"NO", "KODE BAHAN", "NAMA", "HARGA", "QTY", "TOTA"};

    public ArrayList<BahanPenjualan> getListBahanPenjualan() {
        return listBahanPenjualan;
    }

    public void setListBahanPenjualan(ArrayList<BahanPenjualan> listBahanPenjualan) {
        this.listBahanPenjualan = listBahanPenjualan;
    }
    
    public BahanPenjualan getBahanPenjualan(int index) {
        return listBahanPenjualan.get(index);
    }
    
    @Override
    public void clearAll() {
        listBahanPenjualan.removeAll(listBahanPenjualan);
    }

    @Override
    public void insert(Object object) {
        listBahanPenjualan.add((BahanPenjualan) object);
        fireTableRowsInserted(getRowCount() -1, getRowCount() - 1);
    }

    @Override
    public void update(int index, Object object) {
        listBahanPenjualan.set(index, (BahanPenjualan) object);
        fireTableRowsUpdated(index, index);
    }

    @Override
    public void delete(int index) {
        listBahanPenjualan.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return listBahanPenjualan.size();
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
        for (BahanPenjualan bahanPenjualan : listBahanPenjualan) {
            jumlah += bahanPenjualan.getJumlah();
        }
        return jumlah;
    }
    
    public double getTotalBelanja() {
        double jumlah = 0;
        for (BahanPenjualan bahanPenjualan : listBahanPenjualan) {
            jumlah += bahanPenjualan.getTotal();
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
        BahanPenjualan brg = listBahanPenjualan.get(rowIndex);
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
    
    public int getIndex(String kodeBahanPenjualan) {
        int x = -1;
        for(int i = 0; i < listBahanPenjualan.size(); i++) {
            String kode = listBahanPenjualan.get(i).getKode();
            if(kodeBahanPenjualan.equals(kode)) {
                x = i;
            }
        }
        return x;
    }
}
