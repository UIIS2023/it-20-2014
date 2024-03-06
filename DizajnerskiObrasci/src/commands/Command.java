package commands;

import geometry.Shape;
import model.Model;

public interface Command {
	
	void execute();
	
	void unexecute();
	
	void setModel(Model model);
	
	Shape getShape();
}
