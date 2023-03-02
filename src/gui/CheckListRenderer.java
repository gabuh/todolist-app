package gui;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class CheckListRenderer extends JCheckBox implements ListCellRenderer<CheckListItem> {
	public Component getListCellRendererComponent(JList<? extends CheckListItem> list, CheckListItem value,
            int index, boolean isSelected, boolean cellHasFocus) {
        setEnabled(list.isEnabled());
        setSelected(value.isSelected());
        setFont(list.getFont());
        setText(value.toString());
        return this;
    }
}
