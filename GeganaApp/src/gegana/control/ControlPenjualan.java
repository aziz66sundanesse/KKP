package gegana.control;

import chumbucket.database.DbUtil;
import chumbucket.sql.DbaseSql;
import chumbucket.widget.format.FormatAngka;
import gegana.app.DbConnection;
import gegana.entitas.Bahan;
import gegana.entitas.Penjualan;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ControlPenjualan {
    
    public static boolean save(Penjualan penjualan) throws SQLException {
        boolean valid = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            db.begin();
            ArrayList<String> listExclude = new ArrayList<>();
            listExclude.add("list_penjualan");
            valid = db.saveData("GctV7ABZgksMX+Zbowj4Sw==", penjualan, listExclude);
            for (int i = 0; i < penjualan.getListPenjualan().size(); i++) {
                ArrayList<String> listExcludeDetail = new ArrayList<>();
                penjualan.getListPenjualan().get(i).setKodeTransaksi(penjualan.getKodeTransaksi());
                db.saveData("UQqXj83nYaVrgTxbYBqTFYXQr8eaqhjxEtQ3rrQcUug=", penjualan.getListPenjualan().get(i));
                Bahan b = ControlBahan.getBahan(penjualan.getListPenjualan().get(i).getKodeBahan());
                b.setJumlah(b.getJumlah() - penjualan.getListPenjualan().get(i).getJumlah());
                Properties p = new Properties();
                p.put("kode = ", "'" + penjualan.getListPenjualan().get(i).getKodeBahan() + "'");
                db.updateData("nSzLu71SIDiby0WiEplc8w==", b, p);
            }
            db.commit();
        } catch (SQLException er) {
            if (connection != null) {
                connection.rollback();
            }
            throw er;
        } finally {
            DbUtil.close(connection);
        }
        return valid;
    }
    
    public static ArrayList<Penjualan> getAllPenjualan() throws SQLException {
        ArrayList<Penjualan> listPenjualan = new ArrayList<>();
        Connection connection = null;
        DbaseSql db = null;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Properties p = new Properties();
            listPenjualan = db.getSelectData("GctV7ABZgksMX+Zbowj4Sw==", null, p, Penjualan.class);
        } catch (SQLException e) {
            System.err.println("ERROR " + e);
            throw e;
        } finally {
            if (db != null) {
                db.close();
            }
            DbUtil.close(connection);
        }
        return listPenjualan;
    }
    
    public static String generateKodeTransaksi() throws SQLException {
        Connection connection = null;
        DbaseSql db = null;
        String kode = null;
        int no;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            no = db.getLastRowNumber("GctV7ABZgksMX+Zbowj4Sw==", "kode_transaksi", Penjualan.class) + 1;
            kode = "TR-";
            kode += FormatAngka.stringToFormat("00000", no);
        } catch (SQLException e) {
            System.err.println("ERROR Generate Kode " + e);
            throw e;
        } finally {
            if (db != null) {
                db.close();
            }
            DbUtil.close(connection);
        }
        return kode;
    }
}
