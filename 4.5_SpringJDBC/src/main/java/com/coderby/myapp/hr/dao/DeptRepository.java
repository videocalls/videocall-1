package com.coderby.myapp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.coderby.myapp.hr.model.DeptVO;

@Repository
public class DeptRepository implements IDeptRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<DeptVO> getAllDeptInfo() {
		String sql = "select department_id, department_name, manager_id, location_id from departments";
		return jdbcTemplate.query(sql, new RowMapper<DeptVO>() {
			@Override
			public DeptVO mapRow(ResultSet rs, int count) throws SQLException {
				DeptVO dept = new DeptVO();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setManagerId(rs.getInt("manager_id"));
				dept.setLocationId(rs.getInt("lcoation_id"));
				return dept;
			} });
	}

	@Override
	public int insertDept(DeptVO dept) {
		String sql = "insert into departments (department_id, department_name, manager_id, location_id) "
					+"values (departments_seq.nextval, ?, ?, ?)";
		return jdbcTemplate.update(sql, dept.getDepartmentName(), dept.getManagerId(), dept.getLocationId());
	}

	@Override
	public int updateDept(DeptVO dept) {
		String sql = "update departments set department_name=?, manager_id=?, location_id=? "
					+"where department_id=?";	 
		return jdbcTemplate.update(sql, 
									dept.getDepartmentName(), 
									dept.getManagerId(), 
									dept.getLocationId(), 
									dept.getDepartmentId());
	}

	@Override
	public int deleteDept(int departmentId) {
		String sql = "delete departments where department_id=?";
		return jdbcTemplate.update(sql, departmentId);
	}

}
