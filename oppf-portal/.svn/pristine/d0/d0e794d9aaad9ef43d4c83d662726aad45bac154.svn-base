package kr.co.koscom.oppf.spt.usr.mbrReg.service;

import java.io.Serializable;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;

/**
 * NiceRes
 * <p>
 * Created by P.K Choi on 2017-05-11.
 */

@SuppressWarnings("serial")
public class NiceResVO  extends ComDefaultListVO{

	
	// 인증결과코드, 응답코드
    private String returnCode;
    
    // 요청 고유번호
    private String requestSEQ;                  
    
    // 응답 고유번호
    private String responseSEQ;                 
    
    // 인증 완료시간
    private String confirmDateTime;
    
    // 아이핀 연결정보(CI)
    private String responseCI;                   
    
    // 아이핀 중복가입확인정보(DI)
    private String responseDI;       

    // 응답 고유번호 (CPMobileStep1 에서 확인된 cpMobile.getResponseSEQ() 데이타)
    private String sresSeq;
    
	// 요청 고유번호 (CPMobileStep1 에서 정의한 cpMobile.getRequestSEQ() 데이타)
    private String scpRequest;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getRequestSEQ() {
		return requestSEQ;
	}

	public void setRequestSEQ(String requestSEQ) {
		this.requestSEQ = requestSEQ;
	}

	public String getResponseSEQ() {
		return responseSEQ;
	}

	public void setResponseSEQ(String responseSEQ) {
		this.responseSEQ = responseSEQ;
	}

	public String getConfirmDateTime() {
		return confirmDateTime;
	}

	public void setConfirmDateTime(String confirmDateTime) {
		this.confirmDateTime = confirmDateTime;
	}

	public String getResponseCI() {
		return responseCI;
	}

	public void setResponseCI(String responseCI) {
		this.responseCI = responseCI;
	}

	public String getResponseDI() {
		return responseDI;
	}

	public void setResponseDI(String responseDI) {
		this.responseDI = responseDI;
	}

	public String getSresSeq() {
		return sresSeq;
	}

	public void setSresSeq(String sresSeq) {
		this.sresSeq = sresSeq;
	}

	public String getScpRequest() {
		return scpRequest;
	}

	public void setScpRequest(String scpRequest) {
		this.scpRequest = scpRequest;
	}
    
}
