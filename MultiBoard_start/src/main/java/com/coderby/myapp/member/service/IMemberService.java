package com.coderby.myapp.member.service;

import java.util.List;

import com.coderby.myapp.member.model.MemberVO;

public interface IMemberService {
	void insertMember(MemberVO member) ;
	MemberVO selectMember(String userid);
	List<MemberVO> selectAllMembers();
	void updateMember(MemberVO member);
	void deleteMember(MemberVO member);
	String getPassword(String userid);
}
