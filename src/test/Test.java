package test;

import java.util.List;

import model.dao.CategoryDao;
import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.entities.Category;
import model.entities.Task;

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
		
		
		/*
		categoryDao.delete(6);
		System.out.println("deleted");
		*/
		
		list = categoryDao.findAll();
		for(Category c : list) {
			System.out.println(c);
		}
		
		System.out.println("========================");
		
		Category category = categoryDao.findByName("mercado");
		System.out.println(category);
		
		
		
		/**
		 * TEST DE TASK -----------------------------------------------------------------
		 * -------------------------------------------------------------------------------
		 */
		
		
		TaskDao taskDao = DaoFactory.createTaskDao();
		
		List<Task> listTask = taskDao.findByCategory(3);
		
		for(Task c : listTask) {
			System.out.println(c);
		}
		
		taskDao.setState(2, true);
		
		System.out.println(taskDao.findByLabel("TOMAR CAFE"));
		
//		Task task = new Task(14,"task nova3");
//		task.setSelected(true);
//		taskDao.add(task);
		
//		taskDao.delete(14);
		
		listTask = taskDao.findAll();
		for(Task c : listTask) {
			System.out.println(c);
		}
		
		
		
	}

}
