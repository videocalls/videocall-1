package kr.co.koscom.oppfm.app.model;

import kr.co.koscom.oppfm.cmm.model.CommonSearchReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AppSearchReq
 * <p>
 * Created by chungyeol.kim on 2017-05-11.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppSearchReq extends CommonSearchReq{
	/*App 목록과 상세조회에 사용되는 Req*/
	private static final long serialVersionUID = -7515362089621777207L;
	
	private String type=""; //
	
	private String customerRegNo; //로그인 여부 체크
	
	private String appId; //app id(상세조회에 사용)
	
	private String searchAppCategory; //app구분(공통코드.G025)-시세:001, 뉴스:002, 주문:003, 거래종목:004, 잔고현황:005, 포트폴리오:006
	
	private String searchCondition;  //검색조건-all:전체, appName:app 이름, companyName:기업이름
	
	private String searchKeyword; //검색 키워드




}
