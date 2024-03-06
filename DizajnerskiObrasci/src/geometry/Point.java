package geometry;

import java.awt.Color;
import java.awt.Graphics;

 //Klasa tacka omogucava kreiranje objekata Tacka 

public class Point extends Shape{

	private int x;
	private int y;


	public Point(){

	}

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, Color boja, int id){
		super(boja);
		this.x = x;
		this.y = y;
		setId(id);
	}
	
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(x-2, y-2, 4, 4);
	}

	/**
	 * Ova metoda omogucava iscrtavanje tacke
	 * @return*/
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);

		if(isSelected())
			selected(g);
	}

	public boolean equals(Object obj){
		if (obj instanceof Point){
			Point temp = (Point)obj;
			if(this.x == temp.getX() && this.y == temp.getY())
				return true;
			else
				return false;
		}
		else 
			return false;
	}

	public String toString(){
		// (x,y)
		return "("+x+","+y+")";
	}

	public void moveTo(int x, int y){
		this.x = x; 
		this.y = y;
	}

	public void moveBy(int x, int y){
		this.x += x; 
		this.y =  this.y + y;
	}

	public double distance(Point t){
		int dx = this.x - t.x;
		int dy = this.y - t.y;
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean contains(int x, int y) {
		int dx = this.x - x;
		int dy = this.y - y;
		if(Math.abs(dx) <= 4 && Math.abs(dy) <= 4)
			return true;
		else
			return false;
	}


}
