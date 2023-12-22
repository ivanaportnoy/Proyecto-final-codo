
package Logica;
import Datos.vCliente;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

public class fCliente {
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    

    public DefaultTableModel mostrar(String buscar)
    {
    DefaultTableModel modelo;
    String [] titulos={"codigo","nombre","apellidos"};
    String [] registro=new String [8];
    
    modelo=new DefaultTableModel(null,titulos);
    
    sSQL="select * from clientes where nombre like '%"+buscar+"%' order by codigo";
    try{
        //Declaro variable de tipo Statement 
    Statement st= (Statement) cn.createStatement();
    //crear variable tipo resultset ejecuta la consulta de arriba
    ResultSet rs=st.executeQuery(sSQL);
    
    while(rs.next())
    {
       registro [0]=rs.getString("codigo");
       registro [1]=rs.getString("nombre");
       registro [2]=rs.getString("apellidos");
       modelo.addRow(registro);
    }
    return modelo;
    }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e);
        return null;
    }
    }
    
      public boolean insertar (vCliente dts){
       sSQL="insert into clientes (nombre,apellidos)" +
               "values (?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNombre());
           pst.setString(2, dts.getApellido());
        
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
           
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
   
    public boolean editar (vCliente dts){
       sSQL="update clientes set nombre=?,apellidos=? "+
               " where codigo=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
         
           pst.setString(1, dts.getNombre());
           pst.setString(2, dts.getApellido());
           pst.setInt(3, dts.getCodigo());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   } 
  
     public boolean eliminar (vCliente dts){
       sSQL="delete from clientes where codigo=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getCodigo());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
  
}
 
