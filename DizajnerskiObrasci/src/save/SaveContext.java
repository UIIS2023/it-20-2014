package save;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import commands.Command;

import model.Model;

public class SaveContext {

	private Strategy strategy;
	
	public SaveContext(Strategy strategy) {
		this.strategy = strategy; 
	}
	
	public void executeStrategy(JFileChooser fileChooser, Model model, ArrayList<Command> commands) {
		strategy.doOperation(fileChooser, commands);
	}
}
