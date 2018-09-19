package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import java.util.ArrayList;
import java.util.List;

public class MsgPortfolioVO {
	private MsgPortfolioCashVO cash;
	private List<MsgPortfolioEquityListVO> equityList = new ArrayList<MsgPortfolioEquityListVO>();
	private List<MsgPortfolioFundListVO> fundList = new ArrayList<MsgPortfolioFundListVO>();
	private List<MsgPortfolioEtcListVO> etcList = new ArrayList<MsgPortfolioEtcListVO>();
	
	public MsgPortfolioCashVO getCash() {
		return cash;
	}
	public void setCash(MsgPortfolioCashVO cash) {
		this.cash = cash;
	}
	public List<MsgPortfolioEquityListVO> getEquityList() {
		return equityList;
	}
	public void setEquityList(List<MsgPortfolioEquityListVO> equityList) {
		this.equityList = equityList;
	}
	public List<MsgPortfolioFundListVO> getFundList() {
		return fundList;
	}
	public void setFundList(List<MsgPortfolioFundListVO> fundList) {
		this.fundList = fundList;
	}
	public List<MsgPortfolioEtcListVO> getEtcList() {
		return etcList;
	}
	public void setEtcList(List<MsgPortfolioEtcListVO> etcList) {
		this.etcList = etcList;
	}
}
