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

public class DialogBoxRing extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtRadius;
	private JTextField txtThickness;
	private int radius;
	private int thickness;

	public DialogBoxRing (){
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblRadius = new JLabel("Radius");
		lblRadius.setBounds(10, 30, 71, 14);
		contentPanel.add(lblRadius);
		
		txtRadius = new JTextField();
		txtRadius.setBounds(91, 27, 86, 20);
		contentPanel.add(txtRadius);
		txtRadius.setColumns(10);
		
		JLabel lblThickness = new JLabel("Thickness");
		lblThickness.setBounds(10, 55, 71, 14);
		contentPanel.add(lblThickness);
		
		txtThickness = new JTextField();
		txtThickness.setBounds(91, 52, 86, 20);
		contentPanel.add(txtThickness);
		txtThickness.setColumns(10);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer.parseInt(txtRadius.getText());
					radius = Integer.parseInt(txtRadius.getText());	
					Integer.parseInt(txtThickness.getText());
					thickness = Integer.parseInt(txtThickness.getText());	
					
					setVisible(false);
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
			}
		});
		buttonPane.add(cancelButton);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
}
