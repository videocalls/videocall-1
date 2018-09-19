package com.coderby.myapp.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderby.myapp.member.dao.IMemberDAO;
import com.coderby.myapp.member.model.MemberVO;
import com.coderby.myapp.member.service.IMemberService;

@Service
public class MemberService implements IMemberService {

	@Autowired
	IMemberDAO memberDao;
	
	@Override
	public void insertMember(MemberVO member) {
		memberDao.insertMember(member);
	}

	@Override
	public MemberVO selectMember(String userid) {
		return memberDao.selectMember(userid);
	}

	@Override
	public List<MemberVO> selectAllMembers() {
		return memberDao.selectAllMembers();
	}

	@Override
	public void updateMember(MemberVO member) {
		memberDao.updateMember(member);
	}

	@Override
	public void deleteMember(MemberVO member) {
		memberDao.deleteMember(member);
	}

	@Override
	public String getPassword(String userid) {
		return memberDao.getPassword(userid);
	}

}
