package gui;

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
import javax.swing.border.EmptyBorder;

import gui.DialogBox.Move;

public class MoveTo extends DialogBoxCircleModify {
	private JTextField txtMoveToX;
	private JTextField txtMoveToY;
	private int moveToX;
	private int moveToY;
	
	private final JPanel contentPanel = new JPanel();
	
	public MoveTo(){
		
		setModal(true);
		setBounds(100, 100, 200, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblMoveToX = new JLabel("Move to x");
		lblMoveToX.setBounds(10, 30, 71, 14);
		contentPanel.add(lblMoveToX);
		
		txtMoveToX = new JTextField();
		txtMoveToX.setBounds(91, 27, 86, 20);
		contentPanel.add(txtMoveToX);
		txtMoveToX.setColumns(10);
		
		JLabel lblMoveToY = new JLabel("Move to y");
		lblMoveToY.setBounds(10, 55, 71, 14);
		contentPanel.add(lblMoveToY);
		
		txtMoveToY = new JTextField();
		txtMoveToY.setBounds(91, 52, 86, 20);
		contentPanel.add(txtMoveToY);
		txtMoveToY.setColumns(10);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					moveToX = Integer.parseInt(txtMoveToX.getText());
					moveToY = Integer.parseInt(txtMoveToY.getText());
					setVisible(false);
					setState(Move.MOVETO);
				} catch (NumberFormatException e1) {
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
				setState(Move.IDLE);
			}
		});
		buttonPane.add(cancelButton);
		
	}

	public int getMoveToX() {
		return moveToX;
	}

	public void setMoveToX(int pomeriNaX) {
		moveToX = pomeriNaX;
	}

	public int getMoveToY() {
		return moveToY;
	}

	public void setMoveToY(int pomeriNaY) {
		moveToY = pomeriNaY;
	}

	public JTextField getTxtMoveToX() {
		return txtMoveToX;
	}

	public void setTxtMoveToX(JTextField txtMoveToX) {
		this.txtMoveToX = txtMoveToX;
	}

	public JTextField getTxtMoveToY() {
		return txtMoveToY;
	}

	public void setTxtMoveToY(JTextField txtMoveToY) {
		this.txtMoveToY = txtMoveToY;
	}
}


