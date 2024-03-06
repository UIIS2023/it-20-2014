package observer;

import java.util.ArrayList;

import geometry.Shape;
import gui.MainFrame;

public class UpdateZButtons implements Observer {

	@Override
	public void update(ArrayList<Shape> selectedShapes, ArrayList<Shape> shapes) {
		if(selectedShapes.size()==1) {
			if(shapes.indexOf(selectedShapes.get(0))>0) 
				MainFrame.getInstance().getToolBar().getToBackButton().setEnabled(true);
			else
				MainFrame.getInstance().getToolBar().getToBackButton().setEnabled(false);
			if(shapes.indexOf(selectedShapes.get(0))<shapes.size()-1)
				MainFrame.getInstance().getToolBar().getToFrontButton().setEnabled(true);
			else
				MainFrame.getInstance().getToolBar().getToFrontButton().setEnabled(false);
			
		}else {
			MainFrame.getInstance().getToolBar().getToFrontButton().setEnabled(false);
			MainFrame.getInstance().getToolBar().getToBackButton().setEnabled(false);
		}
		
	}

}
