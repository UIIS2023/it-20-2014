package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * @author Alek
 *
 */
public abstract class Shape implements Serializable{
	private int id;
	private Color color;
	private boolean selected;
	public abstract boolean contains(int x, int y);
	public abstract void moveTo(int x, int y);
	public abstract void moveBy(int x, int y);
	
	public Shape(){
		
	}
	
	public Shape(Color color){
		this.color = color;
	}
	
	public abstract void draw(Graphics g);
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color boja) {
		this.color = boja;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
