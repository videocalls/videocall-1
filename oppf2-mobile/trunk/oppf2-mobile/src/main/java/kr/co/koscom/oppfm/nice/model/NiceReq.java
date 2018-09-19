package kr.co.koscom.oppfm.nice.model;

import java.io.Serializable;

import lombok.Data;

/**
 * NiceReq
 * <p>
 * Created by P.K Choi on 2017-05-11.
 */

@Data
public class NiceReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6495145849499430346L;

	// 생년월일(8자리)
    private String birthDay;

	// 내국인 / 외국인 ( Y / N )
    private String nationality;

	// 성별 ( M / F )
    private String sex;
    
    // 성명
    private String sName;
    
    // 이통사 구분 (SKT : 1 / KT : 2 / LG : 3 / SKT알뜰폰 : 5 / KT알뜰폰 : 6 / LGU+알뜰폰 : 7)
    private String sMobileCo;
    
    // 휴대폰 번호
    private String sMobileNo;
    
    // 응답 고유번호 (CPMobileStep1 에서 확인된 cpMobile.getResponseSEQ() 데이타)
    private String sResSeq;
    
    // SMS 인증번호
    private String sAuthNo;
	
	// 요청 고유번호 (CPMobileStep1 에서 정의한 cpMobile.getRequestSEQ() 데이타)
    private String sCPRequest;
	
}
