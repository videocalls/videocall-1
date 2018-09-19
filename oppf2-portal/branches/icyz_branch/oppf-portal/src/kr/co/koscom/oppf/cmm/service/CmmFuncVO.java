package kr.co.koscom.oppf.cmm.service;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmFuncVO.java
* @Comment  : 공통처리를 위한 VO
* @author   : 신동진
* @since    : 2016.05.27
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.27  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class CmmFuncVO extends ComDefaultListVO
{
	//코드 그룹 ID
	private String system_grp_code;
	//코드 ID
	private String system_code;
	//코드명 - 조회조건
	private String code_name;
	//코드 명 한글
	private String code_name_kor;
	//코드 명 영문
	private String code_name_eng;
	//유효 시작 일시
	private String vld_start_date;
	//유효 종료 일시
	private String vld_expire_date;
	//확장코드 1
	private String codeExtend1;
	//확장코드 2
	private String codeExtend2;
	//확장코드 3
	private String codeExtend3;
	//확장코드 4
	private String codeExtend4;
	//확장코드 5
	private String codeExtend5;
	//사용여부
	private String use_yn;
	//코드 표시 순번
	private int code_seq;
	//삭제일시
	private String delete_date;
	
	/**
	 * 기업코드
	 */
    private String companyProfileRegNo;
    private String companyServiceType;
	private String companyCodeId;
	private String companyNameKor;
	private String companyNameEng;
	private String companyNameKorAlias;
	private String companyNameEngAlias;
	
	private String searchCompanyServiceType;
	
	public String getSystem_grp_code() {
		return system_grp_code;
	}
	public void setSystem_grp_code(String system_grp_code) {
		this.system_grp_code = system_grp_code;
	}
	public String getSystem_code() {
		return system_code;
	}
	public void setSystem_code(String system_code) {
		this.system_code = system_code;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getCode_name_kor() {
		return code_name_kor;
	}
	public void setCode_name_kor(String code_name_kor) {
		this.code_name_kor = code_name_kor;
	}
	public String getCode_name_eng() {
		return code_name_eng;
	}
	public void setCode_name_eng(String code_name_eng) {
		this.code_name_eng = code_name_eng;
	}
	public String getVld_start_date() {
		return vld_start_date;
	}
	public void setVld_start_date(String vld_start_date) {
		this.vld_start_date = vld_start_date;
	}
	public String getVld_expire_date() {
		return vld_expire_date;
	}
	public void setVld_expire_date(String vld_expire_date) {
		this.vld_expire_date = vld_expire_date;
	}
	public String getCodeExtend1() {
		return codeExtend1;
	}
	public void setCodeExtend1(String codeExtend1) {
		this.codeExtend1 = codeExtend1;
	}
	public String getCodeExtend2() {
		return codeExtend2;
	}
	public void setCodeExtend2(String codeExtend2) {
		this.codeExtend2 = codeExtend2;
	}
	public String getCodeExtend3() {
		return codeExtend3;
	}
	public void setCodeExtend3(String codeExtend3) {
		this.codeExtend3 = codeExtend3;
	}
	public String getCodeExtend4() {
		return codeExtend4;
	}
	public void setCodeExtend4(String codeExtend4) {
		this.codeExtend4 = codeExtend4;
	}
	public String getCodeExtend5() {
		return codeExtend5;
	}
	public void setCodeExtend5(String codeExtend5) {
		this.codeExtend5 = codeExtend5;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public int getCode_seq() {
		return code_seq;
	}
	public void setCode_seq(int code_seq) {
		this.code_seq = code_seq;
	}
	public String getDelete_date() {
		return delete_date;
	}
	public void setDelete_date(String delete_date) {
		this.delete_date = delete_date;
	}
	public String getCompanyProfileRegNo() {
		return companyProfileRegNo;
	}
	public void setCompanyProfileRegNo(String companyProfileRegNo) {
		this.companyProfileRegNo = companyProfileRegNo;
	}
	public String getCompanyServiceType() {
		return companyServiceType;
	}
	public void setCompanyServiceType(String companyServiceType) {
		this.companyServiceType = companyServiceType;
	}
	public String getCompanyCodeId() {
		return companyCodeId;
	}
	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}
	public String getCompanyNameKor() {
		return companyNameKor;
	}
	public void setCompanyNameKor(String companyNameKor) {
		this.companyNameKor = companyNameKor;
	}
	public String getCompanyNameEng() {
		return companyNameEng;
	}
	public void setCompanyNameEng(String companyNameEng) {
		this.companyNameEng = companyNameEng;
	}
	public String getCompanyNameKorAlias() {
		return companyNameKorAlias;
	}
	public void setCompanyNameKorAlias(String companyNameKorAlias) {
		this.companyNameKorAlias = companyNameKorAlias;
	}
	public String getCompanyNameEngAlias() {
		return companyNameEngAlias;
	}
	public void setCompanyNameEngAlias(String companyNameEngAlias) {
		this.companyNameEngAlias = companyNameEngAlias;
	}
	public String getSearchCompanyServiceType() {
		return searchCompanyServiceType;
	}
	public void setSearchCompanyServiceType(String searchCompanyServiceType) {
		this.searchCompanyServiceType = searchCompanyServiceType;
	}
	
}
