package kr.co.koscom.oppfm.intro.model;

import lombok.Data;

/**
 * FileName : IntroFintechAppRes
 *
 * Description : App 소개 Response
 *
 * Created by LSH on 2017. 4. 20..
 */
@Data
public class IntroFintechAppRes {
    //핀테크 서비스 정보
    private String appId;           // 서비스 기업 출력 Key
    private String companyCodeId;   // 기업코드아이디
    private String appName;         // 서비스 이름
    private String appCategory;     // 카테고리코드
    private String appCategoryName; // 카테고리이름
    private String appDescription;  // 핀테크서비스 설명
    private String appDlUrl;        // 핀테크서비스 URL

    private String companyNameKorAlias;     //기업이름
    private String apiId;                   //apiId
    private String apiTitle;                //apiTitle
}
