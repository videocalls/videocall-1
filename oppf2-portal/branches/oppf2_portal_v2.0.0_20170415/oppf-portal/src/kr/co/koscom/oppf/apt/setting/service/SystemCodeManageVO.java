package kr.co.koscom.oppf.apt.setting.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SystemCodeManageVO.java
* @Comment  : 관리자의 공통 관리를 위한 VO
* @author   : 신동진
* @since    : 2016.05.16
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class SystemCodeManageVO extends ComDefaultListVO{
	/**
	 * com_system_grp_code
	 */
	//코드 그룹 ID
	private String systemGrpCode;
	//코드 설명
	private String systemGrpCodeDesc;
	//유효 시작 일시
	private String vldStartDate;
	//유효 종료 일시
	private String vldExpireDate;
	//사용여부
	private String useYn;
	
	/**
	 * com_system_code
	 */
	//코드 ID
	private String systemCode;
	//코드 명 한글
	private String codeNameKor;
	//코드 명 영문
	private String codeNameEng;
	//코드 표시 순번
	private String codeSeq;
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
	
	/**
	 * 조회
	 */
	private String treeParentCode;
	private String treeCode;
	private String treeLvl;
	private String codeName;
	
	public String getSystemGrpCode() {
		return systemGrpCode;
	}
	public void setSystemGrpCode(String systemGrpCode) {
		this.systemGrpCode = systemGrpCode;
	}
	public String getSystemGrpCodeDesc() {
		return systemGrpCodeDesc;
	}
	public void setSystemGrpCodeDesc(String systemGrpCodeDesc) {
		this.systemGrpCodeDesc = systemGrpCodeDesc;
	}
	public String getVldStartDate() {
		return vldStartDate;
	}
	public void setVldStartDate(String vldStartDate) {
		this.vldStartDate = vldStartDate;
	}
	public String getVldExpireDate() {
		return vldExpireDate;
	}
	public void setVldExpireDate(String vldExpireDate) {
		this.vldExpireDate = vldExpireDate;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getCodeNameKor() {
		return codeNameKor;
	}
	public void setCodeNameKor(String codeNameKor) {
		this.codeNameKor = codeNameKor;
	}
	public String getCodeNameEng() {
		return codeNameEng;
	}
	public void setCodeNameEng(String codeNameEng) {
		this.codeNameEng = codeNameEng;
	}
	public String getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(String codeSeq) {
		this.codeSeq = codeSeq;
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
	public String getTreeParentCode() {
		return treeParentCode;
	}
	public void setTreeParentCode(String treeParentCode) {
		this.treeParentCode = treeParentCode;
	}
	public String getTreeCode() {
		return treeCode;
	}
	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}
	public String getTreeLvl() {
		return treeLvl;
	}
	public void setTreeLvl(String treeLvl) {
		this.treeLvl = treeLvl;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}	
}