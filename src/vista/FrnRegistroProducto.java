package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidad.Producto;
import model.ProductoModel;
import util.ValidateUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class FrnRegistroProducto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JTextField txtFechaVencimiento;
	private JTextField txtCategoria;
	private JTextField txtFechaProduccion;
	private JButton btnRegistrar;
	private JCheckBox chkEnStock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					
					FrnRegistroProducto frame = new FrnRegistroProducto();
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
	public FrnRegistroProducto() {
		setTitle("Registro de Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro de Producto");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(152, 25, 276, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(41, 91, 85, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(155, 88, 235, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(155, 116, 235, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(41, 119, 85, 14);
		contentPane.add(lblPrecio);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(155, 147, 235, 20);
		contentPane.add(txtStock);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(41, 150, 85, 14);
		contentPane.add(lblStock);
		
		txtFechaVencimiento = new JTextField();
		txtFechaVencimiento.setColumns(10);
		txtFechaVencimiento.setBounds(155, 178, 235, 20);
		contentPane.add(txtFechaVencimiento);
		
		JLabel lblFechaVencimiento = new JLabel("FechaVencimiento:");
		lblFechaVencimiento.setBounds(41, 181, 118, 14);
		contentPane.add(lblFechaVencimiento);
		
		txtCategoria = new JTextField();
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(155, 209, 235, 20);
		contentPane.add(txtCategoria);
		
		JLabel lblCategoría = new JLabel("Categoria:");
		lblCategoría.setBounds(41, 212, 85, 14);
		contentPane.add(lblCategoría);
		
		txtFechaProduccion = new JTextField();
		txtFechaProduccion.setColumns(10);
		txtFechaProduccion.setBounds(155, 240, 235, 20);
		contentPane.add(txtFechaProduccion);
		
		JLabel lblFechaProduccion = new JLabel("FechaProducción:");
		lblFechaProduccion.setBounds(41, 243, 118, 14);
		contentPane.add(lblFechaProduccion);
		
		chkEnStock = new JCheckBox("En Stock");
		chkEnStock.setBounds(152, 283, 97, 23);
		contentPane.add(chkEnStock);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(208, 333, 102, 23);
		contentPane.add(btnRegistrar);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(e);
		}
	}
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		try {

			// 1. Capturar datos (se usa trim() para eliminar espacios al inicio y al final)
			String nombre = txtNombre.getText().trim();
			String precio = txtPrecio.getText().trim();
			String stock = txtStock.getText().trim();
			String fechaVencimiento = txtFechaVencimiento.getText().trim();
			String categoria = txtCategoria.getText().trim();
			String fechaProduccion = txtFechaProduccion.getText().trim();
			int estado = chkEnStock.isSelected() ? 1 : 0;

			// 2. Validar datos
			if (nombre.matches(ValidateUtil.TEXTO_45) == false) {
				JOptionPane.showMessageDialog(this, "El nombre no es válido. Tiene que tener de 1 a 45 caracteres");
				return;
			}

			if (precio.matches(ValidateUtil.REAL_CON_O_SIN_DECIMALES) == false) {
				JOptionPane.showMessageDialog(this, "El precio no es válido. Debe ser un número con hasta 2 decimales");
				return;
			}

			if (stock.matches(ValidateUtil.ENTERO_MAS_UN_DIGITO) == false) {
				JOptionPane.showMessageDialog(this, "El stock no es válido. Debe contener solo números");
				return;
			}

			if (fechaVencimiento.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
				JOptionPane.showMessageDialog(this, "La fecha de vencimiento no es válida. Tiene que tener el formato YYYY-MM-DD");
				return;
			}

			if (categoria.matches(ValidateUtil.TEXTO_45) == false) {
				JOptionPane.showMessageDialog(this, "La categoría no es válida. Tiene que tener de 1 a 45 caracteres");
				return;
			}

			if (fechaProduccion.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
				JOptionPane.showMessageDialog(this, "La fecha de producción no es válida. Tiene que tener el formato YYYY-MM-DD");
				return;
			}

            // 3. Crear objeto
            Producto p = new Producto();
            
            p.setNombre(nombre);
            p.setPrecio(Double.parseDouble(precio));
            p.setStock(Integer.parseInt(stock));
            p.setFechaVencimiento(LocalDate.parse(fechaVencimiento));
            p.setCategoria(categoria);
            p.setFechaProduccion(LocalDate.parse(fechaProduccion));
            p.setEstado(estado);
            
            // 4. Enviar al model
           ProductoModel model = new ProductoModel();
            int resultado = model.insertaProducto(p);

            // 5. Mensaje
            if (resultado > 0) {
                JOptionPane.showMessageDialog(this, "Producto registrado correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar Producto");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        }
    }
}
