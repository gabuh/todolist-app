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
import model.dao.TaskDao;
import model.entities.Category;
import model.entities.Task;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final int FRAME_WIDTH = 1385;
	public static final int FRAME_HEIGHT = 735;
	
	public static CategoryDao categoryDao = DaoFactory.createCategoryDao();
	public static TaskDao taskDao = DaoFactory.createTaskDao();
	
	public static List<Category> categoryListBuff;
	public static List<Task> taskListBuff;
	
	private JPanel contentPane;
	public static JPanel panel = new JPanel();
	public static JComboBox<String> categoryList =  new JComboBox<String>();;
	
	public static Category currCategory = null;
	
	private JTextField textField;
	
	private JButton deleteCategoryButton;
	
	private String defaultCategoryName = "Select a category to add tasks to";

	JLabel categoryName = new JLabel(defaultCategoryName);
	
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
		setResizable(false);
		setTitle("todo list app");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1385, 735);
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
		okCategoryButton.addActionListener(e -> updateCategoryDashboard());
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
		
		DefaultListModel<Task> todoListModel = new DefaultListModel<>();

		JList<Task> todoList = new JList<Task>(todoListModel);
		todoScrollPane.setViewportView(todoList);
		todoList.setCellRenderer(new CheckListRenderer());
		
		//----------
		
		//Check-Box list 2 - completed tasks
		JScrollPane doneScrollPane = new JScrollPane();
		doneScrollPane.setBounds(383, 513, 976, 172);
		contentPane.add(doneScrollPane);
		DefaultListModel<Task> doneListModel = new DefaultListModel<>();
		
		JList<Task> doneList = new JList<Task>(doneListModel);
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
		clearAllTasksButton.addActionListener(btn -> { 
			if(currCategory != null || !doneListModel.isEmpty()){
				doneListModel.removeAllElements();
				taskDao.removeAll(currCategory.getId());
			}
		});
		contentPane.add(clearAllTasksButton);
		//----
		
		//Add CheckListTask
		textField = new JTextField();
		textField.setBounds(383, 112, 976, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(e -> {
			if(!textField.getText().equalsIgnoreCase("") || currCategory != null){
			Task task = new Task(textField.getText());
			task.setIdCategory(currCategory.getId());
			taskDao.add(task);
			todoListModel.addElement(task);
			textField.setText("");
			}
		});
		
		JButton addNewTaskButton = new JButton("Add");
		addNewTaskButton.setBounds(1270, 89, 89, 20);
		addNewTaskButton.setFocusable(false);
		addNewTaskButton.setBackground(Utils.sColor);
		addNewTaskButton.addActionListener(e -> {
			if(!textField.getText().equalsIgnoreCase("")){
			todoListModel.addElement(new Task(textField.getText()));
			textField.setText("");
			}
		});
		contentPane.add(addNewTaskButton);
		
		
		
		//delete category button
		
		deleteCategoryButton = new JButton("Delete category");		
		deleteCategoryButton.setVisible(false);
		deleteCategoryButton.addActionListener(e -> deleteCurrentCategory());
		deleteCategoryButton.setFocusable(false);
		deleteCategoryButton.setBackground(Utils.sColor);
		deleteCategoryButton.setBounds(383, 11, 126, 23);
		contentPane.add(deleteCategoryButton);
		
		
		//----
		
		
		//----

		categoryName.addPropertyChangeListener(f -> {
			if(!todoListModel.isEmpty()) {
				todoListModel.removeAllElements();				
			}
			if(!doneListModel.isEmpty()) {
				doneListModel.removeAllElements();				
			}
			if(currCategory != null) {
				taskListBuff = taskDao.findByCategory(currCategory.getId());
				for(Task c : taskListBuff) {
					if(c.getStatus()) {
						doneListModel.addElement(c);						
					}else {
						todoListModel.addElement(c);						
					}
				}
			}
			doneList.repaint();
			todoList.repaint();
		});
		// Add the label to your Swing application as needed

		
	
		//----
		
		//Check-Box Event listener
		todoList.addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					int index = todoList.getSelectedIndex();
					if (index >= 0) {
						Task item = todoListModel.getElementAt(index);
						item.setSelected(!item.getStatus());
						taskDao.setState(item.getId(),item.getStatus());
						todoListModel.removeElement(item);
						doneListModel.addElement(item);
					}
				}
			});
		doneList.addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					int index = doneList.getSelectedIndex();
					if (index >= 0) {
						Task item = doneListModel.getElementAt(index);
						item.setSelected(!item.getStatus());
						taskDao.setState(item.getId(),item.getStatus());
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
		
		categoryListBuff = categoryDao.findAll();
		List<String> nameList = new ArrayList<>();
		for(Category c : categoryListBuff) {
			nameList.add(c.getName());
		}
		
		categoryList.setModel(new DefaultComboBoxModel<String>(nameList.toArray(new String[(categoryListBuff.size())])));
		categoryList.setBounds(10, 81, 259, 22);
		panel.add(categoryList);
	}
	
	private void updateCategoryDashboard() { 
		
		if(Utils.isCategoryNameNull(categoryList) == false) {
			categoryName.setText(Utils.getCategoryName(categoryList));
			currCategory = categoryDao.findByName(Utils.getCategoryName(categoryList));
		}
		
		if(currCategory == null) {
			deleteCategoryButton.setVisible(false);
		}else {
			deleteCategoryButton.setVisible(true);
		}
	}
	
	private void deleteCurrentCategory() {
		if(currCategory == null) {
			return;
		}
		categoryDao.delete(currCategory.getId());
		currCategory = null;

		updateCategoryDashboard();
		updateCategoryList();
		
		categoryName.setText(defaultCategoryName);
	}


	
	
}
