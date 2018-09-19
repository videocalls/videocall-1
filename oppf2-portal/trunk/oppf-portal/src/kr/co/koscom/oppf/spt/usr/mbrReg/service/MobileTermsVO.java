package kr.co.koscom.oppf.spt.usr.mbrReg.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
 * ClassName : MobileTerms
 * *
 * Description :
 * <p>
 * Created by  on 2017. 5. 18..
 */
@SuppressWarnings("serial")
public class MobileTermsVO  extends ComDefaultListVO{

    private String mobileTermsType;							/* 약관 종류 */
    private String mobileTermsContent;						/* 약관 내용 */
    private String mobileTermsSubject;						/* 약관 제목 */
    
	public String getMobileTermsType() {
		return mobileTermsType;
	}
	public void setMobileTermsType(String mobileTermsType) {
		this.mobileTermsType = mobileTermsType;
	}
	public String getMobileTermsContent() {
		return mobileTermsContent;
	}
	public void setMobileTermsContent(String mobileTermsContent) {
		this.mobileTermsContent = mobileTermsContent;
	}
	public String getMobileTermsSubject() {
		return mobileTermsSubject;
	}
	public void setMobileTermsSubject(String mobileTermsSubject) {
		this.mobileTermsSubject = mobileTermsSubject;
	}


}
