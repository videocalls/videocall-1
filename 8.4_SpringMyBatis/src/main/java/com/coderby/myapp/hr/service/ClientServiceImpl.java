package com.coderby.myapp.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.coderby.myapp.hr.dao.IEmpRepository;
import com.coderby.myapp.hr.model.ClientVO;

@Service
public class ClientServiceImpl implements ClientService{

//	@Autowired
//	@Qualifier("IEmpRepository")
	IEmpRepository clientRepository;
	
	@Override
	public int getClientCount() {
		return clientRepository.getClientCount();
	}

	@Override
	public List<ClientVO> getClientList() {
		return clientRepository.getClientList();
	}

	@Override
	public ClientVO getClientInfo(int clntid) {
		return clientRepository.getClientInfo(clntid);
	}

	@Override
	public void updateClient(ClientVO clntVo) {
		clientRepository.updateClient(clntVo);
	}

	@Override
	public void insertClient(ClientVO clntVo) {
		clientRepository.insertClient(clntVo);
	}

	@Override
	public void deleteClient(int clntid) {
		clientRepository.deleteClient(clntid);
	}
}
