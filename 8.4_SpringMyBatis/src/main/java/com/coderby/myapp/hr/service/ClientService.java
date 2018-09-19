package com.coderby.myapp.hr.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coderby.myapp.hr.model.ClientVO;

public interface ClientService {
	int getClientCount();
	List<ClientVO> getClientList();
	ClientVO getClientInfo(int clntid);
	void updateClient(ClientVO clnt);
	void insertClient(ClientVO clnt);
	void deleteClient(int clntid);

}
