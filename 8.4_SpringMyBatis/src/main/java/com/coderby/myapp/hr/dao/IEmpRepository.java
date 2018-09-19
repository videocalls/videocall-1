package com.coderby.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.coderby.myapp.hr.model.ClientVO;
import com.coderby.myapp.hr.model.EmpVO;

public interface IEmpRepository {
	int getEmpCount();
	int getEmpCount(@Param("deptid") int deptid);
	List<EmpVO> getEmpList();
	EmpVO getEmpInfo(int empid);
	void updateEmp(EmpVO emp);
	void insertEmp(EmpVO emp);
	void deleteJobHistory(int empid);
	void deleteEmp(@Param("empid") int empid, @Param("email") String email);
	List<Map<String, Object>> getAllDeptId();
	List<Map<String, Object>> getAllJobId();
	List<Map<String, Object>> getAllManagerId();
	
	int getClientCount();
	int getClientCount(@Param("clntid") int clntid);
	List<ClientVO> getClientList();
	ClientVO getClientInfo(int clntid);
	void updateClient(ClientVO clntVo);
	void insertClient(ClientVO clntVo);
	void deleteHistory(int clntid);
	void deleteClient(@Param("clntid") int clntid);
}
