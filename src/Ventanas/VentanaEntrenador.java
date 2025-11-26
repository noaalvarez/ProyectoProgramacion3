package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.LocalDate;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;

public class VentanaEntrenador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Panel1;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEntrenador frame = new VentanaEntrenador(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param ventanaIniciarSes 
	 */
	public VentanaEntrenador(VentanaIniciarSes ventanaIniciarSes) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 431);
		Panel1 = new JPanel();
		Panel1.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Panel1);
		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[]{536, 0};
		gbl_panel1.rowHeights = new int[]{192, 192, 0};
		gbl_panel1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		Panel1.setLayout(gbl_panel1);
		
		JPanel Panel2 = new JPanel();
		GridBagConstraints gbc_Panel2 = new GridBagConstraints();
		gbc_Panel2.fill = GridBagConstraints.BOTH;
		gbc_Panel2.insets = new Insets(0, 0, 5, 0);
		gbc_Panel2.gridx = 0;
		gbc_Panel2.gridy = 0;
		Panel1.add(Panel2, gbc_Panel2);
		Panel2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCalendario = new JLabel("CALENDARIO");
		lblCalendario.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCalendario.setHorizontalAlignment(SwingConstants.CENTER);
		Panel2.add(lblCalendario, BorderLayout.NORTH);
		
	
		String[] dias = {"L", "M", "X", "J", "V", "S", "D"};
		Object[][] datos = {
		    {1, 2, 3, 4, 5, 6, 7},
		    {8, 9, 10, 11, 12, 13, 14},
		    {15, 16, 17, 18, 19, 20, 21},
		    {22, 23, 24, 25, 26, 27, 28},
		    {29, 30, 31, null, null, null, null}
		};

		table = new JTable(datos, dias);
		table.setCellSelectionEnabled(true);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5), new Integer(6), new Integer(7)},
				{new Integer(8), new Integer(9), new Integer(10), new Integer(11), new Integer(12), new Integer(13), new Integer(14)},
				{new Integer(15), new Integer(16), new Integer(17), new Integer(18), new Integer(19), new Integer(20), new Integer(21)},
				{new Integer(22), new Integer(23), new Integer(24), new Integer(25), new Integer(26), new Integer(27), new Integer(28)},
				{new Integer(29), new Integer(30), new Integer(31), null, null, null, null},
			},
			new String[] {
				"L", "Ma", "Mi", "J", "V", "S", "D"
			}
		));
		table.setBackground(new Color(255, 128, 192));
		//Panel2.add(table, BorderLayout.SOUTH);

		table.setRowHeight(30);
		table.setGridColor(new Color(0, 0, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(table.getBounds());
		Panel2.add(scrollPane);
		
		
		JPanel Panel3 = new JPanel();
		GridBagConstraints gbc_Panel3 = new GridBagConstraints();
		gbc_Panel3.fill = GridBagConstraints.BOTH;
		gbc_Panel3.gridx = 0;
		gbc_Panel3.gridy = 1;
		Panel1.add(Panel3, gbc_Panel3);
		Panel3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		Panel3.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblProxAct = new JLabel("PRÓXIMA ACTIVIDAD\r\n");
		lblProxAct.setForeground(new Color(0, 0, 0));
		lblProxAct.setBackground(new Color(255, 255, 255));
		lblProxAct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProxAct.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblProxAct);
		
		JLabel lblNewLabel = new JLabel("........");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblConDep = new JLabel("Con deportistas:");
		lblConDep.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblConDep);
		
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nombre", "Apellidos"
				});
		
		table_1 = new JTable(modelo);
				
		
		panel_9.add(table_1);
		JScrollPane scrollPane2 = new JScrollPane(table_1);
		scrollPane2.setBounds(table_1.getBounds());
		
		panel_9.add(scrollPane2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		Panel3.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAlojamiento = new JLabel("ALOJAMIENTO");
		lblAlojamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlojamiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_4.add(lblAlojamiento);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		
		JLabel lblNumHab = new JLabel("Numero de habitación:");
		lblNumHab.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_6.add(lblNumHab);
		
		JLabel lblNumHabCalc = new JLabel("");
		lblNumHabCalc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_6.add(lblNumHabCalc);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		
		JLabel lblPiso = new JLabel("Piso:");
		lblPiso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_7.add(lblPiso);
		
		JLabel lblPisoCalc = new JLabel("");
		lblPisoCalc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_7.add(lblPisoCalc);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8);
		
		JLabel lblEdificio = new JLabel("Edificio:");
		lblEdificio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_8.add(lblEdificio);
		
		JLabel lblEdifCalc = new JLabel("");
		panel_8.add(lblEdifCalc);
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent e) {

		        int fila = table.getSelectedRow();
		        int columna = table.getSelectedColumn();
		        Object valor = table.getValueAt(fila, columna);
		        
		        //cuando cargue base de datos, coger las actividades por dia y horarios
		        //seleccionar que date cuadre con el dia de este mes
		        
		        String diaSeleccionado = datos[fila][columna].toString();
		        String mes = Integer.toString(LocalDate.now().getMonthValue());
		        String ano = Integer.toString(LocalDate.now().getYear());
		        
		        //compara ano, mes, dia actuales con el de actividades para coger la actividad que toca. 
		        String actividad = "Actividad, hora," + diaSeleccionado;

		        if (valor != null) {
		            int dia = (int) valor;
					JOptionPane.showMessageDialog(null, actividad);

		        }
		    }
		});
	}
}


