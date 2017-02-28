package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import proceso.Proceso;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GuiCrearProcesos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTextField txtNombres[];
	JTextField txtTiemposDeLlegada[];
	JTextField txtTiemposRequeridos[];
	JButton btnColores[];
	private JTextField txtCambioDeContexto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            // Modificamos la apariencia.
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
		try {
			GuiCrearProcesos dialog = new GuiCrearProcesos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiCrearProcesos() {
		setBounds(100, 100, 546, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		

		JPanel panelProcesos = new JPanel();
		panelProcesos.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(panelProcesos);
		scrollPane.setBounds(47, 34, 380, 128);
		

		JLabel NombreDelProceso = new JLabel("Nombre del proceso");
		NombreDelProceso.setBounds(47, 21, 123, 14);
		contentPanel.add(NombreDelProceso);

		JLabel lblTiempoDeLlegada = new JLabel("tiempo de llegada");
		lblTiempoDeLlegada.setBounds(170, 21, 109, 14);
		contentPanel.add(lblTiempoDeLlegada);

		JLabel lblTiempoRequerido = new JLabel("tiempo requerido");
		lblTiempoRequerido.setBounds(280, 21, 102, 14);
		contentPanel.add(lblTiempoRequerido);

		JLabel lblColor = new JLabel("color");
		
		lblColor.setBounds(384, 21, 33, 14);
		contentPanel.add(lblColor);
		scrollPane.setAutoscrolls(true);
		
		txtNombres = new JTextField[GuiPrincipal.numeroDeProcesos];
		txtTiemposDeLlegada = new JTextField[GuiPrincipal.numeroDeProcesos];
		txtTiemposRequeridos = new JTextField[GuiPrincipal.numeroDeProcesos];
		btnColores=new JButton[GuiPrincipal.numeroDeProcesos];
		panelProcesos.setPreferredSize(new Dimension(panelProcesos.getWidth(), GuiPrincipal.numeroDeProcesos*25));
		for (int i = 0; i < GuiPrincipal.numeroDeProcesos; i++) {
			txtNombres[i] = new JTextField();
			txtNombres[i].setBounds(10, i * 25, 86, 25);
			panelProcesos.add(txtNombres[i]);
			
			txtTiemposDeLlegada[i] = new JTextField();
			txtTiemposDeLlegada[i].setBounds(120, i * 25, 86, 25);
			panelProcesos.add(txtTiemposDeLlegada[i]);
			
			txtTiemposRequeridos[i] = new JTextField();
			txtTiemposRequeridos[i].setBounds(230, i * 25, 86, 25);
			panelProcesos.add(txtTiemposRequeridos[i]);
			
			btnColores[i] = new JButton();
			btnColores[i].setBounds(326, i*25, 30, 25);
			btnColores[i].setOpaque(false);
			btnColores[i].setContentAreaFilled(true);
			
			Random r = new Random();
			btnColores[i].setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			btnColores[i].addActionListener(new ActionListener() {
				int i;
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(
		                     getContentPane(),
		                     "elija el color del proceso",
		                     btnColores[i].getBackground());
					if(newColor!=null){
						btnColores[i].setBackground(newColor);
					}
				}
				public ActionListener set(int i){
					this.i=i;
					return this;
				}
			}.set(i));
			panelProcesos.add(btnColores[i]);
			

		}
		contentPanel.add(scrollPane);
		
		txtCambioDeContexto = new JTextField();
		txtCambioDeContexto.setBounds(220, 174, 88, 28);
		contentPanel.add(txtCambioDeContexto);
		txtCambioDeContexto.setColumns(10);
		
		JLabel lblTiempoParaCambio = new JLabel("tiempo para cambio de contexto");
		lblTiempoParaCambio.setBounds(19, 180, 175, 16);
		contentPanel.add(lblTiempoParaCambio);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						elegirProcesos(e);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						salir(e);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void salir(ActionEvent e) {
		this.setVisible(false);
		
	}

	private void elegirProcesos(ActionEvent e) {
		//comprueba que los campos esten bien
		try{
			if(GuiPrincipal.numeroDeProcesos<=0){
				throw new Exception();	
			}
			for(int i=0;i<GuiPrincipal.numeroDeProcesos;i++){
				if(txtNombres[i].getText().equals("")){
					throw new Exception();
				}
				int tiempoDeLegada=Integer.parseInt(txtTiemposDeLlegada[i].getText());
				int tiempoRequerido=Integer.parseInt(txtTiemposRequeridos[i].getText());
				if(tiempoDeLegada<0||tiempoRequerido<0){
					throw new Exception();
				}
				
			
			}
			int tiempoDeCambioDeContexto=Integer.parseInt(txtCambioDeContexto.getText());
			if(tiempoDeCambioDeContexto<0){
				throw new Exception();
			}
			for(int i=0;i<GuiPrincipal.numeroDeProcesos;i++){
				int tiempoDeLegada=Integer.parseInt(txtTiemposDeLlegada[i].getText());
				int tiempoRequerido=Integer.parseInt(txtTiemposRequeridos[i].getText());
				GuiPrincipal.procesos.add(new Proceso(txtNombres[i].getText(), tiempoDeLegada, tiempoRequerido,btnColores[i].getBackground()));
			}
			GuiPrincipal.cambioDeContexto=tiempoDeCambioDeContexto;
			this.setVisible(false);
		}catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "datos invalidos ");
		
		}
		
		
		
		
	}
}
