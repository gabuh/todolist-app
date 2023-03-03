package model.dao;

import java.util.List;

import model.entities.Category;

public interface CategoryDao {
	List<Category> findAll();
	void add(Category category);
}
