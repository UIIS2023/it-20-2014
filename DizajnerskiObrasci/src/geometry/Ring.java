package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Ring extends SurfaceShape {
	private Point center;
	private int radius;
	private int thickness;
	
	public Ring() {
		
	}
	

	public Ring (Point center, int radius, int thickness, Color color, Color fillColor, int id) {
		this.center=center;
		this.radius=radius;
		this.thickness=thickness;
		setColor(color);
		setFillColor(fillColor);
		setId(id);
		
	}
	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int x, int y) {
		center.moveBy(x, y);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fill(Graphics g) {

		
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
		Point temp = new Point(x,y);
		if (center.distance(temp)<radius && center.distance(temp)>radius-thickness)
			return true;
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
	
	public boolean equals(Ring r) {
		if(this.center.equals(r.getCenter())&& this.radius == r.getRadius() && this.thickness == r.getThickness())
			return true;
		else
			return false;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D)g;
        Ellipse2D outer = new Ellipse2D.Double(
                center.getX() - radius, 
                center.getY() - radius,
                radius + radius, 
                radius + radius);
            Ellipse2D inner = new Ellipse2D.Double(
                center.getX() - radius + thickness, 
                center.getY() - radius + thickness,
                radius + radius - thickness - thickness, 
                radius + radius - thickness - thickness);
            Area area = new Area(outer);
            area.subtract(new Area(inner));

		g2d.setColor(getFillColor());
		g2d.fill(area);
		g2d.setColor(getColor());
		g2d.draw(area);
		
		if(isSelected())
			selected(g);
		
	}
	
    public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

}
