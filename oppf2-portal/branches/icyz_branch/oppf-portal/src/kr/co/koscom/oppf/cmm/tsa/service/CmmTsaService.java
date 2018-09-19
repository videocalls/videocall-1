package kr.co.koscom.oppf.cmm.tsa.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsFileProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsProfileVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmTsaService.java
* @Comment  : [공통회원동의서TSA연계]정보관리를 위한 Service 인터페이스
* @author   : 포털 이환덕
* @since    : 2016.06.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.09  이환덕        최초생성
*
*/
public interface CmmTsaService{
    
   /**
     * @Method Name        : procTsa
     * @Method description : TSA처리
     * @param              : CmmTsaVO
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
   @Transactional
   public HashMap<String,Object> procTsa(CmmTsaVO cmmTsaVO)throws Exception;
    
   /**
     * @Method Name        : mkDirTsa
     * @Method description : TSA처리를 위한 임시 폴더를 생성
     * @param              : folderPathName
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
   public HashMap<String, Object> mkDirTsa(String folderPathName);
    
   /**
     * @Method Name        : rmDirTsa
     * @Method description : TSA처리를 위한 임시 폴더를 삭제
     * @param              : folderPathFileName
     * @return             : void
     * @throws             : Exception
     */
   public void rmDirTsa(String folderPathFileName);
    
   /**
     * @Method Name        : mkHtml
     * @Method description : String reqHtml 을 html파일 make
     * @param              : fileFullPathName, makeHtmlContent
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
   public HashMap<String, Object> mkHtml(String fileFullPathName, String makeHtmlContent);
   
   /**
    * @Method Name        : mkTxt
    * @Method description : String makeContent 을 txt파일 make
    * @param              : fileFullPathName, makeContent
    * @return             : HashMap<String,Object>
    * @throws             : Exception
    */
  public HashMap<String, Object> mkTxt(String fileFullPathName, String makeContent);
    
   /**
     * @Method Name        : htmlCovertPdf
     * @Method description : html파일을 pdf로 파일변환
     * @param              : fileFullPathName, makePdfContent
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
   public HashMap<String, Object> htmlCovertPdf(String fileFullPathName, String makePdfContent);
    
    
   /**
     * @Method Name        : selectSptCustomerServiceTermsFileProfileList
     * @Method description : [일반회원서비스약관파일프로파일]정보를 조회한다.
     * @param              : SptCustomerServiceTermsFileProfileVO
     * @return             : List<SptCustomerServiceTermsFileProfileVO>
     * @throws             : Exception
     */
    public List<SptCustomerServiceTermsFileProfileVO> selectSptCustomerServiceTermsFileProfileList(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception;
   
    /**
     * @Method Name        : selectSptCustomerServiceTermsFileProfile
     * @Method description : [일반회원서비스약관파일프로파일]최신정보를 조회한다.
     * @param              : SptCustomerServiceTermsFileProfileVO
     * @return             : SptCustomerServiceTermsFileProfileVO
     * @throws             : Exception
     */
    public SptCustomerServiceTermsFileProfileVO selectSptCustomerServiceTermsFileProfile(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception;
   
  /**
    * @Method Name        : insertSptCustomerServiceTermsFileProfile
    * @Method description : [일반회원서비스약관파일프로파일]정보를 등록한다.
    * @param              : SptCustomerServiceTermsFileProfileVO
    * @return             : int
    * @throws             : Exception
    */
   @Transactional
   public int insertSptCustomerServiceTermsFileProfile(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception;
   
   /**
     * @Method Name        : updateSptCustomerServiceTermsFileProfile
     * @Method description : [일반회원서비스약관파일프로파일]정보를 수정한다.
     * @param              : SptCustomerServiceTermsFileProfileVO
     * @return             : int
     * @throws             : Exception
     */
   @Transactional
   public int updateSptCustomerServiceTermsFileProfile(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception;
   
  /**
    * @Method Name        : updateSptCustomerServiceTermsProfile
    * @Method description : [일반회원서비스약관프로파일]정보를 수정한다.
    * @param              : SptCustomerServiceTermsProfileVO
    * @return             : int
    * @throws             : Exception
    */
   @Transactional
   public int updateSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO paramVO) throws Exception;
    
}
