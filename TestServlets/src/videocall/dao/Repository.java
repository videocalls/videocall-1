package com.koscom.videocall.dao;

import java.util.List;

import com.koscom.videocall.model.ClientVO;
import com.koscom.videocall.model.FirmVO;

public interface Repository {
	
	void insertClient(ClientVO clntvo);
	ClientVO getClientInfo(String clntemail, String pwd);
	List<ClientVO> getAllClients();
	void updateclient(ClientVO clntvo);
	void deleteClient(ClientVO clntvo);
	String getPassword(String clntemail);
	List<FirmVO> getAllFirms();
	int getNextClientId();
}