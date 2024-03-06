package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogBoxRingModify extends DialogBox {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtRadius;
	private JTextField txtThickness;
	private String radius;
	private String thickness;
	private Color lineColor;
	private Color fillColor;
	private Color tempFillColor;
	private Color tempLineColor;
	private int moveByX;
	private int moveByY;
	private int moveToX;
	private int moveToY;
	private int x;
	private int y;


	
	
	
	public DialogBoxRingModify (){
		
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
		
		JButton lineColorButton = new JButton("Change line color");
		lineColorButton.setBounds(10, 105, 200, 20);
		lineColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempLineColor = MainFrame.getInstance().getJcc().showDialog(lineColorButton, "Choose line color", lineColor);
				}
			});
		contentPanel.add(lineColorButton);
		
		JButton fillColorButton = new JButton("Change fill color");
		fillColorButton.setBounds(10, 135, 200, 20);
		fillColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFillColor = MainFrame.getInstance().getJcc().showDialog(fillColorButton, "Choose fill color", fillColor);
				}
			});
		contentPanel.add(fillColorButton);
		
		JButton moveByButton = new JButton("Move by");
		moveByButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveBy moveBy = new MoveBy();
				moveBy.getTxtMoveByX().setText("0");
				moveBy.getTxtMoveByY().setText("0");
				moveBy.setVisible(true);
				
				moveByX = moveBy.getMoveByX();
				moveByY = moveBy.getMoveByY();
				setState(moveBy.getState());

				
				}
			});
		buttonPane.add(moveByButton);
		
		JButton moveToButton = new JButton("Move to");
		moveToButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveTo moveTo = new MoveTo();
				moveTo.getTxtMoveToX().setText(Integer.toString(x));
				moveTo.getTxtMoveToY().setText(Integer.toString(y));
				moveTo.setVisible(true);
				
				moveToX = moveTo.getMoveToX();
				moveToY = moveTo.getMoveToY();
				setState(moveTo.getState());
				}
			});
		buttonPane.add(moveToButton);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radius = txtRadius.getText();	
				thickness = txtThickness.getText();
				if(tempFillColor != null)
					fillColor = tempFillColor;
				if(tempLineColor != null)
					lineColor = tempLineColor;
				setVisible(false);
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

	public JTextField getTxtThickness() {
		return txtThickness;
	}

	public void setTxtThickness(JTextField txtThickness) {
		this.txtThickness = txtThickness;
	}

	public String getThickness() {
		return thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}
	public int getMoveByX() {
		return moveByX;
	}

	public void setMoveByX(int moveByX) {
		this.moveByX = moveByX;
	}

	public int getMoveByY() {
		return moveByY;
	}

	public void setMoveByY(int moveByY) {
		this.moveByY = moveByY;
	}

	public int getMoveToX() {
		return moveToX;
	}

	public void setMoveToX(int moveToX) {
		this.moveToX = moveToX;
	}

	public int getMoveToY() {
		return moveToY;
	}

	public void setMoveToY(int moveToY) {
		this.moveToY = moveToY;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}


	
}
