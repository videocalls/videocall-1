package kr.co.koscom.oppf.cmm.service;

import java.io.Serializable;

/**
 * ComAbstractDAO.java 클래스
 * 
 * @author 이환덕
 * @since 2016. 04. 20.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일                      수정자        수정내용
 *  -------------  ------  ----------------------
 *  2016. 04. 20.  이환덕        최초 생성
 * </pre>
 */
@SuppressWarnings("serial")
public class ComDefaultListVO implements Serializable {

    /* 디폴트관련 */
    private String useYn = "Y";     //사용여부(Y:사용, N:사용안함) ※sample=쓰임,공통게시판=사용안함 추후 제거
    private String deleteDate = ""; //삭제일
    private String createDate = ""; //생성일
    private String createId = "";   //생성자ID
    private String updateDate = ""; //수정일
    private String updateId = "";   //수정자ID
    private int readCount = 0;      //조회수
    private String userId = "";     //사용자ID
    
    /* 페이징관련 */
    private int pageTotalcount = 0; //총갯수
    private int pageStart = 0;      //mysql limit쿼리 사용을 위한 페이지 시작위치
    private int pageIndex = 1;      //현재페이지번호
    private int pageRowsize = 10;   //목록사이즈
    private int pageBlocksize = 10; //페이징블록사이즈
    private String listOrder = "a.create_date desc"; //페이징목록정렬
    private String listFixOrder = "create_time desc"; //FIX 페이징목록정렬
    
    /* 이전글다음글 관련 */
    private String beforeId=""; //이전글ID
    private String beforeSj=""; //이전글명
    private String afterId="";  //다음글ID
    private String afterSj="";  //다음글명
    
    /* 조회관련 */
    private String searchCondition = ""; //검색조건
    private String searchKeyword = ""; //검색Keyword
    
    /* 엑셀관련 */
    private String excelYn = "N";
    //excel type = excel : 엑셀, down: 다운
  	private String excelType;
    
    /* 첨부파일 관련 */
    private String callBackFunc;
    
    /* return List URL */
	private String returnListURL = "";
	/* 검색조건들 */
	private String searchKeys = "";
	/* ssl 처리여부 [Y: https처리, N or null: http처리] */
	private String returnHttpsURLFlag = "";
    
    /**
     * 페이지 설정
     */
    public void setPageData(){
    	int pageIndex = this.getPageIndex();
    	
    	//페이징 셋팅
        if(pageIndex == 1){
        	this.setPageStart(0);
        }else{
        	int pageRowsize = this.getPageRowsize();
        	int pageStart = pageRowsize * ( pageIndex -1 );
        	
        	this.setPageStart(pageStart);
        }
    }
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUseYn() {
        return useYn;
    }
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    public String getDeleteDate() {
        return deleteDate;
    }
    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getCreateId() {
        return createId;
    }
    public void setCreateId(String createId) {
        this.createId = createId;
    }
    public String getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    public String getUpdateId() {
        return updateId;
    }
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
    public int getReadCount() {
        return readCount;
    }
    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }
    public int getPageTotalcount() {
        return pageTotalcount;
    }
    public void setPageTotalcount(int pageTotalcount) {
        this.pageTotalcount = pageTotalcount;
    }
    public int getPageStart() {
        return pageStart;
    }
    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }
    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        
        //페이징 셋팅
        this.setPageData();
    }
    public int getPageRowsize() {
        return pageRowsize;
    }
    public void setPageRowsize(int pageRowsize) {
        this.pageRowsize = pageRowsize;
        
        //페이징 셋팅
        this.setPageData();
    }
    public int getPageBlocksize() {
        return pageBlocksize;
    }
    public void setPageBlocksize(int pageBlocksize) {
        this.pageBlocksize = pageBlocksize;
    }
    public String getListOrder() {
        return listOrder;
    }
    public void setListOrder(String listOrder) {
        this.listOrder = listOrder;
    }
    public String getBeforeId() {
        return beforeId;
    }
    public void setBeforeId(String beforeId) {
        this.beforeId = beforeId;
    }
    public String getBeforeSj() {
        return beforeSj;
    }
    public void setBeforeSj(String beforeSj) {
        this.beforeSj = beforeSj;
    }
    public String getAfterId() {
        return afterId;
    }
    public void setAfterId(String afterId) {
        this.afterId = afterId;
    }
    public String getAfterSj() {
        return afterSj;
    }
    public void setAfterSj(String afterSj) {
        this.afterSj = afterSj;
    }
    public String getSearchCondition() {
        return searchCondition;
    }
    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }
    public String getSearchKeyword() {
        return searchKeyword;
    }
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
	public String getExcelYn() {
		return excelYn;
	}
	public void setExcelYn(String excelYn) {
		this.excelYn = excelYn;
	}
	public String getExcelType() {
		return excelType;
	}

	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}

	public String getCallBackFunc() {
		return callBackFunc;
	}

	public void setCallBackFunc(String callBackFunc) {
		this.callBackFunc = callBackFunc;
	}

	public String getReturnListURL() {
		return returnListURL;
	}

	public void setReturnListURL(String returnListURL) {
		this.returnListURL = returnListURL;
	}

	public String getSearchKeys() {
		return searchKeys;
	}

	public void setSearchKeys(String searchKeys) {
		this.searchKeys = searchKeys;
	}

	public String getReturnHttpsURLFlag() {
		return returnHttpsURLFlag;
	}

	public void setReturnHttpsURLFlag(String returnHttpsURLFlag) {
		this.returnHttpsURLFlag = returnHttpsURLFlag;
	}	
	
}