package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CloseButton extends JButton{
	
	private static final long serialVersionUID = 1L;
	private ImageIcon icon = new ImageIcon("assets/close.png");

	public CloseButton() {
		this.setText(null);
		this.setFocusable(false);
		this.setIcon(icon);
	}
}
