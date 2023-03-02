package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final int FRAME_WIDTH = 1385;
	public static final int FRAME_HEIGHT = 735;
	
	private JPanel contentPane;
	public static JPanel panel = new JPanel();
	public static JComboBox<String> categoryList =  new JComboBox<String>();;
	
	public static List<String> categoriesArrayList = new ArrayList<String>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Frame() {
		setTitle("todo list app");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, this.FRAME_WIDTH, this.FRAME_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel categoryName = new JLabel("Default");
		categoryName.setHorizontalAlignment(SwingConstants.CENTER);
		categoryName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		categoryName.setBounds(383, 37, 976, 31);
		contentPane.add(categoryName);
		
		panel.setBackground(Utils.pColor);
		panel.setBounds(0, 0, 373, 696);
		contentPane.add(panel);
		panel.setLayout(null);
		
		updateCategoryList();
		categoriesArrayList.add("aaaaaaaaaaaaaa");
		categoriesArrayList.add("bbbbbbbbbbbbbb");
		categoriesArrayList.add("cccccccccccccc");
		categoriesArrayList.add("dddddddddddddd");
		updateCategoryList();
		
		JLabel categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		categoryLabel.setBounds(10, 48, 353, 22);
		panel.add(categoryLabel);
		
		JButton okCategoryButton = new JButton("Ok");
		okCategoryButton.setBounds(279, 81, 84, 23);
		okCategoryButton.setFocusable(false);	
		okCategoryButton.setBackground(Utils.sColor);
		okCategoryButton.addActionListener(e -> categoryName.setText(Utils.getCategoryName(categoryList)));
		panel.add(okCategoryButton);
		
		JButton addCategoryButton = new JButton("Add category");
		addCategoryButton.setFocusable(false);
		addCategoryButton.setBackground(Utils.sColor);
		addCategoryButton.addActionListener(e -> {NewCategoryFrame a = new NewCategoryFrame();});
		addCategoryButton.setBounds(10, 618, 353, 23);
		panel.add(addCategoryButton);
	}
	
	public static void updateCategoryList() {
		panel.remove(categoryList);
		
		categoryList.setMaximumRowCount(20);
		
		String[] categories = categoriesArrayList.toArray(new String[(categoriesArrayList.size())]);
		
		categoryList.setModel(new DefaultComboBoxModel<String>(categories));
		categoryList.setBounds(10, 81, 259, 22);
		panel.add(categoryList);
	}
}
