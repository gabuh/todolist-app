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
		
		
		//Category category = new Category();
		//category.setName("faculdade");
		//categoryDao.add(category);
		//System.out.println("added a new category");
		
		
		
		/*
		categoryDao.delete(6);
		System.out.println("deleted");
		*/
		
		list = categoryDao.findAll();
		for(Category c : list) {
			System.out.println(c);
		}
		
		System.out.println("========================");
		
		//Category category = categoryDao.findByName("faculdade");
		//System.out.println(category);
		
		
		
		/**
		 * TEST DE TASK -----------------------------------------------------------------
		 * -------------------------------------------------------------------------------
		 */
		
		
		TaskDao taskDao = DaoFactory.createTaskDao();
		
		Category category = categoryDao.findByName("faculdade");
		
		//Task task = new Task("test3");
		//task.setCategory(category);
		//taskDao.add(task);
		
		List<Task> listTask = taskDao.findAll();
		
		for(Task t : listTask) {
			System.out.println(t);
		}
		
		System.out.println("===============");
		
		taskDao.deleteAllByCategory(category);
		System.out.println("deleted");
		
		/*
		Task task = taskDao.findByLabel("test3");
		
		List<Task> listTask2 = taskDao.findByCategory(category);
		
		for(Task t : listTask2) {
			System.out.println(t);
		}
		*/
		
		//task.setSelected(false);
		//taskDao.update(task);
		//System.out.println(task);
		
		//System.out.println(task);
		
		//taskDao.deleteById(task.getId());
		//System.out.println("deleted");
		
		//taskDao.deleteById(task.getId());
		//System.out.println("deleted");
		/*
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
		*/
		
		
	}

}
