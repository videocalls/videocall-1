package kr.co.koscom.oppfm.cmm.util;

import kr.co.koscom.oppfm.cmm.model.CommonHeader;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.PageInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * CommonResponseUtil
 * <p>
 * Created by chungyeol.kim on 2017-04-21.
 */
public class CommonResponseUtil {

    public static CommonResponse commonResponseData(MessageUtil messageUtil, String messageId, Object[] argsMessage) {

        String []messageArray = messageUtil.getMessage(messageId, argsMessage).split(";");
        String statusCode = messageArray[0];
        String message = null;
        String description = null;
        if(messageArray.length >= 2) {
            message = messageArray[1];
            if(messageArray.length >= 3) {
                description = messageArray[2];
            }
        }

        CommonHeader commonHeader = new CommonHeader();
        commonHeader.setMessage(message);
        commonHeader.setCode(messageId);
//        commonHeader.setId(statusCode);
        commonHeader.setDescription(description);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setHeader(commonHeader);

        return commonResponse;
    }

    /**
     *  pageInfo 가 필요한 ResponseType
     * @param messageUtil
     * @param messageId
     * @param argsMessage
     * @param body
     * @param pageInfo
     * @param totalCount
     * @param resultCount
     * @return
     */
    public static CommonResponse commonResponseData(MessageUtil messageUtil, String messageId, Object[] argsMessage,
                                                    Map<String,Object> body, PageInfo pageInfo, int totalCount, int resultCount) {

        CommonResponse commonResponse = commonResponseData(messageUtil, messageId, argsMessage);

        if(body == null) {
            body = new HashMap<>();
        }

        if(pageInfo != null) {
            pageInfo.setResultCount(resultCount);
            pageInfo.setTotalCount(totalCount);
            body.put("pageInfo", pageInfo);
        }

        commonResponse.setBody(body);

        return commonResponse;
    }

    /**
     * pageInfo 가 필요없는 Response Type
     * @param messageUtil
     * @param messageId
     * @param argsMessage
     * @param body
     * @return
     */
    public static CommonResponse commonResponseData(MessageUtil messageUtil, String messageId, Object[] argsMessage, Map<String,Object> body) {

        CommonResponse commonResponse = commonResponseData(messageUtil, messageId, argsMessage);

        if(body == null) {
            body = new HashMap<>();
        }

        commonResponse.setBody(body);

        return commonResponse;
    }
}
