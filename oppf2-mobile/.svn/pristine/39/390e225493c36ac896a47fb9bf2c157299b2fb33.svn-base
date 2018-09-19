package kr.co.koscom.oppfm.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.notice.dao.NoticeMapper;
import kr.co.koscom.oppfm.notice.model.NoticeFileRes;
import kr.co.koscom.oppfm.notice.model.NoticeReq;
import kr.co.koscom.oppfm.notice.model.NoticeRes;
import kr.co.koscom.oppfm.notice.service.NoticeService;

/**
 * NoticeServiceImpl
 * <p>
 * Created by Yoojin Han on 2017-04-20.
 */

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private NoticeMapper noticeMapper;

    
    /**
     * 공지사항 리스트 개수 조회 getNotiListTotalCount
     */
    @Override
    public int getNotiListTotalCount(NoticeReq noticeReq) {
        return noticeMapper.selectNotiListTotalCount(noticeReq);
    }

    
    /**
     * 공지사항 리스트 조회 getNotiList
     */
    @Override
    public CommonResponse getNotiList(NoticeReq noticeReq) {

        Map<String, Object> body = new HashMap<>();

        int totalCount = noticeMapper.selectNotiListTotalCount(noticeReq);
        int size = 0;
        
        // 팝업 리스트 조회
        if ("Y".equals(noticeReq.getNoticePopYn())) {
            
            List<NoticeRes> noticePopList = noticeMapper.selectNotiList(noticeReq);
            body.put("noticePopList", noticePopList);
            size = noticePopList.size();
            
        } else {// 전체리스트 조회
            if( OppfStringUtil.isEmpty(noticeReq.getSearchKeyword()) ){
                noticeReq.setNoticeFixYn("N");
            }

            List<NoticeRes> noticeList = noticeMapper.selectNotiList(noticeReq);
            body.put("noticeList", noticeList);
            size = noticeList.size();
            
            if ((noticeReq.getPage()).intValue() <= 1) {
                List<NoticeRes> noticeFixList = noticeMapper.selectNotiListFix(noticeReq);
                body.put("noticeFixList", noticeFixList);
            }
        }
        
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body,
                noticeReq.getPageInfo(), totalCount, size);
    }

    
    /**
     * 공지사항 상세 조회 getNotiDetail
     */
    @Override
    @Transactional
    public CommonResponse getNotiDetail(NoticeReq noticeReq) {
        
        CommonResponse response = new CommonResponse();
        Map<String, Object> body = new HashMap<>();

        // 조회수 증가
        int rs = noticeMapper.updateNotiReadCount(noticeReq);
        
        if(rs == 1){
            NoticeRes noticeDetail = noticeMapper.selectNotiDetail(noticeReq);

            if (noticeDetail == null) {
                throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION, new String[] { "NoticeSeq" });
            }

            // 파일 id가 있을 경우 조회
            List<NoticeFileRes> fileList = null;
            if (noticeDetail != null) {
                String fileId = noticeDetail.getFileId();
                fileList = noticeMapper.selectNotiDetailFileList(fileId);
            }

            body.put("noticeDetail", noticeDetail);
            body.put("fileList", fileList);
            
            response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
            
        } else{
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION,new String[] {"notice Seq"});
        }
         
        return response;
    }


}
