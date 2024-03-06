package geometry;

import java.awt.Color;
import java.awt.Graphics;

import gui.ToolBar;

/**
 * @author Alek
 *Klasa Pravougaonik omugacava kreiranje objekta Pravougaonik
 */
public class RectangleShape extends Square{
	private int height;
	private static enum Type {RECTANGLE};

	public RectangleShape(){

	}

	public RectangleShape(Point upperLeft, int width, int height){
		super(upperLeft,width);
		this.height = height;
	}
	
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		new Line(getupperLeft(), new Point(getupperLeft().getX()+width, getupperLeft().getY())).selected(g);
		new Line(getupperLeft(), new Point(getupperLeft().getX(), getupperLeft().getY()+height)).selected(g);
		new Line(new Point(getupperLeft().getX()+width, getupperLeft().getY()), dijagonala().getendPoint()).selected(g);
		new Line(new Point(getupperLeft().getX(), getupperLeft().getY()+height), dijagonala().getendPoint()).selected(g);
	}
	
	public boolean contains(int x, int y) {
		if (x >= upperLeft.getX() && x <= upperLeft.getX() + width 
				&& y >= upperLeft.getY() && y <= upperLeft.getY() + height)
			return true;
		else
			return false;
	}
	
	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, width-1, height-1);
		
	}
	
	public RectangleShape(Point upperLeft, int width, int height, Color color, Color fillColor, int id){
		this(upperLeft,width,height);
		setFillColor(fillColor);
		setColor(color);
		setId(id);
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), width, height);
	
		if(isSelected())
			selected(g);
	}
	
	public boolean equals (Object obj){
		if (obj instanceof RectangleShape) {
			RectangleShape start = (RectangleShape) obj;
			if (this.getupperLeft().equals(start.getupperLeft()) && 
					this.height == start.height && 
					this.getWidth() == start.getWidth())
				return true;
				else
					return false;
		}else
			return false;
	}
	
	public String toString(){
		// Gore levo = (x,y,), Sirina = sirina, Visina = visina
		return "Gore levo = "+this.getupperLeft()+", Sirina = "+width+", Visina = "+height;
	}
	
	public Point centar(){
		return dijagonala().middleOfLine();
	}
	
	public Line dijagonala(){
		int xDoleDesno = upperLeft.getX()+width;
		int yDoleDesno = upperLeft.getY()+height;
		Point doleDesno = new Point(xDoleDesno, yDoleDesno);
		Line dijagonala = new Line(upperLeft, doleDesno);
		return dijagonala;
		
	}

	public void moveTo(int x, int y){
		upperLeft.moveTo(x, y);
	}

	public void moveBy(int x, int y){
		upperLeft.moveBy(x, y);
	}

	public double circumference(){
		return 2 * (width + height);			
	}

	public double surfaceArea(){
		return width * height;
	}

	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	



}
