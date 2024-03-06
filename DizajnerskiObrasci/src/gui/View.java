package gui;

import java.awt.Graphics;


import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import ctrl.Controller;
import geometry.Circle;
import geometry.Square;
import geometry.Shape;
import model.Model;

public class View extends JPanel{
	private Controller controller;
	private Model model;
	private boolean ctrlPressed = false;

	
	
	
	public View() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int evt = e.getModifiers();
				controller.keyPressed(evt);
				controller.drawShape(e.getX(),e.getY());
				controller.logCommands();
			//	repaint();
			}
		});
	}
	
	public boolean isCtrlPressed() {
		return ctrlPressed;
	}

	public void setCtrlPressed(boolean ctrlPressed) {
		this.ctrlPressed = ctrlPressed;
	}

	public void paint(Graphics g){
		
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()){
			
			Shape o = it.next();
			o.draw(g);
			if(o instanceof Square){
				((Square) o).fill(g);
			}else if(o instanceof Circle){
				((Circle) o).fill(g);
			}
		}
	
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
