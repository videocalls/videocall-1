package com.koscom.videocall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.koscom.videocall.dao.Repository;
import com.koscom.videocall.model.ClientVO;
import com.koscom.videocall.model.FirmVO;
@Service
public class serviceImpl implements service{
	
	Repository repo;
	
	public List<ClientVO> getClientList(){
		List<ClientVO> listclntvo = null;
		return listclntvo;
	}
	public ClientVO getClientInfo(String clntemail, String pwd) {
		ClientVO clntvo = new ClientVO();
		return clntvo;
	}
	public List<FirmVO> getFirmList(){
		List<FirmVO> listfirm = null;
		return listfirm;
	}
	public void insertClient(ClientVO clntvo) {
		System.out.println(clntvo.toString());
		System.out.println(repo.toString());
		repo.insertClient(clntvo);
	}
	
	public int updateClient(ClientVO clntvo) {
		int updateCnt = 0;
		return updateCnt;
	}
}
