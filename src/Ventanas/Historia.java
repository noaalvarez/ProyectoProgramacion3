package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;

public class Historia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaPaginaPrincipal parent;



	/**
	 * Create the frame.
	 */
	public Historia(VentanaPaginaPrincipal parent) {
		this.parent=parent;
		setTitle("Historia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("CAR Euskadi");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Siempre mostrar barra vertical
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Nunca mostrar barra horizontal

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setTabSize(100);
		textArea.setEditable(false);
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setBackground(new Color(240, 240, 240));
		scrollPane.setViewportView(textArea);
		textArea.setText( connect());
		textArea.setLineWrap(true); // Activa el ajuste de línea para que el texto no exceda el ancho
        textArea.setWrapStyleWord(true); // Ajusta por palabras completas (opcional, pero recomendado)

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Historia.this.setVisible(false);

				parent.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnNewButton);

		pack();  
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}
	public String connect() {
		String url="jdbc:sqlite:BaseDatos.db";
		String desc="";
		try(Connection conn = DriverManager.getConnection(url)) {
			Statement stant = conn.createStatement();
			String slq="Select * from historia";
			ResultSet rs= stant.executeQuery(slq);
			desc=rs.getString("descripcion");
			rs.close();
			conn.close();

		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return desc;

	}
}
