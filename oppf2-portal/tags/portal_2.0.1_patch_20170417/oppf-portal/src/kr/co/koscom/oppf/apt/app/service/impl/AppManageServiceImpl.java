package kr.co.koscom.oppf.apt.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.app.service.AppManageService;
import kr.co.koscom.oppf.apt.app.service.AppManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AppManageServiceImpl.java
* @Comment  : app 관리를 위한 Service
* @author   : 신동진
* @since    : 2016.05.30
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.30  신동진        최초생성
*
*/
@Service("AppManageService")
public class AppManageServiceImpl implements AppManageService{
    
    @Resource(name = "AppManageDAO")
    private AppManageDAO appManageDAO;
    
    /**
     * @Method Name        : selectAppManageList
     * @Method description : app 목록을 조회한다.
     * @param              : AppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectAppManageList(AppManageVO appManageVO) throws Exception{
    	List<AppManageVO> result = appManageDAO.selectAppManageList(appManageVO);
    	int totCnt = appManageDAO.selectAppManageListTotalCount(appManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectAppManageListExcel
     * @Method description : app excel 목록을 조회한다.
     * @param              : AppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectAppManageListExcel(AppManageVO appManageVO) throws Exception{
    	List<AppManageVO> result = appManageDAO.selectAppManageListExcel(appManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectAppManageDtl
     * @Method description : app 상세정보를 조회한다.
     * @param              : AppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public AppManageVO selectAppManageDtl(AppManageVO appManageVO) throws Exception{
    	return appManageDAO.selectAppManageDtl(appManageVO);
    }
    
    /**
     * @Method Name        : selectAppManageDtlApiList
     * @Method description : app 상세의 서비스제공자 목록을 조회한다. 
     * @param              : AppManageVO
     * @return             : List<AppManageVO>
     * @throws             : Exception
     */
    public List<AppManageVO> selectAppManageDtlApiList(AppManageVO appManageVO) throws Exception{
    	return appManageDAO.selectAppManageDtlApiList(appManageVO);
    }
    
    /**
     * @Method Name        : saveAppManage
     * @Method description : app 저장
     * @param              : AppManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveAppManage(AppManageVO appManageVO) throws Exception{
    	/*
    	File file1 = new File("C:\\img\\google.png");
    	
    	InputStream is1 = new FileInputStream(file1);
    	
    	long length1 = file1.length();
    	
    	byte[] file1Byte = new byte[(int) length1];
    	
    	int offset = 0;
    	int numRead = 0;
    	
    	//-------------------------------------------------------------------------------------
    	while(offset < file1Byte.length && (numRead=is1.read(file1Byte, offset, file1Byte.length-offset)) >= 0){
    		offset += numRead;
    	}
    	
    	appManageVO.setAppIcon(file1Byte);
    	
    	is1.close();
    	//-------------------------------------------------------------------------------------
    	*/  	    	
    	
    	int saveCnt = appManageDAO.selectAppManageChkCnt(appManageVO);
    	int result = 0;
    	
    	//insert
    	if(saveCnt <= 0){
    		result = appManageDAO.insertAppManage(appManageVO);
    	}else{
    		result = appManageDAO.updateAppManage(appManageVO);
    	}
    	result = appManageDAO.insertAppManageHist(appManageVO);
    	
    	return result;
    }
    
}
