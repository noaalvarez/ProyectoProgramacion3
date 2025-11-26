package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public VentanaAdministrador(VentanaIniciarSes parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelArriba = new JPanel();
		contentPane.add(panelArriba, BorderLayout.CENTER);
		panelArriba.setLayout(new GridLayout(5, 0, 0, 0));
		
		JButton btnDarAlta = new JButton("DAR ALTA");
		btnDarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaDarAlta ventanaAlta=new VentanaDarAlta(VentanaAdministrador.this);
				ventanaAlta.setVisible(true);
				VentanaAdministrador.this.setVisible(false);
				VentanaAdministrador.this.dispose();
			}
		});
		btnDarAlta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelArriba.add(btnDarAlta);
		
		JButton btnDarBaja = new JButton("DAR BAJA");
		btnDarBaja.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelArriba.add(btnDarBaja);
		btnDarBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaDarBaja ventanaBaja=new VentanaDarBaja(VentanaAdministrador.this);
				ventanaBaja.setVisible(true);
				VentanaAdministrador.this.setVisible(false);
				VentanaAdministrador.this.dispose();
			}
		});
		
		JButton btnDesasignar = new JButton("DESASIGNAR ALOJAMIENTO");
		btnDesasignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnEstadisticas = new JButton("ESTADISTICAS\r\n");
		btnEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
						VentanaEstadistica ventanaEst = new VentanaEstadistica(VentanaAdministrador.this);
						ventanaEst.setVisible(true);
						VentanaAdministrador.this.setVisible(false);
						VentanaAdministrador.this.dispose();
	
			}
		});
		btnEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelArriba.add(btnEstadisticas);
		
		JButton btnAsignar = new JButton("ASIGNAR ALOJAMIENTO");
		btnAsignar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelArriba.add(btnAsignar);
		btnDesasignar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelArriba.add(btnDesasignar);
		
	
		
		
		JPanel panelAbajo = new JPanel();
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdministrador.this.setVisible(false);;
				parent.setVisible(true);
				parent.repaint();
			}
		});
		panelAbajo.add(btnVolver);
	}

}
