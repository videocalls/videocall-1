package kr.co.koscom.oppf.cmm.signkorea.service;

public class SKVerifyExtInfo{
    
	public String Version;
	public String Serial;
	public String Issuer;
	public String Subject;
	public String From;
	public String To;
	public String policy;
	public String distributionPoints;
	public String authorityKeyId;
	public String subjectKeyId;
	public String publicKey;
	
    public String getVersion() {
        return Version;
    }
    public void setVersion(String version) {
        Version = version;
    }
    public String getSerial() {
        return Serial;
    }
    public void setSerial(String serial) {
        Serial = serial;
    }
    public String getIssuer() {
        return Issuer;
    }
    public void setIssuer(String issuer) {
        Issuer = issuer;
    }
    public String getSubject() {
        return Subject;
    }
    public void setSubject(String subject) {
        Subject = subject;
    }
    public String getFrom() {
        return From;
    }
    public void setFrom(String from) {
        From = from;
    }
    public String getTo() {
        return To;
    }
    public void setTo(String to) {
        To = to;
    }
    public String getPolicy() {
        return policy;
    }
    public void setPolicy(String policy) {
        this.policy = policy;
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
