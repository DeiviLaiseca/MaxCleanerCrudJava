/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maxcleanercrud;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author TATO
 */
public class Cconextion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contrasena = "Tato1678";
    String bd = "maxcleanerdb";
    String ip = "localhost";
    String puerto = "3306";
    
    String url = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conectar = DriverManager.getConnection(url, usuario, contrasena);
            JOptionPane.showMessageDialog(null, "La conexión se ha registrado con éxito");
            
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos: "+ e.toString());
        }        
        return conectar;        
    }
}
