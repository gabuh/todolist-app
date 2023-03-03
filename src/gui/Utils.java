package gui;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Utils {
	
	public static final Color pColor = new Color(135, 206, 235);
	public static final Color sColor = new Color(240, 240, 240);
	
	public Utils() {}
	
	public static boolean isCategoryNameNull(JComboBox<String> category) {
		return category.getSelectedItem() == null ? true : false;
	}
	
	/*
	public static Category getCategory(JComboBox<String> category) {
		return (Category) category.getSelectedItem();
	}
	*/
	
	public static String getCategoryName(JComboBox<String> category) {
		return category.getSelectedItem().toString();
	}
	
	public static String getNewCategoryName(JTextField newCategory) {
		return newCategory.getText().toString();
	}
}
