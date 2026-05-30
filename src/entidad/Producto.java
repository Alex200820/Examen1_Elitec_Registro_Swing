package entidad;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {
	private int idProducto;
	private String nombre;
	private double precio;
	private int stock;
	private LocalDate fechaVencimiento;
	private String categoria;
    private LocalDate fechaProduccion;
    private int estado;
}

