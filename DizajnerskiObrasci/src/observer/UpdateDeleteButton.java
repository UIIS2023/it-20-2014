package observer;

import java.util.ArrayList;

import geometry.Shape;
import gui.MainFrame;

public class UpdateDeleteButton implements Observer{

	@Override
	public void update(ArrayList<Shape> selectedShapes,ArrayList<Shape> shapes) {
		if(!selectedShapes.isEmpty()) {
			MainFrame.getInstance().getToolBar().getDeleteButton().setEnabled(true);
		}else
			MainFrame.getInstance().getToolBar().getDeleteButton().setEnabled(false);
		
	}

}
