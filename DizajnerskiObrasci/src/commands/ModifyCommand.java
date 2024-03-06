package commands;

import java.awt.Color;
import java.io.Serializable;

import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.RectangleShape;
import geometry.Ring;
import geometry.Shape;
import geometry.Square;
import gui.DialogBoxCircleModify;
import gui.DialogBoxSquareModify;
import gui.DialogBoxModify;
import gui.DialogBoxRectangleModify;
import gui.DialogBoxRingModify;
import model.Model;


public class ModifyCommand implements Command, Serializable {

	private Shape shape;
	private Point startPoint;
	private Point endPoint;
	private Color color;
	private Color fillColor;
	private int dimension1;
	private int dimension2;
	DialogBoxCircleModify dialogBoxKrugSelect;
	DialogBoxRingModify dialogBoxRingSelect;
	DialogBoxRectangleModify dialogBoxPravougaonikSelect;
	DialogBoxSquareModify dialogBoxKvadratSelect;
	DialogBoxModify dialogBoxModify;
	
	public ModifyCommand() {
		
	}
	
	public ModifyCommand(Shape shape) {
		this.shape = shape;
		if(shape instanceof Circle) {
			Circle cr =  new Circle();
			cr = (Circle) shape;
			startPoint = new Point(cr.getCenter().getX(),cr.getCenter().getY());
			dimension1 = cr.getRadius();
			color = cr.getColor();
			fillColor = cr.getFillColor();
			dialogBoxKrugSelect = new DialogBoxCircleModify();
			dialogBoxKrugSelect.getTxtRadius().setText(Integer.toString(cr.getRadius()));
			dialogBoxKrugSelect.setX(cr.getCenter().getX());
			dialogBoxKrugSelect.setY(cr.getCenter().getY());
			dialogBoxKrugSelect.setLineColor(cr.getColor());
			dialogBoxKrugSelect.setFillColor(cr.getFillColor());	
			dialogBoxKrugSelect.setVisible(true);
			
		}else if(shape instanceof HexagonAdapter) {
			dialogBoxKrugSelect = new DialogBoxCircleModify();
			HexagonAdapter hex = (HexagonAdapter) shape;
			startPoint = new Point(hex.getX(),hex.getY());
			dimension1 = hex.getR();
			color = hex.getColor();
			fillColor = hex.getFillColor();
			dialogBoxKrugSelect.getTxtRadius().setText(Integer.toString(hex.getR()));
			dialogBoxKrugSelect.setX(hex.getX());
			dialogBoxKrugSelect.setY(hex.getY());
			dialogBoxKrugSelect.setLineColor(hex.getColor());
			dialogBoxKrugSelect.setFillColor(hex.getFillColor());
			dialogBoxKrugSelect.setVisible(true);
		}else if(shape instanceof Line) {
			dialogBoxModify = new DialogBoxModify();
			Line l = (Line) shape;
			startPoint = new Point(l.getstartPoint().getX(),l.getstartPoint().getY());
			endPoint = new Point(l.getendPoint().getX(),l.getendPoint().getY());
			color = l.getColor();
			dialogBoxModify.setX(l.getstartPoint().getX());
			dialogBoxModify.setY(l.getstartPoint().getY());
			dialogBoxModify.setLineColor(l.getColor());
			dialogBoxModify.setVisible(true);
		}else if(shape instanceof Point) {
			dialogBoxModify = new DialogBoxModify();
			Point p = (Point) shape;
			startPoint = new Point(p.getX(),p.getY());
			color = p.getColor();
			dialogBoxModify.setLineColor(p.getColor());
			dialogBoxModify.setX(p.getX());
			dialogBoxModify.setY(p.getY());
			dialogBoxModify.setVisible(true);
		}else if(shape instanceof RectangleShape) {
			dialogBoxPravougaonikSelect = new DialogBoxRectangleModify();
			RectangleShape rec = (RectangleShape) shape;
			startPoint = new Point(rec.getupperLeft().getX(),rec.getupperLeft().getY());
			dimension1 = rec.getWidth();
			dimension2 = rec.getHeight();
			color = rec.getColor();
			fillColor = rec.getFillColor();
			dialogBoxPravougaonikSelect.getTxtWidth().setText(Integer.toString(rec.getWidth()));
			dialogBoxPravougaonikSelect.getTxtHeight().setText(Integer.toString(rec.getHeight()));
			dialogBoxPravougaonikSelect.setX(rec.getupperLeft().getX());
			dialogBoxPravougaonikSelect.setY(rec.getupperLeft().getY());
			dialogBoxPravougaonikSelect.setLineColor(rec.getColor());
			dialogBoxPravougaonikSelect.setFillColor(rec.getFillColor());
			dialogBoxPravougaonikSelect.setVisible(true);
		}else if(shape instanceof Ring) {
			dialogBoxRingSelect = new DialogBoxRingModify();
			Ring r = (Ring) shape;
			startPoint = new Point(r.getCenter().getX(),r.getCenter().getY());
			dimension1 = r.getRadius();
			dimension2 = r.getThickness();
			color = r.getColor();
			fillColor = r.getFillColor();
			dialogBoxRingSelect.getTxtRadius().setText(Integer.toString(r.getRadius()));
			dialogBoxRingSelect.getTxtThickness().setText(Integer.toString(r.getThickness()));
			dialogBoxRingSelect.setX(r.getCenter().getX());
			dialogBoxRingSelect.setY(r.getCenter().getY());
			dialogBoxRingSelect.setLineColor(r.getColor());
			dialogBoxRingSelect.setFillColor(r.getFillColor());
			dialogBoxRingSelect.setVisible(true);	
		}else if(shape instanceof Square) {
			dialogBoxKvadratSelect = new DialogBoxSquareModify();
			Square sq = (Square) shape;
			startPoint = new Point(sq.getupperLeft().getX(),sq.getupperLeft().getY());
			dimension1 = sq.getWidth();
			color = sq.getColor();
			fillColor = sq.getFillColor();
			dialogBoxKvadratSelect.getTxtSideLength().setText(Integer.toString(sq.getWidth()));
			dialogBoxKvadratSelect.setX(sq.getupperLeft().getX());
			dialogBoxKvadratSelect.setY(sq.getupperLeft().getY());
			dialogBoxKvadratSelect.setLineColor(sq.getColor());
			dialogBoxKvadratSelect.setFillColor(sq.getFillColor());
			dialogBoxKvadratSelect.setVisible(true);
		}
	}
	@Override
	public void execute() {
		if(shape instanceof Circle){
			Circle k = (Circle) shape;	
			k.setColor(dialogBoxKrugSelect.getLineColor());
			k.setFillColor(dialogBoxKrugSelect.getFillColor());
			try {
				Integer.parseInt(dialogBoxKrugSelect.getRadius());
				k.setRadius(Integer.parseInt(dialogBoxKrugSelect.getRadius()));
			} catch (NumberFormatException e1) {
			}
			
			switch (dialogBoxKrugSelect.getState()){
			
				case MOVEBY:
					k.moveBy(dialogBoxKrugSelect.getMoveByX(), dialogBoxKrugSelect.getMoveByY());
					
				break;
				
				case MOVETO:
					k.moveTo(dialogBoxKrugSelect.getMoveToX(), dialogBoxKrugSelect.getMoveToY());
				break;
			}
			
		}else if(shape instanceof HexagonAdapter){
			HexagonAdapter hex = (HexagonAdapter) shape;		
			hex.setColor(dialogBoxKrugSelect.getLineColor());
			hex.setFillColor(dialogBoxKrugSelect.getFillColor());
			try {
				Integer.parseInt(dialogBoxKrugSelect.getRadius());
				hex.setR(Integer.parseInt(dialogBoxKrugSelect.getRadius()));
			} catch (NumberFormatException e1) {
			}
			
			switch (dialogBoxKrugSelect.getState()){
			
				case MOVEBY:
					hex.moveBy(dialogBoxKrugSelect.getMoveByX(), dialogBoxKrugSelect.getMoveByY());
					
				break;
				
				case MOVETO:
					hex.moveTo(dialogBoxKrugSelect.getMoveToX(), dialogBoxKrugSelect.getMoveToY());
				break;
			}
			
		}else if(shape instanceof Ring){
			Ring r = (Ring) shape;	
			r.setColor(dialogBoxRingSelect.getLineColor());
			r.setFillColor(dialogBoxRingSelect.getFillColor());
			try {
				Integer.parseInt(dialogBoxRingSelect.getRadius());
				Integer.parseInt(dialogBoxRingSelect.getThickness());
				r.setRadius(Integer.parseInt(dialogBoxRingSelect.getRadius()));
				r.setThickness(Integer.parseInt(dialogBoxRingSelect.getThickness()));
			} catch (NumberFormatException e1) {
			}
			
			switch (dialogBoxRingSelect.getState()){
			
				case MOVEBY:
					r.moveBy(dialogBoxRingSelect.getMoveByX(), dialogBoxRingSelect.getMoveByY());
					
				break;
				
				case MOVETO:
					r.moveTo(dialogBoxRingSelect.getMoveToX(), dialogBoxRingSelect.getMoveToY());
				break;
			}
			
		}else if(shape instanceof RectangleShape){
			RectangleShape rec = (RectangleShape) shape;
			rec.setColor(dialogBoxPravougaonikSelect.getLineColor());
			rec.setFillColor(dialogBoxPravougaonikSelect.getFillColor());
			try {
				Integer.parseInt(dialogBoxPravougaonikSelect.getWidthh());
				rec.setWidth(Integer.parseInt(dialogBoxPravougaonikSelect.getWidthh()));
			} catch (NumberFormatException e1) {
			}
			try {
				Integer.parseInt(dialogBoxPravougaonikSelect.getHeightt());
				rec.setHeight(Integer.parseInt(dialogBoxPravougaonikSelect.getHeightt()));
			} catch (NumberFormatException e1) {
			}
			switch (dialogBoxPravougaonikSelect.getState()){
			
			case MOVEBY:
				rec.moveBy(dialogBoxPravougaonikSelect.getMoveByX(), dialogBoxPravougaonikSelect.getMoveByY());
				
			break;
			
			case MOVETO:
				rec.moveTo(dialogBoxPravougaonikSelect.getMoveToX(), dialogBoxPravougaonikSelect.getMoveToY());
			break;
		}
	}else if(shape instanceof Square){
			Square sq = (Square) shape;
			sq.setColor(dialogBoxKvadratSelect.getLineColor());
			sq.setFillColor(dialogBoxKvadratSelect.getFillColor());
			try {
				Integer.parseInt(dialogBoxKvadratSelect.getSideLength());
				sq.setWidth(Integer.parseInt(dialogBoxKvadratSelect.getSideLength()));
			} catch (NumberFormatException e1) {
			}
			switch (dialogBoxKvadratSelect.getState()){
			
			case MOVEBY:
				sq.moveBy(dialogBoxKvadratSelect.getMoveByX(), dialogBoxKvadratSelect.getMoveByY());
				
			break;
			
			case MOVETO:
				sq.moveTo(dialogBoxKvadratSelect.getMoveToX(), dialogBoxKvadratSelect.getMoveToY());
			break;
		}
	}else if(shape instanceof Point){
		Point p = (Point) shape;		
		p.setColor(dialogBoxModify.getLineColor());
		
		switch (dialogBoxModify.getState()){
		
			case MOVEBY:
				p.moveBy(dialogBoxModify.getMoveByX(), dialogBoxModify.getMoveByY());
				
			break;
			
			case MOVETO:
				p.moveTo(dialogBoxModify.getMoveToX(), dialogBoxModify.getMoveToY());
			break;
		}
		
	}else if(shape instanceof Line){
		Line l = (Line) shape;		
		l.setColor(dialogBoxModify.getLineColor());
		
		switch (dialogBoxModify.getState()){
		
			case MOVEBY:
				l.moveBy(dialogBoxModify.getMoveByX(), dialogBoxModify.getMoveByY());
				
			break;
			
			case MOVETO:
				l.moveTo(dialogBoxModify.getMoveToX(), dialogBoxModify.getMoveToY());
			break;
		}
		
	}
		
	}

	@Override
	public void unexecute() {
		if(shape instanceof Circle){
			Circle k = (Circle) shape;	
			k.setColor(color);
			k.setFillColor(fillColor);
			k.setRadius(dimension1);
			k.getCenter().setX(startPoint.getX());
			k.getCenter().setY(startPoint.getY());
		}else if(shape instanceof HexagonAdapter){
			HexagonAdapter hex = (HexagonAdapter) shape;		
			hex.setColor(color);
			hex.setFillColor(fillColor);
			hex.setR(dimension1);
			hex.moveTo(startPoint.getX(), startPoint.getY());
		}else if(shape instanceof Ring){
			Ring r = (Ring) shape;	
			r.setColor(color);
			r.setFillColor(fillColor);
			r.setRadius(dimension1);
			r.setThickness(dimension2);
			r.getCenter().setX(startPoint.getX());
			r.getCenter().setY(startPoint.getY());
		}else if(shape instanceof RectangleShape){
			RectangleShape pr = (RectangleShape) shape;
			pr.setColor(color);
			pr.setFillColor(fillColor);
			pr.setWidth(dimension1);
			pr.setHeight(dimension2);
			pr.getupperLeft().setX(startPoint.getX());
			pr.getupperLeft().setY(startPoint.getY());
	}else if(shape instanceof Square){
			Square kv = (Square) shape;
			kv.setColor(color);
			kv.setFillColor(fillColor);
			kv.setWidth(dimension1);
			kv.getupperLeft().setX(startPoint.getX());
			kv.getupperLeft().setY(startPoint.getY());
	}else if(shape instanceof Point) {
			Point p = (Point) shape;
			p.setColor(color);
			p.setX(startPoint.getX());
			p.setY(startPoint.getY());
	}else if(shape instanceof Line) {
			Line l = (Line) shape;
			l.setColor(color);
			l.getstartPoint().setX(startPoint.getX());
			l.getstartPoint().setY(startPoint.getY());
			l.getendPoint().setX(endPoint.getX());
			l.getendPoint().setY(endPoint.getY());
	}
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setModel(Model model) {
		// TODO Auto-generated method stub
		
	}
	
	public Shape getShape() {
		return this.shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public int getDimension1() {
		return dimension1;
	}
	public void setDimension1(int dimension1) {
		this.dimension1 = dimension1;
	}
	public int getDimension2() {
		return dimension2;
	}
	public void setDimension2(int dimension2) {
		this.dimension2 = dimension2;
	}
	public DialogBoxCircleModify getDialogBoxKrugSelect() {
		return dialogBoxKrugSelect;
	}
	public void setDialogBoxKrugSelect(DialogBoxCircleModify dialogBoxKrugSelect) {
		this.dialogBoxKrugSelect = dialogBoxKrugSelect;
	}
	public DialogBoxRingModify getDialogBoxRingSelect() {
		return dialogBoxRingSelect;
	}
	public void setDialogBoxRingSelect(DialogBoxRingModify dialogBoxRingSelect) {
		this.dialogBoxRingSelect = dialogBoxRingSelect;
	}
	public DialogBoxRectangleModify getDialogBoxPravougaonikSelect() {
		return dialogBoxPravougaonikSelect;
	}
	public void setDialogBoxPravougaonikSelect(DialogBoxRectangleModify dialogBoxPravougaonikSelect) {
		this.dialogBoxPravougaonikSelect = dialogBoxPravougaonikSelect;
	}
	public DialogBoxSquareModify getDialogBoxKvadratSelect() {
		return dialogBoxKvadratSelect;
	}
	public void setDialogBoxKvadratSelect(DialogBoxSquareModify dialogBoxKvadratSelect) {
		this.dialogBoxKvadratSelect = dialogBoxKvadratSelect;
	}
	public DialogBoxModify getDialogBoxModify() {
		return dialogBoxModify;
	}
	public void setDialogBoxModify(DialogBoxModify dialogBoxModify) {
		this.dialogBoxModify = dialogBoxModify;
	}
	

}
