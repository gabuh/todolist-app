package model.dao;

import DB.DB;
import model.dao.impl.CategoryDaoJDBC;
import model.dao.impl.TaskDaoJDBC;

public class DaoFactory {

	public static CategoryDao createCategoryDao() {
		return new CategoryDaoJDBC(DB.getConnection());
	}
	
	public static TaskDao createTaskDao() {
		return new TaskDaoJDBC(DB.getConnection());
	}
	
	
}
