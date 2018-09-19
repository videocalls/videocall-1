package kr.co.koscom.oppfm.account.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;


@Data
public class SptCustomerCompanyTermsProfileRes extends CommonVO{
	private static final long serialVersionUID = -1401926247237551193L;

	/* 일반회원기업약관동의(spt_customer_company_terms_profile) */
	private String customerRegNo;
	private String companyCodeId;
	private String companyTermsType;
	private String companyTermsContentRegSeq;
	private String companyTermsAuthYn;
	private String companyTermsAuthDate;
	
	/* 조회용 */
	private String companyName;
	private String companyTermsContent;

	
}
