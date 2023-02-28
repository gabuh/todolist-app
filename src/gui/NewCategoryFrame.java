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

public class NewCategoryFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public NewCategoryFrame() {
		setUndecorated(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{81, 89, 0, 0};
		gridBagLayout.rowHeights = new int[]{362, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton cancelNewCategoryButton = new JButton("Cancel");
		cancelNewCategoryButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_cancelNewCategoryButton = new GridBagConstraints();
		gbc_cancelNewCategoryButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelNewCategoryButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_cancelNewCategoryButton.gridx = 1;
		gbc_cancelNewCategoryButton.gridy = 1;
		cancelNewCategoryButton.addActionListener(e->dispose());
		cancelNewCategoryButton.setBackground(Utils.sColor);
		cancelNewCategoryButton.setFocusable(false);
		getContentPane().add(cancelNewCategoryButton, gbc_cancelNewCategoryButton);
		
		
		setBounds(100+(Frame.FRAME_WIDTH/3), 150, 600, 450);
		setVisible(true);
	

	}
}
