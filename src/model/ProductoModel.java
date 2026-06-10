package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public List<Producto> listaProduccion(String nombreProducto, LocalDate fechaProduccion, LocalDate fechaVencimiento) {
		ArrayList<Producto> lista = new ArrayList<Producto>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "SELECT * FROM producto WHERE "
					+ " nombre LIKE ? AND "
					+ " ( ? ='9999-01-01' or fechaProduccion >= ? ) AND "
					+ " ( ? ='9999-01-01' or FechaVencimiento <= ? ) ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + nombreProducto + "%");
			pstm.setDate(2, java.sql.Date.valueOf(fechaProduccion));
			pstm.setDate(3, java.sql.Date.valueOf(fechaProduccion));
			pstm.setDate(4, java.sql.Date.valueOf(fechaVencimiento));
			pstm.setDate(5, java.sql.Date.valueOf(fechaVencimiento));
			
			//imprimir el query para verificar que se arma correctamente
			System.out.println("SQL: " + pstm.toString());
			
			//Se ejecuta el query en la base de datos
			rs = pstm.executeQuery();

			while (rs.next()) {
				Producto p = new Producto();
			    p.setIdProducto(rs.getInt("idProducto"));
				p.setNombre(rs.getString("nombre"));
				p.setFechaProduccion(rs.getDate("fechaProduccion").toLocalDate());
				p.setFechaVencimiento(rs.getDate("fechaVencimiento").toLocalDate());
			    p.setEstado(rs.getInt("estado"));
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}
	
	
	public List<Producto> listaStock(String nombre, String categoria, int stock) {
		ArrayList<Producto> lista = new ArrayList<Producto>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "SELECT * FROM producto WHERE "
			        + " nombre LIKE ? AND "
			        + " ( ? = '' OR categoria = ? ) AND "
			        + " ( (? = 1 AND stock > 0) OR (? = 0 AND stock = 0) ) ";

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, "%" + nombre + "%");
			pstm.setString(2, categoria);
			pstm.setString(3, categoria);
			pstm.setInt(4, stock);
			pstm.setInt(5, stock);

			// imprimir el query para verificar que se arma correctamente
			System.out.println("SQL: " + pstm.toString());

			// Se ejecuta el query en la base de datos
			rs = pstm.executeQuery();

			while (rs.next()) {
				Producto p = new Producto();

				p.setIdProducto(rs.getInt("idProducto"));
				p.setNombre(rs.getString("nombre"));
				p.setCategoria(rs.getString("categoria"));
				p.setPrecio(rs.getDouble("precio"));
				p.setStock(rs.getInt("stock"));

				lista.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return lista;
	}
}
