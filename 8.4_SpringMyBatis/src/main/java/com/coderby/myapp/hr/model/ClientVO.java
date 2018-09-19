package com.coderby.myapp.hr.model;

import java.sql.Date;

import org.springframework.stereotype.Component;
@Component
public class ClientVO {
	private int clntid; 
	private String clntci; 
	private String clntemail ;
	private String pwd; 
	private String name; 
	private String dob ;
	private String gender ;
	private String hpno ;
	private String smsprslyn; 
	private String postno;
	private String dtladdr; 
	private String ip; 
	private Date newregdate; 
	private Date chgdate;
	private String outyn;
	private Date outdate;
	public int getClntid() {
		return clntid;
	}
	public void setClntid(int clntid) {
		this.clntid = clntid;
	}
	public String getClntci() {
		return clntci;
	}
	public void setClntci(String clntci) {
		this.clntci = clntci;
	}
	public String getClntemail() {
		return clntemail;
	}
	public void setClntemail(String clntemail) {
		this.clntemail = clntemail;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHpno() {
		return hpno;
	}
	public void setHpno(String hpno) {
		this.hpno = hpno;
	}
	public String getSmsprslyn() {
		return smsprslyn;
	}
	public void setSmsprslyn(String smsprslyn) {
		this.smsprslyn = smsprslyn;
	}
	public String getPostno() {
		return postno;
	}
	public void setPostno(String postno) {
		this.postno = postno;
	}
	public String getDtladdr() {
		return dtladdr;
	}
	public void setDtladdr(String dtladdr) {
		this.dtladdr = dtladdr;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getNewregdate() {
		return newregdate;
	}
	public void setNewregdate(Date newregdate) {
		this.newregdate = newregdate;
	}
	public Date getChgdate() {
		return chgdate;
	}
	public void setChgdate(Date chgdate) {
		this.chgdate = chgdate;
	}
	public String getOutyn() {
		return outyn;
	}
	public void setOutyn(String outyn) {
		this.outyn = outyn;
	}
	public Date getOutdate() {
		return outdate;
	}
	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}
	@Override
	public String toString() {
		return "ClientVO [clntid=" + clntid + ", clntci=" + clntci + ", clntemail=" + clntemail + ", pwd=" + pwd
				+ ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", hp_no=" + hpno + ", smsprslyn="
				+ smsprslyn + ", postno=" + postno + ", dtladdr=" + dtladdr + ", ip=" + ip + ", newregdate="
				+ newregdate + ", chgdate=" + chgdate + ", outyn=" + outyn + ", outdate=" + outdate + "]";
	}
	

}
