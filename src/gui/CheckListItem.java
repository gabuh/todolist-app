package gui;

public class CheckListItem {
    private String label;
    private boolean isSelected;

    public CheckListItem(String label) {
        this.label = label;
        isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String toString() {
        return label;
    }
}
