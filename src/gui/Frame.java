package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Frame extends JFrame{
	
	MainPanel mainPanel = new MainPanel();	
	CloseButton closeButton = new CloseButton();
	HeaderPanel headerPanel = new HeaderPanel();
	TagPanel tagPanel = new TagPanel();
	
	private static final long serialVersionUID = 1L;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	public static final int FRAME_WIDTH = (int) tk.getScreenSize().getWidth();
	public static final int FRAME_HEIGHT = (int) tk.getScreenSize().getHeight();
	public static final Color FRAME_BG_COLOR = new Color(197, 218, 250);
	
	public Frame() {
		this.setLayout(new BorderLayout());
		this.add(tagPanel, BorderLayout.WEST);
		this.add(mainPanel, BorderLayout.CENTER);
		
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		headerPanel.add(closeButton);
		closeButton.addActionListener(e -> this.dispose());
		
		mainPanel.setVisible(true);
		
		
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("to do list app");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.getContentPane().setBackground(FRAME_BG_COLOR);
		
	}

	
}
