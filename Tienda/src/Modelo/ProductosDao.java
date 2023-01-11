/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JComboBox;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductosDao {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProductos(Productos pro){
        String sql = "INSERT INTO productos (codigo, nombre, proveedor, stock, precio, preciobs) VALUES (?,?,?,?,?,?)";
        try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, pro.getCodigo());
           ps.setString(2, pro.getNombre());
           ps.setString(3, pro.getProveedor());
           ps.setInt(4, pro.getStock());
           ps.setDouble(5, pro.getPrecio());
           ps.setDouble(6, pro.getPreciobs());
           ps.execute();
           return true;
        } catch (SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    public void ConsultarProveedor(JComboBox proveedor) {
        String sql = "SELECT nombre FROM proveedor";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                proveedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e){
            System.out.println(e.toString());
        }
    }
    
    public List ListarProductos() {
        List<Productos> Listapro = new ArrayList();
        String sql = "SELECT * FROM productos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos pro = new Productos();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
                pro.setPreciobs(rs.getDouble("preciobs"));
                Listapro.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapro;
    }
     
    public boolean EliminarProductos(int id){
        String sql = "DELETE FROM productos WHERE id = ?";
        try {
            ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
     
    public boolean ModificarProductos(Productos pro){
        String sql = "UPDATE productos SET codigo=?, nombre=?, proveedor=?, stock=?, precio=?, preciobs=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());  
            ps.setDouble(5, pro.getPrecio());
            ps.setDouble(6, pro.getPreciobs());
            ps.setInt(7, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e){
            System.out.println(e.toString());
            return false;
        } finally{
            try{
                con.close();
            } catch (SQLException e){
                System.out.println(e.toString());
            }
        }
    } 
    
    public Productos BuscarPro(String cod){
        Productos producto = new Productos();
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setPreciobs(rs.getDouble("preciobs"));
                producto.setStock(rs.getInt("stock"));
                
            }
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return producto;
    }
    
    public Config BuscarDatos(){
        Config Conf = new Config();
        String sql = "SELECT * FROM `config`";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                Conf.setId(rs.getInt("id"));
                Conf.setRif(rs.getString("rif"));
                Conf.setNombre(rs.getString("nombre"));
                Conf.setTelefono(rs.getInt("telefono"));
                Conf.setDireccion(rs.getString("direccion"));
                Conf.setRazon(rs.getString("razon"));
                Conf.setTasa(rs.getDouble("tasa"));
                Conf.setIva(rs.getDouble("iva"));
                
            }
        } catch (SQLException e){
            System.out.println(e.toString());
        }
        return Conf;
    }
    
    public boolean ModificarDatos(Config conf){
        String sql = "UPDATE Config SET rif=?, nombre=?, telefono=?, direccion=?, razon=?, tasa=?, iva=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, conf.getRif());
            ps.setString(2, conf.getNombre());
            ps.setInt(3, conf.getTelefono());
            ps.setString(4, conf.getDireccion());  
            ps.setString(5, conf.getRazon());
            ps.setDouble(6, conf.getTasa());
            ps.setDouble(7, conf.getIva());
            ps.setInt(8, conf.getId());
            ps.execute();
            return true;
        } catch (SQLException e){
            System.out.println(e.toString());
            return false;
        } finally{
            try{
                con.close();
            } catch (SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
}
