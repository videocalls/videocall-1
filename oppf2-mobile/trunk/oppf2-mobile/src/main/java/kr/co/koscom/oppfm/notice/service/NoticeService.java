package kr.co.koscom.oppfm.notice.service;

import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.notice.model.NoticeReq;

/**
 * NoticeService
 * <p>
 * Created by Yoojin Han on 2017-04-20.
 */

public interface NoticeService {

    /**
     * 공지사항 리스트 개수 조회 getNotiListTotalCount
     */
    int getNotiListTotalCount(NoticeReq noticeReq);

    /**
     * 공지사항 리스트 조회 getNotiList
     */
    CommonResponse getNotiList(NoticeReq noticeReq);

    /**
     * 공지사항 상세 조회 getNotiDetail
     */
    CommonResponse getNotiDetail(NoticeReq noticeReq);

}
