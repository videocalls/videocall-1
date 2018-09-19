package com.koscom.videocall.model;

import org.springframework.stereotype.Component;

@Component
public class FirmVO {
	private int firmno;
	private String firmnm;
	private String telno;
	private String email;
	private String ceonm;

	public int getFirmno() {
		return firmno;
	}

	public void setFirmno(int firmno) {
		this.firmno = firmno;
	}

	public String getFirmnm() {
		return firmnm;
	}

	public void setFirmnm(String firmnm) {
		this.firmnm = firmnm;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCeonm() {
		return ceonm;
	}

	public void setCeonm(String ceonm) {
		this.ceonm = ceonm;
	}

	@Override
	public String toString() {
		return "FirmVO [firmno=" + firmno + ", firmnm=" + firmnm + ", telno=" + telno + ", email=" + email + ", ceonm="
				+ ceonm + ", getFirmno()=" + getFirmno() + ", getFirmnm()=" + getFirmnm() + ", getTelno()=" + getTelno()
				+ ", getEmail()=" + getEmail() + ", getCeonm()=" + getCeonm() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
