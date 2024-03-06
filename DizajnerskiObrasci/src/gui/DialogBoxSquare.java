package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DialogBoxSquare extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSideLength;
	private int sideLength;
	
	public DialogBoxSquare (){
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblSideLength = new JLabel("Side length");
		lblSideLength.setBounds(10, 30, 71, 14);
		contentPanel.add(lblSideLength);
		
		txtSideLength = new JTextField();
		txtSideLength.setBounds(91, 27, 86, 20);
		contentPanel.add(txtSideLength);
		txtSideLength.setColumns(10);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer.parseInt(txtSideLength.getText());
					
					sideLength = Integer.parseInt(txtSideLength.getText());
					setVisible(false);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, 
							"Morate uneti brojeve!", 
							"Greska", 
							JOptionPane.ERROR_MESSAGE);
				}
				}
			});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}

	public JTextField getTxtSideLength() {
		return txtSideLength;
	}

	public void setTxtSideLength(JTextField txtSideLength) {
		this.txtSideLength = txtSideLength;
	}

	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}

}
