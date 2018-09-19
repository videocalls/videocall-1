package kr.co.koscom.oppf.apt.pushmng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.apt.pushmng.service.PushService;
import kr.co.koscom.oppf.apt.pushmng.service.PushVO;

@Service("PushService")
public class PushServiceImpl implements PushService {
	
	@Resource(name="PushDAO")
	private PushDAO pushDAO;

	
    /**
     * @Method Name        : selectPushList
     * @Method description : Push 목록을 조회한다.
     * @param              : PushVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */	
	@Override
	public Map<String, Object> selectPushList(PushVO pushVO) throws Exception {
		List<PushVO> result= pushDAO.selectPushList(pushVO);
		int cnt = pushDAO.selectPushListCnt(pushVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("resultList", result);
		map.put("totCnt", cnt);
		
		return map;
	}

    /**
     * @Method Name        : selectDeviceTypeList
     * @Method description : 플랫폼 목록을 조회한다.
     * @param              : PushVO
     * @return             : List<PushVO>
     * @throws             : Exception
     */	
	@Override
	public List<PushVO> selectDeviceTypeList(PushVO pushVO) throws Exception {
		return pushDAO.selectDeviceTypeList(pushVO);

	}

    /**
     * @Method Name        : insertPush
     * @Method description : push 메세지를 추가한다.
     * @param              : PushVO
     * @return             : int
     * @throws             : Exception
     */
	@Override
	public String insertPush(PushVO pushVO) throws Exception {
		String pushMessageRegno = pushDAO.insertPush(pushVO);
		return pushMessageRegno;
	}

    /**
     * @Method Name        : selectPushDtl
     * @Method description : Push 상세 조회한다.
     * @param              : PushVO
     * @return             : PushVO
     * @throws             : Exception
     */
	@Override
	public PushVO selectPushDtl(PushVO pushVO) throws Exception {
		return pushDAO.selectPushDtl(pushVO);
	}

	/**
	 * @Method Name 		: updatePush
	 * @Method description  : Push 수정한다.
	 * @param 				:
	 * @return 				: int
	 * @throws 				: Exception
	 */
	@Override
	public int updatePush(PushVO pushVO) throws Exception {
		int rs=0;
		rs=pushDAO.updatePush(pushVO);		
		return rs;
	}

	 /**
	  * @Method Name        : selectPushListExcel
	     * @Method description : Push Excel 목록을 조회한다.
	     * @param              : PushVO
	     * @return             : Map<String, Object>
	     * @throws             : Exception
	     */
	@Override
	public Map<String, Object> selectPushListExcel(PushVO pushVO) throws Exception {
		List<PushVO> result= pushDAO.selectPushListExcel(pushVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
	}

}
