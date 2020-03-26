package gegana.entitas;

public class DetailPenjualan {
    
    private String kodeTransaksi;
    private String kodeBahan;
    private int jumlah;
    private double total;

    public DetailPenjualan() {
    }
    
    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getKodeBahan() {
        return kodeBahan;
    }

    public void setKodeBahan(String kodeBahan) {
        this.kodeBahan = kodeBahan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
