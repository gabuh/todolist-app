package gui;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.entities.Task;

@SuppressWarnings("serial")
public class CheckListRenderer extends JCheckBox implements ListCellRenderer<Task> {
	public Component getListCellRendererComponent(JList<? extends Task> list, Task value,
            int index, boolean isSelected, boolean cellHasFocus) {
        setEnabled(list.isEnabled());
        setSelected(value.getStatus());
        setFont(list.getFont());
        setText(value.toString());
        return this;
    }
}
