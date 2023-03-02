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

import model.dao.CategoryDao;
import model.dao.DaoFactory;
import model.entities.Category;

import javax.swing.SwingConstants;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final int FRAME_WIDTH = 1385;
	public static final int FRAME_HEIGHT = 735;
	
	public static CategoryDao categoryDao = DaoFactory.createCategoryDao();
	
	private JPanel contentPane;
	public static JPanel panel = new JPanel();
	public static JComboBox<String> categoryList =  new JComboBox<String>();;
	
	public static List<String> categoriesArrayList = new ArrayList<String>();
	
	JLabel categoryName = new JLabel("Default");
	
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
		
		
		categoryName.setHorizontalAlignment(SwingConstants.CENTER);
		categoryName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		categoryName.setBounds(383, 37, 976, 31);
		contentPane.add(categoryName);
		
		panel.setBackground(Utils.pColor);
		panel.setBounds(0, 0, 373, 696);
		contentPane.add(panel);
		panel.setLayout(null);
		
		updateCategoryList();
		
		
		JLabel categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		categoryLabel.setBounds(10, 48, 353, 22);
		panel.add(categoryLabel);
		
		JButton okCategoryButton = new JButton("Ok");
		okCategoryButton.setBounds(279, 81, 84, 23);
		okCategoryButton.setFocusable(false);	
		okCategoryButton.setBackground(Utils.sColor);
		okCategoryButton.addActionListener(e -> setCategoryNameLabel());
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
		
		/*
		String[] categories = categoriesArrayList.toArray(new String[(categoriesArrayList.size())]);
		*/
		
		List<Category> list = categoryDao.findAll();
		List<String> nameList = new ArrayList<>();
		for(Category c : list) {
			nameList.add(c.getName());
		}
		
		categoryList.setModel(new DefaultComboBoxModel<String>(nameList.toArray(new String[(list.size())])/*categories*/));
		categoryList.setBounds(10, 81, 259, 22);
		panel.add(categoryList);
	}
	
	private void setCategoryNameLabel() {
		if(Utils.isCategoryNameNull(categoryList) == false) 
			categoryName.setText(Utils.getCategoryName(categoryList));
	}
}
