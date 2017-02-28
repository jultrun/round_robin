package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GuiProcesos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumeroProcesos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            // Modificamos la apariencia.
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		try {
			GuiProcesos dialog = new GuiProcesos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiProcesos() {
		setBounds(100, 100, 286, 136);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtNumeroProcesos = new JTextField();
		txtNumeroProcesos.setBounds(162, 21, 86, 25);
		contentPanel.add(txtNumeroProcesos);
		txtNumeroProcesos.setColumns(10);

		JLabel lblNumeroDeProcesos = new JLabel("numero de procesos");
		lblNumeroDeProcesos.setBounds(10, 24, 140, 14);
		contentPanel.add(lblNumeroDeProcesos);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elegir(e);
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir(e);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	private void salir(ActionEvent e) {
		this.setVisible(false);
		
	}

	private void elegir(ActionEvent e) {
		try {
			int numero = Integer.parseInt(txtNumeroProcesos.getText());
			if(numero>0){
				GuiPrincipal.numeroDeProcesos=numero;
				this.setVisible(false);
				new GuiCrearProcesos().setVisible(true);
			}else{
				throw new Exception();
			}
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "numero invalido");
		}
		
	}
}
