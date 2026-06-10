package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidad.Producto;
import model.ProductoModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrnConsultaStock extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTable table;
	private JButton btnFiltrar;
	private JButton btnCancelar;
	private JComboBox cboCategoria;
	private JRadioButton rdbtnStock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					FrnConsultaStock frame = new FrnConsultaStock();
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
	public FrnConsultaStock() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Consultas de Stock");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(224, 23, 170, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombrePro = new JLabel("Nombre del Producto:");
		lblNombrePro.setBounds(25, 76, 129, 14);
		contentPane.add(lblNombrePro);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(164, 73, 148, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoría:");
		lblCategoria.setBounds(42, 113, 76, 14);
		contentPane.add(lblCategoria);
		
		cboCategoria = new JComboBox();
		cboCategoria.setModel(new DefaultComboBoxModel(new String[] {"Snack", "Galleta", "Gaseosa"}));
		cboCategoria.setBounds(128, 109, 158, 22);
		contentPane.add(cboCategoria);
		
		rdbtnStock = new JRadioButton("En stock");
		rdbtnStock.setBounds(359, 109, 109, 23);
		contentPane.add(rdbtnStock);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(197, 142, 89, 23);
		contentPane.add(btnFiltrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(317, 142, 89, 23);
		contentPane.add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 188, 572, 182);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Producto", "Nombre", "Categor\u00EDa", "Precio", "Stock"
			}
		));
		scrollPane.setViewportView(table);

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
		String nombre = txtNombre.getText();
		String categoria = cboCategoria.getSelectedItem().toString();
		int stock = rdbtnStock.isSelected() ? 1 : 0;

		//imprimir los parametros recibidos
		System.out.println("Parametros recibidos: ");
		System.out.println("Nombre: " + nombre);
		System.out.println("Categoria: " + categoria);
		System.out.println("Stock: " + stock);

		//3 Crear la clase model
		ProductoModel objProductoModel = new ProductoModel();
		List<Producto> lista = objProductoModel.listaStock(nombre, categoria, stock);

		//4 recorremos la lista
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

		for (Producto p : lista) {
			Object[] rowData = {
					p.getIdProducto(),
					p.getNombre(),
					p.getCategoria(),
					p.getPrecio(),
					p.getStock()
			};

			model.addRow(rowData);
		}
	}
	
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
		  txtNombre.setText("");
		  cboCategoria.setSelectedIndex(-1);
		  rdbtnStock.setSelected(false);
		  DefaultTableModel model = (DefaultTableModel) table.getModel();
		  model.setRowCount(0);
	}
}
