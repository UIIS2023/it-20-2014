package save;

import java.util.ArrayList;

import javax.swing.JFileChooser;

import model.Model;
import commands.Command;

public interface Strategy {
	
	public void doOperation(JFileChooser fileChooser, ArrayList<Command> command);

}
