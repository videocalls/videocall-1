package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import java.util.ArrayList;
import java.util.List;

public class MsgInterestGroupListViewVO {
	private List<MsgInterestGroupViewVO> group = new ArrayList<MsgInterestGroupViewVO>();

	public List<MsgInterestGroupViewVO> getGroup() {
		return group;
	}

	public void setGroup(List<MsgInterestGroupViewVO> group) {
		this.group = group;
	}
	
	
}
