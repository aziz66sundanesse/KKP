package gegana.control;

import chumbucket.database.DbUtil;
import chumbucket.sql.DbaseSql;
import chumbucket.widget.format.FormatAngka;
import gegana.app.DbConnection;
import gegana.entitas.Bahan;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ControlBahan {
    
    public static boolean save(Bahan bahan) throws SQLException {
        boolean valid = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            db.begin();
            valid = db.saveData("nSzLu71SIDiby0WiEplc8w==", bahan);
            db.commit();
        } catch(SQLException er) {
            if (connection != null) {
                connection.rollback();
            }
            throw er;
        } finally {
            DbUtil.close(connection);
        }
        return valid;
    }
    
    public static ArrayList<Bahan> getAllBahan() throws SQLException {
        ArrayList<Bahan> listBahan = new ArrayList<>();
        Connection connection = null;
        DbaseSql db = null;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Properties p = new Properties();
            p.put("data_status = ", "'A'");
            listBahan = db.getSelectData("nSzLu71SIDiby0WiEplc8w==", null, p, Bahan.class);
        } catch (SQLException e) {
            System.err.println("ERROR " + e);
            throw e;
        } finally {
            if (db != null) {
                db.close();
            }
            DbUtil.close(connection);
        }
        return listBahan;
    }
    
    public static Bahan getBahan(String kode) throws SQLException {
        ArrayList<Bahan> listBahan = new ArrayList<>();
        Connection connection = null;
        DbaseSql db = null;
        Bahan b = null;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Properties p = new Properties();
            p.put("data_status = ", "'A'");
            p.put("kode = ", "'" + kode + "'");
            listBahan = db.getSelectData("nSzLu71SIDiby0WiEplc8w==", null, p, Bahan.class);
            b = listBahan.get(0);
        } catch (SQLException e) {
            System.err.println("ERROR " + e);
            throw e;
        } finally {
            if (db != null) {
                db.close();
            }
            DbUtil.close(connection);
        }
        return b;
    }
    
    public static boolean update(Bahan bahan) throws SQLException {
        boolean validBahan = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            db.begin();
            Properties p = new Properties();
            p.put("kode = ", "'" + bahan.getKode() + "'");
            validBahan = db.updateData("nSzLu71SIDiby0WiEplc8w==", bahan, p);
            db.commit();
        } finally {
            DbUtil.close(connection);
        }
        return validBahan;
    }
    
    public static boolean delete(String kodeBahan) throws SQLException {
        boolean validBahan = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Bahan bahan = new Bahan();
            bahan.setDataStatus("N");
            db.begin();
            Properties p = new Properties();
            p.put("kode = ", "'" + kodeBahan + "'");
            validBahan = db.updateData("nSzLu71SIDiby0WiEplc8w==", bahan, p);
            db.commit();
        } finally {
            DbUtil.close(connection);
        }
        return validBahan;
    }
    
    public static String generateKode() throws SQLException {
        Connection connection = null;
        DbaseSql db = null;
        String kode = null;
        int no;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            no = db.getLastRowNumber("nSzLu71SIDiby0WiEplc8w==", "kode", Bahan.class) + 1;
            kode = "BHN-";
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
