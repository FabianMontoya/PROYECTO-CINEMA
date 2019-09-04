package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexionOracle {
    
    public static Connection ConnectDB(){
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:CRONOS", "FABIAN", "fabian1234"); //IP:Puerto:Nombre Base de Datos, Usuario, Contraseña
            return con;   
            } catch(Exception e){
                
                JOptionPane.showMessageDialog(null,e);
            }
            return null;
        }
    
}