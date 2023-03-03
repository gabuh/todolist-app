package gui;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class CheckListRenderer extends JCheckBox implements ListCellRenderer<CheckListTask> {
	public Component getListCellRendererComponent(JList<? extends CheckListTask> list, CheckListTask value,
            int index, boolean isSelected, boolean cellHasFocus) {
        setEnabled(list.isEnabled());
        setSelected(value.getStatus());
        setFont(list.getFont());
        setText(value.toString());
        return this;
    }
}
