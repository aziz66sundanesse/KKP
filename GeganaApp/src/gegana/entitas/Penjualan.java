package gegana.entitas;

import java.util.ArrayList;

public class Penjualan {
    
    private String kodeTransaksi;
    private String kodeCustomer;
    private double totalHarga;
    private ArrayList<DetailPenjualan> listPenjualan;
    private String dataStatus;

    public Penjualan() {
    }
    
    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getKodeCustomer() {
        return kodeCustomer;
    }

    public void setKodeCustomer(String kodeCustomer) {
        this.kodeCustomer = kodeCustomer;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public ArrayList<DetailPenjualan> getListPenjualan() {
        return listPenjualan;
    }

    public void setListPenjualan(ArrayList<DetailPenjualan> listPenjualan) {
        this.listPenjualan = listPenjualan;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
    
}
