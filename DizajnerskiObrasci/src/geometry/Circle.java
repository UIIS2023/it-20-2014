package geometry;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Alek
 *Klasa Krug omogucava iscrtavanje objekta Krug
 */
public class Circle extends SurfaceShape{
	private Point center;
	private int radius;
	private static enum Type {CIRCLE};
	private static Type type = Type.CIRCLE; 


	public Circle(){

	}

	public Circle(Point center, int radius){
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, Color color, Color fillColor, int id){
		setColor(color);
		setFillColor(fillColor);
		this.center = center;
		this.radius = radius;
		setId(id);

	}


	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillOval(center.getX()-radius+1, center.getY()-radius+1, 2*radius-2, 2*radius-2);

	}


	public boolean contains(int x, int y) {
		Point newDot = new Point(x, y);
		if(center.distance(newDot)<=radius)
			return true;
		else 
			return false;
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX()-radius, center.getY()-radius, 2*radius, 2*radius);
		
		
		if(isSelected())
			selected(g);
			
	}

	public boolean equals (Object obj){
		if (obj instanceof Circle){
			Circle temp = (Circle) obj;
			if(this.center.equals(temp.center) && this.radius == temp.radius)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		new Point(center.getX()+radius, center.getY()).selected(g);
		new Point(center.getX(), center.getY()+radius).selected(g);
		new Point(center.getX()-radius, center.getY()).selected(g);
		new Point(center.getX(), center.getY()-radius).selected(g);
		
	}
	

	public String toString(){
		// Centar = (x,y,), Radius = radius
		return "Centar = "+center+", Radius = "+radius;
	}

	public void moveTo(int x, int y){
		center.moveTo(x, y);
	}

	public void moveBy(int x, int y){
		center.moveBy(x, y);
	}

	public double circumference(){
		return 2 * radius * Math.PI;
	}

	public double surfaceArea(){
		return radius * radius * Math.PI;
	}

	public Point getCenter() {
		return center;
	}
	public void setCenter(Point centar) {
		this.center = centar;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}

	public static Type getType() {
		return type;
	}

	public static void setType(Type type) {
		Circle.type = type;
	}
}
