package geometry;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Alek
 *Klasa Linija omogucava kreiranje objekta Linija
 */
public class Line extends Shape{
	private Point startPoint;
	private Point endPoint;
	private static enum Type {LINE};

	public Line(){
		
	}
	
	public Line(Point startPoint, Point endPoint){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, Color color, int id){
		super(color);
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		setId(id);

		
		
	}
	
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		startPoint.selected(g);
		endPoint.selected(g);
		middleOfLine().selected(g);
	}
	
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
	
		if(isSelected())
			selected(g);
	}
	
	public boolean equals (Object obj){
		if(obj instanceof Line){
			Line temp = (Line) obj;
			if(this.startPoint.equals(temp.startPoint) &&
					this.endPoint.equals(temp.endPoint))
				return true;
			else
				return false;
		}
		else 
			return false;
	}
	
	public String toString(){
		// (xp,yp)-->(xk,yk)
		return startPoint+"-->"+endPoint;
	}
	
	public Point middleOfLine(){
		return new Point((startPoint.getX()+endPoint.getX())/2,(startPoint.getY()+endPoint.getY())/2);
	}

	public void moveBy(int poX, int poY) {
		startPoint.moveBy(poX, poY);
		endPoint.moveBy(poX, poY);
	}
	
	public double length(){
		return Math.round(startPoint.distance(endPoint));
	}
	
	
	public Point getstartPoint() {
		return startPoint;
	}

	public void setstartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getendPoint() {
		return endPoint;
	}

	public void setendPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public void moveTo(int x, int y) {
		int moveX = endPoint.getX()-(startPoint.getX()-x);
		int moveY = endPoint.getY()-(startPoint.getY()-y);
		endPoint.moveTo(moveX, moveY);
		startPoint.moveTo(x, y);
	}

	@Override
	public boolean contains(int x, int y) {
		Point point = new Point(x, y);
		if(startPoint.distance(endPoint) + 3 > startPoint.distance(point) + endPoint.distance(point))
			return true;
		else
			return false;
		
	}

	
	


}
