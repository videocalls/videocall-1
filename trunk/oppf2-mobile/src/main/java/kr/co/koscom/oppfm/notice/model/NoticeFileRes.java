package kr.co.koscom.oppfm.notice.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * NoticeFileRes
 * <p>
 * Created by Yoojin Han on 2017-04-20.
 */

@Data
public class NoticeFileRes extends CommonVO {
    
    private static final long serialVersionUID = -2384267318744058348L;
    
    private String fileId;                          // 첨부파일 아이디
    private String fileSeq;                         // 첨부파일 시퀀스
    private String fileName;                        // 첨부파일 이름
    private String fileExtention;                   // 첨부파일 확장자
    private int fileSize;                           // 첨부파일 사이즈
//    private byte[] fileData;                        // 첨부파일 data
}
