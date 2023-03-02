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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final int FRAME_WIDTH = 1385;
	public static final int FRAME_HEIGHT = 735;
	
	private JPanel contentPane;
	private JPanel panel = new JPanel();
	JComboBox<String> categoryList =  new JComboBox<String>();;
	
	List<String> categoriesArrayList = new ArrayList<String>();
	
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
		
		//Check-Box list 1 - new TASKS
		JScrollPane scrollPane = new JScrollPane(); 
		scrollPane.setBounds(383, 111, 976, 380);
		contentPane.add(scrollPane);
		
		DefaultListModel<CheckListItem> listModel = new DefaultListModel<>();
		listModel.addElement(new CheckListItem("item 1"));
		listModel.addElement(new CheckListItem("item 2"));
		listModel.addElement(new CheckListItem("item 232"));
		listModel.addElement(new CheckListItem("item 2123"));
		listModel.addElement(new CheckListItem("item2312 2"));
		listModel.addElement(new CheckListItem("item 2132"));
		listModel.addElement(new CheckListItem("item 324232"));
		listModel.addElement(new CheckListItem("item 222"));
		listModel.addElement(new CheckListItem("item 232"));
		listModel.addElement(new CheckListItem("item2253 2"));
		listModel.addElement(new CheckListItem("ite52332m 2"));
		listModel.addElement(new CheckListItem("ite235m 2"));
		listModel.addElement(new CheckListItem("item2 2"));
		listModel.addElement(new CheckListItem("ite235m 352"));
		listModel.addElement(new CheckListItem("ite 2m 22"));
		listModel.addElement(new CheckListItem("item 532"));
		listModel.addElement(new CheckListItem("item2 2"));
		listModel.addElement(new CheckListItem("item  532"));
		listModel.addElement(new CheckListItem("item 2"));
		listModel.addElement(new CheckListItem("it 235 2em 2"));
		listModel.addElement(new CheckListItem("ite2 523 5m 2"));
		listModel.addElement(new CheckListItem("it 235em 2"));
		listModel.addElement(new CheckListItem("i235 tem 2"));
		listModel.addElement(new CheckListItem("it2 35 2em 2"));
		listModel.addElement(new CheckListItem("ite 235m 2"));
		listModel.addElement(new CheckListItem("ite23 5m 2"));
		listModel.addElement(new CheckListItem("it235 em 2"));
		listModel.addElement(new CheckListItem("23 5325item 2"));
		listModel.addElement(new CheckListItem("235 235item 2"));
		listModel.addElement(new CheckListItem("235 235item 2"));
		listModel.addElement(new CheckListItem("235item 2"));
		listModel.addElement(new CheckListItem("235235item 2"));
		listModel.addElement(new CheckListItem("ite235235m 2"));
		listModel.addElement(new CheckListItem("ite235235m 2"));
		listModel.addElement(new CheckListItem("item 3"));
		
		JList<CheckListItem> list = new JList<CheckListItem>(listModel);
		scrollPane.setViewportView(list);
		list.setCellRenderer(new CheckListRenderer());
		
		//----------
		
		//Check-Box list 2 - completed tasks
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(383, 513, 976, 172);
		contentPane.add(scrollPane_1);
		DefaultListModel<CheckListItem> listModel1 = new DefaultListModel<>();
		
		JList<CheckListItem> list_1 = new JList<CheckListItem>(listModel1);
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
		//----
		
		//Check-Box Event listener
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int index = list.getSelectedIndex();
					if (index >= 0) {
						CheckListItem item = listModel.getElementAt(index);
						item.setSelected(!item.isSelected());
						list.repaint();
						listModel.removeElement(item);
						listModel1.addElement(item);
					}
				}
			}
		});
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e2) {
				if (!e2.getValueIsAdjusting()) {
					int index = list_1.getSelectedIndex();
					if (index >= 0) {
						CheckListItem item = listModel1.getElementAt(index);
						item.setSelected(!item.isSelected());
						list_1.repaint();
						listModel1.removeElement(item);
						listModel.addElement(item);
					}
				}
			}
		});
		
	}
	
	
	public void updateCategoryList() {
		panel.remove(categoryList);
		
		categoryList.setMaximumRowCount(20);
		
		String[] categories = categoriesArrayList.toArray(new String[(categoriesArrayList.size())]);
		
		categoryList.setModel(new DefaultComboBoxModel<String>(categories));
		categoryList.setBounds(10, 81, 259, 22);
		panel.add(categoryList);
	}
}
