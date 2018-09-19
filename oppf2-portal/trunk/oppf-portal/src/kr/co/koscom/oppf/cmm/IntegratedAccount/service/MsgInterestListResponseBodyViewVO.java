package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class MsgInterestListResponseBodyViewVO {
	private MsgInterestGroupListViewVO groupList = new MsgInterestGroupListViewVO();
	public MsgInterestGroupListViewVO getGroupList() {
		return groupList;
	}
	public void setGroupList(MsgInterestGroupListViewVO groupList) {
		this.groupList = groupList;
	}
}
