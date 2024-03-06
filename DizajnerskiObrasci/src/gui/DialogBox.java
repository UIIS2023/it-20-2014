package gui;

import javax.swing.JDialog;
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

public class DialogBox extends JDialog {
	public enum Move {IDLE, MOVEBY, MOVETO}
	private Move state = Move.IDLE;
	
	public Move getState() {
		return state;
	}
	public void setState(Move state) {
		this.state = state;
	}

}
