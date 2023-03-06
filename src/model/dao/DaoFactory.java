package model.dao;

import DB.DB;
import model.dao.impl.CategoryDaoJDBC;

public class DaoFactory {

	public static CategoryDao createCategoryDao() {
		return new CategoryDaoJDBC(DB.getConnection());
	}
}
