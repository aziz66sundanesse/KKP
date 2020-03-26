package gegana.control;

import chumbucket.database.DbUtil;
import chumbucket.sql.DbaseSql;
import chumbucket.widget.format.FormatAngka;
import gegana.app.DbConnection;
import gegana.entitas.Supplier;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ControlSupplier {
    
    public static boolean save(Supplier supplier) throws SQLException {
        boolean valid = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            db.begin();
            valid = db.saveData("rQ2yWB78Hr2V9bbOMHlIEg==", supplier);
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
    
    public static ArrayList<Supplier> getAllSupplier() throws SQLException {
        ArrayList<Supplier> listSupplier = new ArrayList<>();
        Connection connection = null;
        DbaseSql db = null;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Properties p = new Properties();
            p.put("data_status = ", "'A'");
            listSupplier = db.getSelectData("rQ2yWB78Hr2V9bbOMHlIEg==", null, p, Supplier.class);
        } catch (SQLException e) {
            System.err.println("ERROR " + e);
            throw e;
        } finally {
            if (db != null) {
                db.close();
            }
            DbUtil.close(connection);
        }
        return listSupplier;
    }
    
    public static Supplier getSupplier(String kode) throws SQLException {
        ArrayList<Supplier> listSupplier = new ArrayList<>();
        Connection connection = null;
        DbaseSql db = null;
        Supplier b = null;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Properties p = new Properties();
            p.put("data_status = ", "'A'");
            p.put("kode = ", "'" + kode + "'");
            listSupplier = db.getSelectData("rQ2yWB78Hr2V9bbOMHlIEg==", null, p, Supplier.class);
            b = listSupplier.get(0);
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
    
    public static boolean update(Supplier supplier) throws SQLException {
        boolean validSupplier = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            db.begin();
            Properties p = new Properties();
            p.put("kode = ", "'" + supplier.getKode() + "'");
            validSupplier = db.updateData("rQ2yWB78Hr2V9bbOMHlIEg==", supplier, p);
            db.commit();
        } finally {
            DbUtil.close(connection);
        }
        return validSupplier;
    }
    
    public static boolean delete(String kodeSupplier) throws SQLException {
        boolean validSupplier = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Supplier supplier = new Supplier();
            supplier.setDataStatus("N");
            db.begin();
            Properties p = new Properties();
            p.put("kode = ", "'" + kodeSupplier + "'");
            validSupplier = db.updateData("rQ2yWB78Hr2V9bbOMHlIEg==", supplier, p);
            db.commit();
        } finally {
            DbUtil.close(connection);
        }
        return validSupplier;
    }
    
    public static String generateKode() throws SQLException {
        Connection connection = null;
        DbaseSql db = null;
        String kode = null;
        int no;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            no = db.getLastRowNumber("rQ2yWB78Hr2V9bbOMHlIEg==", "kode", Supplier.class) + 1;
            kode = "SPL-";
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
