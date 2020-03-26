package gegana.entitas;

public class Bahan {
    
    private String kode;
    private String nama;
    private double harga;
    private int jumlah;
    private String dataStatus;

    public Bahan() {
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
    
}
