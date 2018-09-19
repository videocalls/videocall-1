package kr.co.koscom.oppf.apt.fixMsg.service;

import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2017. 3. 2..
 */
@SuppressWarnings("serial")
public interface ViewFixMessageService {

    Map<String,Object> selectFixMessageList(SearchFixMessageVO paramVO) throws Exception;

    Map<String,Object> viewInitFixMessageList(SearchFixMessageVO paramVO) throws Exception;

    Map<String,Object> viewFixMessageAccountExcel(SearchFixMessageVO paramVO) throws Exception;

    Map<String,Object> viewFixMessageInitiatorExcel(SearchFixMessageVO paramVO) throws Exception;

    FixMessageVO viewFixMessageDtlAcceptorPopup(FixMessageVO paramVO) throws Exception;

    FixMessageVO viewFixMessageDtlInitiatorPopup(FixMessageVO paramVO) throws Exception;

    List<SearchFixMessageVO> selectCommonCodeName(SearchFixMessageVO paramVO) throws Exception;
}
