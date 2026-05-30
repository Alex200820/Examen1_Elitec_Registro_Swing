package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Producto;
import util.MySqlDBConexion;

public class ProductoModel {
	public int insertaProducto(Producto obj) {
		int salida = -1;

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Crear conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "INSERT INTO producto (nombre, precio, stock, fechaVencimiento, categoria, fechaProduccion, estado) VALUES (?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setDouble(2, obj.getPrecio());
			pstm.setInt(3, obj.getStock());
			pstm.setDate(4, java.sql.Date.valueOf(obj.getFechaVencimiento()));
			pstm.setString(5, obj.getCategoria());
			pstm.setDate(6, java.sql.Date.valueOf(obj.getFechaProduccion()));
			pstm.setInt(7, obj.getEstado());

			//3 Ejecutar sentencia SQL
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}
}
