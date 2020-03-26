package gegana.entitas;

import java.util.ArrayList;

public class Pembelian {
    private String kodeTransaksi;
    private String kodeSupplier;
    private double totalHarga;
    private ArrayList<DetailPembelian> listPembelian;
    private String dataStatus;

    public Pembelian() {
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getKodeSupplier() {
        return kodeSupplier;
    }

    public void setKodeSupplier(String kodeSupplier) {
        this.kodeSupplier = kodeSupplier;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public ArrayList<DetailPembelian> getListPembelian() {
        return listPembelian;
    }

    public void setListPembelian(ArrayList<DetailPembelian> listPembelian) {
        this.listPembelian = listPembelian;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
    
    
}
