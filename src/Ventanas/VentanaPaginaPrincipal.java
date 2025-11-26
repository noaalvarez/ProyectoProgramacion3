package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class VentanaPaginaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPaginaPrincipal frame = new VentanaPaginaPrincipal();
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
	public VentanaPaginaPrincipal() {
		setTitle("CAR Euskadi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1296, 974);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel JPanelDeMenu = new JPanel();
		contentPane.add(JPanelDeMenu, BorderLayout.NORTH);


		JPanelDeMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		JPanelDeMenu.add(menuBar);

		JMenu mnNewMenu = new JMenu("Información");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Establecimientos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Establecimientos ventanaEstablecimiento = new Establecimientos(VentanaPaginaPrincipal.this);
				ventanaEstablecimiento.setVisible(true);
				VentanaPaginaPrincipal.this.setVisible(false);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Historia");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Historia ventanaHistoria = new Historia(VentanaPaginaPrincipal.this);
				ventanaHistoria.setVisible(true);
				VentanaPaginaPrincipal.this.setVisible(false);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Contactanos");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contacto ventanaContacto = new Contacto(VentanaPaginaPrincipal.this);
				ventanaContacto.setVisible(true);
				VentanaPaginaPrincipal.this.setVisible(false);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Iniciar sesión");
		mntmNewMenuItem_3.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaIniciarSes parent = new VentanaIniciarSes(VentanaPaginaPrincipal.this);
				parent.setVisible(true);
				VentanaPaginaPrincipal.this.setVisible(false);
			}
		});
		JPanelDeMenu.add(mntmNewMenuItem_3);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		pack();  
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}
}

