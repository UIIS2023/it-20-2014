package commands;

import java.io.Serializable;

import geometry.Shape;
import model.Model;

public class ToFrontCommand implements Command, Serializable{
	
	private Model model;
	int i;
	
	public ToFrontCommand() {
		
	}
	public ToFrontCommand(Model model) {
		this.model = model;
		i = model.getShapes().indexOf(model.getSelectedShapes().get(0));
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.swap(i, i+1);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.swap(i, i+1);
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
