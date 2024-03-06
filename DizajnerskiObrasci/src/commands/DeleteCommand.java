package commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import geometry.Shape;
import model.Model;

public class DeleteCommand implements Command, Serializable {

	private Model model;
	private LinkedHashMap<Shape,Integer> deletedShapes = new LinkedHashMap<>();
	
	
	public DeleteCommand(Model model) {
		this.model = model;
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		HashMap<Shape,Integer> temp = new HashMap<>();
		ArrayList<Shape> selectedShapes = new ArrayList<Shape>(model.getSelectedShapes());
		Iterator<Shape> its = selectedShapes.iterator();
		while(its.hasNext()) {
			Shape s = its.next();
			indexes.add(model.getShapes().indexOf(s));
			temp.put(s, model.getShapes().indexOf(s));
		}
		indexes.sort(null);
		for(int i : indexes) {
			for(Entry<Shape, Integer> entry : temp.entrySet()) {
				if(entry.getValue()==i) {
					deletedShapes.put(entry.getKey(), i);
				}
				
			}
		}


	}
	
	public DeleteCommand() {
		
	}
	
	@Override
	public void execute() {
		for(Entry<Shape, Integer> entry : deletedShapes.entrySet()) {
			model.getShapes().remove(entry.getKey());
		}
		model.getSelectedShapes().clear();
	//	model.getShapes().removeAll(selectedShapes);    

	}

	@Override
	public void unexecute() {
		
		for(Entry<Shape, Integer> entry : deletedShapes.entrySet()) {
			model.getShapes().add(entry.getValue(), entry.getKey());
			entry.getKey().setSelected(true);
			model.getSelectedShapes().add(entry.getKey());
		}
		


		
	}
	@Override
	public void setModel(Model model) {
		this.model = model;
	}
	
	public Shape getShape() {
		return null;
	}

	public LinkedHashMap<Shape, Integer> getDeletedShapes() {
		return deletedShapes;
	}

	public void setDeletedShapes(LinkedHashMap<Shape, Integer> deletedShapes) {
		this.deletedShapes = deletedShapes;
	}

}
