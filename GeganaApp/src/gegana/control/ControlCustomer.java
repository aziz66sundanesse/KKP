package gegana.control;

import chumbucket.database.DbUtil;
import chumbucket.sql.DbaseSql;
import chumbucket.widget.format.FormatAngka;
import gegana.app.DbConnection;
import gegana.entitas.Customer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ControlCustomer {
    
    public static boolean save(Customer customer) throws SQLException {
        boolean valid = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            db.begin();
            valid = db.saveData("CyvAzdI8sfPiC4hTScVrvg==", customer);
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
    
    public static ArrayList<Customer> getAllCustomer() throws SQLException {
        ArrayList<Customer> listCustomer = new ArrayList<>();
        Connection connection = null;
        DbaseSql db = null;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Properties p = new Properties();
            p.put("data_status = ", "'A'");
            listCustomer = db.getSelectData("CyvAzdI8sfPiC4hTScVrvg==", null, p, Customer.class);
        } catch (SQLException e) {
            System.err.println("ERROR " + e);
            throw e;
        } finally {
            if (db != null) {
                db.close();
            }
            DbUtil.close(connection);
        }
        return listCustomer;
    }
    
    public static Customer getCustomer(String kode) throws SQLException {
        ArrayList<Customer> listCustomer = new ArrayList<>();
        Connection connection = null;
        DbaseSql db = null;
        Customer b = null;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Properties p = new Properties();
            p.put("data_status = ", "'A'");
            p.put("kode = ", "'" + kode + "'");
            listCustomer = db.getSelectData("CyvAzdI8sfPiC4hTScVrvg==", null, p, Customer.class);
            b = listCustomer.get(0);
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
    
    public static boolean update(Customer customer) throws SQLException {
        boolean validCustomer = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            db.begin();
            Properties p = new Properties();
            p.put("kode = ", "'" + customer.getKode() + "'");
            validCustomer = db.updateData("CyvAzdI8sfPiC4hTScVrvg==", customer, p);
            db.commit();
        } finally {
            DbUtil.close(connection);
        }
        return validCustomer;
    }
    
    public static boolean delete(String kodeCustomer) throws SQLException {
        boolean validCustomer = false;
        Connection connection = null;
        DbaseSql db;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            Customer customer = new Customer();
            customer.setDataStatus("N");
            db.begin();
            Properties p = new Properties();
            p.put("kode = ", "'" + kodeCustomer + "'");
            validCustomer = db.updateData("CyvAzdI8sfPiC4hTScVrvg==", customer, p);
            db.commit();
        } finally {
            DbUtil.close(connection);
        }
        return validCustomer;
    }
    
    public static String generateKode() throws SQLException {
        Connection connection = null;
        DbaseSql db = null;
        String kode = null;
        int no;
        try {
            connection = DbConnection.getConnection();
            db = new DbaseSql(connection);
            no = db.getLastRowNumber("CyvAzdI8sfPiC4hTScVrvg==", "kode", Customer.class) + 1;
            kode = "CUST-";
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
