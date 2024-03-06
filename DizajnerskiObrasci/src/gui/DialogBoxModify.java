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

public class DialogBoxModify extends DialogBox {
	
	private final JPanel contentPanel = new JPanel();
	private Color lineColor;
	private Color tempLineColor;
	private int moveByX;
	private int moveByY;
	private int moveToX;
	private int moveToY;
	private int x;
	private int y;


	
	
	
	public DialogBoxModify (){
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
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
				setVisible(false);
				if(tempLineColor != null)
					lineColor=tempLineColor;
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

	public void setMoveByY(int pomeriZaY) {
		moveByY = pomeriZaY;
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

	public int getX() {
		return x;
	}

	public void setX(int trenutnoX) {
		x = trenutnoX;
	}

	public int getY() {
		return y;
	}

	public void setY(int trenutnoY) {
		y = trenutnoY;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	
}
