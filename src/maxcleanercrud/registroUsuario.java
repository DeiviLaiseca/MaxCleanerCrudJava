/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maxcleanercrud;

import com.mysql.cj.protocol.Resultset;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author TATO
 */
public class registroUsuario {
    
    int id;
    String nombre;
    String tipoUsuario;
    String email;
    String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void insertarUsuario(JTextField id, JTextField nombre, JTextField tipoUsuario, JTextField email, JTextField password){
        
        setId(Integer.parseInt(id.getText()));
        setNombre(nombre.getText());
        setTipoUsuario(tipoUsuario.getText());
        setEmail(email.getText());
        setPassword(password.getText());
        
        Cconextion objetoConexion =  new Cconextion();
        
        String consulta = "insert into registro_usuario(id, nombre, tipo_usuario, correoElectronico, contraseña) values (?, ?, ?, ?, ?)";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
                
            cs.setInt(1, getId());
            cs.setString(2, getNombre());
            cs.setString(3, getTipoUsuario());
            cs.setString(4, getEmail());
            cs.setString(5, getPassword());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el usuario");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se inserto correctamente" + e.toString());
        }
    }
    
    public void elimniarUsuarioConId(JTextField id){
        
        setId(Integer.parseInt(id.getText()));
        
        Cconextion objetoConexion = new Cconextion();
        
        String deleteSql = "delete from registro_usuario where ID = ?";
        
        try {
            
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(deleteSql);
             
             cs.setInt(1, getId());
             
             cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el usuario");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se eliminó correctamente" + e.toString());
            
        }
        
    }
    
    public void modificarUsuario (JTextField nombre, JTextField tipoUsuario, JTextField email, JTextField password, JTextField id){
        
        setId(Integer.parseInt(id.getText()));
        setNombre(nombre.getText());
        setTipoUsuario(tipoUsuario.getText());
        setEmail(email.getText());
        setPassword(password.getText());
        
        Cconextion objetoConexion =  new Cconextion();
        
        String modificarSql = "update registro_usuario set registro_usuario.nombre = ?, registro_usuario.tipo_usuario = ?, registro_usuario.correoElectronico = ?, registro_usuario.contraseña = ? where registro_usuario.id = ?";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(modificarSql);
                
            cs.setInt(5, getId());
            cs.setString(1, getNombre());
            cs.setString(2, getTipoUsuario());
            cs.setString(3, getEmail());
            cs.setString(4, getPassword());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modificó correctamente el usuario");
            
        } catch (HeadlessException | SQLException e) {
            
            JOptionPane.showMessageDialog(null, "No se modificó correctamente" + e.toString());
        }
                
    }
    
    public void mostrarUsuarios(JTable tablaUsuarios){
        
        Cconextion obtejoConexion = new Cconextion();
        
        DefaultTableModel modelo =  new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTable = new TableRowSorter<TableModel>(modelo);
        tablaUsuarios.setRowSorter(OrdenarTable);
        
        String sql="";
        
        modelo.addColumn("ID"); 
        modelo.addColumn("Email");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo de Usuario");
        
        tablaUsuarios.setModel(modelo);
        
        sql = "select * from registro_usuario";
        
        String[] datos = new String[5];
        Statement st;
        
        try {
            
            st = obtejoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                
                modelo.addRow(datos);
            }
            
            tablaUsuarios.setModel(modelo);
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "No se puede mostrar usuarios: " + e.toString());
            
        }        
        
    }
    
    public void seleccionarUsuarios(JTable tablaUsuario,JTextField id, JTextField nombre, JTextField tipoUsuario, JTextField email, JTextField password){
        
        try {
            
            int fila =  tablaUsuario.getSelectedRow();
            
            if( fila >=0){
                
                id.setText(tablaUsuario.getValueAt(fila, 0).toString());
                email.setText(tablaUsuario.getValueAt(fila, 1).toString());
                password.setText(tablaUsuario.getValueAt(fila, 2).toString());
                nombre.setText(tablaUsuario.getValueAt(fila, 3).toString());
                tipoUsuario.setText(tablaUsuario.getValueAt(fila, 4).toString());
            } 
            else {
                
                JOptionPane.showMessageDialog(null, "Fila no seleccionada: ");
                
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
            
        }
    }
          
}
