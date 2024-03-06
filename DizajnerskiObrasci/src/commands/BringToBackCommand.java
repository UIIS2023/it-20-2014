package commands;

import java.io.Serializable;

import geometry.Shape;
import model.Model;

public class BringToBackCommand implements Command, Serializable{

	private Model model;
	int i;
	
	public BringToBackCommand() {
		
	}
	
	public BringToBackCommand(Model model) {
		this.model = model;
		i = model.getShapes().indexOf(model.getSelectedShapes().get(0));
	}
	@Override
	public void execute() {
		model.getShapes().add(0,model.getShapes().get(i));
		model.getShapes().remove(i+1);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unexecute() {
		model.getShapes().add(i+1, model.getShapes().get(0));
		model.getShapes().remove(0);
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
