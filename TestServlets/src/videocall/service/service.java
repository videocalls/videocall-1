package com.koscom.videocall.service;

import java.util.List;

import com.koscom.videocall.model.ClientVO;
import com.koscom.videocall.model.FirmVO;

public interface service {

	List<ClientVO> getClientList();
	ClientVO getClientInfo(String email, String pwd);
	List<FirmVO> getFirmList();
	void insertClient(ClientVO clntvo);
	int updateClient(ClientVO clntvo);
	
}
