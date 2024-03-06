package gui;

import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class ToolBarItem extends JToggleButton {
	private ToolBar.Tools tool;
	
	public ToolBarItem(ToolBar.Tools tool) {
		// TODO Auto-generated constructor stub
		this.tool = tool;
	}
	
	public ToolBarItem(ToolBar.Tools tool, String text) {
		// TODO Auto-generated constructor stub
		super(text);
		this.tool = tool;
		
	}

	public ToolBar.Tools getTool() {
		return tool;
	}

	public void setTool(ToolBar.Tools tool) {
		this.tool = tool;
	}

}
