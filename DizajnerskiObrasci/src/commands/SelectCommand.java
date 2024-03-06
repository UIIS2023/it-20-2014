package commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import geometry.Shape;
import model.Model;

public class SelectCommand implements Command, Serializable {
	
	private Shape selectedShape;
	private ArrayList<Shape> deselectedShapes;
	private Model model;
	
	public SelectCommand() {
		
	}
	
	public SelectCommand(Shape selectedShape, ArrayList<Shape> deselectedShapes, Model model) {
		this.selectedShape = selectedShape;
		this.deselectedShapes = deselectedShapes;
		this.model = model;
	}


	@Override
	public void execute() {
		Iterator<Shape> itds = deselectedShapes.iterator();
		while(itds.hasNext()) {
			Shape s = itds.next();
			model.getSelectedShapes().remove(s);
			s.setSelected(false);
		}
		if(selectedShape != null) {
			selectedShape.setSelected(true);
			model.getSelectedShapes().add(selectedShape);
		}
	}

	@Override
	public void unexecute() {
		if(selectedShape != null) {
			selectedShape.setSelected(false);
			model.getSelectedShapes().remove(selectedShape);
		}
		Iterator<Shape> itds = deselectedShapes.iterator();
		while(itds.hasNext()) {
			Shape s = itds.next();
			model.getSelectedShapes().add(s);
			s.setSelected(true);
		}

		
	}

	@Override
	public void setModel(Model model) {
		this.model = model;
		
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return selectedShape;
	}

	public ArrayList<Shape> getDeselectedShapes() {
		return deselectedShapes;
	}

	public void setDeselectedShapes(ArrayList<Shape> deselectedShapes) {
		this.deselectedShapes = deselectedShapes;
	}

}
