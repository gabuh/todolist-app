package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.CategoryDao;
import model.entities.Category;

public class CategoryDaoJDBC implements CategoryDao{

	private Connection conn;
	
	public CategoryDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	private Category instantiateCategory(ResultSet rs){
		Category c = new Category();
		try {
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("cname"));
		}catch (SQLException e) {
			throw new DbException("couldnt instantiate category");
		}
		return c;
	}

	@Override
	public List<Category> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT category.* "
					+"FROM category");
			
			rs = st.executeQuery();
			List<Category> list = new ArrayList<>();
			
			while(rs.next()) {
				Category c = instantiateCategory(rs);
				list.add(c);
			}
			
			return list;
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	
}
