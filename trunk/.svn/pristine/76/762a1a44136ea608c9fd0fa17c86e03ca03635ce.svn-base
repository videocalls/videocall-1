package kr.co.koscom.oppfm.nice.model;

import java.io.Serializable;

import lombok.Data;

/**
 * NiceRes
 * <p>
 * Created by P.K Choi on 2017-05-11.
 */

@Data
public class NiceRes implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6997646881393819002L;

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
    private String sResSeq;
    
	// 요청 고유번호 (CPMobileStep1 에서 정의한 cpMobile.getRequestSEQ() 데이타)
    private String sCPRequest;
    
    
    
}
