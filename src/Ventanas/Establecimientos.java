package Ventanas;




import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Establecimientos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaPaginaPrincipal parent;


	/**
	 * Create the frame.
	 */
	public Establecimientos(VentanaPaginaPrincipal parent) {
		this.parent=parent;
		setTitle("Establecimientos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 928, 970);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{66, 85, 85, 0, 85, 85, 85, 85, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);



		try {

			ImageIcon icono = new ImageIcon(javax.imageio.ImageIO.read(getClass().getResourceAsStream("/imagenes/alojamiento.jpg")));
			Image imagenEscalada = icono.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
			ImageIcon iconoEscalado6 = new ImageIcon(imagenEscalada);

			JButton btnNewButton_4 = new JButton(iconoEscalado6);
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cconnection("Alojamiento");


				}
			});
			GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
			gbc_btnNewButton_4.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_4.gridx = 1;
			gbc_btnNewButton_4.gridy = 7;
			panel_1.add(btnNewButton_4, gbc_btnNewButton_4);


			ImageIcon icono2 = new ImageIcon(javax.imageio.ImageIO.read(getClass().getResourceAsStream("/imagenes/atletismo.jpg")));
			Image imagenEscalada2 = icono2.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
			ImageIcon iconoEscalado = new ImageIcon(imagenEscalada2);
			JButton btnNewButton_6 = new JButton(iconoEscalado);
			btnNewButton_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cconnection("Atletismo");

				}
			});
			GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
			gbc_btnNewButton_6.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_6.gridx = 5;
			gbc_btnNewButton_6.gridy = 13;
			panel_1.add(btnNewButton_6, gbc_btnNewButton_6);

			ImageIcon icono3 = new ImageIcon(javax.imageio.ImageIO.read(getClass().getResourceAsStream("/imagenes/futbol.jpg")));
			Image imagenEscalada3 = icono3.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
			ImageIcon iconoEscalado3 = new ImageIcon(imagenEscalada3);

			JButton btnNewButton_1 = new JButton(iconoEscalado3);			
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cconnection("Futbol");

				}
			});

			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnNewButton_1.gridx = 5;
			gbc_btnNewButton_1.gridy = 7;
			panel_1.add(btnNewButton_1, gbc_btnNewButton_1);

			ImageIcon icono4 = new ImageIcon(javax.imageio.ImageIO.read(getClass().getResourceAsStream("/imagenes/piscina.jpg")));
			Image imagenEscalada4 = icono4.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
			ImageIcon iconoEscalado2 = new ImageIcon(imagenEscalada4);

			JButton btnNewButton_2 = new JButton(iconoEscalado2);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cconnection("Piscina");

				}
			});

			GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
			gbc_btnNewButton_2.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_2.gridx = 1;
			gbc_btnNewButton_2.gridy = 13;
			panel_1.add(btnNewButton_2, gbc_btnNewButton_2);

			ImageIcon icono5 = new ImageIcon(javax.imageio.ImageIO.read(getClass().getResourceAsStream("/imagenes/gimnasia.jpg")));
			Image imagenEscalada5 = icono5.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
			ImageIcon iconoEscalado4 = new ImageIcon(imagenEscalada5);
			JButton btnNewButton_5 = new JButton(iconoEscalado4);
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cconnection("Gimnasia");

				}
			});
			GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
			gbc_btnNewButton_5.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_5.gridx = 1;
			gbc_btnNewButton_5.gridy = 16;
			panel_1.add(btnNewButton_5, gbc_btnNewButton_5);

			ImageIcon icono6 = new ImageIcon(javax.imageio.ImageIO.read(getClass().getResourceAsStream("/imagenes/rugby.jpg")));
			Image imagenEscalada6 = icono6.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
			ImageIcon iconoEscalado5 = new ImageIcon(imagenEscalada6);
			JButton btnNewButton_3 = new JButton(iconoEscalado5);
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cconnection("Rugby");

				}
			});
			GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
			gbc_btnNewButton_3.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_3.gridx = 5;
			gbc_btnNewButton_3.gridy = 16;
			panel_1.add(btnNewButton_3, gbc_btnNewButton_3);	
		} catch (Exception e) {
			System.err.println("Error cargando imagen: " + e.getMessage());
		}

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Establecimientos.this.setVisible(false);

				parent.setVisible(true);
			}
		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 18;
		panel_1.add(btnNewButton, gbc_btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("Piscina");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 12;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Pista de atletismo");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 5;
		gbc_lblNewLabel_3.gridy = 12;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Alojamiento");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Campo de fútbol");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 5;
		gbc_lblNewLabel_1.gridy = 4;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_4 = new JLabel("Gimnasia");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 15;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Campo de rugby");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 5;
		gbc_lblNewLabel_5.gridy = 15;
		panel_1.add(lblNewLabel_5, gbc_lblNewLabel_5);

		pack();  
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}
	public void cconnection(String nombre) {
		String url="jdbc:sqlite:BaseDatos.db";
		try(Connection conn = DriverManager.getConnection(url)) {
			Statement stant = conn.createStatement();

			switch(nombre) {
			case "Alojamiento":
				String slq="Select descripcion from lugar_act where nombre_lugar='Alojamiento'";
				ResultSet rs= stant.executeQuery(slq);

				String desc=rs.getString("descripcion");
				JOptionPane.showMessageDialog(Establecimientos.this,"Alojamientos: "+desc);


				rs.close();
				conn.close();
				break;
			case "Piscina":
				String slq2="Select descripcion from lugar_act where nombre_lugar='Piscina Olímpica'";
				ResultSet rs2= stant.executeQuery(slq2);

				String desc2=rs2.getString("descripcion");
				JOptionPane.showMessageDialog(Establecimientos.this,"Piscina: "+desc2);
				rs2.close();
				conn.close();


				break;
			case "Gimnasia":
				String slq3="Select descripcion from lugar_act where nombre_lugar='Gimnasio Polideportivo'";
				ResultSet rs3= stant.executeQuery(slq3);

				String desc3=rs3.getString("descripcion");
				JOptionPane.showMessageDialog(Establecimientos.this,"Gimnasia: "+desc3);
				rs3.close();
				conn.close();
				break;
			case "Atletismo":
				String slq4="Select descripcion from lugar_act where nombre_lugar='Atletismo'";
				ResultSet rs4= stant.executeQuery(slq4);

				String desc4=rs4.getString("descripcion");
				JOptionPane.showMessageDialog(Establecimientos.this,"Atletísmo: "+desc4);
				rs4.close();
				conn.close();
				break;
			case "Futbol":
				String slq5="Select descripcion from lugar_act where nombre_lugar='Campo de futbol'";
				ResultSet rs5= stant.executeQuery(slq5);

				String desc5=rs5.getString("descripcion");
				JOptionPane.showMessageDialog(Establecimientos.this,"Campo de fútbol: "+desc5);
				rs5.close();
				conn.close();
				break;
			case "Rugby":
				String slq6="Select descripcion from lugar_act where nombre_lugar='Campo de rugby'";
				ResultSet rs6= stant.executeQuery(slq6);

				String desc6=rs6.getString("descripcion");
				JOptionPane.showMessageDialog(Establecimientos.this,"Campo de rugby: "+desc6);
				rs6.close();
				conn.close();
				break;
			}



		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
