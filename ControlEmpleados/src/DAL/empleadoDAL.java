/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import BL.empleadoBL;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;
import sun.misc.IOUtils;
/**
 *
 * @author Christian
 */
public class empleadoDAL {
    Conexion con = new Conexion();
    
    public int Agregar (empleadoBL objemp){
        int ComandoEjecutado = con.EjecutarComandoSQL("Insert into Empleados (nombreEmp) values ('"+objemp.getnombreEmp()+"')");
        con.Desconectar();
        return ComandoEjecutado;
    }
    
    public int Eliminar (empleadoBL objemp){
        int ComandoEjecutado = con.EjecutarComandoSQL("Delete from Empleados where id='"+objemp.getId()+"'");
        con.Desconectar();
        return ComandoEjecutado;
    }
    
    public int Modificar (empleadoBL objemp){
        int ComandoEjecutado = con.EjecutarComandoSQL("Update Empleados set nombreEmp='"+objemp.getnombreEmp()+"' where id="+objemp.getId());
        con.Desconectar();
        return ComandoEjecutado;        
    }    
     public DefaultTableModel CargarDatos(){
        
        DefaultTableModel dtm =new DefaultTableModel(
        new Object [][] {},
        new String [] {
         "id","nombreEmp","foto"
        }
       ){ @Override
        public boolean isCellEditable(int row, int column) {
        // Para no editar en el jTable
         return false;
        }};

        try{
            Conexion objConexion= new Conexion();
            ResultSet Resultado=objConexion.EjecutarSentenciaSQL("SELECT * FROM Empleados"); 
        while(Resultado.next()){
        // Recuperar Datos de la BD
        Object[] Fila={
        Resultado.getString(1),
        Resultado.getString(2),            
    };
        // Agregar Datos al JTable
        dtm.addRow(Fila);
    }
    return dtm;
    }catch(SQLException e){
    return null;
    }
    }    
    /*
    public int Agregar (empleadoBL objemp){
        int ComandoEjecutado = con.EjecutarComandoSQL("Insert into Empleados (nombreEmp, foto, sexo, fechaIng, fechaRet, turno) "
                + "values  ('"+objemp.getnombreEmp()+"','"+objemp.getfoto()+"','"+objemp.getsexo()+"','"+objemp.getfechaIng()+"','"+objemp.getfechaRet()+"','"+objemp.getturno()+"');");
        con.Desconectar();
        return ComandoEjecutado;
    }
    
    public int Eliminar (empleadoBL objemp){
        int ComandoEjecutado = con.EjecutarComandoSQL("Delete from Empleados where id='"+objemp.getId()+"'");
        con.Desconectar();
        return ComandoEjecutado;
    }
    
    public int Modificar (empleadoBL objemp){
        int ComandoEjecutado = con.EjecutarComandoSQL("Update Empleados set nombreEmp='"+objemp.getnombreEmp()+"', foto='"+objemp.getfoto()+"', sexo='"+objemp.getsexo()+"', fechaIng='"+objemp.getfechaIng()+"', "
                + "fechaRet='"+objemp.getfechaRet()+"', turno='"+objemp.getturno()+"' where id='"+objemp.getId());
        con.Desconectar();
        return ComandoEjecutado;
    }
    */
    public empleadoBL BuscarEmpleado(int id) throws IOException{
        empleadoBL empleado = new empleadoBL();
        try {
            
            Conexion objConexion= new Conexion();
            ResultSet Resultado=objConexion.EjecutarSentenciaSQL("SELECT * FROM Empleados where Id="+id);
            if(Resultado.next()){
                empleado.setId(Resultado.getInt(1));
                empleado.setnombreEmp(Resultado.getString(2));
                byte[] bytes = Resultado.getBytes(3);
                empleado.setfoto(bytes);
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(empleadoDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }
    /*
    public DefaultTableModel CargarDatos(){
        
        DefaultTableModel dtm =new DefaultTableModel(
        new Object [][] {},
        new String [] {
         "id","nombreEmp","foto","sexo","fechaIng","fechaRet","turno"
        }
       ){ @Override
        public boolean isCellEditable(int row, int column) {
        // Para no editar en el jTable
         return false;
        }};

        try{
            Conexion objConexion= new Conexion();
            ResultSet Resultado=objConexion.EjecutarSentenciaSQL("SELECT * FROM Empleados"); 
        while(Resultado.next()){
        // Recuperar Datos de la BD
        Object[] Fila={
        Resultado.getString(1),
        Resultado.getString(2),
        Resultado.getString(3),
        Resultado.getString(4),
        Resultado.getString(5),
        Resultado.getString(6),
        Resultado.getString(7)
            
    };
        // Agregar Datos al JTable
        dtm.addRow(Fila);
    }
    return dtm;
    }catch(SQLException e){
    return null;
    }
    }*/
}