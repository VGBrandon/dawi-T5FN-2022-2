package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCodigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTable tblSalida;
	
	//Agregando valor global de la tabla

	DefaultTableModel modelo = new DefaultTableModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 693);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(173, 335, 89, 23);
		contentPane.add(btnListado);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(324, 63, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 378, 414, 265);
		contentPane.add(scrollPane_1);
		
		tblSalida = new JTable();
		scrollPane_1.setViewportView(tblSalida);
		//Agregando tabla
		tblSalida.setModel(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Producto");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio");
		modelo.addColumn("Categoria");
		modelo.addColumn("Provedor");
		
		llenaCombo();
	}

	void llenaCombo() {
		
		//Conexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//Llenado de combo categoria
		List<Categoria> lstCategorias = em.createQuery("Select c from  Categoria c", Categoria.class).getResultList();
				cboCategorias.addItem("Seleccione...");
		for(Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());
		}
		
		//Llenado de combo proveedor
		List<Proveedor> lstProveedores =em.createQuery("Select p from Proveedor p", Proveedor.class).getResultList();
		cboProveedores.addItem("Seleccione...");
		for(Proveedor p : lstProveedores) {
			cboProveedores.addItem(p.getNombre_rs());
		}
		
		//cerrar
		em.close();
	}

	void registrar() {
		// Leer campos del formulario
		String codigo	 = leerCodigo();
		String nombre = txtDescripcion.getText();
		int  stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int categoria = cboCategorias.getSelectedIndex();
		int proveedor = cboProveedores.getSelectedIndex();	
		
		//Generar objeto nuevo de lo que queremos grabar
		Producto p = new Producto();
		p.setId_prod(codigo);
		p.setDes_prod(nombre);
		p.setPre_prod(precio);
		p.setStk_prod(stock);
		p.setIdcategoria(categoria);
		p.setIdproveedor(proveedor);
		p.setEst_prod(1);//valor fijo x default
		try {
			//guardar objeto en la tabla
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
			EntityManager em = fabrica.createEntityManager();
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			//mostrar los mensajes de exito o error
			aviso("Producto registrado...!", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			aviso("Error al registrar...!", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

	void listado() {
		// TODO Auto-generated method stub
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//proceso --> consulta -> no lleva ...begin()
		//select * from tb_xxxx --> coleccion List / Array
		//lista de java utils
		// Para ser consultas hay varias formas
		//createNamedQuery ->ejecuta consulta asociada a un nombre
		//createNativeQuery -> ejecuta sentencias propiamente sql -> recomendable
		//cuando las tablas no estan normalizadas
		//createQuery -> ejecuta sentencias sql normalizadas utilizando sintanxis jpa(entidades) usando  jpql
		List<Producto> lstProducto =em.createQuery("select p from Producto p",Producto.class).getResultList();
		
		//mostrar el resultado (lista)
		System.out.println("-------------------------------------------------");
		for (Producto p : lstProducto) {
			imprimir("*******************" + "\n") ;
			imprimir("Id Producto : " + p.getId_prod()) ;
			imprimir("Descripcion : " + p.getDes_prod());
			imprimir("Stock : " + p.getStk_prod());
			imprimir("Precio : " + p.getPre_prod()) ;
			imprimir("Categoria : " + p.getIdcategoria()+ " - " + p.getObjCategoria().getDescripcion()) ;
			imprimir("Estado : " + p.getEst_prod()) ;
			imprimir("Id Proveedor : " + p.getIdproveedor()+ " - " + p.getObjProveedor().getNombre_rs()) ;
	
			//Para la tabla
			Object datos[] = {p.getId_prod(),p.getDes_prod(),p.getStk_prod(),p.getPre_prod(),
							  p.getIdcategoria()+ " - " + p.getObjCategoria(),p.getDes_prod(),
							  p.getIdproveedor()+ " - "+ p.getObjProveedor().getNombre_rs()};
		}
		
		//cerrar
		em.close();
		
	}
	
	void buscar() {
		// leer codigo
		String codigo = leerCodigo();
		
		//obtener un producto segun el codigo ingresado --> consulta
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//puedes usar el consulta de la clase pasada pero este al ser un objeto es mucho mejor usar find ya que este siempre busca por id
		//Recuerda si buscas algo por codigo usar find 
		Producto p = em.find(Producto.class, codigo );
		
		//si el codigo no existe muestra un mensaje, sino muestra los datos del producto
		if(p == null) {
			aviso("Codigo no encontrado o no existe", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
		}else {
			txtDescripcion.setText(p.getDes_prod());
			txtStock.setText(p.getStk_prod()+ "");
			cboCategorias.setSelectedIndex(p.getIdcategoria());
			cboProveedores.setSelectedIndex(p.getIdproveedor());
			txtPrecio.setText(p.getPre_prod()+ "");				
		}
	}
	
	//Metodos
	
	
	private String leerCodigo() {
		//validaciones
		if(txtCodigo.getText().isEmpty()) {
			aviso("Debe ingresar un Codigo... ","Error en campo", JOptionPane.ERROR_MESSAGE);
			return null;//debe mandar un valor de retorno
		}
		return txtCodigo.getText();
	}
	
	private void aviso(String msg, String titulo, int icono) {
		JOptionPane.showMessageDialog(this,msg, titulo, icono);
	}
	
	void imprimir(String s) {
			txtSalida.append(s+"\n");
	}
}
