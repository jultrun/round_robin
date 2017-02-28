package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import util.Robin;

@SuppressWarnings("serial")
public class GuiMostrar extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMostrar frame = new GuiMostrar();
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
	public GuiMostrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		
		JScrollPane scrollPane_table = new JScrollPane();
		scrollPane_table.setBounds(10, 194, 662, 84);
		contentPane.add(scrollPane_table);
		
		
		JPanel contenedorDiagrama = new JPanel();
		
		Robin robin = new Robin(GuiPrincipal.quantum, GuiPrincipal.cambioDeContexto, GuiPrincipal.procesos);
		robin.ejecutar();
		
		JPanel listaDeProcesos = new JPanel();
		
		
		JPanel panelColor[]=new JPanel[GuiPrincipal.procesos.size()];
		JLabel nombre[]=new JLabel[GuiPrincipal.procesos.size()];
		for(int i=0;i<GuiPrincipal.procesos.size();i++){
			panelColor[i]=new JPanel();
			nombre[i]=new JLabel(GuiPrincipal.procesos.get(i).getNombre());
			if(i==0){
				panelColor[i].setBounds(i*22, 10, 22, 22);
				nombre[i].setBounds(panelColor[i].getWidth(),10,50,20);
				nombre[i].setSize(nombre[i].getPreferredSize());
			}else{
				panelColor[i].setBounds(nombre[i-1].getWidth()+nombre[i-1].getX(), 10, 22, 22);
				nombre[i].setBounds(panelColor[i].getWidth()+panelColor[i].getX(),10,50,20);
				nombre[i].setSize(nombre[i].getPreferredSize());
				
			}
			panelColor[i].setBackground(GuiPrincipal.procesos.get(i).getColor());
			listaDeProcesos.add(panelColor[i]);
			listaDeProcesos.add(nombre[i]);
		}
		
		listaDeProcesos.setPreferredSize(new Dimension(nombre[GuiPrincipal.procesos.size()-1].getWidth()+nombre[GuiPrincipal.procesos.size()-1].getX(), listaDeProcesos.getHeight()));
		
		JScrollPane scrollPane_listaProcesos = new JScrollPane(listaDeProcesos);
		scrollPane_listaProcesos.setAutoscrolls(true);
		listaDeProcesos.setLayout(null);
		scrollPane_listaProcesos.setBounds(66, 11, 282, 66);
		contentPane.add(scrollPane_listaProcesos);
		
		
		table = new JTable();
		Object[][] datos = new Object[robin.procesosFinales.size()][7];
		for (int i = 0; i < datos.length; i++) {
			datos[i][0]=robin.procesosFinales.get(i).getNombre();
			datos[i][1]=robin.procesosFinales.get(i).getTiempoDeLlegada();
			datos[i][2]=robin.procesosFinales.get(i).getTiempoDeEjecucion();
			datos[i][3]=robin.procesosFinales.get(i).getTiempoRequetido();
			datos[i][4]=robin.procesosFinales.get(i).getTimempoDeInicio();
			datos[i][5]=robin.procesosFinales.get(i).getTimempoFinal();
			datos[i][6]=robin.procesosFinales.get(i).getTiempoRequetido()-robin.procesosFinales.get(i).getTiempoDeEjecucion();
		}
		DefaultTableModel dtm = new DefaultTableModel(datos, new String[]{"nombre","tiempo de llegada","tiempo de ejecucion T","tiempo requerido","inico","final","tiempo de espera"});
		table.setModel(dtm);
		
		
		scrollPane_table.setViewportView(table);
		
		
		int tiempo=robin.registro.size();
		contenedorDiagrama.setPreferredSize(new Dimension((tiempo*36), contenedorDiagrama.getHeight()));
		
		JScrollPane scrollPane_procesosContainer = new JScrollPane(contenedorDiagrama);
		contenedorDiagrama.setLayout(null);
		
		for (int i =0; i <= tiempo; i++) {
			
			JLabel label = new JLabel(""+(i));
			label.setBounds((i)*36, 4, 35, 14);
			contenedorDiagrama.add(label);
			
			JSeparator linea = new JSeparator();
			linea.setBackground(Color.BLACK);
			linea.setOrientation(SwingConstants.VERTICAL);
			linea.setBounds((i)*36, 20, 2, 19);
			contenedorDiagrama.add(linea);
			if(i<tiempo){
				JPanel tick = new JPanel();
				tick.setBounds((i-1)*36, 32, 36, 20);
				tick.setBackground(robin.registro.get(i).getColor());
				contenedorDiagrama.add(tick);
			}
			
			
		}
		
		scrollPane_procesosContainer.setBounds(0, 88, 499, 94);
		contentPane.add(scrollPane_procesosContainer);
		
		
		
		
		
		
	}
}
