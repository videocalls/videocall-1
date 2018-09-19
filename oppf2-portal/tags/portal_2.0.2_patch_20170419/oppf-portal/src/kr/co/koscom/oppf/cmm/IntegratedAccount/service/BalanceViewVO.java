package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import java.util.ArrayList;
import java.util.List;

public class BalanceViewVO {
	private MsgBalanceSummaryVO summary;
	private List<MsgBalanceEquityListViewVO> equityList = new ArrayList<MsgBalanceEquityListViewVO>();
	private List<MsgBalanceFundListViewVO> fundList = new ArrayList<MsgBalanceFundListViewVO>();
	private List<MsgBalanceEtcListViewVO> etcList = new ArrayList<MsgBalanceEtcListViewVO>();
	
	public MsgBalanceSummaryVO getSummary() {
		return summary;
	}
	public void setSummary(MsgBalanceSummaryVO summary) {
		this.summary = summary;
	}
	public List<MsgBalanceEquityListViewVO> getEquityList() {
		return equityList;
	}
	public void setEquityList(List<MsgBalanceEquityListViewVO> equityList) {
		this.equityList = equityList;
	}
	public List<MsgBalanceFundListViewVO> getFundList() {
		return fundList;
	}
	public void setFundList(List<MsgBalanceFundListViewVO> fundList) {
		this.fundList = fundList;
	}
	public List<MsgBalanceEtcListViewVO> getEtcList() {
		return etcList;
	}
	public void setEtcList(List<MsgBalanceEtcListViewVO> etcList) {
		this.etcList = etcList;
	}

}
