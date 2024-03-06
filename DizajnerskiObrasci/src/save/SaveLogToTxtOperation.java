package save;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.FileWriter;

import javax.swing.JFileChooser;

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
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.RectangleShape;
import geometry.Ring;
import geometry.Shape;
import geometry.Square;
import gui.MainFrame;
public class SaveLogToTxtOperation implements Strategy {
	private File file;
	@Override
	public void doOperation(JFileChooser fileChooser, ArrayList<Command> commands) {
		file = new File(fileChooser.getSelectedFile().toString());
		if(file.getAbsolutePath().toString().endsWith(".txt")) {  
		} else
			file=new File(fileChooser.getSelectedFile().toString()+".txt"); 
		if(file.exists()) {
			file.delete();
		}
		  FileWriter writer= null; 
		  try{ 
			  writer = new FileWriter(fileChooser.getSelectedFile().toString()+".txt"); 
			  for(Command c:commands) { 
					if(c instanceof AddCommand) {
						writer.write("AddCommand"+System.lineSeparator());
						AddCommand acmd = (AddCommand) c;
						Shape s = acmd.getOriginalShape();
						printShapes(writer,s);
						
					}else if (c instanceof BringToBackCommand) {
						writer.write("BringToBackCommand"+System.lineSeparator());
						BringToBackCommand btbcmd = (BringToBackCommand) c;
						writer.write("index="+btbcmd.getI()+System.lineSeparator());
						
					}else if (c instanceof BringToFrontCommand) {
						writer.write("BringToFrontCommand"+System.lineSeparator());
						BringToFrontCommand btfcmd = (BringToFrontCommand) c;
						writer.write("index="+btfcmd.getI()+System.lineSeparator());
					}else if (c instanceof ToBackCommand) {
						writer.write("ToBackCommand"+System.lineSeparator());
						ToBackCommand tbcmd = (ToBackCommand) c;
						writer.write("index="+tbcmd.getI()+System.lineSeparator());
					}else if (c instanceof ToFrontCommand) {
						writer.write("ToFrontCommand"+System.lineSeparator());
						ToFrontCommand tfcmd = (ToFrontCommand) c;
						writer.write("index="+tfcmd.getI()+System.lineSeparator());
					}else if (c instanceof DeleteCommand) {
						writer.write("DeleteCommand"+System.lineSeparator());
						DeleteCommand dcmd = (DeleteCommand) c;
						for(Entry<Shape, Integer> e:dcmd.getDeletedShapes().entrySet()) {
							writer.write("Shape"+System.lineSeparator());
							writer.write("Id="+e.getKey().getId()+System.lineSeparator());
							writer.write("Key="+e.getValue()+System.lineSeparator());

						}
						writer.write("end"+System.lineSeparator());
						
					}else if (c instanceof ModifyCommand) {
						writer.write("ModifyCommand"+System.lineSeparator());
						printPreviusValues(writer,c);
						writer.write("end"+System.lineSeparator());
					}else if (c instanceof SelectCommand) {
						writer.write("SelectCommand"+System.lineSeparator());
						SelectCommand sc = (SelectCommand) c;
						writer.write("SelectedShape"+System.lineSeparator());
						if(sc.getShape()!=null) {
							writer.write("id="+sc.getShape().getId()+System.lineSeparator());
						}
						for(Shape s :sc.getDeselectedShapes()) {
							writer.write("Shape"+System.lineSeparator());
							writer.write("id="+s.getId()+System.lineSeparator());
						}
						writer.write("end"+System.lineSeparator());
					}else if (c instanceof UndoCommand) {
						writer.write("UndoCommand"+System.lineSeparator());
					}else if (c instanceof RedoCommand) {
						writer.write("RedoCommand"+System.lineSeparator());
					}
				  
				  }
			  writer.close();
		  
		  }catch(IOException i) { i.printStackTrace(); }
		
	}
	private void printShapes(FileWriter writer, Shape s) throws IOException {
		if(s instanceof Circle) {
			writer.write("Circle"+System.lineSeparator());
			Circle cr = new Circle();
			cr = (Circle) s;
			writer.write("id="+cr.getId()+System.lineSeparator());
			writer.write("x="+cr.getCenter().getX()+System.lineSeparator());
			writer.write("y="+cr.getCenter().getY()+System.lineSeparator());
			writer.write("r="+cr.getRadius()+System.lineSeparator());
			writer.write("color="+cr.getColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+cr.getFillColor().getRGB()+System.lineSeparator());
				
		}else if(s instanceof HexagonAdapter) {
			writer.write("Hexagon"+System.lineSeparator());
			HexagonAdapter hex = new HexagonAdapter();
			hex = (HexagonAdapter) s;
			writer.write("id="+hex.getId()+System.lineSeparator());
			writer.write("x="+hex.getX()+System.lineSeparator());
			writer.write("y="+hex.getY()+System.lineSeparator());
			writer.write("r="+hex.getR()+System.lineSeparator());
			writer.write("color="+hex.getColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+hex.getFillColor().getRGB()+System.lineSeparator());
						
		}else if(s instanceof Line) {
			writer.write("Line"+System.lineSeparator());
			Line l = new Line();
			l = (Line) s;
			writer.write("id="+l.getId()+System.lineSeparator());
			writer.write("x="+l.getstartPoint().getX()+System.lineSeparator());
			writer.write("y="+l.getstartPoint().getY()+System.lineSeparator());
			writer.write("x1="+l.getendPoint().getX()+System.lineSeparator());
			writer.write("y1="+l.getendPoint().getY()+System.lineSeparator());
			writer.write("color="+l.getColor().getRGB()+System.lineSeparator());
		
		}else if(s instanceof Point) {
			writer.write("Point"+System.lineSeparator());
			Point p = new Point();
			p = (Point) s;
			writer.write("id="+p.getId()+System.lineSeparator());
			writer.write("x="+p.getX()+System.lineSeparator());
			writer.write("y="+p.getY()+System.lineSeparator());
			writer.write("color="+p.getColor().getRGB()+System.lineSeparator());
				
		}else if(s instanceof RectangleShape) {
			writer.write("Rectangle"+System.lineSeparator());
			RectangleShape r = new RectangleShape();
			r = (RectangleShape) s;
			writer.write("id="+r.getId()+System.lineSeparator());
			writer.write("x="+r.getupperLeft().getX()+System.lineSeparator());
			writer.write("y="+r.getupperLeft().getY()+System.lineSeparator());
			writer.write("width="+r.getWidth()+System.lineSeparator());
			writer.write("heigght="+r.getHeight()+System.lineSeparator());
			writer.write("color="+r.getColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+r.getFillColor().getRGB()+System.lineSeparator());
				
		}else if(s instanceof Ring) {
			writer.write("Ring"+System.lineSeparator());
			Ring r = new Ring();
			r = (Ring) s;
			writer.write("id="+r.getId()+System.lineSeparator());
			writer.write("x="+r.getCenter().getX()+System.lineSeparator());
			writer.write("y="+r.getCenter().getY()+System.lineSeparator());
			writer.write("r="+r.getRadius()+System.lineSeparator());
			writer.write("thickness="+r.getThickness()+System.lineSeparator());
			writer.write("color="+r.getColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+r.getFillColor().getRGB()+System.lineSeparator());
				
		}else if(s instanceof Square) {
			writer.write("Square"+System.lineSeparator());
			Square sq = new Square();
			sq = (Square) s;
			writer.write("id="+sq.getId()+System.lineSeparator());
			writer.write("x="+sq.getupperLeft().getX()+System.lineSeparator());
			writer.write("y="+sq.getupperLeft().getY()+System.lineSeparator());
			writer.write("width="+sq.getWidth()+System.lineSeparator());
			writer.write("color="+sq.getColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+sq.getFillColor().getRGB()+System.lineSeparator());
		}
	} 
	private void printPreviusValues(FileWriter writer, Command c) throws IOException {
		ModifyCommand mcmd = (ModifyCommand) c;
		if(c.getShape() instanceof Circle) {
			writer.write("Circle"+System.lineSeparator());
			writer.write("id="+c.getShape().getId()+System.lineSeparator());
			writer.write("toX="+mcmd.getDialogBoxKrugSelect().getMoveToX()+System.lineSeparator());
			writer.write("toY="+mcmd.getDialogBoxKrugSelect().getMoveToY()+System.lineSeparator());
			writer.write("byX="+mcmd.getDialogBoxKrugSelect().getMoveByX()+System.lineSeparator());
			writer.write("byY="+mcmd.getDialogBoxKrugSelect().getMoveByY()+System.lineSeparator());
			writer.write("r="+mcmd.getDialogBoxKrugSelect().getRadius()+System.lineSeparator());
			writer.write("color="+mcmd.getDialogBoxKrugSelect().getLineColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+mcmd.getDialogBoxKrugSelect().getFillColor().getRGB()+System.lineSeparator());
			writer.write("state="+mcmd.getDialogBoxKrugSelect().getState()+System.lineSeparator());
			writer.write("PreviousValues"+System.lineSeparator());
			writer.write("x="+mcmd.getStartPoint().getX()+System.lineSeparator());
			writer.write("y="+mcmd.getStartPoint().getY()+System.lineSeparator());
			writer.write("r="+mcmd.getDimension1()+System.lineSeparator());
			writer.write("color="+mcmd.getColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+mcmd.getFillColor().getRGB()+System.lineSeparator());	
		}else if(c.getShape() instanceof HexagonAdapter) {
			writer.write("Hexagon"+System.lineSeparator());
			writer.write("id="+c.getShape().getId()+System.lineSeparator());
			writer.write("toX="+mcmd.getDialogBoxKrugSelect().getMoveToX()+System.lineSeparator());
			writer.write("toY="+mcmd.getDialogBoxKrugSelect().getMoveToY()+System.lineSeparator());
			writer.write("byX="+mcmd.getDialogBoxKrugSelect().getMoveByX()+System.lineSeparator());
			writer.write("byY="+mcmd.getDialogBoxKrugSelect().getMoveByY()+System.lineSeparator());
			writer.write("r="+mcmd.getDialogBoxKrugSelect().getRadius()+System.lineSeparator());
			writer.write("color="+mcmd.getDialogBoxKrugSelect().getLineColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+mcmd.getDialogBoxKrugSelect().getFillColor().getRGB()+System.lineSeparator());
			writer.write("state="+mcmd.getDialogBoxKrugSelect().getState()+System.lineSeparator());
			writer.write("PreviousValues"+System.lineSeparator());
			writer.write("x="+mcmd.getStartPoint().getX()+System.lineSeparator());
			writer.write("y="+mcmd.getStartPoint().getY()+System.lineSeparator());
			writer.write("r="+mcmd.getDimension1()+System.lineSeparator());
			writer.write("color="+mcmd.getColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+mcmd.getFillColor().getRGB()+System.lineSeparator());	
		}else if(c.getShape() instanceof Line) {
			writer.write("Line"+System.lineSeparator());
			writer.write("id="+c.getShape().getId()+System.lineSeparator());
			writer.write("toX="+mcmd.getDialogBoxModify().getMoveToX()+System.lineSeparator());
			writer.write("toY="+mcmd.getDialogBoxModify().getMoveToY()+System.lineSeparator());
			writer.write("byX="+mcmd.getDialogBoxModify().getMoveByX()+System.lineSeparator());
			writer.write("byY="+mcmd.getDialogBoxModify().getMoveByY()+System.lineSeparator());
			writer.write("color="+mcmd.getDialogBoxModify().getLineColor().getRGB()+System.lineSeparator());
			writer.write("state="+mcmd.getDialogBoxModify().getState()+System.lineSeparator());
			writer.write("PreviousValues"+System.lineSeparator());
			writer.write("x="+mcmd.getStartPoint().getX()+System.lineSeparator());
			writer.write("y="+mcmd.getStartPoint().getY()+System.lineSeparator());
			writer.write("x1="+mcmd.getEndPoint().getX()+System.lineSeparator());
			writer.write("y1="+mcmd.getEndPoint().getY()+System.lineSeparator());
			writer.write("color="+mcmd.getColor().getRGB()+System.lineSeparator());
		}else if(c.getShape() instanceof Point) {
			writer.write("Point"+System.lineSeparator());
			writer.write("id="+c.getShape().getId()+System.lineSeparator());
			writer.write("toX="+mcmd.getDialogBoxModify().getMoveToX()+System.lineSeparator());
			writer.write("toY="+mcmd.getDialogBoxModify().getMoveToY()+System.lineSeparator());
			writer.write("byX="+mcmd.getDialogBoxModify().getMoveByX()+System.lineSeparator());
			writer.write("byY="+mcmd.getDialogBoxModify().getMoveByY()+System.lineSeparator());
			writer.write("color="+mcmd.getDialogBoxModify().getLineColor().getRGB()+System.lineSeparator());
			writer.write("state="+mcmd.getDialogBoxModify().getState()+System.lineSeparator());
			writer.write("PreviousValues"+System.lineSeparator());
			writer.write("x="+mcmd.getStartPoint().getX()+System.lineSeparator());
			writer.write("y="+mcmd.getStartPoint().getY()+System.lineSeparator());
			writer.write("color="+mcmd.getColor().getRGB()+System.lineSeparator());
		}else if(c.getShape() instanceof RectangleShape) {
			writer.write("Rectangle"+System.lineSeparator());
			writer.write("id="+c.getShape().getId()+System.lineSeparator());
			writer.write("toX="+mcmd.getDialogBoxPravougaonikSelect().getMoveToX()+System.lineSeparator());
			writer.write("toY="+mcmd.getDialogBoxPravougaonikSelect().getMoveToY()+System.lineSeparator());
			writer.write("byX="+mcmd.getDialogBoxPravougaonikSelect().getMoveByX()+System.lineSeparator());
			writer.write("byY="+mcmd.getDialogBoxPravougaonikSelect().getMoveByY()+System.lineSeparator());
			writer.write("w="+mcmd.getDialogBoxPravougaonikSelect().getWidthh()+System.lineSeparator());
			writer.write("h="+mcmd.getDialogBoxPravougaonikSelect().getHeightt()+System.lineSeparator());
			writer.write("color="+mcmd.getDialogBoxPravougaonikSelect().getLineColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+mcmd.getDialogBoxPravougaonikSelect().getFillColor().getRGB()+System.lineSeparator());
			writer.write("state="+mcmd.getDialogBoxPravougaonikSelect().getState()+System.lineSeparator());
			writer.write("PreviousValues"+System.lineSeparator());
			writer.write("x="+mcmd.getStartPoint().getX()+System.lineSeparator());
			writer.write("y="+mcmd.getStartPoint().getY()+System.lineSeparator());
			writer.write("width="+mcmd.getDimension1()+System.lineSeparator());
			writer.write("heigght="+mcmd.getDimension2()+System.lineSeparator());
			writer.write("color="+mcmd.getColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+mcmd.getFillColor().getRGB()+System.lineSeparator());
			
		}else if(c.getShape() instanceof Ring) {
			writer.write("Ring"+System.lineSeparator());
			writer.write("id="+c.getShape().getId()+System.lineSeparator());
			writer.write("toX="+mcmd.getDialogBoxRingSelect().getMoveToX()+System.lineSeparator());
			writer.write("toY="+mcmd.getDialogBoxRingSelect().getMoveToY()+System.lineSeparator());
			writer.write("byX="+mcmd.getDialogBoxRingSelect().getMoveByX()+System.lineSeparator());
			writer.write("byY="+mcmd.getDialogBoxRingSelect().getMoveByY()+System.lineSeparator());
			writer.write("r="+mcmd.getDialogBoxRingSelect().getRadius()+System.lineSeparator());
			writer.write("t="+mcmd.getDialogBoxRingSelect().getThickness()+System.lineSeparator());
			writer.write("color="+mcmd.getDialogBoxRingSelect().getLineColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+mcmd.getDialogBoxRingSelect().getFillColor().getRGB()+System.lineSeparator());
			writer.write("state="+mcmd.getDialogBoxRingSelect().getState()+System.lineSeparator());
			writer.write("PreviousValues"+System.lineSeparator());
			writer.write("x="+mcmd.getStartPoint().getX()+System.lineSeparator());
			writer.write("y="+mcmd.getStartPoint().getY()+System.lineSeparator());
			writer.write("r="+mcmd.getDimension1()+System.lineSeparator());
			writer.write("thickness="+mcmd.getDimension2()+System.lineSeparator());
			writer.write("color="+mcmd.getColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+mcmd.getFillColor().getRGB()+System.lineSeparator());
			
		}else if(c.getShape() instanceof Square) {
			writer.write("Square"+System.lineSeparator());
			writer.write("id="+c.getShape().getId()+System.lineSeparator());
			writer.write("toX="+mcmd.getDialogBoxKvadratSelect().getMoveToX()+System.lineSeparator());
			writer.write("toY="+mcmd.getDialogBoxKvadratSelect().getMoveToY()+System.lineSeparator());
			writer.write("byX="+mcmd.getDialogBoxKvadratSelect().getMoveByX()+System.lineSeparator());
			writer.write("byY="+mcmd.getDialogBoxKvadratSelect().getMoveByY()+System.lineSeparator());
			writer.write("w="+mcmd.getDialogBoxKvadratSelect().getSideLength()+System.lineSeparator());
			writer.write("color="+mcmd.getDialogBoxKvadratSelect().getLineColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+mcmd.getDialogBoxKvadratSelect().getFillColor().getRGB()+System.lineSeparator());
			writer.write("state="+mcmd.getDialogBoxKvadratSelect().getState()+System.lineSeparator());
			writer.write("PreviousValues"+System.lineSeparator());
			writer.write("x="+mcmd.getStartPoint().getX()+System.lineSeparator());
			writer.write("y="+mcmd.getStartPoint().getY()+System.lineSeparator());
			writer.write("width="+mcmd.getDimension1()+System.lineSeparator());
			writer.write("color="+mcmd.getColor().getRGB()+System.lineSeparator());
			writer.write("fillcolor="+mcmd.getFillColor().getRGB()+System.lineSeparator());
			
		}
	}

}
