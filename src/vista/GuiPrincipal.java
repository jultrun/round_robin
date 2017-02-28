package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import proceso.Proceso;

@SuppressWarnings("serial")
public class GuiPrincipal extends JFrame {

	private JPanel contentPane;
	public static ArrayList<Proceso> procesos=new ArrayList<>();
	public static int numeroDeProcesos;
	public static int cambioDeContexto;
	public static int quantum;

	public static void main(String[] args) {
		try {
            // Modificamos la apariencia.
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel(new NimbusLookAndFeel());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiPrincipal frame = new GuiPrincipal();
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
	public GuiPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("ayuda");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItemAcerca = new JMenuItem("Acerca del algoritmo");
		menuItemAcerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getContentPane(), "Round-robin es un método para seleccionar todos los elementos en un grupo de manera equitativa y en un orden racional, \nnormalmente comenzando por el primer elemento de la lista hasta llegar al último y empezando de nuevo desde el primer elemento.\n El nombre del algoritmo viene del principio de Round-Robin conocido de otros campos,\n donde cada persona toma una parte de un algo compartido en cantidades parejas.");
			}
		});
		mnNewMenu.add(menuItemAcerca);
		
		JMenuItem menuItemAutores = new JMenuItem("Autores");
		menuItemAutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getContentPane(), "Julian Andres ortega \nGerdy Alejando Cifuentes");
			}
		});
		mnNewMenu.add(menuItemAutores);
		
		JButton btnCrearProcesos = new JButton("Crear Procesos");
		btnCrearProcesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearProcesos(e);
				
			}
		});
		btnCrearProcesos.setBounds(155, 64, 162, 23);
		contentPane.add(btnCrearProcesos);
		
		JButton btnAsignarQuantum = new JButton("Asignar quantum");
		btnAsignarQuantum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GuiQuantum().setVisible(true);
			}
		});
		btnAsignarQuantum.setBounds(155, 107, 162, 23);
		contentPane.add(btnAsignarQuantum);
		
		JButton btnEjecutarRaundRobin = new JButton("ejecutar raund robin");
		btnEjecutarRaundRobin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutar(e);
			}
		});
		btnEjecutarRaundRobin.setBounds(155, 141, 162, 23);
		contentPane.add(btnEjecutarRaundRobin);
		
		JButton btnSalir = new JButton("salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(2);
			}
		});
		btnSalir.setBounds(155, 175, 162, 23);
		contentPane.add(btnSalir);
	}

	private void crearProcesos(ActionEvent e) {
		if(procesos.isEmpty()){
			new GuiProcesos().setVisible(true);
			}else{
				int elecion=JOptionPane.showConfirmDialog(this, "los procesos ya fueron creados, desea crearlos de nuevo");
				if(elecion==JOptionPane.YES_OPTION){
					procesos=new ArrayList<>();
					new GuiProcesos().setVisible(true);
				}
			}
		
	}

	private void ejecutar(ActionEvent e) {
		if(procesos.isEmpty()||quantum<=0){
			if(procesos.isEmpty()){
			JOptionPane.showMessageDialog(this, "llene los procesos primero");
			}
			if(quantum<=0){
				JOptionPane.showMessageDialog(this, "asigne el quantum primero");
			}
		}else{
			this.setVisible(false);
			new GuiMostrar().setVisible(true);
		}
		
	}
}
