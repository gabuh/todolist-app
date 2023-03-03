package model.dao;

import java.util.List;

import model.entities.Category;

public interface CategoryDao {
	List<Category> findAll();
	Category findByName(String name);
	void add(Category category);
	void delete(Integer id);
}
