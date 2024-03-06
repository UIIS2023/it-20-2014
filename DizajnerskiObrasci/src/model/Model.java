package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import geometry.Shape;
public class Model implements Serializable{
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	
	public Model() {}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes.clear();
		this.shapes.addAll(shapes);
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}
	
	public void addShape(Shape shape) {
		shapes.add(shape);
	}
	
	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}	
	 
	public void addSelectedShape(Shape shape) {
		shape.setSelected(true);
		selectedShapes.add(shape);
	}
	
	public void removeSelectedShape(Shape shape) {
		shape.setSelected(false);
		selectedShapes.remove(shape);
	}
	
	public void swap(int x, int y) {
		Collections.swap(shapes, x, y);
	}
	
	
	
	
	

}
