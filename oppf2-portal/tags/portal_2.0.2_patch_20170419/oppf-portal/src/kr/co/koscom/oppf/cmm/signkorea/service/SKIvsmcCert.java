// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)
// Source File Name:   SKIvsmcCert.java

package kr.co.koscom.oppf.cmm.signkorea.service;


public class SKIvsmcCert{
	public SKIvsmcCert(){
	}

	public int set_err(int i, String s){
		err_code = i;
		err_msg = new String(s);
		return err_code;
	}

	public String serial_no;
	public String dn;
	public String ssn;
	public String status;
	public String status_flag;
	public String ssnmatch;
	public int err_code;
	public String err_msg;
	
    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_flag() {
        return status_flag;
    }

    public void setStatus_flag(String status_flag) {
        this.status_flag = status_flag;
    }

    public String getSsnmatch() {
        return ssnmatch;
    }

    public void setSsnmatch(String ssnmatch) {
        this.ssnmatch = ssnmatch;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }
	
	
}
