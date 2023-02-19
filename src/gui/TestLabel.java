package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class TestLabel extends JLabel{
	
	private static final long serialVersionUID = 1L;
	private Border border = BorderFactory.createLineBorder(Color.RED, 3);
	
	public TestLabel() {
		this.setText("foo");
		this.setForeground(Color.gray);
		this.setFont(new Font("Monospaced", Font.PLAIN, 20));
		this.setVerticalAlignment(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBounds(0, 0, 100, 100);
		this.setBorder(border);
	}
}
