package observer;

import java.util.ArrayList;

import geometry.Shape;

public interface Observer {
	void update(ArrayList<Shape> selectedShapes, ArrayList<Shape> shapes);
}
