package ctrl;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import commands.AddCommand;
import commands.BringToBackCommand;
import commands.BringToFrontCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ModifyCommand;
import commands.RedoCommand;
import commands.SelectCommand;
import commands.ToBackCommand;
import commands.ToFrontCommand;
import commands.UndoCommand;
import geometry.Circle;
import geometry.Square;
import geometry.Line;
import geometry.Shape;
import geometry.RectangleShape;
import geometry.Ring;
import geometry.Point;
import geometry.HexagonAdapter;
import gui.DialogBoxCircle;
import gui.DialogBoxCircleModify;
import gui.DialogBoxModify;
import gui.DialogBoxSquare;
import gui.DialogBoxSquareModify;
import gui.DialogBoxRectangle;
import gui.DialogBoxRectangleModify;
import gui.DialogBoxRing;
import gui.DialogBoxRingModify;
import gui.View;
import gui.DialogBox.Move;
import model.Model;
import observer.Observable;
import observer.Observer;
import save.SaveContext;
import save.SaveLogToTxtOperation;
import save.SaveSerializationOperation;
import gui.MainFrame;


public class Controller implements Observable {
	
	int idCounter=0;
	int counter=0;
	private int previousX;
	private int previousY;
	private Model model;
	private View view;
	public enum States {DRAWING, IDLE}
	private States state = States.IDLE;
	public static Shape poslednjiSelektovan = null;
	private ArrayList<Command> commands = new ArrayList<Command>();
	private ArrayList<Command> redoCommands = new ArrayList<Command>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private ArrayList<Command> logCommands = new ArrayList<Command>();
	private JFileChooser fileChooser;
	private FileFilter serFilter = new FileNameExtensionFilter("Serializable file", "ser");
	private FileFilter txtFilter = new FileNameExtensionFilter("Txt file", "txt");
	private int onClick;




	public void drawShape(int x, int y) {
		// TODO Auto-generated method stub
		switch (MainFrame.getInstance().getToolBar().getSelectedTool()){
		
		case LINE:
			if(state == States.IDLE){
				previousX = x;
				previousY = y;
				state = States.DRAWING;
			}else{
				Line l = new Line(new Point(previousX, previousY), new Point(x, y), MainFrame.getInstance().getToolBar().getLineColor(), idCounter);
				Line l1 = new Line(new Point(previousX, previousY), new Point(x, y), MainFrame.getInstance().getToolBar().getLineColor(), idCounter);
				AddCommand acmd = new AddCommand(l,model,l1);
				doCmd(acmd);
				idCounter++;
				state = States.IDLE;
			}
			break;
			
		case POINT:
			Point p = new Point(x, y, MainFrame.getInstance().getToolBar().getLineColor(), idCounter);
			Point p1 = new Point(x, y, MainFrame.getInstance().getToolBar().getLineColor(), idCounter);
			AddCommand acmd = new AddCommand(p,model,p1);
			doCmd(acmd);
			idCounter++;
			break;
			
		case CIRCLE:
			int x1 = x;
			int y1 = y;
			DialogBoxCircle dialogBoxKrug = new DialogBoxCircle();
			dialogBoxKrug.setVisible(true);
			if(dialogBoxKrug.getRadius() > 0){
				Circle cr = new Circle(new Point(x1, y1), dialogBoxKrug.getRadius(), MainFrame.getInstance().getToolBar().getLineColor(), MainFrame.getInstance().getToolBar().getFillColor(), idCounter);
				Circle cr1 = new Circle(new Point(x1, y1), dialogBoxKrug.getRadius(), MainFrame.getInstance().getToolBar().getLineColor(), MainFrame.getInstance().getToolBar().getFillColor(), idCounter);
				AddCommand acmd1 = new AddCommand(cr,model,cr1);
				doCmd(acmd1);
				idCounter++;
			}
			break;
			
		case HEXAGON:
			int x4 = x;
			int y4 = y;
			DialogBoxCircle dialogBoxHex = new DialogBoxCircle();
			dialogBoxHex.setVisible(true);
			if(dialogBoxHex.getRadius() > 0){
				HexagonAdapter hex = new HexagonAdapter(false,MainFrame.getInstance().getToolBar().getLineColor(),MainFrame.getInstance().getToolBar().getFillColor(), new Point(x4,y4), dialogBoxHex.getRadius(), idCounter);
				HexagonAdapter hex1 = new HexagonAdapter(false,MainFrame.getInstance().getToolBar().getLineColor(),MainFrame.getInstance().getToolBar().getFillColor(), new Point(x4,y4), dialogBoxHex.getRadius(), idCounter);
				AddCommand acmd1 = new AddCommand(hex,model,hex1);
				doCmd(acmd1);
				idCounter++;
			}
			break;
			
		case RECTANGLE:
			int x2 = x;
			int y2 = y;
			DialogBoxRectangle dialogBoxPravougaonik = new DialogBoxRectangle();
			dialogBoxPravougaonik.setVisible(true);
			if(dialogBoxPravougaonik.getWidthh() > 0 && dialogBoxPravougaonik.getHeightt() > 0){

					RectangleShape rec = new RectangleShape(new Point(x2, y2), dialogBoxPravougaonik.getWidthh(), dialogBoxPravougaonik.getHeightt(), MainFrame.getInstance().getToolBar().getLineColor(), MainFrame.getInstance().getToolBar().getFillColor(), idCounter);
					RectangleShape rec1 = new RectangleShape(new Point(x2, y2), dialogBoxPravougaonik.getWidthh(), dialogBoxPravougaonik.getHeightt(), MainFrame.getInstance().getToolBar().getLineColor(), MainFrame.getInstance().getToolBar().getFillColor(), idCounter);
					AddCommand acmd2 = new AddCommand(rec,model,rec1);
					doCmd(acmd2);
					idCounter++;
			}
			break;
			
		case SQUARE:
			int x3 = x;
			int y3 = y;
			DialogBoxSquare dialogBoxKvadrat = new DialogBoxSquare();
			dialogBoxKvadrat.setVisible(true);
			if(dialogBoxKvadrat.getSideLength() > 0){
					Square sq = new Square(new Point(x3, y3), dialogBoxKvadrat.getSideLength(), MainFrame.getInstance().getToolBar().getLineColor(), MainFrame.getInstance().getToolBar().getFillColor(), idCounter);
					Square sq1 = new Square(new Point(x3, y3), dialogBoxKvadrat.getSideLength(), MainFrame.getInstance().getToolBar().getLineColor(), MainFrame.getInstance().getToolBar().getFillColor(), idCounter);
					AddCommand acmd2 = new AddCommand(sq,model,sq1);
					doCmd(acmd2);
					idCounter++;
			}
			break;
			
		case RING:
			int x5 = x;
			int y5 = y;
			DialogBoxRing dialogBoxRing = new DialogBoxRing();
			dialogBoxRing.setVisible(true);
			if(dialogBoxRing.getRadius() >0 && dialogBoxRing.getThickness() >0){
					Ring r = new Ring(new Point(x5,y5), dialogBoxRing.getRadius(), dialogBoxRing.getThickness(), MainFrame.getInstance().getToolBar().getLineColor(), MainFrame.getInstance().getToolBar().getFillColor(), idCounter);
					Ring r1 = new Ring(new Point(x5,y5), dialogBoxRing.getRadius(), dialogBoxRing.getThickness(), MainFrame.getInstance().getToolBar().getLineColor(), MainFrame.getInstance().getToolBar().getFillColor(), idCounter);
					AddCommand acmd3 = new AddCommand(r,model,r1);
					doCmd(acmd3);
					idCounter++;
			}
			break;
			
		case SELECT:
			Iterator<Shape> it = model.getSelectedShapes().iterator();
			ArrayList<Shape> deselectedShapes;
			boolean removed = false;
			if(view.isCtrlPressed()) {
				for(int i=model.getShapes().size()-1;i>=0;i--) {
					
					Shape o = model.getShapes().get(i);
					if(o.contains(x, y)){
						if(!model.getSelectedShapes().isEmpty()) {
							for(int j = model.getSelectedShapes().size()-1; j>=0;j--) {
								if(model.getSelectedShapes().get(j)==o) {
									deselectedShapes = new ArrayList();
									deselectedShapes.add(o);
									SelectCommand s = new SelectCommand(null, deselectedShapes, model);
									doCmd(s);
									removed = true;
									break;
								}
							}
						}
						if(removed)
							break;
						else {
							deselectedShapes = new ArrayList();
							SelectCommand s = new SelectCommand(o, deselectedShapes, model);
							doCmd(s);
							break;
						}
					}
				}
			}else {		
				boolean selected = false;
				for(int i=model.getShapes().size()-1;i>=0;i--) {
					Shape o = model.getShapes().get(i);
					if(o.contains(x, y)){
						if(model.getSelectedShapes().size()>1) {
							deselectedShapes = new ArrayList();
							Iterator<Shape> itds = model.getSelectedShapes().iterator();
							while(itds.hasNext()) {
								deselectedShapes.add(itds.next());
							}
							//deselectedShapes.remove(o);
							SelectCommand s = new SelectCommand(o, deselectedShapes, model);
							doCmd(s);
							selected = true;
							break;
						}else {
							if(model.getSelectedShapes().contains(o)) {
								selected=true;
								break;
							}else {
								deselectedShapes = new ArrayList();
								Iterator<Shape> itds = model.getSelectedShapes().iterator();
								while(itds.hasNext()) {
									deselectedShapes.add(itds.next());
								}
								SelectCommand s = new SelectCommand(o, deselectedShapes, model);
								doCmd(s);
								selected = true;
								break;
							}
						}
					}
				}
				if(!selected) {
					  if(!model.getSelectedShapes().isEmpty()) {
						  deselectedShapes = new ArrayList();
						  while(it.hasNext()) { 
							  deselectedShapes.add(it.next());
							  } 
						  SelectCommand s = new SelectCommand(null, deselectedShapes, model);
						  doCmd(s);
						  }
				}
				
			}
			logCommands();
			notifyObservers();
			MainFrame.getInstance().repaint();

		}
	}
	
	public void doLogCmd() {
		logCommands.get(counter).execute();
		if(!(logCommands.get(counter) instanceof UndoCommand) && !(logCommands.get(counter) instanceof RedoCommand)) {
			commands.add(logCommands.get(counter));
		}
		counter++;
		MainFrame.getInstance().repaint();
		if(logCommands.size()==counter) {
			MainFrame.getInstance().getToolBar().getDoCmdButton().setEnabled(false);
		}
		if(!redoCommands.isEmpty())
			MainFrame.getInstance().getToolBar().getRedoButton().setEnabled(true);
		else
			MainFrame.getInstance().getToolBar().getRedoButton().setEnabled(false);
		if(!commands.isEmpty())
			MainFrame.getInstance().getToolBar().getUndoButton().setEnabled(true);
		else
			MainFrame.getInstance().getToolBar().getUndoButton().setEnabled(false);
	}
	
	public void deleteShape() {
		DeleteCommand dcmd = new DeleteCommand(model);
		doCmd(dcmd);
		poslednjiSelektovan = null;
		notifyObservers();
	}
	
	public void keyPressed (int evt)
	{
		if((evt & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK) {
		    view.setCtrlPressed(true);
		}else {
			view.setCtrlPressed(false);
		}
	}
	
	private void doCmd(Command cmd) {
		commands.add(cmd);
		logCommands.add(cmd);
		cmd.execute();
		MainFrame.getInstance().repaint();
		MainFrame.getInstance().getToolBar().getUndoButton().setEnabled(true);
		MainFrame.getInstance().getToolBar().getRedoButton().setEnabled(false);
		MainFrame.getInstance().getToolBar().getDoCmdButton().setEnabled(false);
		redoCommands.clear();
		
		
		
	}
	
	public void undoCmd() {
		UndoCommand ucmd = new UndoCommand(commands, redoCommands);
		logCommands.add(ucmd);
		ucmd.execute();
		MainFrame.getInstance().repaint();
		MainFrame.getInstance().getToolBar().getRedoButton().setEnabled(true);
		notifyObservers();
		logCommands();
		if(commands.isEmpty())
			MainFrame.getInstance().getToolBar().getUndoButton().setEnabled(false);
		
	}
	
	public void redoCmd() {
		RedoCommand rcmd = new RedoCommand(commands, redoCommands);
		logCommands.add(rcmd);
		rcmd.execute();
		MainFrame.getInstance().repaint();
		MainFrame.getInstance().getToolBar().getUndoButton().setEnabled(true);
		notifyObservers();
		logCommands();
		if(redoCommands.isEmpty())
			MainFrame.getInstance().getToolBar().getRedoButton().setEnabled(false);
	}
	
	public void logCommands() {
		MainFrame.getInstance().getChangeLog().setText(null);
		Iterator<Command> itc = logCommands.iterator();
		while(itc.hasNext()) {
			Command c = itc.next();
			if(c instanceof AddCommand) {
				MainFrame.getInstance().getChangeLog().append("Draw shape \n");
			}else if (c instanceof BringToBackCommand) {
				MainFrame.getInstance().getChangeLog().append("Bring To Back \n");
			}else if (c instanceof BringToFrontCommand) {
				MainFrame.getInstance().getChangeLog().append("Bring To Front \n");
			}else if (c instanceof ToBackCommand) {
				MainFrame.getInstance().getChangeLog().append("To Back \n");
			}else if (c instanceof ToFrontCommand) {
				MainFrame.getInstance().getChangeLog().append("To Front \n");
			}else if (c instanceof DeleteCommand) {
				MainFrame.getInstance().getChangeLog().append("Delete \n");
			}else if (c instanceof ModifyCommand) {
				MainFrame.getInstance().getChangeLog().append("Modify \n");
			}else if (c instanceof SelectCommand) {
				MainFrame.getInstance().getChangeLog().append("Select/deselect \n");
			}else if (c instanceof UndoCommand) {
				MainFrame.getInstance().getChangeLog().append("Undo \n");
			}else if (c instanceof RedoCommand) {
				MainFrame.getInstance().getChangeLog().append("Redo \n");
			}
		}
	}
		
	
	public void modifyCmd() {
		ModifyCommand mcmd = new ModifyCommand(model.getSelectedShapes().get(0));
		doCmd(mcmd);
	}
	
	public void toBackCmd() {
		ToBackCommand tbcmd = new ToBackCommand(model);
		doCmd(tbcmd);
		notifyObservers();
	}
	
	public void toFrontCmd() {
		ToFrontCommand tfcmd = new ToFrontCommand(model);
		doCmd(tfcmd);
		notifyObservers();
	}
	
	public void bringToBackCmd() {
		BringToBackCommand btbcmd = new BringToBackCommand(model);
		doCmd(btbcmd);
		notifyObservers();
	}
	
	public void bringToFrontCmd() {
		BringToFrontCommand btfcmd = new BringToFrontCommand(model);
		doCmd(btfcmd);
		notifyObservers();
	}
	
	public void saveFile() {
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(serFilter);
		fileChooser.addChoosableFileFilter(txtFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		onClick=fileChooser.showSaveDialog(MainFrame.getInstance());
		if(onClick == JFileChooser.APPROVE_OPTION){
			
			if(fileChooser.getFileFilter().equals(serFilter)){
				SaveContext context = new SaveContext(new SaveSerializationOperation());
				context.executeStrategy(fileChooser, model, logCommands);
			}else if(fileChooser.getFileFilter().equals(txtFilter)){
				SaveContext context = new SaveContext(new SaveLogToTxtOperation());
				context.executeStrategy(fileChooser, model, logCommands);
			}
		}
	}
	
	public void loadFile() {
		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(serFilter);
		fileChooser.addChoosableFileFilter(txtFilter);
		onClick=fileChooser.showOpenDialog(MainFrame.getInstance());
		if(onClick == JFileChooser.APPROVE_OPTION){
			if(fileChooser.getSelectedFile()!= null){
				redoCommands.clear();
				commands.clear();
				logCommands.clear();
				model.getShapes().clear();
				model.getSelectedShapes().clear();

				
				if(fileChooser.getSelectedFile().getAbsolutePath().toString().endsWith(".ser")) {
					
					model.getShapes().clear();
					ArrayList<Command> desserializedShapes=null;

					try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(
							fileChooser.getSelectedFile().toString()))) {

						desserializedShapes = (ArrayList<Command>) in.readObject();

					}catch(IOException | ClassNotFoundException i) {
						i.printStackTrace();        

					}

					for(Command c: desserializedShapes){
						if(!(c instanceof UndoCommand) && !(c instanceof RedoCommand)) {
							commands.add(c);
							c.setModel(model);
							
						}
						if(c instanceof UndoCommand) {
							UndoCommand ucmd = (UndoCommand) c;
							ucmd.setCommands(commands);
							ucmd.setRedoCommands(redoCommands);
						}else if(c instanceof RedoCommand) {
							RedoCommand rcmd = (RedoCommand) c;
							rcmd.setCommands(commands);
							rcmd.setRedoCommands(redoCommands);
						}
						logCommands.add(c);
						MainFrame.getInstance().getToolBar().getUndoButton().setEnabled(true);
						logCommands();
						c.execute();
						if(c.getShape() != null) {
							if(c.getShape().getId()>=idCounter) {
								idCounter=c.getShape().getId();
								idCounter++;
							}
						}

					}
					if(!redoCommands.isEmpty()) {
						MainFrame.getInstance().getToolBar().getRedoButton().setEnabled(true);
					}
					MainFrame.getInstance().repaint();
				}else if(fileChooser.getSelectedFile().getAbsolutePath().toString().endsWith(".txt")) {
					Scanner s = null;
					ArrayList<String> list = new ArrayList<String>();
					ArrayList<Command> temp = new ArrayList<Command>();
					try {
						s = new Scanner(new File(fileChooser.getSelectedFile().toString()));
						
						while(s.hasNext()) {
							list.add(s.next());
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					s.close();
					for(int i=0; i<list.size(); i++) {
						if(list.get(i).contentEquals("AddCommand")) {
							AddCommand acmd1 = new AddCommand(parseShape(i,list),model,parseShape(i,list));
							logCommands.add(acmd1);
							if(acmd1.getShape().getId()>idCounter) {
								idCounter=acmd1.getShape().getId();
							}
						}else if(list.get(i).contentEquals("DeleteCommand")) {
							ArrayList<Integer> id = new ArrayList<Integer>();
							ArrayList<Integer> index = new ArrayList<Integer>();
							int j = i;
							while(!list.get(j).contentEquals("end")) {
								if(list.get(j).contentEquals("Shape")) {
									id.add(Integer.parseInt(list.get(j+1).substring(list.get(j+1).lastIndexOf("=")+1)));
									index.add(Integer.parseInt(list.get(j+2).substring(list.get(j+2).lastIndexOf("=")+1)));
								}
								j++;
							}
							DeleteCommand dcmd = new DeleteCommand();
							dcmd.setModel(model);
							LinkedHashMap<Shape,Integer> deletedShapes = new LinkedHashMap<>();
							for(int k=0; k<=id.size()-1;k++) {
								Shape ss = null;
								for(Command c : logCommands) {
									if(c.getShape()!=null) {
										if(c.getShape().getId()==id.get(k)) {
											ss = c.getShape();
										}
									}
										
								}
								deletedShapes.put(ss, index.get(k));
							}
							dcmd.setDeletedShapes(deletedShapes);
							logCommands.add(dcmd);
						}else if(list.get(i).contentEquals("BringToBackCommand")) {
							BringToBackCommand btbcmd = new BringToBackCommand();
							btbcmd.setModel(model);
							btbcmd.setI(Integer.parseInt(list.get(i+1).substring(list.get(i+1).lastIndexOf("=")+1)));
							logCommands.add(btbcmd);
							
						}else if(list.get(i).contentEquals("BringToFrontCommand")) {
							BringToFrontCommand btfcmd = new BringToFrontCommand();
							btfcmd.setModel(model);
							btfcmd.setI(Integer.parseInt(list.get(i+1).substring(list.get(i+1).lastIndexOf("=")+1)));
							logCommands.add(btfcmd);
							
						}else if(list.get(i).contentEquals("ToBackCommand")) {
							ToBackCommand tbcmd = new ToBackCommand();
							tbcmd.setModel(model);
							tbcmd.setI(Integer.parseInt(list.get(i+1).substring(list.get(i+1).lastIndexOf("=")+1)));
							logCommands.add(tbcmd);
							
						}else if(list.get(i).contentEquals("ToFrontCommand")) {
							ToFrontCommand tfcmd = new ToFrontCommand();
							tfcmd.setModel(model);
							tfcmd.setI(Integer.parseInt(list.get(i+1).substring(list.get(i+1).lastIndexOf("=")+1)));
							logCommands.add(tfcmd);
							
						}else if(list.get(i).contentEquals("SelectCommand")) {
							Shape scs = null;
							ArrayList<Shape> dsal = new ArrayList<Shape>();
							if(!list.get(i+2).contentEquals("Shape")) {
								for(Command c : logCommands) {
									if(c.getShape() != null)
										if(c.getShape().getId()==Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1))) {
											scs = c.getShape();
										}
								}
							}
							if(!list.get(i+3).contentEquals("end")) {
								System.out.println("Test");
								int j = i;
								while(!list.get(j).contentEquals("end")) {
									if(list.get(j).contentEquals("Shape")) {
										int id = Integer.parseInt(list.get(j+1).substring(list.get(j+1).lastIndexOf("=")+1));
										for(Command c : logCommands){
											if(c.getShape() != null)
												if(c.getShape().getId()==id)
													dsal.add(c.getShape());
										}
									}
									j++;
								}
							}
							SelectCommand sccmd = new SelectCommand(scs,dsal,model);
							logCommands.add(sccmd);
						}else if(list.get(i).contentEquals("ModifyCommand")) {
							ModifyCommand mcmd = new ModifyCommand();
							logCommands.add(parseCommand(mcmd, list, i, logCommands));
							
							
						}else if(list.get(i).contentEquals("UndoCommand")) {
							UndoCommand ucmd = new UndoCommand(commands, redoCommands);
							logCommands.add(ucmd);
							
						}else if(list.get(i).contentEquals("RedoCommand")) {
							RedoCommand rcmd = new RedoCommand(commands, redoCommands);
							logCommands.add(rcmd);
							
						}
					}
					/*
					 * for(int i = logCommands.size()-1; i>=0; i--) { temp.add(logCommands.get(i));
					 * } logCommands = temp;
					 */
					logCommands();
					MainFrame.getInstance().repaint();
					MainFrame.getInstance().getToolBar().getDoCmdButton().setEnabled(true);
				}
				if(redoCommands.size()>0)
					MainFrame.getInstance().getToolBar().getRedoButton().setEnabled(true);
				else
					MainFrame.getInstance().getToolBar().getRedoButton().setEnabled(false);
				if(commands.size()>0)
					MainFrame.getInstance().getToolBar().getUndoButton().setEnabled(true);
				else
					MainFrame.getInstance().getToolBar().getUndoButton().setEnabled(false);
				notifyObservers();
			}
		}
		
	}
	
	private Shape parseShape(int i,ArrayList<String> list ) {

		if(list.get(i+1).contentEquals("Circle")) {
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int x = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int y = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int r = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1)));
			Color fillColor = new Color(Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1)));
			Circle cr = new Circle(new Point(x, y), r, color, fillColor, id);
			return cr;
		}else if(list.get(i+1).contentEquals("Point")){
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int x = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int y = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1)));
			Point p = new Point(x,y,color, id);
			return p;
			
		}else if(list.get(i+1).contentEquals("Line")){
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int x = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int y = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int x1 = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			int y1 = Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1)));
			Line l = new Line(new Point(x,y),new Point(x1,y1),color, id);
			return l;
		}else if(list.get(i+1).contentEquals("Hexagon")){
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int x = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int y = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int r = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1)));
			Color fillColor = new Color(Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1)));
			HexagonAdapter hex = new HexagonAdapter(false, color, fillColor, new Point(x,y), r, id);
			return hex;
		}else if(list.get(i+1).contentEquals("Ring")){
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int x = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int y = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int r = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			int t = Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1)));
			Color fillColor = new Color(Integer.parseInt(list.get(i+8).substring(list.get(i+8).lastIndexOf("=")+1)));
			Ring ring = new Ring(new Point(x,y),r,t,color,fillColor, id);
			return ring;
		}else if(list.get(i+1).contentEquals("Square")){
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int x = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int y = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int w = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1)));
			Color fillColor = new Color(Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1)));
			Square sq = new Square(new Point(x,y),w,color,fillColor, id);
			return sq;
		}else if(list.get(i+1).contentEquals("Rectangle")){
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int x = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int y = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int w = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			int h = Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1)));
			Color fillColor = new Color(Integer.parseInt(list.get(i+8).substring(list.get(i+8).lastIndexOf("=")+1)));
			RectangleShape rec = new RectangleShape(new Point(x,y),w,h,color,fillColor, id);
			return rec;
		}else 
			return null;
	}
	
	private Command parseCommand(ModifyCommand c, ArrayList<String> list, int i,ArrayList<Command> redoCommands) {
		if(list.get(i+1).contentEquals("Circle")) {
			DialogBoxCircleModify dbcm = new DialogBoxCircleModify();
			c.setDialogBoxKrugSelect(dbcm);
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int toX = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int toY = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int byX = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			int byY = Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1));
			int r = Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+8).substring(list.get(i+8).lastIndexOf("=")+1)));
			Color fillColor = new Color(Integer.parseInt(list.get(i+9).substring(list.get(i+9).lastIndexOf("=")+1)));
			String state = list.get(i+1).substring(list.get(i+10).lastIndexOf("=")+1);
			int previousX = Integer.parseInt(list.get(i+12).substring(list.get(i+12).lastIndexOf("=")+1));
			int previousY = Integer.parseInt(list.get(i+13).substring(list.get(i+13).lastIndexOf("=")+1));
			int previousR = Integer.parseInt(list.get(i+14).substring(list.get(i+14).lastIndexOf("=")+1));
			Color previousColor = new Color(Integer.parseInt(list.get(i+15).substring(list.get(i+15).lastIndexOf("=")+1)));
			Color previousFillColor = new Color(Integer.parseInt(list.get(i+16).substring(list.get(i+16).lastIndexOf("=")+1)));
			c.setStartPoint(new Point(previousX,previousY));
			c.setDimension1(previousR);
			c.setColor(previousColor);
			c.setFillColor(previousFillColor);
			c.getDialogBoxKrugSelect().setRadius(Integer.toString(r));
			c.getDialogBoxKrugSelect().setMoveToX(toX);
			c.getDialogBoxKrugSelect().setMoveToY(toY);
			c.getDialogBoxKrugSelect().setMoveByX(byX);
			c.getDialogBoxKrugSelect().setMoveByY(byY);
			c.getDialogBoxKrugSelect().setLineColor(color);
			c.getDialogBoxKrugSelect().setFillColor(fillColor);
			c.getDialogBoxKrugSelect().setState(Move.valueOf(state));
			for(Command cmd : redoCommands) {
				if(cmd.getShape() != null){
					if(cmd.getShape().getId()==id) {
						ModifyCommand mcmd = (ModifyCommand) c;
						mcmd.setShape(cmd.getShape());
					}
				}
			}
		}else if(list.get(i+1).contentEquals("Point")){
			DialogBoxModify dbm = new DialogBoxModify();
			c.setDialogBoxModify(dbm);
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int toX = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int toY = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int byX = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			int byY = Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1)));
			String state = list.get(i+8).substring(list.get(i+8).lastIndexOf("=")+1);
			int previousX = Integer.parseInt(list.get(i+10).substring(list.get(i+10).lastIndexOf("=")+1));
			int previousY = Integer.parseInt(list.get(i+11).substring(list.get(i+11).lastIndexOf("=")+1));
			Color previousColor = new Color(Integer.parseInt(list.get(i+12).substring(list.get(i+12).lastIndexOf("=")+1)));
			c.setStartPoint(new Point(previousX,previousY));
			c.setColor(previousColor);
			c.getDialogBoxModify().setMoveToX(toX);
			c.getDialogBoxModify().setMoveToY(toY);
			c.getDialogBoxModify().setMoveByX(byX);
			c.getDialogBoxModify().setMoveByY(byY);
			c.getDialogBoxModify().setLineColor(color);
			c.getDialogBoxModify().setState(Move.valueOf(state)); 
			for(Command cmd : redoCommands) {
				if(cmd.getShape() != null){
					if(cmd.getShape().getId()==id) {
						ModifyCommand mcmd = (ModifyCommand) c;
						mcmd.setShape(cmd.getShape());
					}
				}
			}
			
		}else if(list.get(i+1).contentEquals("Line")){
			DialogBoxModify dbm = new DialogBoxModify();
			c.setDialogBoxModify(dbm);
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int toX = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int toY = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int byX = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			int byY = Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1)));
			String state = list.get(i+8).substring(list.get(i+8).lastIndexOf("=")+1);
			int previousX = Integer.parseInt(list.get(i+10).substring(list.get(i+10).lastIndexOf("=")+1));
			int previousY = Integer.parseInt(list.get(i+11).substring(list.get(i+11).lastIndexOf("=")+1));
			int previousX1 = Integer.parseInt(list.get(i+12).substring(list.get(i+12).lastIndexOf("=")+1));
			int previousY1 = Integer.parseInt(list.get(i+13).substring(list.get(i+13).lastIndexOf("=")+1));
			Color previousColor = new Color(Integer.parseInt(list.get(i+14).substring(list.get(i+14).lastIndexOf("=")+1)));
			c.setStartPoint(new Point(previousX,previousY));
			c.setEndPoint(new Point(previousX1,previousY1));
			c.setColor(previousColor);
			c.getDialogBoxModify().setMoveToX(toX);
			c.getDialogBoxModify().setMoveToY(toY);
			c.getDialogBoxModify().setMoveByX(byX);
			c.getDialogBoxModify().setMoveByY(byY);
			c.getDialogBoxModify().setLineColor(color);
			c.getDialogBoxModify().setState(Move.valueOf(state)); 
			for(Command cmd : redoCommands) {
				if(cmd.getShape() != null){
					if(cmd.getShape().getId()==id) {
						ModifyCommand mcmd = (ModifyCommand) c;
						mcmd.setShape(cmd.getShape());
					}
				}
			}
			
		}else if(list.get(i+1).contentEquals("Hexagon")){
			DialogBoxCircleModify dbcm = new DialogBoxCircleModify();
			c.setDialogBoxKrugSelect(dbcm);
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int toX = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int toY = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int byX = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			int byY = Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1));
			int r = Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+8).substring(list.get(i+8).lastIndexOf("=")+1)));
			Color fillColor = new Color(Integer.parseInt(list.get(i+9).substring(list.get(i+9).lastIndexOf("=")+1)));
			String state = list.get(i+10).substring(list.get(i+10).lastIndexOf("=")+1);
			int previousX = Integer.parseInt(list.get(i+12).substring(list.get(i+12).lastIndexOf("=")+1));
			int previousY = Integer.parseInt(list.get(i+13).substring(list.get(i+13).lastIndexOf("=")+1));
			int previousR = Integer.parseInt(list.get(i+14).substring(list.get(i+14).lastIndexOf("=")+1));
			Color previousColor = new Color(Integer.parseInt(list.get(i+15).substring(list.get(i+15).lastIndexOf("=")+1)));
			Color previousFillColor = new Color(Integer.parseInt(list.get(i+16).substring(list.get(i+16).lastIndexOf("=")+1)));
			c.setStartPoint(new Point(previousX,previousY));
			c.setDimension1(previousR);
			c.setColor(previousColor);
			c.setFillColor(previousFillColor);
			c.getDialogBoxKrugSelect().setRadius(Integer.toString(r));
			c.getDialogBoxKrugSelect().setMoveToX(toX);
			c.getDialogBoxKrugSelect().setMoveToY(toY);
			c.getDialogBoxKrugSelect().setMoveByX(byX);
			c.getDialogBoxKrugSelect().setMoveByY(byY);
			c.getDialogBoxKrugSelect().setLineColor(color);
			c.getDialogBoxKrugSelect().setFillColor(fillColor);
			c.getDialogBoxKrugSelect().setState(Move.valueOf(state));
			for(Command cmd : redoCommands) {
				if(cmd.getShape() != null){
					if(cmd.getShape().getId()==id) {
						ModifyCommand mcmd = (ModifyCommand) c;
						mcmd.setShape(cmd.getShape());
					}
				}
			}
			
		}else if(list.get(i+1).contentEquals("Ring")){
			DialogBoxRingModify dbrm = new DialogBoxRingModify();
			c.setDialogBoxRingSelect(dbrm);
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int toX = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int toY = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int byX = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			int byY = Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1));
			int r = Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1));
			int t = Integer.parseInt(list.get(i+8).substring(list.get(i+8).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+9).substring(list.get(i+9).lastIndexOf("=")+1)));
			Color fillColor = new Color(Integer.parseInt(list.get(i+10).substring(list.get(i+10).lastIndexOf("=")+1)));
			String state = list.get(i+11).substring(list.get(i+11).lastIndexOf("=")+1);
			int previousX = Integer.parseInt(list.get(i+13).substring(list.get(i+13).lastIndexOf("=")+1));
			int previousY = Integer.parseInt(list.get(i+14).substring(list.get(i+14).lastIndexOf("=")+1));
			int previousR = Integer.parseInt(list.get(i+15).substring(list.get(i+15).lastIndexOf("=")+1));
			int previousT = Integer.parseInt(list.get(i+16).substring(list.get(i+16).lastIndexOf("=")+1));
			Color previousColor = new Color(Integer.parseInt(list.get(i+17).substring(list.get(i+17).lastIndexOf("=")+1)));
			Color previousFillColor = new Color(Integer.parseInt(list.get(i+18).substring(list.get(i+18).lastIndexOf("=")+1)));
			c.setStartPoint(new Point(previousX,previousY));
			c.setDimension1(previousR);
			c.setDimension2(previousT);
			c.setColor(previousColor);
			c.setFillColor(previousFillColor);
			c.getDialogBoxRingSelect().setRadius(Integer.toString(r));
			c.getDialogBoxRingSelect().setThickness(Integer.toString(t));
			c.getDialogBoxRingSelect().setLineColor(color);
			c.getDialogBoxRingSelect().setFillColor(fillColor);
			c.getDialogBoxRingSelect().setMoveByX(byX);
			c.getDialogBoxRingSelect().setMoveByY(byY);
			c.getDialogBoxRingSelect().setMoveToX(toX);
			c.getDialogBoxRingSelect().setMoveToY(toY);
			c.getDialogBoxRingSelect().setState(Move.valueOf(state));
			for(Command cmd : redoCommands) {
				if(cmd.getShape() != null){
					if(cmd.getShape().getId()==id) {
						ModifyCommand mcmd = (ModifyCommand) c;
						mcmd.setShape(cmd.getShape());
					}
				}
			}
		}else if(list.get(i+1).contentEquals("Square")){
			DialogBoxSquareModify dbsm = new DialogBoxSquareModify();
			c.setDialogBoxKvadratSelect(dbsm);
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int toX = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int toY = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int byX = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			int byY = Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1));
			int w = Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+8).substring(list.get(i+8).lastIndexOf("=")+1)));
			Color fillColor = new Color(Integer.parseInt(list.get(i+9).substring(list.get(i+9).lastIndexOf("=")+1)));
			String state = list.get(i+10).substring(list.get(i+10).lastIndexOf("=")+1);
			int previousX = Integer.parseInt(list.get(i+12).substring(list.get(i+12).lastIndexOf("=")+1));
			int previousY = Integer.parseInt(list.get(i+13).substring(list.get(i+13).lastIndexOf("=")+1));
			int prevoiusW = Integer.parseInt(list.get(i+14).substring(list.get(i+14).lastIndexOf("=")+1));
			Color prevoiusColor = new Color(Integer.parseInt(list.get(i+15).substring(list.get(i+15).lastIndexOf("=")+1)));
			Color prevoiusFillColor = new Color(Integer.parseInt(list.get(i+16).substring(list.get(i+16).lastIndexOf("=")+1)));
			c.setStartPoint(new Point(previousX,previousY));
			c.setDimension1(prevoiusW);
			c.setColor(prevoiusColor);
			c.setFillColor(prevoiusFillColor);
			c.getDialogBoxKvadratSelect().setSideLength(Integer.toString(w));;
			c.getDialogBoxKvadratSelect().setLineColor(color);
			c.getDialogBoxKvadratSelect().setFillColor(fillColor);
			c.getDialogBoxKvadratSelect().setMoveByX(byX);
			c.getDialogBoxKvadratSelect().setMoveByY(byY);
			c.getDialogBoxKvadratSelect().setMoveToX(toX);
			c.getDialogBoxKvadratSelect().setMoveToY(toY);
			c.getDialogBoxKvadratSelect().setState(Move.valueOf(state));
			for(Command cmd : redoCommands) {
				if(cmd.getShape() != null){
					if(cmd.getShape().getId()==id) {
						ModifyCommand mcmd = (ModifyCommand) c;
						mcmd.setShape(cmd.getShape());
					}
				}
			}
			
		}else if(list.get(i+1).contentEquals("Rectangle")){
			DialogBoxRectangleModify dbrm = new DialogBoxRectangleModify();
			c.setDialogBoxPravougaonikSelect(dbrm);
			int id =Integer.parseInt(list.get(i+2).substring(list.get(i+2).lastIndexOf("=")+1));
			int toX = Integer.parseInt(list.get(i+3).substring(list.get(i+3).lastIndexOf("=")+1));
			int toY = Integer.parseInt(list.get(i+4).substring(list.get(i+4).lastIndexOf("=")+1));
			int byX = Integer.parseInt(list.get(i+5).substring(list.get(i+5).lastIndexOf("=")+1));
			int byY = Integer.parseInt(list.get(i+6).substring(list.get(i+6).lastIndexOf("=")+1));
			int w = Integer.parseInt(list.get(i+7).substring(list.get(i+7).lastIndexOf("=")+1));
			int h = Integer.parseInt(list.get(i+8).substring(list.get(i+8).lastIndexOf("=")+1));
			Color color = new Color(Integer.parseInt(list.get(i+9).substring(list.get(i+9).lastIndexOf("=")+1)));
			Color fillColor = new Color(Integer.parseInt(list.get(i+10).substring(list.get(i+10).lastIndexOf("=")+1)));
			String state = list.get(i+11).substring(list.get(i+11).lastIndexOf("=")+1);
			int previousX = Integer.parseInt(list.get(i+13).substring(list.get(i+13).lastIndexOf("=")+1));
			int previousY = Integer.parseInt(list.get(i+14).substring(list.get(i+14).lastIndexOf("=")+1));
			int previousW = Integer.parseInt(list.get(i+15).substring(list.get(i+15).lastIndexOf("=")+1));
			int previousH = Integer.parseInt(list.get(i+16).substring(list.get(i+16).lastIndexOf("=")+1));
			Color previousColor = new Color(Integer.parseInt(list.get(i+17).substring(list.get(i+17).lastIndexOf("=")+1)));
			Color previousFillColor = new Color(Integer.parseInt(list.get(i+18).substring(list.get(i+18).lastIndexOf("=")+1)));
			c.setStartPoint(new Point(previousX,previousY));
			c.setDimension1(previousW);
			c.setDimension2(previousH);
			c.setColor(previousColor);
			c.setFillColor(previousFillColor);
			c.getDialogBoxPravougaonikSelect().setWidthh(Integer.toString(w));
			c.getDialogBoxPravougaonikSelect().setHeightt(Integer.toString(h));
			c.getDialogBoxPravougaonikSelect().setLineColor(color);
			c.getDialogBoxPravougaonikSelect().setFillColor(fillColor);
			c.getDialogBoxPravougaonikSelect().setMoveByX(byX);
			c.getDialogBoxPravougaonikSelect().setMoveByY(byY);
			c.getDialogBoxPravougaonikSelect().setMoveToX(toX);
			c.getDialogBoxPravougaonikSelect().setMoveToY(toY);
			c.getDialogBoxPravougaonikSelect().setState(Move.valueOf(state));
			for(Command cmd : redoCommands) {
				if(cmd.getShape() != null){
					if(cmd.getShape().getId()==id) {
						ModifyCommand mcmd = (ModifyCommand) c;
						mcmd.setShape(cmd.getShape());
					}
				}
			}
		}
		return c;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Shape getPoslednjiSelektovan() {
		return poslednjiSelektovan;
	}

	public void setPoslednjiSelektovan(Shape poslednjiSelektovan) {
		this.poslednjiSelektovan = poslednjiSelektovan;
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public States getStanje() {
		return state;
	}

	public void setStanje(States stanje) {
		this.state = stanje;
	}

	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
		
	}

	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);		
	}

	@Override
	public void notifyObservers() {
		
		Iterator<Observer> it = observers.iterator();
		while (it.hasNext())
			it.next().update(model.getSelectedShapes(),model.getShapes());
		
	}

}
