package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class NewCategoryFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField newCategoryName;

	public NewCategoryFrame() {
		setUndecorated(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton cancelNewCategoryButton = new JButton("Cancel");
		cancelNewCategoryButton.setBounds(81, 362, 93, 25);
		cancelNewCategoryButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cancelNewCategoryButton.setFocusable(false);
		cancelNewCategoryButton.setBackground(Utils.sColor);
		cancelNewCategoryButton.addActionListener(e->dispose());
		getContentPane().setLayout(null);
		
		newCategoryName = new JTextField();
		newCategoryName.setBounds(81, 98, 408, 37);
		getContentPane().add(newCategoryName);
		newCategoryName.setColumns(10);
		cancelNewCategoryButton.setBackground(Utils.sColor);
		cancelNewCategoryButton.setFocusable(false);
		getContentPane().add(cancelNewCategoryButton);
		
		JButton addNewCategoryButton = new JButton("Add");
		addNewCategoryButton.setBounds(405, 362, 84, 25);
		addNewCategoryButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addNewCategoryButton.setFocusable(false);
		addNewCategoryButton.setBackground(Utils.sColor);
		addNewCategoryButton.addActionListener(e -> addNewCategory());
		getContentPane().add(addNewCategoryButton);
		
		JLabel newCategoryLabel = new JLabel("Name");
		newCategoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newCategoryLabel.setBounds(81, 62, 113, 25);
		getContentPane().add(newCategoryLabel);
		
		
		setBounds(100+(Frame.FRAME_WIDTH/3), 150, 600, 450);
		setVisible(true);
	

	}
	
	private void addNewCategory() {
		Frame.categoriesArrayList.add(Utils.getNewCategoryName(newCategoryName));
		Frame.updateCategoryList();
		this.dispose();
	}
	
}
