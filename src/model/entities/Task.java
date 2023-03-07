package model.entities;

import java.util.Objects;

public class Task {
	private Integer id;
    private String label;
    private boolean status;
    private Integer idCategory;
    
    public Task(){}
    
	public Task(String label) {    	
	    this.label = label;
	    status = false;
	}
    
	public Task(Integer id,String label) {    	
    	this.id = id;
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
        return "Task [id="+ id +", label=" + label + ", status=" + status + ", idCategory=" + idCategory + "]";
    }

    public int hashCode() {
    	return Objects.hash(id,label,status,idCategory);
    }
    
    public boolean equals(Object obj) {
    	if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(id, other.id) && Objects.equals(label, other.label);
    }



	
    public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

}
