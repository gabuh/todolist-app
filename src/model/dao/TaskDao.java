package model.dao;

import java.util.List;

import model.entities.Category;
import model.entities.Task;


public interface TaskDao {
	List<Task> findAll();
	List<Task> findByCategory(Category category);
	Task findByLabel(String label);
	void add(Task task);
	void deleteById(Integer id);
	void update(Task task);
	void deleteAllByCategory(Category category);
}