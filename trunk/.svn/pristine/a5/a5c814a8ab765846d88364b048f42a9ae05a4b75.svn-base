package kr.co.koscom.oppfm.account.dao;

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import kr.co.koscom.oppfm.account.model.AccountReq
import kr.co.koscom.oppfm.account.model.AccountRes
import kr.co.koscom.oppfm.account.model.ComCompanyProfileReq
import kr.co.koscom.oppfm.account.model.ComCompanyProfileRes
import kr.co.koscom.oppfm.account.model.ComOauthTokenReq
import kr.co.koscom.oppfm.account.model.ComOauthTokenRes
import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileReq
import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileRes
import kr.co.koscom.oppfm.account.model.SvcApplReq
import kr.co.koscom.oppfm.account.model.SvcApplRes
import kr.co.koscom.oppfm.cmm.annotation.Mapper

@Mapper
public interface AccountMapper {

	@Select("""<script>
       select
            a.customer_reg_no                as customerRegNo,                /* 일반회원가상계좌프로파일.회원OP가입번호 */
            a.company_code_id                as companyCodeId,                /* 일반회원가상계좌프로파일.기업코드 */
            dec_char_sel(a.customer_realaccount_no,10,'ARIA','spt_customer_account_profile','costomer_realaccount_no',user(),connection_id()) as customerRealaccountNo, /* 일반회원가상계좌프로파일.실계좌번호 */
            a.customer_vtaccount_no          as customerVtaccountNo,          /* 일반회원가상계좌프로파일.가상계좌번호 */
            a.customer_vtaccount_alias       as customerVtaccountAlias,       /* 일반회원가상계좌프로파일.가상계좌별칭 */
            a.customer_vtaccount_status      as customerVtaccountStatus,      /* 일반회원가상계좌프로파일.가상계좌상태 */
            a.customer_realaccount_type      as customerRealaccountType,      /* 일반회원가상계좌프로파일.실계좌유형 */
            ifnull((select code_name_kor from com_system_code 
             where system_grp_code='G010' and a.customer_realaccount_type = concat('G010',system_code)
             ),'')                           as customerRealaccountTypeNm,    /* 일반회원가상계좌프로파일.실계좌유형명 */
            ifnull((select code_name_eng from com_system_code 
             where system_grp_code='G010' and a.customer_realaccount_type = concat('G010',system_code)
             ),'')                           as customerRealaccountTypeNmEng, /* 일반회원가상계좌프로파일.실계좌유형영문명 */
            ifnull(date_format(a.customer_vtaccount_reg_date,'%Y-%m-%d'),'')    as customerVtaccountRegDate,    /* 일반회원가상계좌프로파일.가상계좌발급일시 */
            ifnull(date_format(a.customer_vtaccount_expire_date,'%Y-%m-%d'),'') as customerVtaccountExpireDate, /* 일반회원가상계좌프로파일.가상계좌폐기일시 */
            a.create_date                    as createDate,                   /* 일반회원가상계좌프로파일.생성일시 */
            a.create_id                      as createId,                     /* 일반회원가상계좌프로파일.생성자ID */
            a.update_date                    as updateDate,                   /* 일반회원가상계좌프로파일.변경일시 */
            a.update_id                      as updateId,                     /* 일반회원가상계좌프로파일.변경자ID */
            c.company_profile_reg_no         as companyCodeRegNo,             /* 기업코드.기업코드등록번호 */
            c.company_name_kor               as companyNameKor,               /* 기업코드.기업이름한글 */
            c.company_name_eng               as companyNameEng,               /* 기업코드.기업이름영문 */
            c.company_name_kor_alias         as companyNameKorAlias,          /* 기업코드.기업이름Alias한글 */
            c.company_name_eng_alias         as companyNameEngAlias           /* 기업코드.기업이름Alias영문 */
			from spt_customer_account_profile a /* 일반회원가상계좌프로파일 */
        left outer join com_company_profile c /* 기업코드 */
            on a.company_code_id = c.company_code_id
            and c.delete_date is null
        where 1=1
        and a.customer_vtaccount_expire_date is null
        and a.customer_reg_no = #{customerRegNo}
		<if test="customerVtaccountNo!=null">
		and a.customer_vtaccount_no = #{customerVtaccountNo}
		</if>
		order by ifnull(c.exposure_order, 'Z') asc, c.company_name_kor_alias asc, a.customer_vtaccount_reg_date desc
		<if test="pageInfo!=null">
            limit #{pageInfo.startRowNum},#{pageInfo.endRowNum}
        </if>
    </script>""")
	List<AccountRes> selectAccountList(AccountReq accountReq);
	
	/**
	 * 기업 리스트 조회
	 * @param accountReq
	 * @return
	 */
	@Select("""<script>
       select
		
            c.company_profile_reg_no         as companyCodeRegNo,             /* 기업코드.기업코드등록번호 */
            c.company_name_kor               as companyNameKor,               /* 기업코드.기업이름한글 */
            c.company_name_eng               as companyNameEng,               /* 기업코드.기업이름영문 */
            c.company_name_kor_alias         as companyNameKorAlias,          /* 기업코드.기업이름Alias한글 */
            c.company_name_eng_alias         as companyNameEngAlias           /* 기업코드.기업이름Alias영문 */
			from spt_customer_account_profile a /* 일반회원가상계좌프로파일 */
        left outer join com_company_profile c /* 기업코드 */
            on a.company_code_id = c.company_code_id
            and c.delete_date is null
        where 1=1
        and a.customer_vtaccount_expire_date is null
        and a.customer_reg_no = #{customerRegNo}
        group by  c.company_profile_reg_no, c.company_name_kor, c.company_name_eng, c.company_name_kor_alias, c.company_name_eng_alias
		order by c.company_name_kor_alias asc
		<if test="pageInfo!=null">
            limit #{pageInfo.startRowNum},#{pageInfo.endRowNum}
        </if>
        
    </script>""")
	List<AccountRes> selectCompList(AccountReq accountReq);

	/**
	 * 일반회원 가상계좌 리스트 조회
	 */
	@Select("""<script>
 
       select
            a.customer_reg_no                as customerRegNo,                /* 일반회원가상계좌프로파일.회원OP가입번호 */
            a.company_code_id                as companyCodeId,                /* 일반회원가상계좌프로파일.기업코드 */
            dec_char_sel(a.customer_realaccount_no,10,'ARIA','spt_customer_account_profile','costomer_realaccount_no',user(),connection_id()) as customerRealaccountNo, /* 일반회원가상계좌프로파일.실계좌번호 */
            a.customer_vtaccount_no          as customerVtaccountNo,          /* 일반회원가상계좌프로파일.가상계좌번호 */
            a.customer_vtaccount_alias       as customerVtaccountAlias,       /* 일반회원가상계좌프로파일.가상계좌별칭 */
            a.customer_vtaccount_status      as customerVtaccountStatus,      /* 일반회원가상계좌프로파일.가상계좌상태 */
            a.customer_realaccount_type      as customerRealaccountType,      /* 일반회원가상계좌프로파일.실계좌유형 */
            ifnull((select code_name_kor from com_system_code 
             where system_grp_code='G010' and a.customer_realaccount_type = concat('G010',system_code)
             ),'')                           as customerRealaccountTypeNm,    /* 일반회원가상계좌프로파일.실계좌유형명 */
            ifnull((select code_name_eng from com_system_code 
             where system_grp_code='G010' and a.customer_realaccount_type = concat('G010',system_code)
             ),'')                           as customerRealaccountTypeNmEng, /* 일반회원가상계좌프로파일.실계좌유형영문명 */
            ifnull(date_format(a.customer_vtaccount_reg_date,'%Y-%m-%d'),'')    as customerVtaccountRegDate,    /* 일반회원가상계좌프로파일.가상계좌발급일시 */
            ifnull(date_format(a.customer_vtaccount_expire_date,'%Y-%m-%d'),'') as customerVtaccountExpireDate, /* 일반회원가상계좌프로파일.가상계좌폐기일시 */
            a.create_date                    as createDate,                   /* 일반회원가상계좌프로파일.생성일시 */
            a.create_id                      as createId,                     /* 일반회원가상계좌프로파일.생성자ID */
            a.update_date                    as updateDate,                   /* 일반회원가상계좌프로파일.변경일시 */
            a.update_id                      as updateId,                     /* 일반회원가상계좌프로파일.변경자ID */
            c.company_profile_reg_no         as companyCodeRegNo,             /* 기업코드.기업코드등록번호 */
            c.company_name_kor               as companyNameKor,               /* 기업코드.기업이름한글 */
            c.company_name_eng               as companyNameEng,               /* 기업코드.기업이름영문 */
            c.company_name_kor_alias         as companyNameKorAlias,          /* 기업코드.기업이름Alias한글 */
            c.company_name_eng_alias         as companyNameEngAlias,          /* 기업코드.기업이름Alias영문 */
			c.company_profile_reg_no         as companyProfileRegNo
			from spt_customer_account_profile a /* 일반회원가상계좌프로파일 */
        left outer join com_company_profile c /* 기업코드 */
            on a.company_code_id = c.company_code_id
            and c.delete_date is null
        where 1=1
        and a.customer_vtaccount_expire_date is null
        and a.customer_reg_no = #{customerRegNo}
        AND c.company_profile_reg_no = #{companyCodeRegNo}
		
		order by ifnull(c.exposure_order, 'Z') asc, c.company_name_kor_alias asc, a.customer_vtaccount_reg_date desc
		<if test="pageInfo!=null">
            limit #{pageInfo.startRowNum},#{pageInfo.endRowNum}
        </if>
    </script>""")
	List<AccountRes> selectCompVtAccountList(AccountReq accountReq);


	@Select("""<script>
              select count(a.customer_realaccount_no) as totalcount
			from spt_customer_account_profile a /* 일반회원가상계좌프로파일 */
        left outer join com_company_profile c /* 기업코드 */
            on a.company_code_id = c.company_code_id
            and c.delete_date is null
        where 1=1
        and a.customer_vtaccount_expire_date is null
        and a.customer_reg_no = #{customerRegNo}
    </script>""")
	int selectAccountListTotalCount(AccountReq accountReq);


	@Select("""<script>
		select
			ccp.company_profile_reg_no as companyProfileRegNo, /* 기업프로파일.기업프로파일등록번호 */
			ccp.company_code_id        as companyCodeId,       /* 기업프로파일.기업코드 */
			ccp.company_service_type   as companyServiceType,  /* 기업프로파일.기업핀테크서비스타입 */
			ccp.company_name_kor       as companyNameKor,      /* 기업프로파일.기업이름한글 */
			ccp.company_bizreg_no      as companyBizregNo,     /* 기업프로파일.기업사업자등록번호 */
			ccp.issue_vtaccount        as issueVtaccount,      /* 기업프로파일.가상계좌발급여부 */
			ccp.issue_accountlist      as issueAccountlist,    /* 기업프로파일.실계좌목록조회요청여부 */
			ccp.company_name_eng       as companyNameEng,      /* 기업프로파일.기업이름영문 */
			ccp.company_name_kor_alias as companyNameKorAlias, /* 기업프로파일.기업이름Alias한글 */
			ccp.company_name_eng_alias as companyNameEngAlias, /* 기업프로파일.기업이름영문Alias */
			ccp.company_post_no        as companyPostNo,       /* 기업프로파일.기업우편번호 */
			ccp.company_address        as companyAddress,      /* 기업프로파일.기업주소 */
			ccp.company_address_dtl    as companyAddressDtl,   /* 기업프로파일.기업상세주소 */
			ccp.exposure_yn            as exposureYn,          /* 기업프로파일.노출여부 */
			ccp.exposure_order         as exposureOrder,       /* 기업프로파일.노출순서 */
			ccp.delete_date            as deleteDate,          /* 기업프로파일.기업탈퇴일시 */
			ccp.create_date            as createDate,          /* 기업프로파일.생성일시 */
			ccp.create_id              as createId,            /* 기업프로파일.생성자ID */
			ccp.update_date            as updateDate,          /* 기업프로파일.변경일시 */
			ccp.update_id              as updateId             /* 기업프로파일.변경자ID */
		from com_company_profile ccp /* 기업프로파일 */
		join (
			/* 계좌사용하는 API 기업만 조회 */
		select a.company_code_id
		from com_api_info a
		join com_api_view b on a.api_id = b.api_id
		join (
			select a.api_id
			from com_app_apilist_view a
			left join com_api_info as b on a.api_id = b.api_id
			where b.exposure_yn = 'Y'
			and b.api_account_yn = 'Y'
			group by a.api_id
		) c on a.api_id = c.api_id
		where a.exposure_yn = 'Y'
		and a.api_account_yn = 'Y'
			group by a.company_code_id
		) b on ccp.company_code_id = b.company_code_id
		where 1=1
		and ccp.delete_date is null
		and ccp.exposure_yn = 'Y'
		<if test="companyServiceType!=null">
		and ccp.company_service_type in ( 'G002001'
			<if test="companyServiceType!=null">
				, #{companyServiceType}
			</if>
			)
		</if>
		<if test="companyProfileRegNo!=null">
			and ccp.company_profile_reg_no = #{companyProfileRegNo}
		</if>
		order by ifnull(ccp.exposure_order, 'Z') asc, ccp.company_name_kor_alias asc
	</script>""")
	List<ComCompanyProfileRes> selectComCompanyProfileList(ComCompanyProfileReq comCompanyProfileReq);
	
	
	
	@Select("""<script>
        select
            a.customer_reg_no                as customerRegNo,                /* 일반회원가상계좌프로파일.회원OP가입번호 */
            a.company_code_id                as companyCodeId,                /* 일반회원가상계좌프로파일.기업코드 */
            dec_char_sel(a.customer_realaccount_no,10,'ARIA','spt_customer_account_profile','costomer_realaccount_no',user(),connection_id()) as customerRealaccountNo,
            a.customer_vtaccount_no          as customerVtaccountNo,          /* 일반회원가상계좌프로파일.가상계좌번호 */
            a.customer_vtaccount_alias       as customerVtaccountAlias,       /* 일반회원가상계좌프로파일.가상계좌별칭 */
            a.customer_vtaccount_status      as customerVtaccountStatus,      /* 일반회원가상계좌프로파일.가상계좌상태 */
            a.customer_realaccount_type      as customerRealaccountType,      /* 일반회원가상계좌프로파일.실계좌유형 */
            (select code_name_kor from com_system_code 
             where system_grp_code='G010' and a.customer_realaccount_type = concat('G010',system_code)
             )                               as customerRealaccountTypeNm,    /* 일반회원가상계좌프로파일.실계좌유형명 */
            (select code_name_eng from com_system_code 
             where system_grp_code='G010' and a.customer_realaccount_type = concat('G010',system_code)
             )                               as customerRealaccountTypeNmEng, /* 일반회원가상계좌프로파일.실계좌유형영문명 */
            a.customer_vtaccount_reg_date    as customerVtaccountRegDate,     /* 일반회원가상계좌프로파일.가상계좌발급일시 */
            a.customer_vtaccount_expire_date as customerVtaccountExpireDate,  /* 일반회원가상계좌프로파일.가상계좌폐기일시 */
            a.create_date                    as createDate,                   /* 일반회원가상계좌프로파일.생성일시 */
            a.create_id                      as createId,                     /* 일반회원가상계좌프로파일.생성자ID */
            a.update_date                    as updateDate,                   /* 일반회원가상계좌프로파일.변경일시 */
            a.update_id                      as updateId,                     /* 일반회원가상계좌프로파일.변경자ID */
            c.company_name_kor               as companyNameKor,               /* 기업프로파일.기업이름한글 */
            c.company_name_eng               as companyNameEng,               /* 기업프로파일.기업이름영문 */
            c.company_name_kor_alias         as companyNameKorAlias,          /* 기업프로파일.기업이름Alias한글 */
            c.company_name_eng_alias         as companyNameEngAlias           /* 기업프로파일.기업이름Alias영문 */
        from spt_customer_account_profile a /* 일반회원가상계좌프로파일 */
        left outer join com_company_profile c /* 기업프로파일 */
            on a.company_code_id = c.company_code_id
            and c.delete_date is null
        where 1=1
        and a.customer_vtaccount_expire_date is null
        and a.customer_reg_no = #{customerRegNo}
		<if test="companyCodeId!=null">
            and a.company_code_id = #{companyCodeId}
        </if>
        order by ifnull(c.exposure_order, 'Z') asc, c.company_name_kor_alias asc
	</script>""")
	List<SvcApplRes> selectSptCustAccList(SvcApplReq svcApplReq)
	
	
	@Select("""<script>
		select
			a.reg_no           as regNo,          /* OauthToken.등록번호(yyyymmdd000) */
			a.access_token     as accessToken,    /* OauthToken.엑세스토큰 */
			a.client_id        as clientId,       /* OauthToken.클라이언트ID(=API Key) */
			a.secret_id        as secretId,       /* OauthToken.클라이언트시크릿ID */
			a.scope            as scope,          /* OauthToken.스코프 */
			a.expires_in       as expiresIn,      /* OauthToken.유효기간 */
			a.token_type       as tokenType,      /* OauthToken.토큰타입 */
			a.use_yn           as useYn,          /* OauthToken.사용여부(Y:사용,N:사용안함) */
			a.delete_date      as deleteDate,     /* OauthToken.삭제일시 */
			a.update_date      as updateDate,     /* OauthToken.수정일시 */
			a.update_id        as updateId,       /* OauthToken.수정자ID */
			a.create_date      as createDate,     /* OauthToken.생성일시 */
			a.create_id        as createId        /* OauthToken.생성자ID */
		from com_oauth_token_info a /* OauthToken */
		where 1=1
		and a.delete_date is null
		and a.use_yn = 'Y'
		<if test="regNo != null and regNo != '' ">
			and a.reg_no = #{regNo}
		</if>
		order by a.create_date desc
		limit 0,1
	</script>""")
	ComOauthTokenRes selectTokenInfo(ComOauthTokenReq comOauthTokenReq)
	
			
	@Select("""<script>
		select
			ccp.company_profile_reg_no as companyProfileRegNo, /* 기업프로파일.기업프로파일등록번호 */
			ccp.company_code_id        as companyCodeId,       /* 기업프로파일.기업코드 */
			ccp.company_service_type   as companyServiceType,  /* 기업프로파일.기업핀테크서비스타입 */
			ccp.company_name_kor       as companyNameKor,      /* 기업프로파일.기업이름한글 */
			ccp.company_bizreg_no      as companyBizregNo,     /* 기업프로파일.기업사업자등록번호 */
			ccp.issue_vtaccount        as issueVtaccount,      /* 기업프로파일.가상계좌발급여부 */
			ccp.company_name_eng       as companyNameEng,      /* 기업프로파일.기업이름영문 */
			ccp.company_name_kor_alias as companyNameKorAlias, /* 기업프로파일.기업이름Alias한글 */
			ccp.company_name_eng_alias as companyNameEngAlias, /* 기업프로파일.기업이름영문Alias */
			ccp.company_post_no        as companyPostNo,       /* 기업프로파일.기업우편번호 */
			ccp.company_address        as companyAddress,      /* 기업프로파일.기업주소 */
			ccp.company_address_dtl    as companyAddressDtl,   /* 기업프로파일.기업상세주소 */
			ccp.exposure_yn            as exposureYn,          /* 기업프로파일.노출여부 */
			ccp.exposure_order         as exposureOrder,       /* 기업프로파일.노출순서 */
			ccp.delete_date            as deleteDate,          /* 기업프로파일.기업탈퇴일시 */
			ccp.create_date            as createDate,          /* 기업프로파일.생성일시 */
			ccp.create_id              as createId,            /* 기업프로파일.생성자ID */
			ccp.update_date            as updateDate,          /* 기업프로파일.변경일시 */
			ccp.update_id              as updateId             /* 기업프로파일.변경자ID */
		from com_company_profile ccp /* 기업프로파일 */
		where 1=1
		and ccp.delete_date is null
		and ccp.company_profile_reg_no = #{companyProfileRegNo}
		<if test="exposureYn == null or exposureYn == '' ">
			and ccp.exposure_yn = 'Y'
		</if>
		<if test="exposureYn != null and exposureYn != '' ">
			and ccp.exposure_yn = #{exposureYn}
		</if>
		order by ccp.exposure_order asc, ccp.company_name_kor asc
		limit 0,1
	</script>""")
	ComCompanyProfileRes selectComCompanyProfileInfo(ComCompanyProfileReq comCompanyProfileReq);
	
	
	
	/**
	  * @Method Name        : selectComOauthTokenInfo
	  * @Method description : [OauthToken]정보를 조회한다.
	  * @param              : ComOauthTokenRes
	  * @return             : ComOauthTokenReq
	  * @throws             : Exception
	*/
	@Select("""<script>
		select
			a.reg_no           as regNo,          /* OauthToken.등록번호(yyyymmdd000) */
			a.access_token     as accessToken,    /* OauthToken.엑세스토큰 */
			a.client_id        as clientId,       /* OauthToken.클라이언트ID(=API Key) */
			a.secret_id        as secretId,       /* OauthToken.클라이언트시크릿ID */
			a.scope            as scope,          /* OauthToken.스코프 */
			a.expires_in       as expiresIn,      /* OauthToken.유효기간 */
			a.token_type       as tokenType,      /* OauthToken.토큰타입 */
			a.use_yn           as useYn,          /* OauthToken.사용여부(Y:사용,N:사용안함) */
			a.delete_date      as deleteDate,     /* OauthToken.삭제일시 */
			a.update_date      as updateDate,     /* OauthToken.수정일시 */
			a.update_id        as updateId,       /* OauthToken.수정자ID */
			a.create_date      as createDate,     /* OauthToken.생성일시 */
			a.create_id        as createId        /* OauthToken.생성자ID */
		from com_oauth_token_info a /* OauthToken */
		where 1=1
		and a.delete_date is null
		and a.use_yn = 'Y'
		<if test="regNo != null and regNo != '' ">
			and a.reg_no = #{regNo}
		</if>
		order by a.create_date desc
		limit 0,1
	</script>""")
	ComOauthTokenRes selectComOauthTokenInfo(ComOauthTokenReq comOauthTokenReq);
	
	
	
	/**
	  * @Method Name        : usr.SvcApplDAO.selectSvcCompanyTermsConsntList
	  * @Method description : [약관동의] 기업 약관동의 대상을 조회한다.
	  * @param              : SvcApplVO
	  * @return             : SptCustomerCompanyTermsProfileVO
	  * @throws             : Exception
	*/
	
	@Select("""<script>

		select  a.company_code_id                 as companyCodeId,
		        c.company_name_kor_alias          as companyName,
		        a.company_terms_type              as companyTermsType,
		        a.company_terms_content_reg_seq   as companyTermsContentRegSeq,
		        a.company_terms_content           as companyTermsContent
		from(
		    /* 기업약관 */
		    select  company_code_id,
		            company_terms_type,
		            company_terms_content_reg_seq,
		            company_terms_content_ver,
		            company_terms_content
		    from spt_customer_company_terms_content_profile
		    where company_terms_type = 'G031001' 
		    and (company_code_id, company_terms_content_ver) in (
		        select  company_code_id, 
		                max(company_terms_content_ver) as company_terms_content_ver
		        from spt_customer_company_terms_content_profile
		        where company_terms_type = 'G031001'
		        and company_code_id = #{companyCodeId}
		    )
		) a
		left join (
		    select *    
		    from spt_customer_company_terms_profile
		    where customer_reg_no = #{customerRegNo}
		    and company_terms_type = 'G031001'  
		    and delete_date is null
		) b
		on a.company_code_id = b.company_code_id
		and a.company_terms_type = b.company_terms_type
		and a.company_terms_content_reg_seq = b.company_terms_content_reg_seq
		join com_company_profile c on a.company_code_id = c.company_code_id
		where b.company_code_id is null 
		order by c.exposure_order asc, c.company_name_kor_alias asc
	</script>""")
	SptCustomerCompanyTermsProfileRes selectSvcCompanyTermsConsntList(SptCustomerCompanyTermsProfileReq sptCustomerCompanyTermsProfileReq);
	
	
	/**
	  * @Method Name        : usr.SvcApplDAO.checkSptCustomerCompanyTermsProfile
	  * @Method description : [약관동의] 기업 약관 저장 전 기업약관 정보 확인.
	  * @param              : SptCustomerCompanyTermsProfileVO
	  * @return             : int
	  * @throws             : Exception
	*/
	@Select("""<script>
		select count(*) as cnt
		from spt_customer_company_terms_profile
		where customer_reg_no = #{customerRegNo}
		and company_code_id = #{companyCodeId}
		and company_terms_type = #{companyTermsType}
	</script>""")
	int checkSptCustomerCompanyTermsProfile(SptCustomerCompanyTermsProfileReq sptCustomerCompanyTermsProfileReq);
	
	
	/**
	  * @Method Name        : usr.SvcApplDAO.insertSptCustomerCompanyTermsProfile
	  * @Method description : [약관동의] 기업 약관 정보를 insert.
	  * @param              : SptCustomerCompanyTermsProfileVO
	  * @throws             : Exception
	*/
	@Insert("""<script>
		insert into spt_customer_company_terms_profile(
			customer_reg_no,
			company_code_id,
			company_terms_type,
			company_terms_content_reg_seq,
			company_terms_auth_yn,
			company_terms_auth_date,
			create_date,
			create_id,
			update_date,
			update_id
		)values(
			#{customerRegNo},
			#{companyCodeId},
			#{companyTermsType},
			#{companyTermsContentRegSeq},
			#{companyTermsAuthYn},
			sysdate(),
			sysdate(),
			#{createId},
			sysdate(),
			#{createId}
		)
	</script>""")
	int insertSptCustomerCompanyTermsProfile(SptCustomerCompanyTermsProfileReq sptCustomerCompanyTermsProfileReq);
	
	
	/**
	  * @Method Name        : usr.SvcApplDAO.updateSptCustomerCompanyTermsProfile
	  * @Method description : [약관동의] 기업 약관 정보를 update.
	  * @param              : SptCustomerCompanyTermsProfileVO
	  * @throws             : Exception
	*/
	@Update("""<script>
		update spt_customer_company_terms_profile set
			company_terms_content_reg_seq = #{companyTermsContentRegSeq},
			company_terms_auth_yn = #{companyTermsAuthYn},
			company_terms_auth_date = sysdate(),
			update_date = sysdate(),
			update_id = #{createId}
		where customer_reg_no = #{customerRegNo}
		and company_code_id = #{companyCodeId}
		and company_terms_type = #{companyTermsType}
	
	</script>""")
	int updateSptCustomerCompanyTermsProfile(SptCustomerCompanyTermsProfileReq sptCustomerCompanyTermsProfileReq);
	
	/**
	  * @Method Name        : usr.SvcApplDAO.insertSptCustomerCompanyTermsProfileHist
	  * @Method description : [약관동의] 기업 약관 정보의 hist를 insert.
	  * @param              : SptCustomerCompanyTermsProfileVO
	  * @throws             : Exception
	*/
	@Insert("""<script>
		insert into spt_customer_company_terms_profile_hist(
			customer_reg_no,
			company_code_id,
			company_terms_type,
			company_terms_seq,
			company_terms_content_reg_seq,
			company_terms_auth_yn,
			company_terms_auth_date,
			create_date,
			create_id
		)
		select  customer_reg_no,
				company_code_id,
				company_terms_type,
				ifnull((
					select ifnull(max(cast(company_terms_seq as SIGNED)), 0) + 1
					from spt_customer_company_terms_profile_hist
					where customer_reg_no = a.customer_reg_no
					and company_code_id = a.company_code_id
					and company_terms_type = a.company_terms_type
				), 1),
				company_terms_content_reg_seq,
				company_terms_auth_yn,
				company_terms_auth_date,
				update_date,
				update_id
		from spt_customer_company_terms_profile a
		where customer_reg_no = #{customerRegNo}
		and company_code_id = #{companyCodeId}
		and company_terms_type = #{companyTermsType}
	</script>""")
	int insertSptCustomerCompanyTermsProfileHist(SptCustomerCompanyTermsProfileReq sptCustomerCompanyTermsProfileReq);
	
}
