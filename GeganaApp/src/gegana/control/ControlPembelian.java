package gegana.control;

import chumbucket.database.DbUtil;
import chumbucket.sql.DbaseSql;
import chumbucket.widget.format.FormatAngka;
import gegana.app.DbConnection;
import gegana.entitas.Bahan;
import gegana.entitas.Pembelian;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ControlPembelian {
    
    public static boolean save(Pembelian pembelian) throws SQLException {
        boolean valid = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            db.begin();
            ArrayList<String> listExclude = new ArrayList<>();
            listExclude.add("list_pembelian");
            valid = db.saveData("RlpLPQfQQl6QqluSloDD9Q==", pembelian, listExclude);
            for (int i = 0; i < pembelian.getListPembelian().size(); i++) {
                ArrayList<String> listExcludeDetail = new ArrayList<>();
                pembelian.getListPembelian().get(i).setKodeTransaksi(pembelian.getKodeTransaksi());
                db.saveData("CBZTEQJt8VIyQ/7pXbAi0IXQr8eaqhjxEtQ3rrQcUug=", pembelian.getListPembelian().get(i));
                Bahan b = ControlBahan.getBahan(pembelian.getListPembelian().get(i).getKodeBahan());
                b.setJumlah(b.getJumlah() + pembelian.getListPembelian().get(i).getJumlah());
                Properties p = new Properties();
                p.put("kode = ", "'" + pembelian.getListPembelian().get(i).getKodeBahan() + "'");
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
    
    public static ArrayList<Pembelian> getAllPembelian() throws SQLException {
        ArrayList<Pembelian> listPembelian = new ArrayList<>();
        Connection connection = null;
        DbaseSql db = null;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Properties p = new Properties();
            listPembelian = db.getSelectData("RlpLPQfQQl6QqluSloDD9Q==", null, p, Pembelian.class);
        } catch (SQLException e) {
            System.err.println("ERROR " + e);
            throw e;
        } finally {
            if (db != null) {
                db.close();
            }
            DbUtil.close(connection);
        }
        return listPembelian;
    }
    
    public static String generateKodeTransaksi() throws SQLException {
        Connection connection = null;
        DbaseSql db = null;
        String kode = null;
        int no;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            no = db.getLastRowNumber("RlpLPQfQQl6QqluSloDD9Q==", "kode_transaksi", Pembelian.class) + 1;
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
