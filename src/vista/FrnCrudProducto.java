package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Producto;
import model.ProductoModel;
import util.ValidateUtil;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrnCrudProducto extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProducto;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JTextField txtVencimiento;
	private JTextField txtProduccion;
	private JButton btnListar;
	private JButton btnBuscar;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnEliminarLogico;
	private JButton btnEliminarFisico;
	private JButton btnLimpiar;
	private JTable table;
	private JCheckBox chkEnStock;
	private JScrollPane scrollPane;
	private JComboBox<String>  cboCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					FrnCrudProducto frame = new FrnCrudProducto();
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
	public FrnCrudProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1365, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdproducto = new JLabel("IdProducto");
		lblIdproducto.setBounds(51, 118, 101, 14);
		contentPane.add(lblIdproducto);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(51, 153, 101, 14);
		contentPane.add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(51, 194, 73, 14);
		contentPane.add(lblPrecio);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(51, 237, 78, 14);
		contentPane.add(lblStock);
		
		JLabel lblFechavencimiento = new JLabel("FechaVencimiento:");
		lblFechavencimiento.setBounds(51, 278, 132, 14);
		contentPane.add(lblFechavencimiento);
		
		txtProducto = new JTextField();
		txtProducto.setColumns(10);
		txtProducto.setBounds(183, 115, 143, 20);
		contentPane.add(txtProducto);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(183, 150, 222, 20);
		contentPane.add(txtNombre);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(183, 191, 143, 20);
		contentPane.add(txtPrecio);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(183, 234, 143, 20);
		contentPane.add(txtStock);
		
		txtVencimiento = new JTextField();
		txtVencimiento.setColumns(10);
		txtVencimiento.setBounds(183, 275, 143, 20);
		contentPane.add(txtVencimiento);
		
		chkEnStock = new JCheckBox("En Stock");
		chkEnStock.setBounds(183, 387, 97, 23);
		contentPane.add(chkEnStock);
		
		btnListar = new JButton("Listar Todos");
		btnListar.addActionListener(this);
		btnListar.setBounds(452, 118, 132, 23);
		contentPane.add(btnListar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(452, 161, 132, 23);
		contentPane.add(btnBuscar);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(452, 195, 132, 23);
		contentPane.add(btnRegistrar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(452, 234, 132, 23);
		contentPane.add(btnActualizar);
		
		btnEliminarLogico = new JButton("Eliminar lógico");
		btnEliminarLogico.addActionListener(this);
		btnEliminarLogico.setBounds(452, 268, 132, 23);
		contentPane.add(btnEliminarLogico);
		
		btnEliminarFisico = new JButton("Eliminar físico");
		btnEliminarFisico.addActionListener(this);
		btnEliminarFisico.setBounds(452, 302, 132, 23);
		contentPane.add(btnEliminarFisico);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(452, 336, 132, 23);
		contentPane.add(btnLimpiar);
		
		JLabel lblNewLabel = new JLabel("Mantenimiento Producto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(447, 25, 212, 40);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(this);
		scrollPane.setBounds(606, 76, 733, 357);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener((MouseListener) this);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "IdProducto", "Nombre", "Precio", "Stock", "Fecha Vencimiento", "Categor\u00EDa", "Fecha Producci\u00F3n", "Estado"}));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(80);   // IdProducto
		table.getColumnModel().getColumn(1).setPreferredWidth(150);  // Nombre
		table.getColumnModel().getColumn(2).setPreferredWidth(80);   // Precio
		table.getColumnModel().getColumn(3).setPreferredWidth(80);   // Stock
		table.getColumnModel().getColumn(4).setPreferredWidth(120);  // Fecha Vencimiento
		table.getColumnModel().getColumn(5).setPreferredWidth(100);  // Categoria
		table.getColumnModel().getColumn(6).setPreferredWidth(120);  // Fecha Produccion
		table.getColumnModel().getColumn(7).setPreferredWidth(80);   // Estado
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		defaults.putIfAbsent("Table.alternateRowColor", new Color(176, 245, 215));
		scrollPane.setViewportView(table);
		
		JLabel lblCategora = new JLabel("Categoría:");
		lblCategora.setBounds(51, 317, 108, 14);
		contentPane.add(lblCategora);
		
		JLabel lblFechaproduccin = new JLabel("FechaProducción:");
		lblFechaproduccin.setBounds(51, 354, 132, 14);
		contentPane.add(lblFechaproduccin);
		
		txtProduccion = new JTextField();
		txtProduccion.setColumns(10);
		txtProduccion.setBounds(183, 351, 143, 20);
		contentPane.add(txtProduccion);
		
		cboCategoria = new JComboBox<String> ();
		cboCategoria.setModel(new DefaultComboBoxModel(new String[] {"Snack", "Galleta", "Gaseosa"}));
		cboCategoria.setBounds(183, 313, 143, 22);
		contentPane.add(cboCategoria);

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			do_btnLimpiar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminarFisico) {
			do_btnEliminarFisico_actionPerformed(e);
		}
		if (e.getSource() == btnEliminarLogico) {
			do_btnEliminarLogico_actionPerformed(e);
		}
		if (e.getSource() == btnActualizar) {
			do_btnActualizar_actionPerformed(e);
		}
		if (e.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnListar) {
			do_btnListar_actionPerformed(e);
		}
	}

	protected void do_btnListar_actionPerformed(ActionEvent e) {
		listarTodos();
	}
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		buscar();
	}
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		registrar();
		listarTodos();
		limpiar();
	}
	protected void do_btnActualizar_actionPerformed(ActionEvent e) {
		actualizar();
		listarTodos();
		limpiar();
	}
	protected void do_btnEliminarLogico_actionPerformed(ActionEvent e) {
		eliminarLogico();
		listarTodos();
		limpiar();
	}
	protected void do_btnEliminarFisico_actionPerformed(ActionEvent e) {
		eliminarFisico();
		listarTodos();
		limpiar();
	}
	protected void do_btnLimpiar_actionPerformed(ActionEvent e) {
		limpiar();
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == scrollPane) {
			do_scrollPane_mouseClicked(e);
		}
		if (e.getSource() == table) {
			do_table_mouseClicked(e);
		}
	}
	
	protected void do_scrollPane_mouseClicked(MouseEvent e) {
	}
	
	protected void do_table_mouseClicked(MouseEvent e) {
		seleccionarFila();
	}
	
	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
	
	void seleccionarFila() {

		int fila = table.getSelectedRow();
		txtProducto.setText(table.getValueAt(fila, 0).toString());
		txtNombre.setText(table.getValueAt(fila, 1).toString());
		txtPrecio.setText(table.getValueAt(fila, 2).toString());
		txtStock.setText(table.getValueAt(fila, 3).toString());
		txtVencimiento.setText(table.getValueAt(fila, 4).toString());
		cboCategoria.setSelectedItem(table.getValueAt(fila, 5).toString());
		txtProduccion.setText(table.getValueAt(fila, 6).toString());
		String estado = table.getValueAt(fila, 7).toString();
		if ("En Stock".equalsIgnoreCase(estado)) {
			chkEnStock.setSelected(true);
		} else {
			chkEnStock.setSelected(false);
		}
	}
	
	void listarTodos() {

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ProductoModel model = new ProductoModel();
		List<Producto> lista = model.listaTodos();

		for (Producto p : lista) {

			Object[] rowData = {
					p.getIdProducto(),
					p.getNombre(),
					p.getPrecio(),
					p.getStock(),
					p.getFechaVencimiento(),
					p.getCategoria(),
					p.getFechaProduccion(),
					p.getEstado()==1?"Activo":"Inactivo"
			};

			dtm.addRow(rowData);
		}
	}
	
	void buscar() {

		String codigo = txtProducto.getText().trim();
		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Seleccione un producto o ingrese un código válido");
			return;
		}
		ProductoModel model = new ProductoModel();
		Producto objProducto = model.buscaProducto(
				Integer.parseInt(codigo));

		if (objProducto == null) {
			JOptionPane.showMessageDialog(this,
					"No existe el producto de código " + codigo);
			limpiar();
			return;
		}
		txtProducto.setText(String.valueOf(objProducto.getIdProducto()));
		txtNombre.setText(objProducto.getNombre());
		txtPrecio.setText(String.valueOf(objProducto.getPrecio()));
		txtStock.setText(String.valueOf(objProducto.getStock()));
		txtVencimiento.setText(objProducto.getFechaVencimiento().toString());
		txtProduccion.setText(objProducto.getFechaProduccion().toString());
		cboCategoria.setSelectedItem(objProducto.getCategoria());
		chkEnStock.setSelected(objProducto.getEstado() == 1);
		DefaultTableModel dtm =
				(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		Object[] rowData = {
				objProducto.getIdProducto(),
				objProducto.getNombre(),
				objProducto.getPrecio(),
				objProducto.getStock(),
				objProducto.getFechaVencimiento(),
				objProducto.getCategoria(),
				objProducto.getFechaProduccion(),
				objProducto.getEstado()==1?"Activo":"Inactivo"
		};
		dtm.addRow(rowData);
	}
	
	void registrar() {
		String nombre = txtNombre.getText().trim();
		String precio = txtPrecio.getText().trim();
		String stock = txtStock.getText().trim();
		String fechaVencimiento = txtVencimiento.getText().trim();
		String categoria = cboCategoria.getSelectedItem().toString();
		String fechaProduccion = txtProduccion.getText().trim();
		int stockNum = Integer.parseInt(stock);

		// Validaciones
		if (nombre.matches(ValidateUtil.TEXTO_45) == false) {
			JOptionPane.showMessageDialog(this,
					"El nombre no es válido. Tiene que tener de 1 a 45 caracteres");
			return;
		}

		if (precio.matches(ValidateUtil.REAL_CON_O_SIN_DECIMALES) == false) {
			JOptionPane.showMessageDialog(this,
					"El precio no es válido. Debe ser un número con hasta 2 decimales");
			return;
		}

		if (stock.matches(ValidateUtil.ENTERO_MAS_UN_DIGITO) == false) {
			JOptionPane.showMessageDialog(this,
					"El stock no es válido. Debe contener solo números");
			return;
		}

		if (fechaVencimiento.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
			JOptionPane.showMessageDialog(this,
					"La fecha de vencimiento no es válida. Tiene que tener el formato YYYY-MM-DD");
			return;
		}

		if (categoria.matches(ValidateUtil.TEXTO_45) == false) {
			JOptionPane.showMessageDialog(this,
					"La categoría no es válida. Tiene que tener de 1 a 45 caracteres");
			return;
		}

		if (fechaProduccion.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
			JOptionPane.showMessageDialog(this,
					"La fecha de producción no es válida. Tiene que tener el formato YYYY-MM-DD");
			return;
		}
		
		if (stockNum > 0 && !chkEnStock.isSelected()) {
			JOptionPane.showMessageDialog(this,
					"Si el stock es mayor a 0, el producto debe estar marcado como En Stock");
			return;
		}

		if (stockNum == 0 && chkEnStock.isSelected()) {
			JOptionPane.showMessageDialog(this,
					"Si el stock es 0, el producto no puede estar marcado como En Stock");
			return;
		}

		Producto obj = new Producto();
		obj.setNombre(nombre);
		obj.setPrecio(Double.parseDouble(precio));
		obj.setStock(Integer.parseInt(stock));
		obj.setFechaVencimiento(LocalDate.parse(fechaVencimiento));
		obj.setCategoria(categoria);
		obj.setFechaProduccion(LocalDate.parse(fechaProduccion));
		obj.setEstado(chkEnStock.isSelected() ? 1 : 0);

		ProductoModel model = new ProductoModel();
		int salida = model.insertaProducto(obj);

		if (salida > 0) {
			JOptionPane.showMessageDialog(this, "Producto registrado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "Error al registrar el producto");
		}
	}
	
	void actualizar() {

		String codigo = txtProducto.getText().trim();
		String nombre = txtNombre.getText().trim();
		String precio = txtPrecio.getText().trim();
		String stock = txtStock.getText().trim();
		String fechaVencimiento = txtVencimiento.getText().trim();
		String categoria = cboCategoria.getSelectedItem().toString();
		String fechaProduccion = txtProduccion.getText().trim();
		int stockNum = Integer.parseInt(stock);

		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Seleccione un producto o ingrese un código válido");
			return;
		}

		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese el nombre");
			return;
		}

		if (precio.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese el precio");
			return;
		}

		if (stock.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese el stock");
			return;
		}

		if (fechaVencimiento.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese la fecha de vencimiento");
			return;
		}

		if (fechaProduccion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese la fecha de producción");
			return;
		}

		if (cboCategoria.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Seleccione una categoría");
			return;
		}

		Producto obj = new Producto();

		obj.setIdProducto(Integer.parseInt(codigo));
		obj.setNombre(nombre);
		obj.setPrecio(Double.parseDouble(precio));
		obj.setStock(Integer.parseInt(stock));
		obj.setFechaVencimiento(LocalDate.parse(fechaVencimiento));
		obj.setCategoria(categoria);
		obj.setFechaProduccion(LocalDate.parse(fechaProduccion));
		obj.setEstado(chkEnStock.isSelected() ? 1 : 0);

		ProductoModel model = new ProductoModel();

		int salida = model.actualizaProducto(obj);

		if (salida > 0) {
			JOptionPane.showMessageDialog(this, "Producto actualizado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "Error al actualizar el producto");
		}
	}
	
	void eliminarLogico() {

		String codigo = txtProducto.getText().trim();

		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Seleccione un producto o ingrese un código válido");
			return;
		}

		ProductoModel model = new ProductoModel();
		Producto objProducto = model.buscaProducto(Integer.parseInt(codigo));
		
		int nuevoEstado = objProducto.getEstado() == 0 ? 1 : 0;
		objProducto.setEstado(nuevoEstado);
		model.actualizaProducto(objProducto);

		JOptionPane.showMessageDialog(this,
				nuevoEstado == 1 ? "Producto marcado como En Stock"
								 : "Producto marcado como Sin Stock");
	}
	
	void eliminarFisico() {

		String codigo = txtProducto.getText().trim();

		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Seleccione un producto o ingrese un código válido");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(
				this,
				"¿Confirma eliminar el producto con código " + codigo + "?",
				"Confirmar eliminación",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE
		);

		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}

		ProductoModel model = new ProductoModel();
		int salida = model.eliminaProductoFisico(Integer.parseInt(codigo));

		if (salida > 0) {
			JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "Error al eliminar el producto");
		}
	}
	
	void limpiar() {

		txtProducto.setText("");
		txtNombre.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
		txtVencimiento.setText("");
		txtProduccion.setText("");
		cboCategoria.setSelectedIndex(0);
		chkEnStock.setSelected(false);
	}

}
