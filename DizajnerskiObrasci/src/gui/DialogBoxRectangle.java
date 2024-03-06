package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogBoxRectangle extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtHeight;
	private JTextField txtWidth;
	private int height;
	private int width;
	
	public DialogBoxRectangle (){
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(10, 30, 71, 14);
		contentPanel.add(lblHeight);
		
		txtHeight = new JTextField();
		txtHeight.setBounds(91, 27, 86, 20);
		contentPanel.add(txtHeight);
		txtHeight.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(10, 55, 71, 14);
		contentPanel.add(lblWidth);
		
		txtWidth = new JTextField();
		txtWidth.setBounds(91, 52, 86, 20);
		contentPanel.add(txtWidth);
		txtWidth.setColumns(10);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer.parseInt(txtHeight.getText());
					Integer.parseInt(txtWidth.getText());
					
					height = Integer.parseInt(txtHeight.getText());
					width = Integer.parseInt(txtWidth.getText());
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

	public int getHeightt() {
		return height;
	}

	public void setHeightt(int width) {
		this.height = width;
	}

	public int getWidthh() {
		return width;
	}

	public void setWidthh(int width) {
		this.width = width;
	}
}
