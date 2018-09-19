package kr.co.koscom.oppf.cmm.signkorea.service;

public class SKSignedDataInfo{
	public byte[] signed_data;
	public byte   opcode;
	public int    status_flag;	/* OK : 0, NOT OK : 1 */
	public String sid;
	public String errmsg;
	public String signer_dn;
	public String Certificate;
	public byte[] publickey;
	public byte[] plaintext;
	
    public byte[] getSigned_data() {
        return signed_data;
    }
    public void setSigned_data(byte[] signed_data) {
        this.signed_data = signed_data;
    }
    public byte getOpcode() {
        return opcode;
    }
    public void setOpcode(byte opcode) {
        this.opcode = opcode;
    }
    public int getStatus_flag() {
        return status_flag;
    }
    public void setStatus_flag(int status_flag) {
        this.status_flag = status_flag;
    }
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public String getSigner_dn() {
        return signer_dn;
    }
    public void setSigner_dn(String signer_dn) {
        this.signer_dn = signer_dn;
    }
    public String getCertificate() {
        return Certificate;
    }
    public void setCertificate(String certificate) {
        Certificate = certificate;
    }
    public byte[] getPublickey() {
        return publickey;
    }
    public void setPublickey(byte[] publickey) {
        this.publickey = publickey;
    }
    public byte[] getPlaintext() {
        return plaintext;
    }
    public void setPlaintext(byte[] plaintext) {
        this.plaintext = plaintext;
    }
	
	
}