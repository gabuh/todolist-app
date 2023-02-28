package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Choice;
import javax.swing.DefaultComboBoxModel;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
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
		setBounds(100, 100, 1385, 735);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel categoryName = new JLabel("Default");
		categoryName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		categoryName.setBounds(857, 37, 56, 31);
		contentPane.add(categoryName);
		
		JPanel panel = new JPanel();
		panel.setBackground(Utils.pColor);
		panel.setBounds(0, 0, 373, 696);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(20);
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"klhjlkjh", "ljklkjlçkj", "lkjçlkjlç", "jlkççljk"}));
		comboBox.setBounds(10, 81, 259, 22);
		//comboBox.setBackground(pColor);
		panel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Category");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 48, 353, 22);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setBounds(279, 81, 84, 23);
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(e -> categoryName.setText(Utils.getCategoryName(comboBox)));
		panel.add(btnNewButton);
	}
}
