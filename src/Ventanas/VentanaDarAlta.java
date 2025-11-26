package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class VentanaDarAlta extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField contrasena;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDeporte;


	/**
	 * Create the frame.
	 */
	public VentanaDarAlta(VentanaAdministrador parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 150, 352, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panelArriba = new JPanel();
		panel.add(panelArriba);
		panelArriba.setLayout(new BorderLayout(0, 0));
		
		JLabel lbltituloLogin = new JLabel("DAR ALTA");
		lbltituloLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbltituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panelArriba.add(lbltituloLogin, BorderLayout.CENTER);
		
		JPanel panelCentral = new JPanel();
		panel.add(panelCentral);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelCentral.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JLabel lblNuevoUsuario = new JLabel("Nuevo usuario: ");
		GridBagConstraints gbc_lblNuevoUsuario = new GridBagConstraints();
		gbc_lblNuevoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNuevoUsuario.gridx = 1;
		gbc_lblNuevoUsuario.gridy = 0;
		panelCentral.add(lblNuevoUsuario, gbc_lblNuevoUsuario);
		
		txtUsuario = new JTextField();
		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsuario.gridx = 3;
		gbc_txtUsuario.gridy = 0;
		panelCentral.add(txtUsuario, gbc_txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panelCentral.add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		panelCentral.add(txtNombre, gbc_txtNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 2;
		panelCentral.add(lblApellidos, gbc_lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		GridBagConstraints gbc_txtApellidos = new GridBagConstraints();
		gbc_txtApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_txtApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellidos.gridx = 3;
		gbc_txtApellidos.gridy = 2;
		panelCentral.add(txtApellidos, gbc_txtApellidos);
		
		JLabel lblcontraseña = new JLabel("Contraseña: ");
		GridBagConstraints gbc_lblcontraseña = new GridBagConstraints();
		gbc_lblcontraseña.insets = new Insets(0, 0, 5, 5);
		gbc_lblcontraseña.gridx = 1;
		gbc_lblcontraseña.gridy = 3;
		panelCentral.add(lblcontraseña, gbc_lblcontraseña);
		
		contrasena = new JPasswordField();
		GridBagConstraints gbc_contrasena = new GridBagConstraints();
		gbc_contrasena.insets = new Insets(0, 0, 5, 5);
		gbc_contrasena.fill = GridBagConstraints.HORIZONTAL;
		gbc_contrasena.gridx = 3;
		gbc_contrasena.gridy = 3;
		panelCentral.add(contrasena, gbc_contrasena);
		
		JPanel panelAbajo = new JPanel();
		panel.add(panelAbajo);
		panelAbajo.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panelAbajo2_1 = new JPanel();
		panelAbajo.add(panelAbajo2_1);
		panelAbajo2_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelAbajo2_2 = new JPanel();
		panelAbajo2_1.add(panelAbajo2_2);
		
		JCheckBox chckbxDeportista = new JCheckBox("Deportista");
		panelAbajo2_2.add(chckbxDeportista);
		
		JCheckBox chckbxEntrenador = new JCheckBox("Entrenador");
		panelAbajo2_2.add(chckbxEntrenador);
		
		JCheckBox chckbxFisioterapeuta = new JCheckBox("Fisioterapeuta");
		panelAbajo2_2.add(chckbxFisioterapeuta);
		
		JPanel panelAbajo1_1 = new JPanel();
		panelAbajo.add(panelAbajo1_1);
		
		JPanel panelAbajo2_1_1 = new JPanel();
		panelAbajo1_1.add(panelAbajo2_1_1);
		panelAbajo2_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAlta = new JButton("Dar Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre=txtNombre.getText();
				String apellidos=txtApellidos.getText();
				String usuario=txtUsuario.getText();
				String contra=String.copyValueOf(contrasena.getPassword());
				if(!chckbxDeportista.isSelected()&&!chckbxEntrenador.isSelected()&&!chckbxFisioterapeuta.isSelected()) {
					JOptionPane.showMessageDialog(null, "Selecciona un tipo de usuario.");
				}
				if(chckbxDeportista.isSelected()&&chckbxEntrenador.isSelected()&&chckbxFisioterapeuta.isSelected()) {
					JOptionPane.showMessageDialog(null, "Solo puedes elegir uno.");
				}
				if((chckbxDeportista.isSelected()&&chckbxEntrenador.isSelected())||(chckbxDeportista.isSelected()&&chckbxFisioterapeuta.isSelected())||chckbxEntrenador.isSelected()&&chckbxFisioterapeuta.isSelected()) {
					JOptionPane.showMessageDialog(null, "Solo puedes elegir uno.");
				}
				if(nombre.equals("")||apellidos.equals("")||usuario.equals("")||contra.equals("")) {
					JOptionPane.showMessageDialog(null, "Rellena todos los campos");
				}
				
				if(chckbxDeportista.isSelected()) {
					 String texto = txtDeporte.getText().toUpperCase(); 
					 if(texto.equals("FUTBOL")||texto.equals("BALONCESTO")||texto.equals("GIMNASIA")||texto.equals("NATACION")||texto.equals("RUGBY")||texto.equals("ATLETISMO")) {
					 Deporte deporte = Deporte.valueOf(texto);
					 Deportista deportista=new Deportista(nombre,apellidos,usuario,contra,deporte);
					 }else {
						 JOptionPane.showMessageDialog(null, "Escribe en mayuscula y bien el deporte correcto");
					 }
				}else if(chckbxEntrenador.isSelected()) {		 
					 String texto = txtDeporte.getText().toUpperCase(); 
					 if(texto.equals("FUTBOL")||texto.equals("BALONCESTO")||texto.equals("GIMNASIA")||texto.equals("NATACION")||texto.equals("RUGBY")||texto.equals("ATLETISMO")) {
					 Deporte deporte = Deporte.valueOf(texto);
					 Entrenador entrenador=new Entrenador(nombre,apellidos,usuario,contra,deporte);
					 }else {
						 JOptionPane.showMessageDialog(null, "Escribe en mayuscula y bien el deporte correcto");
					 }
				}else if(chckbxFisioterapeuta.isSelected()) {
					Fisioterapeuta fisioterapeuta=new Fisioterapeuta(nombre, apellidos, usuario, contra);
				}
				
				String sqlUsuario = "INSERT INTO usuario(nombre, apellidos, usuario, contrasena) VALUES (?, ?, ?, ?)";
				String url="jdbc:sqlite:baseDatosProyecto.bd";
				

				try (Connection conn = DriverManager.getConnection(url);
					     PreparedStatement pstUsuario = conn.prepareStatement(sqlUsuario)) {
					String sqlCheck = "SELECT COUNT(*) FROM usuario WHERE usuario = ?";
					try (PreparedStatement pstCheck = conn.prepareStatement(sqlCheck)) {
					    pstCheck.setString(1, usuario);
					    try (ResultSet rs = pstCheck.executeQuery()) {
					        if (rs.next() && rs.getInt(1) > 0) {
					            JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe, elige otro");
					            return; // Salir del ActionListener
					        }
					    }
					}
				    pstUsuario.setString(1, nombre);
				    pstUsuario.setString(2, apellidos);
				    pstUsuario.setString(3, usuario);
				    pstUsuario.setString(4, contra);

				    int affectedRows = pstUsuario.executeUpdate();
				    int idUsuario = -1;
				    try (Statement stmt = conn.createStatement();
				         ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
				        if (rs.next()) {
				            idUsuario = rs.getInt(1);
				        } else {
				            throw new SQLException("No se pudo obtener id_usuario");
				        }
				    }

				    if (affectedRows == 0) {
				        throw new SQLException("Error al insertar usuario");
				    }

				    try (Statement stmt = conn.createStatement();
				    	     ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
				    	    if (rs.next()) {
				    	        idUsuario = rs.getInt(1);
				    	    } else {
				    	        throw new SQLException("No se pudo obtener id_usuario");
				    	    }
				    	}

				    if (chckbxDeportista.isSelected()) {
				        // Primero obtener id_deporte
				        String deporteTexto = txtDeporte.getText().toUpperCase();
				        int idDeporte = -1;
				        String sqlDeporte = "SELECT id_deporte FROM deporte WHERE tipo = ?";
				        try (PreparedStatement pstDeporte = conn.prepareStatement(sqlDeporte)) {
				            pstDeporte.setString(1, deporteTexto);
				            ResultSet rs = pstDeporte.executeQuery();
				            if (rs.next()) {
				                idDeporte = rs.getInt("id_deporte");
				            } else {
				                JOptionPane.showMessageDialog(null, "Deporte no válido");
				                return;
				            }
				        }

				        // Elegimos una actividad y una habitación (puedes adaptar esto)
				        int idActividad = 1; // por defecto o lógica propia
				        int idHabitacion = 1; // por defecto o lógica propia

				        String sqlDeportista = "INSERT INTO deportista(id_usuario, id_actividad, id_deporte, id_habitacion) VALUES (?, ?, ?, ?)";
				        try (PreparedStatement pstDep = conn.prepareStatement(sqlDeportista)) {
				            pstDep.setInt(1, idUsuario);
				            pstDep.setInt(2, idActividad);
				            pstDep.setInt(3, idDeporte);
				            pstDep.setInt(4, idHabitacion);
				            pstDep.executeUpdate();
				        }

				    } else if (chckbxEntrenador.isSelected()) {
				        // Similar a deportista pero tabla entrenador
				        int idDeporte = 1; // lógica para seleccionar el deporte
				        int idHabitacion = 1;

				        String sqlEntrenador = "INSERT INTO entrenador(id_usuario, id_deporte, id_habitacion) VALUES (?, ?, ?)";
				        try (PreparedStatement pstEnt = conn.prepareStatement(sqlEntrenador)) {
				            pstEnt.setInt(1, idUsuario);
				            pstEnt.setInt(2, idDeporte);
				            pstEnt.setInt(3, idHabitacion);
				            pstEnt.executeUpdate();
				        }

				    } else if (chckbxFisioterapeuta.isSelected()) {
				        int idHabitacion = 1; // lógica propia
				        String sqlFisio = "INSERT INTO fisioterapeuta(id_usuario, id_habitacion) VALUES (?, ?)";
				        try (PreparedStatement pstFisio = conn.prepareStatement(sqlFisio)) {
				            pstFisio.setInt(1, idUsuario);
				            pstFisio.setInt(2, idHabitacion);
				            pstFisio.executeUpdate();
				        }
		            }

		            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");

		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al registrar usuario: " + ex.getMessage());
		        }
		    }
		});
	
	
		
		panelAbajo2_1_1.add(btnAlta);
	
		
		JButton btnVolver_1 = new JButton("Volver");
		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				parent.repaint();
				VentanaDarAlta.this.setVisible(false);
			}
		});
		panelAbajo2_1_1.add(btnVolver_1);
		
		JPanel panel_1 = new JPanel();
		panelAbajo.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Deporte: ");
		panel_1.add(lblNewLabel);
		
		txtDeporte = new JTextField();
		panel_1.add(txtDeporte);
		txtDeporte.setColumns(10);
		
		pack();  
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	
	}

}
