package kr.co.koscom.oppfm.push.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * PushMessageReq
 * <p>
 * Created by Yoojin Han on 2017-05-19.
 */

@Data
public class PushMessageReq extends CommonVO{


    private static final long serialVersionUID = 1721962793951055670L;
    
    private String customerRegNo;					/*receiver regNo*/
    private String pushMessageRegNo;        /* push 메세지 등록번호 */
    private String pushMessageTitle;        /* push 메세지 제목 */
    private String pushMessageType;         /* push 메세지 구분 */
    private String pushContents;            /* 메세지 내용 */
    private String pushContentsUrl;         /* 메세지 url */
    private String deviceType;					/*deviceType*/
    private String sendYn;						/*전송여부*/
    private String sendDate;
	private String pushMessageReciverNo;
    
}
