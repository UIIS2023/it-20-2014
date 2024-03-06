package application;

import javax.swing.JFrame;

import ctrl.Controller;
import gui.View;
import model.Model;
import observer.UpdateDeleteButton;
import observer.UpdateModifyButton;
import observer.UpdateZButtons;
import gui.MainFrame;
import gui.ToolBar;


public class MyApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame glavniProzor = MainFrame.getInstance();
		glavniProzor.setVisible(true);
		glavniProzor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		glavniProzor.setSize(1100, 600);
		glavniProzor.setTitle("Paint");
		ToolBar toolbar = glavniProzor.getToolBar();
		Model model = new Model();
		Controller controller = new Controller();
		UpdateDeleteButton updateDeleteButton = new UpdateDeleteButton();
		UpdateModifyButton updateModifyButton = new UpdateModifyButton();
		UpdateZButtons updateZButtons = new UpdateZButtons();
		controller.addObserver(updateDeleteButton);
		controller.addObserver(updateModifyButton);
		controller.addObserver(updateZButtons);
		controller.setModel(model);
		View view = glavniProzor.getDrawingPanel();
		view.setModel(model);
		view.setController(controller);
		toolbar.setController(controller);
		controller.setView(view);
		
		
		
	}

}
