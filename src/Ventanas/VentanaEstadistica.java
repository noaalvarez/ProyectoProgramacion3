package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VentanaEstadistica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaAdministrador parent;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//				
//					VentanaEstadistica frame = new VentanaEstadistica(null);
//				
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaEstadistica(VentanaAdministrador parent) {
		this.parent=parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("Estadisticas del CAR");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("% Habitacion Ocupadas");
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_3.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 169, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblTrabajdores = new JLabel("% Entrenadores");
		GridBagConstraints gbc_lblTrabajdores = new GridBagConstraints();
		gbc_lblTrabajdores.anchor = GridBagConstraints.WEST;
		gbc_lblTrabajdores.insets = new Insets(0, 0, 5, 5);
		gbc_lblTrabajdores.gridx = 1;
		gbc_lblTrabajdores.gridy = 0;
		panel_2.add(lblTrabajdores, gbc_lblTrabajdores);
		
		JProgressBar progressBarEntrenadores = new JProgressBar();
		progressBarEntrenadores.setForeground(new Color(0, 128, 255));
		GridBagConstraints gbc_progressBarEntrenadores = new GridBagConstraints();
		gbc_progressBarEntrenadores.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBarEntrenadores.insets = new Insets(0, 0, 5, 5);
		gbc_progressBarEntrenadores.gridx = 1;
		gbc_progressBarEntrenadores.gridy = 1;
		panel_2.add(progressBarEntrenadores, gbc_progressBarEntrenadores);
		
		JLabel lblNewLabel = new JLabel("% Fisioterapeutas");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		JProgressBar progressBarFisioterapeuta = new JProgressBar();
		progressBarFisioterapeuta.setForeground(new Color(0, 128, 255));
		GridBagConstraints gbc_progressBarFisioterapeuta = new GridBagConstraints();
		gbc_progressBarFisioterapeuta.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBarFisioterapeuta.insets = new Insets(0, 0, 0, 5);
		gbc_progressBarFisioterapeuta.gridx = 1;
		gbc_progressBarFisioterapeuta.gridy = 3;
		panel_2.add(progressBarFisioterapeuta, gbc_progressBarFisioterapeuta);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 2;
		gbc_panel_4.gridy = 3;
		panel_2.add(panel_4, gbc_panel_4);
		
		JPanel panelAbajo = new JPanel();
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaEstadistica.this.dispose();
				parent.setVisible(true);
			}
		});
		panelAbajo.add(btnVolver);
		
		JProgressBar progressBarHabiOcupa = new JProgressBar();
		progressBarHabiOcupa.setForeground(new Color(0, 128, 255));
		GridBagConstraints gbc_progressBarHabiOcupa = new GridBagConstraints();
		gbc_progressBarHabiOcupa.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBarHabiOcupa.insets = new Insets(0, 0, 5, 5);
		gbc_progressBarHabiOcupa.gridx = 1;
		gbc_progressBarHabiOcupa.gridy = 1;
		panel_3.add(progressBarHabiOcupa, gbc_progressBarHabiOcupa);
		
		iniciarRellenoBarras(progressBarHabiOcupa,progressBarEntrenadores,progressBarFisioterapeuta);
	}
	private int calcularPorcentajeHabitacionesOcupadas() {
		String URL="jdbc:sqlite:baseDatosProyecto.bd";
		String sql = "SELECT Count(CASE WHEN ocupado = 1 THEN 1 END)*100/count(*) porcentaje FROM habitacion ";
		try (Connection conn = DriverManager.getConnection(URL);
		     PreparedStatement pstmt = conn.prepareStatement(sql);
		     ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				//System.out.println((int) Math.round(rs.getDouble("porcentaje")));
				return (int) Math.round(rs.getDouble("porcentaje"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0; // Valor por defecto si hay error
	}
	private int calcularPorcentajeEntrenadores() {
		// Ejemplo: Porcentaje de trabajadores por tipo (ajusta la consulta según tu BD)
		String URL="jdbc:sqlite:baseDatosProyecto.bd";

		String sqlBuscar = "SELECT Count(id_entrenador) *100/10 porcentaje FROM entrenador";
		try (Connection conn = DriverManager.getConnection(URL);
		     PreparedStatement pstmt = conn.prepareStatement(sqlBuscar);
		     ResultSet rs = pstmt.executeQuery()) {
			if(rs.next()) {
				int numEntrenador = rs.getInt("porcentaje");
				//System.out.println("Número de entrenadores encontrados: " + numEntrenador); // Para depuración
				return numEntrenador;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return 0;

	}
	private int calcularPorcentajeFisioterapeuta() {
		// Ejemplo: Porcentaje de trabajadores por tipo (ajusta la consulta según tu BD)
		String URL="jdbc:sqlite:baseDatosProyecto.bd";

		String sqlBuscar = "SELECT Count(id_fisio) *100/10 porcentaje FROM fisioterapeuta";
		try (Connection conn = DriverManager.getConnection(URL);
		     PreparedStatement pstmt = conn.prepareStatement(sqlBuscar);
		     ResultSet rs = pstmt.executeQuery()) {
			if(rs.next()) {
				int numFisio = rs.getInt("porcentaje");
				//System.out.println("Número de fisios encontrados: " + numFisio); // Para depuración
				return numFisio;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return 0;

	}
	
	private void iniciarRellenoBarras(JProgressBar bar1, JProgressBar bar2,JProgressBar bar3) {
		// Calcular porcentajes desde la BD
		int porcentajeHabitaciones = calcularPorcentajeHabitacionesOcupadas();
		int porcentajeEntrenadores = calcularPorcentajeEntrenadores();
		int porcentajeFisioterapeuta=calcularPorcentajeFisioterapeuta();
		// Hilo para la barra de habitaciones ocupadas
		Thread hiloHabitaciones = new Thread(() -> {
			for (int i = 0; i <= porcentajeHabitaciones; i++) {
				final int value = i;
				SwingUtilities.invokeLater(() -> bar1.setValue(value));
				try {
					Thread.sleep(50); // Retraso para animación (50 ms por paso)
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		
		// Hilo para la barra de trabajadores
		Thread hiloTrabajadores = new Thread(() -> {
			for (int i = 0; i <= porcentajeEntrenadores; i++) {
				final int value = i;
				SwingUtilities.invokeLater(() -> bar2.setValue(value));
				try {
					Thread.sleep(50); // Retraso para animación (50 ms por paso)
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		Thread hiloFisioterapeutas = new Thread(() -> {
			for (int i = 0; i <= porcentajeFisioterapeuta; i++) {
				final int value = i;
				SwingUtilities.invokeLater(() -> bar3.setValue(value));
				try {
					Thread.sleep(50); // Retraso para animación (50 ms por paso)
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		
		// Iniciar los hilos
		hiloHabitaciones.start();
		hiloTrabajadores.start();
		hiloFisioterapeutas.start();
	}
}


