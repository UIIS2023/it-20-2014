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

public class MoveBy extends DialogBoxCircleModify {
	private JTextField txtMoveByX;
	private JTextField txtMoveByY;
	private int moveByX;
	private int moveByY;
	
	private final JPanel contentPanel = new JPanel();
	
	public MoveBy(){
		
		setModal(true);
		setBounds(100, 100, 200, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblMoveByX = new JLabel("Move by x");
		lblMoveByX.setBounds(10, 30, 71, 14);
		contentPanel.add(lblMoveByX);
		
		txtMoveByX = new JTextField();
		txtMoveByX.setBounds(91, 27, 86, 20);
		contentPanel.add(txtMoveByX);
		txtMoveByX.setColumns(10);
		
		JLabel lblMoveByY = new JLabel("Move by y");
		lblMoveByY.setBounds(10, 55, 71, 14);
		contentPanel.add(lblMoveByY);
		
		txtMoveByY = new JTextField();
		txtMoveByY.setBounds(91, 52, 86, 20);
		contentPanel.add(txtMoveByY);
		txtMoveByY.setColumns(10);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					moveByX = Integer.parseInt(txtMoveByX.getText());
					moveByY = Integer.parseInt(txtMoveByY.getText());
					setVisible(false);
					setState(Move.MOVEBY);
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

	public int getMoveByX() {
		return moveByX;
	}

	public void setMoveByX(int pomeriZaX) {
		moveByX = pomeriZaX;
	}

	public int getMoveByY() {
		return moveByY;
	}

	public void setMoveByY(int moveByY) {
		this.moveByY = moveByY;
	}

	public JTextField getTxtMoveByX() {
		return txtMoveByX;
	}

	public void setTxtMoveByX(JTextField txtMoveByX) {
		this.txtMoveByX = txtMoveByX;
	}

	public JTextField getTxtMoveByY() {
		return txtMoveByY;
	}

	public void setTxtMoveByY(JTextField txtMoveByY) {
		this.txtMoveByY = txtMoveByY;
	}
}


