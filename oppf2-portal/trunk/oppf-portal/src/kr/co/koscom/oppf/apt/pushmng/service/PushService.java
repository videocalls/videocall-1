package kr.co.koscom.oppf.apt.pushmng.service;

import java.util.List;
import java.util.Map;

import kr.co.koscom.oppf.apt.mockup.service.MockUpVO;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegVO;

public interface PushService {
	
    /**
     * @Method Name        : selectPushList
     * @Method description : Push 목록을 조회한다.
     * @param              : PushVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
	 Map<String, Object> selectPushList(PushVO pushVO) throws Exception ;
	 
	 /**
	  * @Method Name        : selectPushListExcel
	     * @Method description : Push Excel 목록을 조회한다.
	     * @param              : PushVO
	     * @return             : Map<String, Object>
	     * @throws             : Exception
	     */
	Map<String, Object> selectPushListExcel(PushVO pushVO) throws Exception ;
	
    /**
     * @Method Name        : selectDeviceTypeList
     * @Method description : 플랫폼 목록을 조회한다.
     * @param              : PushVO
     * @return             : List<PushVO>
     * @throws             : Exception
     */
	 List<PushVO> selectDeviceTypeList(PushVO pushVO) throws Exception ;
	
    /**
     * @Method Name        : insertPush
     * @Method description : push 메세지를 추가한다.
     * @param              : PushVO
     * @return             : int
     * @throws             : Exception
     */
	String insertPush(PushVO pushVO) throws Exception ;
    
    /**
     * @Method Name        : selectPushDtl
     * @Method description : Push 상세 조회한다.
     * @param              : PushVO
     * @return             : PushVO
     * @throws             : Exception
     */
    PushVO selectPushDtl(PushVO pushVO) throws Exception;
    
    
	/**
	 * @Method Name 		: updatePush
	 * @Method description  : Push 수정한다.
	 * @param 				:
	 * @return 				: int
	 * @throws 				: Exception
	 */
	public int updatePush(PushVO pushVO) throws Exception;


}
