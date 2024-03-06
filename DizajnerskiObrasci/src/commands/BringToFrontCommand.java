package commands;

import java.io.Serializable;

import geometry.Shape;
import model.Model;

public class BringToFrontCommand implements Command, Serializable  {

	private Model model;
	int i;
	
	public BringToFrontCommand() {
		
	}
	
	public BringToFrontCommand(Model model) {
		this.model = model;
		i = model.getShapes().indexOf(model.getSelectedShapes().get(0));
	}

	@Override
	public void execute() {
		model.getShapes().add(model.getShapes().get(i));
		model.getShapes().remove(i);
		
	}

	@Override
	public void unexecute() {
		model.getShapes().add(i, model.getShapes().get(model.getShapes().size()-1));
		model.getShapes().remove(model.getShapes().size()-1);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setModel(Model model) {
		this.model = model;
	}
	
	public Shape getShape() {
		return null;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
