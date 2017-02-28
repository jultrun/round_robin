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
public class GuiQuantum extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txQuantum;

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
			GuiQuantum dialog = new GuiQuantum();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuiQuantum() {
		setBounds(100, 100, 272, 136);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txQuantum = new JTextField();
		txQuantum.setBounds(136, 19, 86, 25);
		contentPanel.add(txQuantum);
		txQuantum.setColumns(10);

		JLabel lbquantun = new JLabel("quantum");
		lbquantun.setBounds(10, 24, 61, 14);
		contentPanel.add(lbquantun);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assignar(e);
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

	private void assignar(ActionEvent e) {
		try {
			int numero = Integer.parseInt(txQuantum.getText());
			if(numero>0){
				GuiPrincipal.quantum=numero;
				this.setVisible(false);
			}else{
				throw new Exception();
			}
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "numero invalido");
		}
		
	}
}
