package save;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import commands.Command;

public class SaveSerializationOperation implements Strategy{
	private File file;
	@Override
	public void doOperation(JFileChooser fileChooser, ArrayList<Command> commands) {
		
		file = new File(fileChooser.getSelectedFile().toString());
		if(file.getAbsolutePath().toString().endsWith(".ser")) {  
		} else
			file=new File(fileChooser.getSelectedFile().toString()+".ser"); 
		if(file.exists()) {
			file.delete();
		}
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
			out.writeObject(commands);

		}catch(IOException i) {
			i.printStackTrace();
		}
	}
		
}
