package observer;

import java.util.ArrayList;

import geometry.Shape;
import gui.MainFrame;

public class UpdateModifyButton implements Observer{

	@Override
	public void update(ArrayList<Shape> selectedShapes,ArrayList<Shape> shapes) {
		if(selectedShapes.size()==1) {
			MainFrame.getInstance().getToolBar().getModifyButton().setEnabled(true);
			MainFrame.getInstance().getToolBar().getBringToBackButton().setEnabled(true);
			MainFrame.getInstance().getToolBar().getBringToFrontButton().setEnabled(true);
		}
		else {
			MainFrame.getInstance().getToolBar().getModifyButton().setEnabled(false);
			MainFrame.getInstance().getToolBar().getBringToBackButton().setEnabled(false);
			MainFrame.getInstance().getToolBar().getBringToFrontButton().setEnabled(false);
		}
	}

}
