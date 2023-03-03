package gui;

public class CheckListTask {
    private String label;
    private boolean status;

    public CheckListTask(String label) {
        this.label = label;
        status = false;
    }

    public boolean getStatus() {
        return status;
    }

    public void setSelected(boolean isSelected) {
        this.status = isSelected;
    }

    public String toString() {
        return label;
    }
}
