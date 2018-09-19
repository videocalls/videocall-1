package kr.co.koscom.web;

import java.util.List;

public interface MemberDAO {
	public int addMember(MemberDTO member);
	public MemberDTO getMember(String id);
	public List<MemberDTO> getMemberList();
	public int updateMember(MemberDTO member);
	public int deleteMember(String id);
}
