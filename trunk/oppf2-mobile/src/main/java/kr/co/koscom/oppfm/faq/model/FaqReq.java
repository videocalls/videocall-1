package kr.co.koscom.oppfm.faq.model;

import kr.co.koscom.oppfm.cmm.model.CommonSearchReq;
import lombok.Data;

/**
 * FaqSearchVO
 * <p>
 * Created by hanbyul.lee on 2017-04-24.
 */
@Data
public class FaqReq extends CommonSearchReq{
	
	private static final long serialVersionUID = -105966215822013763L;

    private String searchType;            // 검색조건 ('G040'+ '001~3')
    private String searchKeyword;         // 검색어
    private String faqSeq;                //faq 시퀀스
}
