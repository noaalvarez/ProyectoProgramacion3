package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import java.awt.Color;

public class VentanaDarBaja extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<Usuario> modelo;
	private JList<Usuario> lista;
	private Usuario usuarioSeleccionado;
	



	/**
	 * Create the frame.
	 */
	public VentanaDarBaja(VentanaAdministrador parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelIzq = new JPanel();
		contentPane.add(panelIzq);
		panelIzq.setLayout(new BorderLayout(0, 0));
		

		
		JLabel lblNewLabel = new JLabel("LISTA USUARIOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelIzq.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panelLista = new JPanel();
		panelIzq.add(panelLista, BorderLayout.CENTER);
		panelLista.setLayout(null); 

		modelo = new DefaultListModel<>();
		lista = new JList<Usuario>(modelo);
		JScrollPane scrollPane = new JScrollPane(lista);
		scrollPane.setBounds(10, 10, 1400, 800); 

		panelLista.add(scrollPane);  
		JPanel panelDcha = new JPanel();
		contentPane.add(panelDcha);
		panelDcha.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelDcha1 = new JPanel();
		panelDcha.add(panelDcha1);
		
		JPanel panelDcha2 = new JPanel();
		panelDcha.add(panelDcha2);
		
		
		JPanel panelDcha3 = new JPanel();
		panelDcha.add(panelDcha3);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaDarBaja.this.setVisible(false);
				VentanaDarBaja.this.dispose();
				parent.setVisible(true);
			}
		});
		panelDcha3.add(btnVolver);
		pack();  
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	
		 
        lista.addListSelectionListener(e -> usuarioSeleccionado = lista.getSelectedValue());

        
        cargarUsuarios();
		
	
	
	

		        
	
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 if (usuarioSeleccionado == null) {
		                JOptionPane.showMessageDialog(VentanaDarBaja.this, "Selecciona un usuario para eliminar.");
		                return;
		            }

		            int confirm = JOptionPane.showConfirmDialog(VentanaDarBaja.this,
		                    "¿Seguro que quieres eliminar al usuario " + usuarioSeleccionado.getUsuario() + "?",
		                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

		            if (confirm != JOptionPane.YES_OPTION) return;

		            eliminarUsuario(usuarioSeleccionado);
		    }
		});
		   
		 btnVolver.addActionListener(e -> {
	            this.setVisible(false);
	            this.dispose();
	            parent.setVisible(true);
	        });
		
	
		panelDcha2.add(btnEliminar);
		btnEliminar.setBackground(new Color(240, 240, 240));
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
	}
		private void cargarUsuarios() {
			String url = "jdbc:sqlite:baseDatosProyecto.bd";
	        String sql = "SELECT * FROM usuario";
	        modelo.clear();

	        modelo.clear();

	        try (Connection conn = DriverManager.getConnection(url);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                int id = rs.getInt("id_usuario");
	                String nombre = rs.getString("nombre");
	                String apellidos = rs.getString("apellidos");
	                String usuario = rs.getString("usuario");
	                String contrasena = rs.getString("contrasena");

	                modelo.addElement(new Usuario(id,nombre, apellidos, usuario, contrasena));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error cargando usuarios: " + e.getMessage());
	        }
	    }

	    private void eliminarUsuario(Usuario usuario) {
	        String url = "jdbc:sqlite:baseDatosProyecto.bd";
	        String sql = "DELETE FROM usuario WHERE id_usuario = ?";

	        try (Connection conn = DriverManager.getConnection(url);
	             PreparedStatement pst = conn.prepareStatement(sql)) {

	           pst.setInt(1, usuario.getId_usuario());
	            int affectedRows = pst.executeUpdate();

	            if (affectedRows > 0) {
	                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
	                modelo.removeElement(usuario);
	                usuarioSeleccionado = null;
	            } else {
	                JOptionPane.showMessageDialog(this, "No se pudo eliminar el usuario.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error al eliminar usuario: " + e.getMessage());
	        }
	    }
	}