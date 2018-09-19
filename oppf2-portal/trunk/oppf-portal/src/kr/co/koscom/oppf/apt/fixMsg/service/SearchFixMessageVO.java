package kr.co.koscom.oppf.apt.fixMsg.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;

import java.util.List;

/**
 * Created by LSH on 2017. 3. 2..
 */
@Data
@SuppressWarnings("serial")
public class SearchFixMessageVO extends ComDefaultListVO{
    /**
     * search
     * */

    private List<String> searchMessageType;  //msgType
    private String searchMessageTypeAllYn;   //msgType All Sellect

    private List<String> rejectYn;           //주문거부여부AcceptorValidation-> Acceptor만
    private String rejectYnAllYn;            //주문거부여부 All Sellect

    private List<String> hubType;
    private String hubTypeAllYn;          //hubType All Sellect

    private String searchDateFrom;              //시작날짜
    private String searchDateTo;                //끝날짜
    private String searchTimeHourFrom;
    private String searchTimeMinuteFrom;
    private String searchTimeHourTo;
    private String searchTimeMinuteTo;

    private String searchDateTimeFrom;          // yyyy-MM-dd HH:mm(begin)
    private String searchDateTimeTo;            // yyyy-MM-dd HH:mm(end)

    /**
     * CommonCode
     * */
    private String commonCode;
    private String commonCodeName;
    private String commonCodeId;

    /**
     * tabDivision ( Acceptor or Initiator )
     * */

    private String tabDivision;

}
