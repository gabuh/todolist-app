package model.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DB.DB;
import DB.DbException;
import model.dao.TaskDao;
import model.entities.Category;
import model.entities.Task;

public class TaskDaoJDBC implements TaskDao{
	Connection conn;
	
	public TaskDaoJDBC(){}
	public TaskDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	private Category instantiateCategory(ResultSet rs){
		Category c = new Category();
		try {
		c.setId(rs.getInt("idCategory"));
		c.setName(rs.getString("categoryName"));
		}catch (SQLException e) {
			throw new DbException("couldnt instantiate category");
		}
		return c;
	}
	
	private Task instantiateTask(ResultSet rs, Category category){
		Task t = new Task();
		try {
		t.setId(rs.getInt("id"));
		t.setLabel(rs.getString("label"));
		t.setStatus(rs.getBoolean("status"));
		t.setCategory(category);
		}catch (SQLException e) {
			throw new DbException("couldnt instantiate task");
		}
		return t;
	}
	
	@Override
	public Task findByLabel(String label) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT task.*, category.cname as categoryName "
					+"FROM task INNER JOIN category "
					+"ON task.idCategory = category.id "
					+"WHERE task.label = ?");
			
			st.setString(1, label);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Category category = instantiateCategory(rs);
				Task task = instantiateTask(rs, category);
				return task;
			}
			return null;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	

	@Override
	public List<Task> findAll() {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement(
						"SELECT task.*, category.cname as categoryName "
						+"FROM task INNER JOIN category "
						+"ON task.idCategory = category.id");
				
				rs = st.executeQuery();
				
				List<Task> list = new ArrayList<>();
				Map<Integer, Category> map = new HashMap<>();
				
				while(rs.next()) {
					
					//verifica se a categoria ligada a task ja foi instanciada em memoria
					Category category = map.get(rs.getInt("idCategory"));
					
					if(category == null) {
						category = instantiateCategory(rs);
						map.put(rs.getInt("idCategory"), category);
					}
					
					Task t = instantiateTask(rs, category);
					list.add(t);
				}
				
				return list;
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		}
	

	@Override
	public void add(Task task) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO task "
					+"(label, status ,idCategory) "
					+"VALUES "
					+"(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, task.getLabel());
			st.setBoolean(2, task.getStatus());
			st.setInt(3, task.getCategory().getId());
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					task.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				System.err.println("couldnt insert into Task table");
			}
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
			
			try {
			st = conn.prepareStatement(
					"DELETE FROM task WHERE id = ?");
			
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				throw new DbException("couldnt delete the task [ id = "+id+" ]");
			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}
	@Override
	public void update(Task task) {
			PreparedStatement st = null;
			
			try {
			st = conn.prepareStatement(
					"UPDATE task "
					+"SET label = ?, status = ?, idCategory = ? "
					+"WHERE id = ?");
			
			st.setString(1, task.getLabel());
			st.setBoolean(2, task.getStatus());
			st.setInt(3, task.getCategory().getId());
			st.setInt(4, task.getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				throw new DbException("couldnt update task [ id = "+task.getId()+" ]");
			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public void deleteAllByCategory(Category category) {
			PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM task "
					+"WHERE idCategory = ? "
					+"AND status = false");
			
				st.setInt(1, category.getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				throw new DbException("couldnt delete tasks from category [ idCategory = "+category.getId()+" ]");
			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}
	
	@Override
	public List<Task> findByCategory(Category category) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT task.*, category.cname as categoryName "
					+"FROM task INNER JOIN category "
					+"ON task.idCategory = category.id "
					+"WHERE idCategory = ? "
					+"ORDER BY category.cname");
			
			st.setInt(1, category.getId());
			rs = st.executeQuery();
			
			List<Task> list = new ArrayList<>();
			
			while(rs.next()) {
				Task t = instantiateTask(rs, category);
				list.add(t);
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
