package model.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.DB;
import DB.DbException;
import model.dao.TaskDao;
import model.entities.Task;

public class TaskDaoJDBC implements TaskDao{
	Connection conn;
	
	public TaskDaoJDBC(){}
	public TaskDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	private Task instantiateTask(ResultSet rs){
		Task c = new Task();
		try {
		c.setId(rs.getInt("id"));
		c.setLabel(rs.getString("label"));
		c.setStatus(rs.getBoolean("state"));
		c.setIdCategory(rs.getInt("idCategory"));
		}catch (SQLException e) {
			throw new DbException("couldnt instantiate Task");
		}
		return c;
	}

	@Override
	public List<Task> findAll() {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement(
						"SELECT Task.* "
						+"FROM Task");
				
				rs = st.executeQuery();
				List<Task> list = new ArrayList<>();
				
				while(rs.next()) {
					Task c = instantiateTask(rs);
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
	

	@Override
	public Task findByLabel(String label) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT Task.* "
					+"FROM Task "
					+"WHERE Task.label = ?");
			
			st.setString(1, label);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Task Task = instantiateTask(rs);
				return Task;
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
	public void add(Task task) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO Task "
					+"(label, state ,idCategory)"
					+"VALUES "
					+"(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, task.getLabel());
			st.setBoolean(2, task.getStatus());
			st.setInt(3, task.getIdCategory());
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
	public void delete(Integer id) {
		PreparedStatement st = null;
			
			try {
			st = conn.prepareStatement(
					"DELETE FROM Task WHERE id = ?");
			
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				throw new DbException("couldnt delete the Task [ id = "+id+" ]");
			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}
	@Override
	public void updateState(Integer id ,boolean state) {
			PreparedStatement st = null;
			
			try {
			st = conn.prepareStatement(
					"UPDATE task SET state = ? WHERE id = ?");
			
			st.setBoolean(1,state);
			st.setInt(2, id);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				throw new DbException("couldnt update state of the Task [ id = "+id+" ]");
			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}
	@Override
	public void removeAll(Integer idCategory) {
			PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM task WHERE idCategory = ? AND state = true");
			
				st.setInt(1, idCategory);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				throw new DbException("couldnt delete tasks from category [ idCategory = "+idCategory+" ]");
			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}
	@Override
	public List<Task> findByCategory(Integer idCategory) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Task WHERE idCategory = ?");
			st.setInt(1, idCategory);
			
			rs = st.executeQuery();
			List<Task> list = new ArrayList<>();
			
			while(rs.next()) {
				Task c = instantiateTask(rs);
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
