package kr.co.koscom.oppf.cmm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.cmm.service.CmmFileService;
import kr.co.koscom.oppf.cmm.service.CmmFileVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmFileServiceImpl.java
* @Comment  : file 공통기능을 제공하는 serviceImpl
* @author   : 신동진
* @since    : 2016.06.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.02  신동진        최초생성
*
*/
@Service("CmmFileService")
public class CmmFileServiceImpl implements CmmFileService{
    
    @Resource(name = "CmmFileDAO")
    private CmmFileDAO cmmFileDAO;
    
    
    /**
     * @Method Name        : selectAppImg
     * @Method description : app Img를 가져온다.
     * @param              : CmmFileVO
     * @return             : CmmFileVO
     * @throws             : Exception
     */
    public CmmFileVO selectAppImg(CmmFileVO cmmFileVO) throws Exception{
    	return cmmFileDAO.selectAppImg(cmmFileVO);
    }
    
    /**
     * @Method Name        : selectCompanyCi
     * @Method description : company ci Img를 가져온다.
     * @param              : CmmFileVO
     * @return             : CmmFileVO
     * @throws             : Exception
     */
    public CmmFileVO selectCompanyCi(CmmFileVO cmmFileVO) throws Exception{
    	return cmmFileDAO.selectCompanyCi(cmmFileVO);
    }
    
    /**
     * @Method Name        : selectNotiFileDown
     * @Method description : 공지사항 첨부파일을 가져온다.
     * @param              : CmmFileVO
     * @return             : CmmFileVO
     * @throws             : Exception
     */
    public CmmFileVO selectNotiFileDown(CmmFileVO cmmFileVO) throws Exception{
    	return cmmFileDAO.selectNotiFileDown(cmmFileVO);
    }
    
}
