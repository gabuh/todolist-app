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
	
	
	static List<String> categoriesArrayList = new ArrayList<String>();
	private JTextField textField;

	JLabel categoryName = new JLabel("Select a category to add tasks to");
	
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
		setBounds(100, 100, Frame.FRAME_WIDTH, Frame.FRAME_HEIGHT);
		setResizable(false);
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
		addCategoryButton.addActionListener(e -> new NewCategoryFrame());
		addCategoryButton.setBounds(10, 618, 353, 23);
		panel.add(addCategoryButton);

		
		//Check-Box list 1 - new TASKS
		JScrollPane todoScrollPane = new JScrollPane(); 
		todoScrollPane.setBounds(383, 133, 976, 358);
		contentPane.add(todoScrollPane);
		
		DefaultListModel<CheckListTask> todoListModel = new DefaultListModel<>();

		JList<CheckListTask> todoList = new JList<CheckListTask>(todoListModel);
		todoScrollPane.setViewportView(todoList);
		todoList.setCellRenderer(new CheckListRenderer());
		
		//----------
		
		//Check-Box list 2 - completed tasks
		JScrollPane doneScrollPane = new JScrollPane();
		doneScrollPane.setBounds(383, 513, 976, 172);
		contentPane.add(doneScrollPane);
		DefaultListModel<CheckListTask> doneListModel = new DefaultListModel<>();
		
		JList<CheckListTask> doneList = new JList<CheckListTask>(doneListModel);
		doneScrollPane.setViewportView(doneList);
		doneList.setCellRenderer(new CheckListRenderer());
		
		JLabel labelDone = new JLabel("Done");
		labelDone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelDone.setBounds(383, 498, 46, 14);
		contentPane.add(labelDone);
		
		JLabel labelTodo = new JLabel("To-Do");
		labelTodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelTodo.setBounds(383, 96, 46, 14);
		contentPane.add(labelTodo);
		
		JButton clearAllTasksButton = new JButton("Clear");
		clearAllTasksButton.setBounds(1270, 491, 89, 20);
		clearAllTasksButton.setFocusable(false);
		clearAllTasksButton.setBackground(Utils.sColor);
		clearAllTasksButton.addActionListener(btn -> { doneListModel.removeAllElements();});
		contentPane.add(clearAllTasksButton);
		//----
		
		//Add CheckListTask
		textField = new JTextField();
		textField.setBounds(383, 112, 976, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(e -> {
			if(!textField.getText().equalsIgnoreCase("")){
			todoListModel.addElement(new CheckListTask(textField.getText()));
			textField.setText("");
			}
		});
		
		JButton addNewTaskButton = new JButton("Add");
		addNewTaskButton.setBounds(1270, 89, 89, 20);
		addNewTaskButton.setFocusable(false);
		addNewTaskButton.setBackground(Utils.sColor);
		addNewTaskButton.addActionListener(e -> {
			if(!textField.getText().equalsIgnoreCase("")){
			todoListModel.addElement(new CheckListTask(textField.getText()));
			textField.setText("");
			}
		});
		contentPane.add(addNewTaskButton);
		//----
	
		//----
		
		//Check-Box Event listener
		todoList.addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					int index = todoList.getSelectedIndex();
					if (index >= 0) {
						CheckListTask item = todoListModel.getElementAt(index);
						item.setSelected(!item.getStatus());
						todoListModel.removeElement(item);
						doneListModel.addElement(item);
					}
				}
			});
		doneList.addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					int index = doneList.getSelectedIndex();
					if (index >= 0) {
						CheckListTask item = doneListModel.getElementAt(index);
						item.setSelected(!item.getStatus());
						doneListModel.removeElement(item);
						todoListModel.addElement(item);
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
