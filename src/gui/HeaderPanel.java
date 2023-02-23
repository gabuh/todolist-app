package gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class HeaderPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public HeaderPanel() {
		this.setBackground(Color.blue);
		this.setLayout(new FlowLayout(FlowLayout.TRAILING));
	}

}
