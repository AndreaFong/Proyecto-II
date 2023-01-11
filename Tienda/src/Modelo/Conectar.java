
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrea
 */
public class Conectar {
    Connection conect = null;
    public Connection conexion()
    {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost/sistemaventa","root","");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error "+e);
        }
        return conect;
    }
}
