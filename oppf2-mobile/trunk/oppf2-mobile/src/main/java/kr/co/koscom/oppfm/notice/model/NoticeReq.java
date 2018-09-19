package kr.co.koscom.oppfm.notice.model;

import kr.co.koscom.oppfm.cmm.model.CommonSearchReq;
import lombok.Data;

/**
 * NoticeReq
 * <p>
 * Created by Yoojin Han on 2017-04-20.
 */

@Data
public class NoticeReq  extends CommonSearchReq{

    private static final long serialVersionUID = -3098496399867839957L;

    private String searchKeyword;                   // 검색어
    private String searchType;                      // 검색조건 ('G040' + '001~3')
    private String noticeFixYn;                     // 공지사항 고정 여부 ( 'Y' / 'N' )
    private String noticePopYn;                     // 팝업 여부
    
    private String noticeSeq;                       // 공지사항 시퀀스
}
