package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public MainPanel() {
		
		this.setBackground(Color.blue);
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
	}

}
