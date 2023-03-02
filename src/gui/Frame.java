package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
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
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final int FRAME_WIDTH = 1385;
	public static final int FRAME_HEIGHT = 735;
	
	public static CategoryDao categoryDao = DaoFactory.createCategoryDao();
	
	private JPanel contentPane;
	public static JPanel panel = new JPanel();
	public static JComboBox<String> categoryList =  new JComboBox<String>();;
	

	List<String> categoriesArrayList = new ArrayList<String>();
	private JTextField textField;

	
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

		
		//Check-Box list 1 - new TASKS
		JScrollPane scrollPane = new JScrollPane(); 
		scrollPane.setBounds(383, 133, 976, 358);
		contentPane.add(scrollPane);
		
		DefaultListModel<CheckListTask> listModel = new DefaultListModel<>();

		JList<CheckListTask> list = new JList<CheckListTask>(listModel);
		scrollPane.setViewportView(list);
		list.setCellRenderer(new CheckListRenderer());
		
		//----------
		
		//Check-Box list 2 - completed tasks
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(383, 513, 976, 172);
		contentPane.add(scrollPane_1);
		DefaultListModel<CheckListTask> listModel1 = new DefaultListModel<>();
		
		JList<CheckListTask> list_1 = new JList<CheckListTask>(listModel1);
		scrollPane_1.setViewportView(list_1);
		list_1.setCellRenderer(new CheckListRenderer());
		
		JLabel lblNewLabel = new JLabel("Done");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(383, 498, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTodo = new JLabel("To-Do");
		lblTodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTodo.setBounds(383, 96, 46, 14);
		contentPane.add(lblTodo);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.setBounds(1270, 491, 89, 20);
		btnNewButton.addActionListener(btn -> { listModel1.removeAllElements();});
		contentPane.add(btnNewButton);
		//----
		
		//Add CheckListTask
		textField = new JTextField();
		textField.setBounds(383, 112, 976, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(txtf -> {
			if(!textField.getText().equalsIgnoreCase("")){
			listModel.addElement(new CheckListTask(textField.getText()));
			textField.setText("");
			}
		});
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBounds(1270, 89, 89, 20);
		btnNewButton_1.addActionListener(btn1 -> {
			if(!textField.getText().equalsIgnoreCase("")){
			listModel.addElement(new CheckListTask(textField.getText()));
			textField.setText("");
			}
		});
		contentPane.add(btnNewButton_1);
		//----
	
		//----
		
		//Check-Box Event listener
		list.addListSelectionListener(e ->{
				if (!e.getValueIsAdjusting()) {
					int index = list.getSelectedIndex();
					if (index >= 0) {
						CheckListTask item = listModel.getElementAt(index);
						item.setSelected(!item.getStatus());
						listModel.removeElement(item);
						listModel1.addElement(item);
					}
				}
			});
		list_1.addListSelectionListener(e2 ->{
				if (!e2.getValueIsAdjusting()) {
					int index = list_1.getSelectedIndex();
					if (index >= 0) {
						CheckListTask item = listModel1.getElementAt(index);
						item.setSelected(!item.getStatus());
						listModel1.removeElement(item);
						listModel.addElement(item);
					}
				}
			});
    }
		//------
		
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
