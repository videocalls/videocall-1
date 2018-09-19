package kr.co.koscom.oppf.apt.mockup.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;

import java.util.List;

/**
 * Created by LSH on 2017. 2. 10..
 */

@Data
@SuppressWarnings("serial")
public class MockUpVO extends ComDefaultListVO {


    private String mockupServiceNumber;         //목업 서비스 등록번호
    private String companyCodeId;               //기업코드
    private String mockupUri;                   //URI정보
    private String title;                       //제목
    private String headerInfo;                  //헤더정보
    private String methodType;                  //메소드방식
    private String deleteDate;                  //삭제일시
    private String queryStringInfo;             //쿼리 스트링 정보
    private String bodyInfo;                    //바디정보
    private String httpStatus;                  //HTTP Status
    private String responseMessage;             //응답 메세지

    private String mockupSeq;                   //순번(history)

    private String companyNameKorAlias;         //서비스제공자(한글기업이름)
    private String httpStatusName;                 //httpStatusName(공통코드 한글이름)



    /**
     * 검색조건
     * */
    private String searchCondition;             //키워드 검색 구분
    private String searchKeyword;               //키워드

    private List<String> searchPubcompanyCodeId;      //서비스제공자 검색
    private String searchPubcompanyCodeIdAllYn;

    private List<String> searchMethod;                //메소드 검색
    private String searchMethodAllYn;

    private List<String> searchHttpStatus;            //http
    private String searchHttpStatusAllYn;

    private String searchDateType;              //검색날짜 타입 : 등록일, 수정일
    private String searchDateFrom;              //작날짜
    private String searchDateTo;                //끝날짜

}
