package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaIniciarSes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordFieldContraseña;

	// RadioButtons
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnEntrenador;
	private JRadioButton rdbtnFisio;
	private JRadioButton rdbtnDeportista;
	private ButtonGroup grupoRoles;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIniciarSes frame = new VentanaIniciarSes(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaIniciarSes(JFrame parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 1, 0, 0));

		// --- 1. TÍTULO ---
		JPanel panelArriba = new JPanel();
		panel.add(panelArriba);
		panelArriba.setLayout(new BorderLayout(0, 0));

		JLabel lbltituloLogin = new JLabel("INICIAR SESIÓN");
		lbltituloLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbltituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panelArriba.add(lbltituloLogin, BorderLayout.CENTER);

		// --- 2. FORMULARIO ---
		JPanel panelCentral = new JPanel();
		panel.add(panelCentral);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelCentral.rowHeights = new int[]{0, 0, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);

		JLabel lblusuario = new JLabel("Usuario:");
		GridBagConstraints gbc_lblusuario = new GridBagConstraints();
		gbc_lblusuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblusuario.gridx = 1;
		gbc_lblusuario.gridy = 0;
		panelCentral.add(lblusuario, gbc_lblusuario);

		txtUsuario = new JTextField();
		GridBagConstraints gbc_textFieldUsuario = new GridBagConstraints();
		gbc_textFieldUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsuario.gridx = 3;
		gbc_textFieldUsuario.gridy = 0;
		panelCentral.add(txtUsuario, gbc_textFieldUsuario);
		txtUsuario.setColumns(10);

		JLabel lblcontraseña = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblcontraseña = new GridBagConstraints();
		gbc_lblcontraseña.insets = new Insets(0, 0, 0, 5);
		gbc_lblcontraseña.gridx = 1;
		gbc_lblcontraseña.gridy = 1;
		panelCentral.add(lblcontraseña, gbc_lblcontraseña);

		passwordFieldContraseña = new JPasswordField();
		GridBagConstraints gbc_passwordFieldContraseña = new GridBagConstraints();
		gbc_passwordFieldContraseña.insets = new Insets(0, 0, 0, 5);
		gbc_passwordFieldContraseña.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldContraseña.gridx = 3;
		gbc_passwordFieldContraseña.gridy = 1;
		panelCentral.add(passwordFieldContraseña, gbc_passwordFieldContraseña);


		JPanel panelAbajo = new JPanel();
		panel.add(panelAbajo);
		panelAbajo.setLayout(new GridLayout(2, 1, 0, 0));


		JPanel panelRadios = new JPanel();
		panelAbajo.add(panelRadios);
		panelRadios.setLayout(new GridLayout(2, 2, 0, 0));

		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnAdmin.setSelected(true);
		panelRadios.add(rdbtnAdmin);

		rdbtnEntrenador = new JRadioButton("Entrenador");
		rdbtnEntrenador.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadios.add(rdbtnEntrenador);

		rdbtnFisio = new JRadioButton("Fisioterapeuta");
		rdbtnFisio.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadios.add(rdbtnFisio);

		rdbtnDeportista = new JRadioButton("Deportista");
		rdbtnDeportista.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadios.add(rdbtnDeportista);

		grupoRoles = new ButtonGroup();
		grupoRoles.add(rdbtnAdmin);
		grupoRoles.add(rdbtnEntrenador);
		grupoRoles.add(rdbtnFisio);
		grupoRoles.add(rdbtnDeportista);


		JPanel panelBotones = new JPanel();
		panelAbajo.add(panelBotones);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnIniciarSes = new JButton("Iniciar Sesión");
		btnIniciarSes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarCredenciales();
			}
		});
		panelBotones.add(btnIniciarSes);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaIniciarSes.this.dispose();
				if (parent != null) {
					parent.setVisible(true);
				}
			}
		});
		panelBotones.add(btnVolver);
	}

	/**
	 * Método principal de lógica de login.
	 */
	private void verificarCredenciales() {
		String usuario = txtUsuario.getText();
		String contraseña = String.valueOf(passwordFieldContraseña.getPassword());

		if (usuario.isEmpty() || contraseña.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Rellene todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// 1. Determinar rol seleccionado
		String rolSeleccionado = "";
		if (rdbtnAdmin.isSelected()) rolSeleccionado = "Admin";
		else if (rdbtnEntrenador.isSelected()) rolSeleccionado = "Entrenador";
		else if (rdbtnFisio.isSelected()) rolSeleccionado = "Fisioterapeuta";
		else if (rdbtnDeportista.isSelected()) rolSeleccionado = "Deportista";

		String url = "jdbc:sqlite:baseDatosProyecto.bd";

		try (Connection conn = DriverManager.getConnection(url)) {

			// 2. Consulta con JOINS
			String sql = "SELECT u.nombre, u.apellidos, " +
					"CASE " +
					"  WHEN d.id_usuario IS NOT NULL THEN 'Deportista' " +
					"  WHEN e.id_usuario IS NOT NULL THEN 'Entrenador' " +
					"  WHEN f.id_usuario IS NOT NULL THEN 'Fisioterapeuta' " +
					"  WHEN a.id_usuario IS NOT NULL THEN 'Admin' " + 
					"  ELSE 'Ninguno' " +
					"END as RolReal " +
					"FROM usuario u " +
					"LEFT JOIN deportista d ON u.id_usuario = d.id_usuario " +
					"LEFT JOIN entrenador e ON u.id_usuario = e.id_usuario " +
					"LEFT JOIN fisioterapeuta f ON u.id_usuario = f.id_usuario " +
					"LEFT JOIN administrador a ON u.id_usuario = a.id_usuario " + 
					"WHERE u.usuario = ? AND u.contrasena = ?";

			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setString(1, usuario);
				pst.setString(2, contraseña);

				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					String nombre = rs.getString("nombre");
					String apellidos = rs.getString("apellidos");
					String rolReal = rs.getString("RolReal");

					// Comparar roles
					if (rolSeleccionado.equalsIgnoreCase(rolReal)) {
						JOptionPane.showMessageDialog(this, "¡Bienvenido " + nombre + " " + apellidos + "!");


						abrirVentanaSegunRol(rolReal);

					} else {
						if (rolReal.equals("Ninguno")) {
							JOptionPane.showMessageDialog(this, "Usuario sin rol asignado.", "Error", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(this, 
									"Usted NO es " + rolSeleccionado + ".\nSu rol es: " + rolReal, 
									"Error de Rol", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error BD: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Método encargado EXCLUSIVAMENTE de cerrar esta ventana y abrir la siguiente.
	 */
	private void abrirVentanaSegunRol(String rol) {

		this.dispose();


		try {
			switch (rol) {
			case "Admin":
				new VentanaAdministrador(this).setVisible(true);
				break;
			case "Entrenador":
				new VentanaEntrenador(this).setVisible(true);
				break;
			case "Deportista":
				new VentanaDeportista(this).setVisible(true);
				break;
			case "Fisioterapeuta":
				new VentanaFisioterapeuta(this).setVisible(true);
			}
		}}
}