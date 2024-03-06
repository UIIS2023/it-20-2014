package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.Graphics;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToolBar;

import gui.DialogBox.Move;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.border.LineBorder;

import ctrl.Controller;
import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Square;
import geometry.Shape;
import geometry.RectangleShape;
import geometry.Ring;
import geometry.Point;
import java.awt.Rectangle;
import java.awt.Dimension;


public class ToolBar extends JToolBar {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public enum Tools {
		LINE, POINT, SELECT, RECTANGLE, CIRCLE, SQUARE, MODIFY, HEXAGON, RING
	}
	private ToolBarItem tglbtnLine = null;
	private ToolBarItem tglbtnPoint = null;
	private ToolBarItem tglbtnCircle = null;
	private ToolBarItem tglbtnRectangle = null;
	private ToolBarItem tglbtnSelect = null;
	private ToolBarItem tglbtnSquare = null;
	private ToolBarItem tglbtnHex = null;
	private ToolBarItem tglbtnRing = null;
	private Color lineColor = Color.black;
	private Color fillColor = Color.white;
	private Tools selectedTool = Tools.POINT;
	private Controller controller;
	private JButton modifyButton = new JButton("Modify");
	private JButton deleteButton = new JButton("Delete");
	private JButton undoButton = new JButton("Undo");
	private JButton redoButton = new JButton("Redo");
	private JButton toFrontButton = new JButton("To front");
	private JButton toBackButton = new JButton("To back");
	private JButton bringToFrontButton = new JButton("Bring to front");
	private JButton bringToBackButton = new JButton("Bring to back");
	private JButton saveButton = new JButton("Save");
	private JButton loadButton = new JButton("Load");
	private JButton doCmdButton = new JButton("Do cmd");


	public ToolBar() {

		
		tglbtnPoint = new ToolBarItem(Tools.POINT, "Point");
		tglbtnPoint.addActionListener(new ButtonToggled());
		tglbtnPoint.setSelected(true);
		buttonGroup.add(tglbtnPoint);
		add(tglbtnPoint);

		tglbtnLine = new ToolBarItem(Tools.LINE, "Line");
		tglbtnLine.addActionListener(new ButtonToggled());
		buttonGroup.add(tglbtnLine);
		add(tglbtnLine);
		
		tglbtnSquare = new ToolBarItem(Tools.SQUARE, "Square");
		tglbtnSquare.addActionListener(new ButtonToggled());
		buttonGroup.add(tglbtnSquare);
		add(tglbtnSquare);

		tglbtnRectangle = new ToolBarItem(Tools.RECTANGLE, "Rectangle");
		tglbtnRectangle.addActionListener(new ButtonToggled());
		buttonGroup.add(tglbtnRectangle);
		add(tglbtnRectangle);
		
		tglbtnCircle = new ToolBarItem(Tools.CIRCLE, "Circle");
		tglbtnCircle.addActionListener(new ButtonToggled());
		buttonGroup.add(tglbtnCircle);
		add(tglbtnCircle);
		
		tglbtnHex = new ToolBarItem(Tools.HEXAGON, "Hexagon");
		tglbtnHex.addActionListener(new ButtonToggled());
		buttonGroup.add(tglbtnHex);
		add(tglbtnHex);
		
		tglbtnRing = new ToolBarItem(Tools.RING, "Ring");
		tglbtnRing.addActionListener(new ButtonToggled());
		buttonGroup.add(tglbtnRing);
		add(tglbtnRing);
		
		tglbtnSelect = new ToolBarItem(Tools.SELECT, "Select");
		tglbtnSelect.addActionListener(new ButtonToggled());
		buttonGroup.add(tglbtnSelect);
		add(tglbtnSelect);
		
		modifyButton.setEnabled(false);
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modifyCmd();
				controller.logCommands();
				}
			});
		add(modifyButton);
		
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteShape();
				controller.logCommands();
				}
			});
		add(deleteButton);
		
		undoButton.setEnabled(false);
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undoCmd();
				controller.logCommands();
				}
			});
		
		add(undoButton);
		
		redoButton.setEnabled(false);
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redoCmd();
				controller.logCommands();
				}
			});
		add(redoButton);
		
		toFrontButton.setEnabled(false);
		toFrontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFrontCmd();
				controller.logCommands();
				}
			});
		add(toFrontButton);
		
		toBackButton.setEnabled(false);
		toBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBackCmd();
				controller.logCommands();
				}
			});
		add(toBackButton);
		
		bringToFrontButton.setEnabled(false);
		bringToFrontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFrontCmd();
				controller.logCommands();
				}
			});
		add(bringToFrontButton);
		
		bringToBackButton.setEnabled(false);
		bringToBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBackCmd();
				controller.logCommands();
				}
			});
		add(bringToBackButton);
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controller.saveFile();
				}
			});
		add(saveButton);
		
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controller.loadFile();
				}
			});
		add(loadButton);
		
		doCmdButton.setEnabled(false);
		doCmdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controller.doLogCmd();
				}
			});
		add(doCmdButton);
		
		JButton lineColorButton = new JButton();
		lineColorButton.setBackground(lineColor);
		lineColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lineColor = MainFrame.getInstance().getJcc().showDialog(lineColorButton, "Choose line color", lineColor);
				lineColorButton.setBackground(lineColor);
				
				}
			});
		add(lineColorButton);
		
		JButton fillColorButton = new JButton();
		fillColorButton.setBackground(fillColor);
		fillColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillColor = MainFrame.getInstance().getJcc().showDialog(fillColorButton, "Choose fill color", fillColor);
				fillColorButton.setBackground(fillColor);
				}
			});
		add(fillColorButton);
		

	}
	
	public JButton getDoCmdButton() {
		return doCmdButton;
	}

	public void setDoCmdButton(JButton doCmdButton) {
		this.doCmdButton = doCmdButton;
	}

	private class ButtonToggled implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ToolBarItem source = null;
			if (e.getSource() instanceof ToolBarItem) {
				source = (ToolBarItem) e.getSource();
			} else {
				return;
			}
			
			switch (source.getTool()) {
			case POINT:
				selectedTool = source.getTool();
				break;
				
			case LINE:
				selectedTool = source.getTool();
				break;
				
			case CIRCLE:
				controller.setStanje(Controller.States.IDLE);
				selectedTool = source.getTool();
				break;
				
			case RECTANGLE:
				controller.setStanje(Controller.States.IDLE);
				selectedTool = source.getTool();
				break;
			
			case SELECT:
				controller.setStanje(Controller.States.IDLE);
				selectedTool = source.getTool();
				break;
				
			case SQUARE:
				controller.setStanje(Controller.States.IDLE);
				selectedTool = source.getTool();
				break;
			case HEXAGON:
				controller.setStanje(Controller.States.IDLE);
				selectedTool = source.getTool();
				break;
			case RING:
				controller.setStanje(Controller.States.IDLE);
				selectedTool = source.getTool();
				break;
			}	
		}
	}
	
	
	public Tools getSelectedTool() {
		return selectedTool;
	}

	public void setSelectedTool(Tools selectedTool) {
		this.selectedTool = selectedTool;
	}

	public  Color getLineColor() {
		return lineColor;
	}

	public  void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public  Color getFillColor() {
		return fillColor;
	}

	public  void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public JButton getModifyButton() {
		return modifyButton;
	}

	public void setModifyButton(JButton modifyButton) {
		this.modifyButton = modifyButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton obrisiButton) {
		deleteButton = obrisiButton;
	}

	public JButton getUndoButton() {
		return undoButton;
	}

	public void setUndoButton(JButton undoButton) {
		undoButton = undoButton;
	}

	public JButton getRedoButton() {
		return redoButton;
	}

	public void setRedoButton(JButton redoButton) {
		redoButton = redoButton;
	}

	public JButton getToFrontButton() {
		return toFrontButton;
	}

	public void setToFrontButton(JButton toFrontButton) {
		toFrontButton = toFrontButton;
	}

	public JButton getToBackButton() {
		return toBackButton;
	}

	public void setToBackButton(JButton toBackButton) {
		toBackButton = toBackButton;
	}

	public JButton getBringToFrontButton() {
		return bringToFrontButton;
	}

	public void setBringToFrontButton(JButton bringToFrontButton) {
		bringToFrontButton = bringToFrontButton;
	}

	public JButton getBringToBackButton() {
		return bringToBackButton;
	}

	public void setBringToBackButton(JButton bringToBackButton) {
		bringToBackButton = bringToBackButton;
	}
	
	
}
