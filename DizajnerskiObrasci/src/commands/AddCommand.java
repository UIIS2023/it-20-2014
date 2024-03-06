package commands;

import java.io.Serializable;

import geometry.Shape;
import model.Model;

public class AddCommand implements Command, Serializable{

	private Shape shape;
	private Shape originalShape;
	private Model model;
	
	public AddCommand(Shape shape, Model model, Shape originalShape) {
		this.shape = shape;
		this.model = model;
		this.originalShape = originalShape;
	}
	@Override
	public void execute() {
		
		model.getShapes().add(shape);
		
	}

	@Override
	public void unexecute() {
		
		model.getShapes().remove(shape);

	}
	public Model getModel() {
		return model;
	}
	@Override
	public void setModel(Model model) {
		this.model = model;

	}
	
	public Shape getShape() {
		return this.shape;
	}
	public Shape getOriginalShape() {
		return this.originalShape;
	}

}
