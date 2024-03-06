package geometry;
import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape {
	
	Hexagon hexagon;
	
	
	public HexagonAdapter()	{}
	
	public HexagonAdapter(boolean selected, Color lineColor, Color fillColor, Point center, int radius, int id) {
		hexagon = new Hexagon(center.getX(), center.getY(), radius);
		hexagon.setAreaColor(fillColor);
		hexagon.setBorderColor(lineColor);
		hexagon.setSelected(selected);
		setId(id);
	}
	
	public boolean equals(HexagonAdapter hex) {
		return hexagon.equals(hex);
	}
	

	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
		
	}

	@Override
	public void moveBy(int x, int y) {
		hexagon.setX(hexagon.getX()+x);
		hexagon.setY(hexagon.getY()+y);
		
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double circumference() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double surfaceArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}
		
	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}
	
	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	@Override
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
	}
	
	@Override
	public Color getFillColor() {
		return hexagon.getAreaColor();
	}
	
	@Override
	public void setFillColor(Color color) {
		hexagon.setAreaColor(color);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	public void setX(int x) {
		hexagon.setX(x);
	}
	public int getY() {
		return hexagon.getY();
	}
	public void setY(int y) {
		hexagon.setY(y);
	}
	public int getR() {
		return hexagon.getR();
	}
	public void setR(int r) {
		hexagon.setR(r);
	}
	
	

}
