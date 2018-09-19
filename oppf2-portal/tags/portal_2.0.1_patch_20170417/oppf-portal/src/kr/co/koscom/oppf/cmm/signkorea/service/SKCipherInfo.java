package kr.co.koscom.oppf.cmm.signkorea.service;

public class SKCipherInfo{
	public byte	opcode;
	public String sid;
	public String cert;
	public String plaintext;
	public byte[] envelopedData;
	public byte[] bPlaintext;
	public String encryptedData;
	public String decryptedData;
	public String envelopdata;
	public String errmsg;
	
    public byte getOpcode() {
        return opcode;
    }
    public void setOpcode(byte opcode) {
        this.opcode = opcode;
    }
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getCert() {
        return cert;
    }
    public void setCert(String cert) {
        this.cert = cert;
    }
    public String getPlaintext() {
        return plaintext;
    }
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }
    public byte[] getEnvelopedData() {
        return envelopedData;
    }
    public void setEnvelopedData(byte[] envelopedData) {
        this.envelopedData = envelopedData;
    }
    public byte[] getbPlaintext() {
        return bPlaintext;
    }
    public void setbPlaintext(byte[] bPlaintext) {
        this.bPlaintext = bPlaintext;
    }
    public String getEncryptedData() {
        return encryptedData;
    }
    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }
    public String getDecryptedData() {
        return decryptedData;
    }
    public void setDecryptedData(String decryptedData) {
        this.decryptedData = decryptedData;
    }
    public String getEnvelopdata() {
        return envelopdata;
    }
    public void setEnvelopdata(String envelopdata) {
        this.envelopdata = envelopdata;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
	
	
}
