package gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame{
	
	TestLabel testLabel = new TestLabel();
	
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 640;
	public static final int FRAME_HEIGHT = 720;
	public static final Color FRAME_BG_COLOR = new Color(197, 218, 250);
	private final ImageIcon FRAME_ICON = new ImageIcon("assets/majoras_mask_wallpaper.jpg");
	
	public Frame() {
		this.setLayout(null);
		this.add(testLabel);
		
		this.setVisible(true);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("to do list app");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setIconImage(FRAME_ICON.getImage());
		this.getContentPane().setBackground(FRAME_BG_COLOR);

	}
}
