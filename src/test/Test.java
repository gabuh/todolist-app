package test;

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
		
		/* ok
		Category category = new Category();
		category.setName("faculdade");
		categoryDao.add(category);
		System.out.println("added a new category");
		*/
		
		list = categoryDao.findAll();
		for(Category c : list) {
			System.out.println(c);
		}
		
	}

}
