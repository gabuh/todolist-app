package model.dao;

import java.util.List;

import model.entities.Task;


public interface TaskDao {
	List<Task> findAll();
	List<Task> findByCategory(Integer Id);
	Task findByLabel(String label);
	void add(Task task);
	void delete(Integer id);
	void updateState(Integer id,boolean state);
	void removeAll(Integer idCategory);
}