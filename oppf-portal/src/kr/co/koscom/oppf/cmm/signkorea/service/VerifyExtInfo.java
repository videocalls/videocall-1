package kr.co.koscom.oppf.cmm.signkorea.service;

public class VerifyExtInfo{
	public String sign;                    //[256];
	public String szVersion;               //[15];
	public String szSerial;                //[10];
	public String szSignatureAlgId;        //[30];
	public String szIssuer;                //[256];
	public String szSubject;               //[256];
	public String szSubjectPublicKeyAlgId; //[30];
	public String szFrom;                  //[30];
	public String szTo;                    //[30];
	public String signature;               //[1024];
	public String subjectAltName;          //[300];
	public String keyUsage;                //[300];
	public String extKeyUsage;             //[300];
	public String policy;                  //[300];
	public String policyMapping;           //[30];
	public String basicConstraint;         //[100];
	public String policyConstraint;        //[100];
	public String distributionPoints;      //[256];
	public String authorityKeyId;          //[100];
	public String subjectKeyId;            //[100];
	public String publicKey;               //[1024];
	
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getSzVersion() {
        return szVersion;
    }
    public void setSzVersion(String szVersion) {
        this.szVersion = szVersion;
    }
    public String getSzSerial() {
        return szSerial;
    }
    public void setSzSerial(String szSerial) {
        this.szSerial = szSerial;
    }
    public String getSzSignatureAlgId() {
        return szSignatureAlgId;
    }
    public void setSzSignatureAlgId(String szSignatureAlgId) {
        this.szSignatureAlgId = szSignatureAlgId;
    }
    public String getSzIssuer() {
        return szIssuer;
    }
    public void setSzIssuer(String szIssuer) {
        this.szIssuer = szIssuer;
    }
    public String getSzSubject() {
        return szSubject;
    }
    public void setSzSubject(String szSubject) {
        this.szSubject = szSubject;
    }
    public String getSzSubjectPublicKeyAlgId() {
        return szSubjectPublicKeyAlgId;
    }
    public void setSzSubjectPublicKeyAlgId(String szSubjectPublicKeyAlgId) {
        this.szSubjectPublicKeyAlgId = szSubjectPublicKeyAlgId;
    }
    public String getSzFrom() {
        return szFrom;
    }
    public void setSzFrom(String szFrom) {
        this.szFrom = szFrom;
    }
    public String getSzTo() {
        return szTo;
    }
    public void setSzTo(String szTo) {
        this.szTo = szTo;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getSubjectAltName() {
        return subjectAltName;
    }
    public void setSubjectAltName(String subjectAltName) {
        this.subjectAltName = subjectAltName;
    }
    public String getKeyUsage() {
        return keyUsage;
    }
    public void setKeyUsage(String keyUsage) {
        this.keyUsage = keyUsage;
    }
    public String getExtKeyUsage() {
        return extKeyUsage;
    }
    public void setExtKeyUsage(String extKeyUsage) {
        this.extKeyUsage = extKeyUsage;
    }
    public String getPolicy() {
        return policy;
    }
    public void setPolicy(String policy) {
        this.policy = policy;
    }
    public String getPolicyMapping() {
        return policyMapping;
    }
    public void setPolicyMapping(String policyMapping) {
        this.policyMapping = policyMapping;
    }
    public String getBasicConstraint() {
        return basicConstraint;
    }
    public void setBasicConstraint(String basicConstraint) {
        this.basicConstraint = basicConstraint;
    }
    public String getPolicyConstraint() {
        return policyConstraint;
    }
    public void setPolicyConstraint(String policyConstraint) {
        this.policyConstraint = policyConstraint;
    }
    public String getDistributionPoints() {
        return distributionPoints;
    }
    public void setDistributionPoints(String distributionPoints) {
        this.distributionPoints = distributionPoints;
    }
    public String getAuthorityKeyId() {
        return authorityKeyId;
    }
    public void setAuthorityKeyId(String authorityKeyId) {
        this.authorityKeyId = authorityKeyId;
    }
    public String getSubjectKeyId() {
        return subjectKeyId;
    }
    public void setSubjectKeyId(String subjectKeyId) {
        this.subjectKeyId = subjectKeyId;
    }
    public String getPublicKey() {
        return publicKey;
    }
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
	
	
}

