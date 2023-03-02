package test;

import java.util.ArrayList;
import java.util.List;

import model.dao.CategoryDao;
import model.dao.DaoFactory;
import model.entities.Category;

public class Test {

	public static void main(String[] args) {		
		
		CategoryDao categoryDao = DaoFactory.createCategoryDao();
		
		List<Category> list = categoryDao.findAll();
		for(Category c : list) {
			System.out.println(c);
		}

	}

}
