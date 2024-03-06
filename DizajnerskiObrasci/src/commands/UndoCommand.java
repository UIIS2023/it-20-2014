package commands;

import java.io.Serializable;
import java.util.ArrayList;

import geometry.Shape;
import model.Model;

public class UndoCommand implements Command, Serializable{
	
	ArrayList<Command> commands;
	ArrayList<Command> redoCommands;
	
	public UndoCommand(ArrayList<Command> commands, ArrayList<Command>redoCommands) {
		this.commands=commands;
		this.redoCommands=redoCommands;
	}

	@Override
	public void execute() {
		commands.get(commands.size()-1).unexecute();
		redoCommands.add(commands.get(commands.size()-1));
		commands.remove(commands.size()-1);
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModel(Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCommands(ArrayList<Command> commands){
		this.commands = commands;
	}
	
	public void setRedoCommands(ArrayList<Command> redoCommands) {
		this.redoCommands = redoCommands;
	}

}
