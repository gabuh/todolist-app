package gui;

import java.awt.Color;

import javax.swing.JComboBox;

public class Utils {
	
	public static final Color pColor = new Color(135, 206, 235);
	
	public Utils() {}
	
	public static String getCategoryName(JComboBox<?> category) {
		return category.getSelectedItem().toString();
	}
}
