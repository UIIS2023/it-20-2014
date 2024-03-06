package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -888790554862002427L;
	private static MainFrame instance = null;
	private View drawingPanel = null;
	private ToolBar toolBar = null;
	private JColorChooser jcc = null;
	private JTextArea changeLog;

	
	private MainFrame() {
		toolBar = new ToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		drawingPanel = new View();
		getContentPane().add(drawingPanel, BorderLayout.CENTER);
		getContentPane().setBackground(Color.white);
		
		jcc = new JColorChooser(Color.black);
		
		changeLog = new JTextArea(5,10);
		changeLog.setEditable(false);
        JScrollPane scrollPaneForLog = new JScrollPane(changeLog);
        getContentPane().add(scrollPaneForLog, BorderLayout.EAST);
        
	}
	
	public JTextArea getChangeLog() {
		return changeLog;
	}

	public void setChangeLog(JTextArea changeLog) {
		this.changeLog = changeLog;
	}

	public static MainFrame getInstance(){
		if(instance == null){
			instance = new MainFrame();
		}
		return instance;
	}

	public View getDrawingPanel() {
		return drawingPanel;
	}

	public void setDrawingPanel(View drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public JColorChooser getJcc() {
		return jcc;
	}

	public void setJcc(JColorChooser jcc) {
		this.jcc = jcc;
	}
	
	
}
