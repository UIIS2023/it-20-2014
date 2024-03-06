package geometry;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Alek
 *Ovaka klasa predstavlja sve oblike koji imaju povrsinu
 */
public abstract class SurfaceShape extends Shape{
	private Color fillColor = Color.white;

	public abstract void fill(Graphics g);
	public abstract double circumference();
	public abstract double surfaceArea();
	
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}


}
