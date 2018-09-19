package kr.co.koscom.oppf.cmm.noti.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.noti.service.NotiFileManageVO;
import kr.co.koscom.oppf.cmm.noti.service.NotiManageService;
import kr.co.koscom.oppf.cmm.noti.service.NotiManageVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiManageServiceImpl.java
* @Comment  : 관리자의 공지사항 관리를 위한 Service 클래스
* @author   : 신동진
* @since    : 2016.05.19
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.19  신동진        최초생성
*
*/
@Service("NotiManageService")
public class NotiManageServiceImpl implements NotiManageService{
    
    @Resource(name = "NotiManageDAO")
    private NotiManageDAO notiManageDAO;
    
    /**
     * @Method Name        : selectNotiManageList
     * @Method description : 공지사항 목록을 조회한다.
     * @param              : NotiManageVO
     * @return             : List<NotiManageVO>
     * @throws             : Exception
     */
    public Map<String, Object> selectNotiManageList(NotiManageVO notiManageVO) throws Exception{
    	List<NotiManageVO> result = notiManageDAO.selectNotiManageList(notiManageVO);
    	int totCnt = notiManageDAO.selectNotiManageListTotalCount(notiManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectNotiManageListExcel
     * @Method description : 공지사항 Excel 목록을 조회한다.
     * @param              : NotiManageVO
     * @return             : List<NotiManageVO>
     * @throws             : Exception
     */
    public Map<String, Object> selectNotiManageListExcel(NotiManageVO notiManageVO) throws Exception{
    	List<NotiManageVO> result = notiManageDAO.selectNotiManageList(notiManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectNotiManageDetailFileList
     * @Method description : 공지사항 첨부파일 목록을 조회한다.
     * @param              : NotiManageVO
     * @return             : List<NotiFileManageVO>
     * @throws             : Exception
     */
    public List<NotiFileManageVO> selectNotiManageDetailFileList(NotiManageVO notiManageVO) throws Exception{
    	return notiManageDAO.selectNotiManageDetailFileList(notiManageVO);
    }
    
    /**
     * @Method Name        : selectNotiManageDetail
     * @Method description : 공지사항 상세정보를 조회한다.
     * @param              : NotiManageVO
     * @return             : NotiManageVO
     * @throws             : Exception
     */
    public NotiManageVO selectNotiManageDetail(NotiManageVO notiManageVO) throws Exception{
    	return notiManageDAO.selectNotiManageDetail(notiManageVO);
    }
    
    /**
     * @Method Name        : insertNoti
     * @Method description : 공지사항을 등록한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int insertNoti(NotiManageVO notiManageVO) throws Exception{
    	String fileId = "";
    	//첨부파일 정보가 있을 경우 파일 정보를 셋팅한다.
    	if(notiManageVO.getFileList() != null && notiManageVO.getFileList().size() > 0){
    		//공지 사항 등록이므로 file_id를 생성한다.
    		fileId = notiManageDAO.selectNotiFileId(notiManageVO);
    		
    		List<NotiFileManageVO> fileList = notiManageVO.getFileList();
    		for(int i=0; i<fileList.size(); i++){
    			NotiFileManageVO fileVO = (NotiFileManageVO) fileList.get(i);
    			
    			fileVO.setFileId(fileId);
    			notiManageDAO.insertNotiFileData(fileVO);
    		}
    	}
    	notiManageVO.setFileId(fileId); 
    	return notiManageDAO.insertNoti(notiManageVO);
    }
    
    /**
     * @Method Name        : updateNoti
     * @Method description : 공지사항을 수정한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateNoti(NotiManageVO notiManageVO) throws Exception{
    	String fileId = notiManageVO.getFileId();
    	
    	//삭제파일 정보가 있을 경우 파일을 삭제한다.
    	if(notiManageVO.getDelFileSeq() != null && notiManageVO.getDelFileSeq().size() > 0){
    		List<String> fileList = notiManageVO.getDelFileSeq();
    		for(int i=0; i<fileList.size(); i++){
    			String fileSeq = (String) fileList.get(i);
    			
    			NotiFileManageVO fileVO = new NotiFileManageVO();
    			fileVO.setFileId(fileId);
    			fileVO.setFileSeq(fileSeq);
    			
    			notiManageDAO.deleteNotiFileData(fileVO);
    		}
    	}
    	
    	//첨부파일 정보가 있을 경우 파일 정보를 셋팅한다.
    	if(notiManageVO.getFileList() != null && notiManageVO.getFileList().size() > 0){
    		if(OppfStringUtil.isEmpty(fileId)){
	    		//공지 사항 등록이므로 file_id를 생성한다.
	    		fileId = notiManageDAO.selectNotiFileId(notiManageVO);
	    		//파일을 신규등록일 경우 파일 정보를 셋팅한다.
	    		notiManageVO.setFileId(fileId);
    		}
    		
    		List<NotiFileManageVO> fileList = notiManageVO.getFileList();
    		for(int i=0; i<fileList.size(); i++){
    			NotiFileManageVO fileVO = (NotiFileManageVO) fileList.get(i);
    			
    			fileVO.setFileId(fileId);
    			notiManageDAO.insertNotiFileData(fileVO);
    		}
    	}
    	
    	return notiManageDAO.updateNoti(notiManageVO);
    }
    
    /**
     * @Method Name        : deleteNoti
     * @Method description : 공지사항을 삭제한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int deleteNoti(NotiManageVO notiManageVO) throws Exception{
    	//첨부파일 정보가 있을 경우 메모리 확보를 위해 파일을 삭제 해 준다.
    	if(!OppfStringUtil.isEmpty(notiManageVO.getFileId())){
    		NotiFileManageVO fileVO = new NotiFileManageVO();
			fileVO.setFileId(notiManageVO.getFileId());
			
			notiManageDAO.deleteNotiFileData(fileVO);
    	}
    	
    	return notiManageDAO.deleteNoti(notiManageVO);
    }
}
