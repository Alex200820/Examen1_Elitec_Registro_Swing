package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import entidad.Producto;
import model.ProductoModel;
import util.ValidateUtil;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrnConsultaProduccion extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProducto;
	private JTextField txtProduccion;
	private JTextField txtVencimiento;
	private JButton btnFiltrar;
	private JButton btnCancelar;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					FrnConsultaProduccion frame = new FrnConsultaProduccion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrnConsultaProduccion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Consultas sobre Producción");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(192, 26, 238, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombreProducto = new JLabel("Nombre del Producto:");
		lblNombreProducto.setBounds(28, 73, 155, 14);
		contentPane.add(lblNombreProducto);
		
		txtProducto = new JTextField();
		txtProducto.setBounds(176, 70, 178, 20);
		contentPane.add(txtProducto);
		txtProducto.setColumns(10);
		
		JLabel lblFechaProduccion = new JLabel("Fecha Producción:");
		lblFechaProduccion.setBounds(28, 118, 127, 14);
		contentPane.add(lblFechaProduccion);
		
		JLabel lblFechaVencimiento = new JLabel("Vencimiento");
		lblFechaVencimiento.setBounds(340, 118, 127, 14);
		contentPane.add(lblFechaVencimiento);
		
		txtProduccion = new JTextField();
		txtProduccion.setColumns(10);
		txtProduccion.setBounds(148, 115, 144, 20);
		contentPane.add(txtProduccion);
		
		txtVencimiento = new JTextField();
		txtVencimiento.setColumns(10);
		txtVencimiento.setBounds(429, 115, 144, 20);
		contentPane.add(txtVencimiento);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 205, 580, 181);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Producto", "Nombre", "Fecha Producci\u00F3n", "Fecha Vencimiento", "Estado"
			}
		));
		scrollPane.setViewportView(table);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(203, 160, 89, 23);
		contentPane.add(btnFiltrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(327, 160, 89, 23);
		contentPane.add(btnCancelar);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
		if (e.getSource() == btnFiltrar) {
			do_btnFiltrar_actionPerformed(e);
		}
	}
	protected void do_btnFiltrar_actionPerformed(ActionEvent e) {
		//1 Recibimos todos los parametros del formulario
	    String nombre = txtProducto.getText().trim();
	    String desde = txtProduccion.getText().trim();
	    String hasta = txtVencimiento.getText().trim();

	    //imprimir los parametros recibidos
	    System.out.println("Parametros recibidos: ");
	    System.out.println("Nombre: " + nombre);
	    System.out.println("Desde: " + desde);
	    System.out.println("Hasta: " + hasta);

	    //2 Validacion
	    if (!desde.isEmpty() && desde.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
	        JOptionPane.showMessageDialog(this,
	                "La fecha de producción(Desde) no es válida. Tiene que tener el formato YYYY-MM-DD");
	        return;
	    }

	    if (!hasta.isEmpty() && hasta.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
	        JOptionPane.showMessageDialog(this,
	                "La fecha de vencimiento(Hasta) no es válida. Tiene que tener el formato YYYY-MM-DD");
	        return;
	    }

	    //Fecha desde debe ser menor o igual a fecha hasta
	    if (!desde.isEmpty() && !hasta.isEmpty()) {
	        LocalDate fechaProduccion = LocalDate.parse(desde);
	        LocalDate fechaVencimiento = LocalDate.parse(hasta);

	        if (fechaProduccion.isAfter(fechaVencimiento)) {
	            JOptionPane.showMessageDialog(this,
	                    "La fecha de producción(Desde) no puede ser mayor a la fecha de vencimiento(Hasta)");
	            return;
	        }
	    }

	    LocalDate fechaProduccion = desde.isEmpty()
	            ? LocalDate.parse("9999-01-01")
	            : LocalDate.parse(desde);

	    LocalDate fechaVencimiento = hasta.isEmpty()
	            ? LocalDate.parse("9999-01-01")
	            : LocalDate.parse(hasta);

	    //3 Crear la clase model
	    ProductoModel objProductoModel = new ProductoModel();
	    List<Producto> lista = objProductoModel.listaProduccion(
	            nombre,
	            fechaProduccion,
	            fechaVencimiento);

	    //4 recorremos la lista
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

	    for (Producto p : lista) {
	        Object[] rowData = {
	                p.getIdProducto(),
	                p.getNombre(),
	                p.getFechaProduccion(),
	                p.getFechaVencimiento(),
	                p.getEstado()
	        };

	        model.addRow(rowData);
	    }
	}
	
	
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
		txtProducto.setText("");
		txtProduccion.setText("");
		txtVencimiento.setText("");
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Limpiar la tabla
	}
}
