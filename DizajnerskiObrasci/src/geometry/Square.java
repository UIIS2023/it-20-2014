package geometry;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gui.ToolBar;

/**
 * @author Alek
 *Klasa Kvadrat omogucava kreiranje objekta Kvadrat
 */
public class Square extends SurfaceShape{
	protected Point upperLeft;
	protected int width;
	private static enum Type {SQUARE};

	public Square(){

	}

	public Square (Point upperLeft, int width){
		this.upperLeft = upperLeft;
		this.width = width;
	}

	public Square (Point upperLeft, int width, Color color, Color fillColor, int id){
		setColor(color);
		setFillColor(fillColor);
		this.upperLeft = upperLeft;
		this.width = width;
		setId(id);

	}

	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		new Line(getupperLeft(), new Point(getupperLeft().getX()+width, getupperLeft().getY())).selected(g);
		new Line(getupperLeft(), new Point(getupperLeft().getX(), getupperLeft().getY()+width)).selected(g);
		new Line(new Point(getupperLeft().getX()+width, getupperLeft().getY()), dijagonala().getendPoint()).selected(g);
		new Line(new Point(getupperLeft().getX(), getupperLeft().getY()+width), dijagonala().getendPoint()).selected(g);
	}


	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, width-1, width-1);

	}


	public boolean contains(int x, int y) {
		if (x >= upperLeft.getX() && x <= upperLeft.getX() + width 
				&& y >= upperLeft.getY() && y <= upperLeft.getY() + width)
			return true;
		else
			return false;
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), width, width);
		
		if(isSelected())
			selected(g);
	}

	public boolean equals(Object obj){
		if (obj instanceof Square){
			Square temp = (Square) obj;
			if (this.upperLeft.equals(temp.upperLeft) && this.width == temp.width)
				return true;
			else 
				return false;
		}
		else 
			return false;
	}

	public String toString(){
		// Gore levo = (x,y,), Stranica = stranica
		return "Gore levo = "+upperLeft+", Stranica = "+width;
	}

	public Point centar(){
		return dijagonala().middleOfLine();
	}

	public Line dijagonala(){
		int xDoleDesno = upperLeft.getX()+width;
		int yDoleDesno = upperLeft.getY()+width;
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
		return 4*width;
	}

	public double surfaceArea(){
		return width * width;
	}

	public Point getupperLeft() {
		return upperLeft;
	}
	public void setupperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
}
