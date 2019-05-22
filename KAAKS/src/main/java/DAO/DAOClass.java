/*
 * Developed by : I2S2
 * Project Name : OptoCare  * 
 * Language : Java  * 
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author santosh
 */
public class DAOClass {

    Connection con;

    String url = "jdbc:mysql://209.99.16.221:3306/jbworlds_macro";
    String username = "jbworlds_root";
    String password = "Shriya_786";
    String Driver = "com.mysql.jdbc.Driver";

    /**
     *
     * @throws SQLException
     */
    public DAOClass() {
        try {
            if (con == null) {
                Class.forName(Driver).newInstance();
            }else{
            JOptionPane.showMessageDialog(null,"Connection Already....");
                System.out.println("DAOClasss Already have connection....");
            }
            

        } catch (Exception ex) {
//            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "DAOClass : " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

    public Connection getConnection() {
        try {
            if (con == null) {
                con = DriverManager.getConnection(url, username, password);
                return con;
            }else{
//            JOptionPane.showMessageDialog(null,"Connection Already....");
                System.out.println("DAOClasss Already have connection....");
            }
        } catch (Exception ex) {
//            Logger.getLogger(DAOClass.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
            JOptionPane.showMessageDialog(null, "DAOClass : " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return null;
    }

}
