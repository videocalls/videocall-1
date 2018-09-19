package com.coderby.myapp.hr.dao;

import java.util.List;

import com.coderby.myapp.hr.model.DeptVO;

public interface IDeptRepository {
	List<DeptVO> getAllDeptInfo();
	int insertDept(DeptVO dept);
	int updateDept(DeptVO dept);
	int deleteDept(int departmentId);	
}
