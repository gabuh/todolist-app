package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TagPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public TagPanel() {
		this.setBackground(Color.red);
		this.setPreferredSize(new Dimension(300, Frame.FRAME_HEIGHT));
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
	}
}
