package kr.co.koscom.oppf.cmm.tsa.service;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmTsaVO.java
* @Comment  : [공통회원동의서TSA연계]정보관리를 위한 VO 클래스
* @author   : 포털 이환덕
* @since    : 2016.06.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.09  이환덕        최초생성
*
*/
public class CmmTsaVO{
    
    //사용자번호
    private String customerRegNo = "";
    
    //약관등록번호
    private String termsRegNo = "";
    
    //약관동의시작일시
    private String termsStartDate = "";
    
    //약관표기동의최총기간(개월)
    private String termsPolicy = "";
    
    //약관표기동의최총기간(년)
    private String termsPolicyYear = "";
    
    //사용자 공인인증서 서명값
    private String signData = "";
    
    //사용자 공인인증서 DN값
    private String dn = "";
    
    //사용자 request약관html
    private String reqHtml = "";
    
    //사용자 공인인증서 R값(인증비교값)
    private String rValue = "";
    
    //만들html내용(htmlCovertPdf메소드에쓰임)
    private String makeHtmlContent = "";
    
    //만들pdf내용(htmlCovertPdf메소드에쓰임)
    private String makePdfContent = "";
    
    //사용자 임시지정경로 약관html
    private String filePath = "";
    
    //파일명
    private String fileName = "";
    
    //원본파일명
    private String fileNameOriginal = "";
    
    //오리지널마크
    private String fileNmMarkOriginal = "-original";
    
    //TSA 연동서버에서 response받은 파일 해쉬값
    private String resTsaHashValue = "";
    
    /* 메일발송용 데이터 */
    private String customerName;
    private String customerEmail;

    
    public String getCustomerRegNo() {
        return customerRegNo;
    }

    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
    }

    public String getTermsRegNo() {
        return termsRegNo;
    }

    public void setTermsRegNo(String termsRegNo) {
        this.termsRegNo = termsRegNo;
    }

    public String getTermsStartDate() {
        return termsStartDate;
    }

    public void setTermsStartDate(String termsStartDate) {
        this.termsStartDate = termsStartDate;
    }

    public String getTermsPolicy() {
        return termsPolicy;
    }

    public void setTermsPolicy(String termsPolicy) {
        this.termsPolicy = termsPolicy;
    }

    public String getTermsPolicyYear() {
        return termsPolicyYear;
    }

    public void setTermsPolicyYear(String termsPolicyYear) {
        this.termsPolicyYear = termsPolicyYear;
    }

    public String getSignData() {
        return signData;
    }

    public void setSignData(String signData) {
        this.signData = signData;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getReqHtml() {
        return reqHtml;
    }

    public void setReqHtml(String reqHtml) {
        this.reqHtml = reqHtml;
    }

    public String getrValue() {
        return rValue;
    }

    public void setrValue(String rValue) {
        this.rValue = rValue;
    }

    public String getMakeHtmlContent() {
        return makeHtmlContent;
    }

    public void setMakeHtmlContent(String makeHtmlContent) {
        this.makeHtmlContent = makeHtmlContent;
    }

    public String getMakePdfContent() {
        return makePdfContent;
    }

    public void setMakePdfContent(String makePdfContent) {
        this.makePdfContent = makePdfContent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameOriginal() {
        return fileNameOriginal;
    }

    public void setFileNameOriginal(String fileNameOriginal) {
        this.fileNameOriginal = fileNameOriginal;
    }

    public String getFileNmMarkOriginal() {
        return fileNmMarkOriginal;
    }

    public void setFileNmMarkOriginal(String fileNmMarkOriginal) {
        this.fileNmMarkOriginal = fileNmMarkOriginal;
    }

    public String getResTsaHashValue() {
        return resTsaHashValue;
    }

    public void setResTsaHashValue(String resTsaHashValue) {
        this.resTsaHashValue = resTsaHashValue;
    }

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
    
}