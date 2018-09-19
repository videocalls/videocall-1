package kr.co.koscom.oppfm.member.dao

import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileRes
import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.login.model.LoginRes
import kr.co.koscom.oppfm.member.model.MemberReq
import kr.co.koscom.oppfm.member.model.MemberRes
import kr.co.koscom.oppfm.member.model.MemberVerifyRes
import kr.co.koscom.oppfm.member.model.TemporaryMemberInfoRes
import kr.co.koscom.oppfm.terms.model.TermsRes
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.SelectKey
import org.apache.ibatis.annotations.Update

@Mapper
public interface MemberMapper {

	/**
	 * 회원 상세 조회
	 * * Modify by sh.lee on 2017-05-17.
	 */
	@Select("""<script>
	 select
			a.customer_reg_no                                                          as customerRegNo,              
			a.customer_id                                                              as customerId,
			a.customer_temp_password_yn                                                as customerTempPasswordYn,
			<if test='customerPassword != null'>
             	if(a.customer_password = enc_char_ins(#{customerPassword},10,'SHA','spt_customer_info_profile','customer_password',user(),connection_id()), 'Y', 'N') as customerPasswordYn,  /* 회원 패스워드 유효여부 */
            </if>
			ifnull(date_format(a.customer_chg_password_date, '%Y-%m-%d %H:%i'), '')    as customerChgPasswordDate,   
			ifnull(date_format(a.customer_expire_password_date, '%Y-%m-%d %H:%i'), '') as customerExpirePasswordDate,
			ifnull(date_format(a.customer_final_password_date, '%Y-%m-%d %H:%i'), '')  as customerFinalPasswordDate, 
			a.customer_password_fail_cnt                                               as customerPasswordFailCnt,   
			a.customer_reg_status                                                      as customerRegStatus,          
			a.customer_step                                                            as customerStep,                
			a.customer_name_kor                                                        as customerNameKor,            
			a.customer_name_eng                                                        as customerNameEng,            
			dec_char_sel(a.customer_phone,10,'ARIA','spt_customer_info_profile','customer_phone',user(),connection_id())  as customerPhone,
			replace(dec_char_sel(a.customer_phone,10,'ARIA','spt_customer_info_profile','customer_phone',user(),connection_id()),'-','')  as customerPhoneReplace,
			dec_char_sel(a.customer_email,10,'ARIA','spt_customer_info_profile','customer_email',user(),connection_id())  as customerEmail,               
			a.customer_email_auth_yn                                                   as customerEmailAuthYn,       
			ifnull(date_format(a.customer_email_reg_date, '%Y-%m-%d %H:%i'), '')       as customerEmailRegDate,      
			ifnull(date_format(dec_char_sel(a.customer_birth_day,10,'ARIA','spt_customer_info_profile','customer_birth_day',user(),connection_id()),'%Y-%m-%d'),'')  as customerBirthDay,
			a.customer_sex                                                             as customerSex,                 
	        a.customer_mobile_push_yn                                                  as customerMobilePushYn,                 
			a.customer_email_receive_yn                                                as customerEmailReceiveYn,    
			ifnull(date_format(a.customer_email_receive_date, '%Y-%m-%d %H:%i'), '')   as customerEmailReceiveDate,
			if(ifnull(otp.customer_otp_id, 'N') = 'N', 'N', 'Y')                       as customerOtpYn,
            otp.customer_otp_id                                                        as customerOtpId,  
            verify.customer_verify_dn                                                  as customerVerifyDn,
			verifyCi.customer_verify_ci												   as customerCi, 
			ifnull(date_format(a.customer_reg_date, '%Y-%m-%d %H:%i'), '')             as customerRegDate,            
			ifnull(date_format(a.delete_date, '%Y-%m-%d %H:%i'), '')                   as deleteDate,                  
			ifnull(date_format(a.create_date, '%Y-%m-%d %H:%i'), '')                   as createDate,                  
			ifnull(a.create_id,'')                                                     as createId,                    
			ifnull(date_format(a.update_date, '%Y-%m-%d %H:%i'), '')                   as updateDate,                  
			ifnull(a.update_id,'')                                                     as updateId                    
	    from spt_customer_info_profile a
	    left join (
            select  customer_reg_no,
                    customer_otp_id
            from spt_customer_otp_profile
            where customer_otp_status not in('G019003') /*폐기 아님*/
            and customer_reg_no = #{customerRegNo}
        ) as otp on a.customer_reg_no = otp.customer_reg_no
        left join (
			select  customer_reg_no,
			        customer_verify as customer_verify_dn
			from spt_customer_verify_profile
			where delete_date is null
			and customer_reg_no = #{customerRegNo}
			and customer_verify_type = 'G007002'        
        ) as verify on a.customer_reg_no = verify.customer_reg_no
       left join (SELECT customer_reg_no, 
                         customer_verify AS customer_verify_ci 
                  FROM   spt_customer_verify_profile 
                  WHERE  delete_date IS NULL 
                         AND customer_reg_no = #{customerRegNo}
                         AND customer_verify_type = 'G007001') AS verifyCi
              ON a.customer_reg_no = verifyCi.customer_reg_no
	    where 1=1
	    and a.customer_reg_no = #{customerRegNo}
	</script>""")
	MemberRes selectUserInfo(MemberReq memberReq);

	@Update("""<script>
	    update spt_customer_info_profile set
			update_date = sysdate()
			<if test="customerNameKor!=null and customerNameKor!=''">
				,customer_name_kor = #{customerNameKor}
			</if>
			<if test="customerId!=null and customerId!=''">
				,customer_id = #{customerId}
			</if>
			<if test="customerPassword!=null and customerPassword!=''">
			    ,customer_password = enc_char_ins(#{customerPassword},10,'SHA','spt_customer_info_profile','customer_password',user(),connection_id())
			    ,customer_temp_password_yn = 'N'
			    ,customer_chg_password_date = sysdate()
				,customer_expire_password_date = date_format(date_add(sysdate(), interval #{customerExpirePasswordDate} month), '%Y%m%d')
				,customer_final_password_date = date_format(date_add(sysdate(), interval #{customerFinalPasswordDate} month), '%Y%m%d')
		        ,customer_password_fail_cnt = 0
		        ,customer_otp_fail_cnt = 0
			</if>
			<if test="customerEmail != null and customerEmail != ''">
		    	,customer_email = enc_char_ins(#{customerEmail},10,'ARIA','spt_customer_info_profile','customer_email',user(),connection_id())
		    </if>
		    <if test="customerEmailReceiveYn != null and customerEmailReceiveYn != ''">
				,customer_email_receive_yn = #{customerEmailReceiveYn}
			</if>
		    <if test="customerPhone != null and customerPhone != ''">
		    	,customer_phone = enc_char_ins(#{customerPhone},10,'ARIA','spt_customer_info_profile','customer_phone',user(),connection_id())
		    </if>
			<if test="temporaryMemberYn != null and temporaryMemberYn != ''">
				,temporary_member_yn = #{temporaryMemberYn}
			</if>
		    ,update_id = #{customerRegNo}
	    where 1=1
	    and customer_reg_no = #{customerRegNo}
	</script>""")
	int updateUserInfo(MemberReq memberReq);
	
	@Select("""<script>
	   	  SELECT if(
			  a.customer_password =
			  enc_char_ins(#{customerPassword},10,'SHA','spt_customer_info_profile','customer_password',user(),connection_id()), 'Y', 'N'
			  )  AS cfPasswordResult
			FROM  spt_customer_info_profile a
		   WHERE customer_reg_no = #{customerRegNo};
	</script>""")
	String selectCheckPw(MemberReq memberReq);


	@Insert("""<script>
		 INSERT INTO spt_customer_info_profile_hist(customer_reg_no,
                                                   customer_seq,
                                                   customer_id
												  ,customer_password
												  ,customer_temp_password_yn
												  ,customer_chg_password_date
												  ,customer_expire_password_date
												  ,customer_final_password_date
												  ,customer_otp_fail_cnt
												  ,customer_password_fail_cnt
												  ,customer_reg_status
												  ,customer_step
												  ,customer_name_kor
												  ,customer_name_eng
												  ,customer_phone
												  ,customer_email
												  ,customer_email_auth_yn
												  ,customer_email_reg_date
												  ,customer_birth_day
												  ,customer_sex
												  ,customer_email_receive_yn
												  ,customer_email_receive_date
												  ,customer_mobile_push_yn
												  ,customer_dataset_lock_yn
												  ,customer_reg_date
												  ,delete_date
												  ,customer_withdrawal_proc_yn
												  ,customer_join_type
												  ,temporary_member_yn
												  ,integration_account_yn
												  ,create_date
												  ,create_id
							)
           SELECT customer_reg_no,
                  ifnull((SELECT ifnull(max(cast(customer_seq AS SIGNED)), 0) + 1
                            FROM spt_customer_info_profile_hist
                           WHERE customer_reg_no = a.customer_reg_no),
                         1),
                  customer_id
				  ,customer_password
				  ,customer_temp_password_yn
				  ,customer_chg_password_date
				  ,customer_expire_password_date
				  ,customer_final_password_date
				  ,customer_otp_fail_cnt
				  ,customer_password_fail_cnt
				  ,customer_reg_status
				  ,customer_step
				  ,customer_name_kor
				  ,customer_name_eng
				  ,customer_phone
				  ,customer_email
				  ,customer_email_auth_yn
				  ,customer_email_reg_date
				  ,customer_birth_day
				  ,customer_sex
				  ,customer_email_receive_yn
				  ,customer_email_receive_date
				  ,customer_mobile_push_yn
				  ,customer_dataset_lock_yn
				  ,customer_reg_date
				  ,delete_date
				  ,customer_withdrawal_proc_yn
				  ,customer_join_type
				  ,temporary_member_yn
				  ,integration_account_yn
				  ,update_date
				  ,update_id
             FROM spt_customer_info_profile a
            WHERE customer_reg_no = #{customerRegNo}
	</script>""")
	void insertMemberHist(MemberReq memberReq);

    /**
     * 회원등록
     * */
    @Insert("""<script>
           insert into spt_customer_info_profile(
           customer_reg_no
           , customer_step
           , customer_join_type
           <if test="temporaryMemberYn != null">
           , temporary_member_yn
		   </if>
           , customer_password_fail_cnt
           <if test="customerId != null">
           ,customer_id
           </if>
           <if test="customerPassword != null">
           ,customer_password
           </if>
           ,customer_temp_password_yn
           <if test="customerChgPasswordDate != null">
           ,customer_chg_password_date
           </if>
           ,customer_reg_status
           <if test="customerNameKor != null">
           ,customer_name_kor
           </if>
           <if test="customerNameEng != null">
           ,customer_name_eng
           </if>
           <if test="customerPhone != null">
           ,customer_phone
           </if>
           <if test="customerEmail != null">
           ,customer_email
           </if>
           ,customer_email_auth_yn
           ,customer_email_reg_date
           <if test="customerExpirePasswordDate != null">
           ,customer_expire_password_date
           </if>
           <if test="customerFinalPasswordDate != null">
           ,customer_final_password_date
           </if>
           <if test="customerBirthDay != null">
           ,customer_birth_day
           </if>
           <if test="customerSex != null">
           ,customer_sex
           </if>
           <if test="customerEmailReceiveYn != null">
           ,customer_email_receive_yn
           </if>
           ,customer_email_receive_date
           ,customer_reg_date
           ,create_date
           ,create_id
           ,update_date
           ,update_id
        )values(
           #{customerRegNo}
           , 'G006005'
           , 'G039002'
           <if test="temporaryMemberYn != null">
           , #{temporaryMemberYn}
           </if>
           , 0
           <if test="customerId != null">
           ,#{customerId}
           </if>
           <if test="customerPassword != null">
           ,enc_char_ins(#{customerPassword},10,'SHA','spt_customer_info_profile','customer_password',user(),connection_id())
           </if>
           ,'N'
           <if test="customerChgPasswordDate != null">
           ,#{customerChgPasswordDate}
           </if>
           ,'G005002'
           <if test="customerNameKor != null">
           ,#{customerNameKor}
           </if>
           <if test="customerNameEng != null">
           ,#{customerNameEng}
           </if>
           <if test="customerPhone != null">
           ,enc_char_ins(#{customerPhone},10,'ARIA','spt_customer_info_profile','customer_phone',user(),connection_id())
           </if>
           <if test="customerEmail != null">
           ,enc_char_ins(#{customerEmail},10,'ARIA','spt_customer_info_profile','customer_email',user(),connection_id())
           </if>
           ,'Y'
           ,sysdate()
           <if test="customerExpirePasswordDate != null">
           ,#{customerExpirePasswordDate}
           </if>
           <if test="customerFinalPasswordDate != null">
           ,#{customerFinalPasswordDate}
           </if>
           <if test="customerBirthDay != null">
           ,enc_char_ins(#{customerBirthDay},10,'ARIA','spt_customer_info_profile','customer_birth_day',user(),connection_id())
           </if>
           <if test="customerSex != null">
           ,#{customerSex}
           </if>
           <if test="customerEmailReceiveYn != null">
           ,#{customerEmailReceiveYn}
           </if>
           ,sysdate()
           ,sysdate()
           ,sysdate()
           ,#{customerRegNo}
           ,sysdate()
           ,#{customerRegNo}
        )
    </script>""")
    @SelectKey(statement="select concat(#{customerRegNoPrefix}, date_format(sysdate(),'%Y%m%d'), lpad( (select ifnull( max(cast(right(customer_reg_no,3) as signed)), 0)+1 from spt_customer_info_profile where left(customer_reg_no,9) = concat(#{customerRegNoPrefix}, date_format(sysdate(),'%Y%m%d'))) , 3, '0')) as customerRegNo"
            , keyProperty="customerRegNo", before=true, resultType=String.class)
    void insertMember(MemberReq memberReq);

	@Select("""<script>
	   	  select   a.customer_reg_no as customerRegNo /* 회원 OP 등록 번호 */
                ,a.customer_id as customerId /* 회원 ID */
                dec_char_sel(a.customer_email,10,'ARIA','spt_customer_info_profile','customer_email',user(),connection_id())  as customerEmail,	/* 회원 Email 조회 */
                ,a.customer_name_kor as customerNameKor /* 회원명 한글 */
                ,a.customer_name_eng as customerNameEng /* 회원명 영문 */
                ,a.customer_temp_password_yn as customerTempPasswordYn /* 임시비밀번호여부 */
                ,a.customer_withdrawal_proc_yn as customerWithdrawalProcYn /* 탈퇴처리 여부 */
                ,a.customer_password_fail_cnt as customerPasswordFailCnt /* 비밀번호 실패 카운트 */
                ,a.customer_email_auth_yn as customerEmailAuthYn /* 이메일 인증여부 */
                ,a.customer_reg_status as customerRegStatus /* 회원가입 상태 */
                ,if(ifnull(a.delete_date, 'N') = 'N', 'N', 'Y') as deleteYn /* 탈퇴여부 */
                ,a.customer_step as customerStep /* 회원가입스텝 */
                ,a.integration_account_yn as integrationAccountYn /* 통합계좌메뉴 사용여부 */
                ,a.customer_join_type as customerJoinType
                ,a.temporary_member_yn as temporaryMemberYn
        from    spt_customer_info_profile a, spt_customer_verify_profile b
        where b.customer_verify_type = 'G007001'
         and b.customer_verify = #{verify}
         and b.delete_date is null
         and a.customer_reg_no = b.customer_reg_no
         and a.customer_withdrawal_proc_yn = 'N' /*탈퇴하지 않은 회원*/
         and a.temporary_member_yn = 'Y'
        limit 1
	</script>""")
	LoginRes selectGuestMember(String verify);

	@Select("""<script>
	   	  select   a.customer_reg_no as customerRegNo /* 회원 OP 등록 번호 */
                ,a.customer_id as customerId /* 회원 ID */
                ,a.customer_name_kor as customerNameKor /* 회원명 한글 */
                ,a.customer_name_eng as customerNameEng /* 회원명 영문 */
                ,a.customer_temp_password_yn as customerTempPasswordYn /* 임시비밀번호여부 */
                ,a.customer_withdrawal_proc_yn as customerWithdrawalProcYn /* 탈퇴처리 여부 */
                ,a.customer_password_fail_cnt as customerPasswordFailCnt /* 비밀번호 실패 카운트 */
                ,a.customer_email_auth_yn as customerEmailAuthYn /* 이메일 인증여부 */
                ,a.customer_reg_status as customerRegStatus /* 회원가입 상태 */
                ,if(ifnull(a.delete_date, 'N') = 'N', 'N', 'Y') as deleteYn /* 탈퇴여부 */
                ,a.customer_step as customerStep /* 회원가입스텝 */
                ,a.integration_account_yn as integrationAccountYn /* 통합계좌메뉴 사용여부 */
                ,a.customer_join_type as customerJoinType
                ,a.temporary_member_yn as temporaryMemberYn
        from    spt_customer_info_profile a, spt_customer_verify_profile b
        where a.customer_id = #{customerId}
         and a.customer_withdrawal_proc_yn = 'N' /*탈퇴하지 않은 회원*/
         and a.temporary_member_yn = 'N'
        limit 1
	</script>""")
	LoginRes selectMember(String customerId);

	@Select("""<script>
	   	  select   a.customer_reg_no as customerRegNo /* 회원 OP 등록 번호 */
                ,a.customer_id as customerId /* 회원 ID */
                ,a.customer_name_kor as customerNameKor /* 회원명 한글 */
                ,a.customer_name_eng as customerNameEng /* 회원명 영문 */
                ,a.customer_temp_password_yn as customerTempPasswordYn /* 임시비밀번호여부 */
                ,a.customer_withdrawal_proc_yn as customerWithdrawalProcYn /* 탈퇴처리 여부 */
                ,a.customer_password_fail_cnt as customerPasswordFailCnt /* 비밀번호 실패 카운트 */
                ,a.customer_email_auth_yn as customerEmailAuthYn /* 이메일 인증여부 */
                ,a.customer_reg_status as customerRegStatus /* 회원가입 상태 */
                ,if(ifnull(a.delete_date, 'N') = 'N', 'N', 'Y') as deleteYn /* 탈퇴여부 */
                ,a.customer_step as customerStep /* 회원가입스텝 */
                ,a.integration_account_yn as integrationAccountYn /* 통합계좌메뉴 사용여부 */
                ,a.customer_join_type as customerJoinType
                ,a.temporary_member_yn as temporaryMemberYn
        from    spt_customer_info_profile a, spt_customer_verify_profile b
        where a.customer_id = #{customerId}
         and a.customer_withdrawal_proc_yn = 'N' /*탈퇴하지 않은 회원*/
         and a.temporary_member_yn = 'Y'
        limit 1
	</script>""")
	LoginRes selectGuestMemberInfo(String customerId);

	/**
	 * CI 중복체크
	 * */
	@Select("""<script>
		  SELECT v.customer_reg_no    AS customerRegNo,          /* 일반회원검증.회원OP등록번호 */
		  		 c.customer_name_kor  AS customerNameKor,		 /* 이름 */
		  		 dec_char_sel(c.customer_email,10,'ARIA','spt_customer_info_profile','customer_email',user(),connection_id())  as customerEmail,  	/* 회원 Email 조회 */
		  		 c.temporary_member_yn AS temporaryMemberYn, 		 /* 임시회원 여부 */
				 v.customer_verify_type AS customerVerifyType,     /* 일반회원검증.회원검증종류 */
				 v.customer_verify    AS customerVerify,            /* 일반회원검증.회원검증값 */
				 v.customer_verify_date AS customerVerifyDate,     /* 일반회원검증.회원검증일시 */
				 v.delete_date        AS deleteDate,               /* 일반회원검증.회원탈퇴일시 */
				 v.create_date        AS createDate,                 /* 일반회원검증.생성일시 */
				 v.create_id          AS createId,                 /* 일반회원검증.생성자 ID */
				 v.update_date        AS updateDate,                 /* 일반회원검증.변경일시 */
				 v.update_id          AS udpateId,                  /* 일반회원검증.변경자 ID */
				 c.customer_reg_status 	  AS customerRegStatus		/* 회원 상태 */
			FROM spt_customer_verify_profile v, spt_customer_info_profile c /* 일반회원검증 */
		   WHERE     1 = 1
				 AND c.customer_reg_no = v.customer_reg_no
				 AND v.customer_verify =#{customerVerify}
				 AND v.customer_verify_type = 'G007001'
		ORDER BY v.customer_reg_no DESC              /* 최근의 회원 중 탈퇴한 회원이 아닌 회원 중 CI값이 존재하면 실존 회원 */
		   LIMIT 0, 1
	</script>""")
	MemberRes selectDuplicationCheckCi(MemberReq memberReq);
	
	/**
	 * 탈퇴 회원 CI 중복체크
	 * */
	@Select("""<script>
			select 
				count(a.customer_reg_no) 
			from leave_customer_info_profile a
			join leave_customer_verify_profile b
			on a.customer_reg_no = b.customer_reg_no
			and b.customer_verify_type = 'G007001'
			and b.customer_verify = #{customerVerify}
			<![CDATA[
			AND DATE_ADD(b.create_date, INTERVAL +3 DAY) > NOW()
			]]>
			</script>""")
	int selectwithDrawMemberCheckCi(MemberReq memberReq);

	/**
	 * ID 중복체크
	 * */
	@Select("""<script>
		  SELECT CASE WHEN count(customer_id) > 0 THEN 'n' ELSE '' END AS ids
			FROM spt_customer_info_profile
		   WHERE 1 = 1
		     AND customer_id = #{customerId}
		ORDER BY customer_reg_no DESC
		   LIMIT 0, 1
	</script>""")
	String selectDuplicationCheckId(MemberReq memberReq);

	/**
	 * Email 중복체크
	 * */
	@Select("""<script>
		SELECT CASE WHEN count(customer_id) > 0 THEN 'n' ELSE '' END AS ids
		FROM spt_customer_info_profile
		WHERE 1 = 1
		<if test="customerRegNo != null and customerRegNo != ''">
			AND customer_reg_no != #{customerRegNo}
		</if>
			AND customer_email = enc_char_ins(#{customerEmail},
                                                       10,
                                                       'ARIA',
                                                       'spt_customer_info_profile',
                                                       'customer_email',
                                                       user(),
                                                       connection_id())
             AND temporary_member_yn = 'N'
             AND customer_withdrawal_proc_yn = 'N'
             AND customer_reg_status = 'G005002'
		ORDER BY customer_reg_no DESC
   		   LIMIT 0, 1
	</script>""")
	String selectDuplicationCheckEmail(MemberReq memberReq);

	/**
	 * CI, DN 등록
	 * */
	@Insert("""<script>
	insert into spt_customer_verify_profile(
        customer_reg_no      /* 회원OP등록번호 */
       ,customer_verify_type /* 회원검증종류  */
    <if test="customerVerify != null or customerVerify != ''">
       ,customer_verify      /* 회원검증값 */
       ,customer_verify_date /* 회원검증일시 */
    </if>
       ,delete_date          /* 삭제일시 */
       ,create_date          /* 생성일시 */
       ,create_id            /* 생성자 ID */
       ,update_date          /* 변경일시 */
       ,update_id            /* 변경자 ID */
    )values(
        #{customerRegNo}      /* 회원OP등록번호 */
       ,#{customerVerifyType} /* 회원검증종류  */
    <if test="customerVerify != null or customerVerify !=''">
       ,#{customerVerify}     /* 회원검증값 */
       ,sysdate()            /* 회원검증일시 */
    </if>
       ,null            /* 삭제일시 */
       ,sysdate()            /* 생성일시 */
       ,#{customerRegNo}      /* 생성자 ID */
       ,sysdate()            /* 변경일시 */
       ,#{customerRegNo}      /* 변경자 ID */
       )
	</script>""")
	int insertMemberVerifyProfile(MemberReq memberReq);


	@Insert("""<script>
		   INSERT INTO spt_customer_verify_profile_hist(customer_verify_seq,
													 customer_reg_no,
													 customer_verify_type,
													 customer_verify,
													 customer_verify_date,
													 delete_date,
													 create_date,
													 create_id
															  )
		   SELECT ifnull(
					 (SELECT ifnull(max(cast(customer_verify_seq AS SIGNED)), 0) + 1
						FROM spt_customer_verify_profile_hist
					   WHERE customer_reg_no = a.customer_reg_no),
					 1),
				  customer_reg_no,
				  customer_verify_type,
				  customer_verify,
				  customer_verify_date,
				  delete_date,
				  create_date,
				  create_id
			 FROM spt_customer_verify_profile a
			WHERE     1 = 1
				  AND customer_reg_no = #{customerRegNo}
				  AND customer_verify_type = #{customerVerifyType}
	</script>""")
	int insertMemberVerifyProfileHist(MemberReq memberReq);
	
	@Update("""<script>
        update spt_customer_terms_profile set
            delete_date = sysdate(),  
            update_date = sysdate(),
            update_id = #{customerRegNo}
        where 1=1
        and customer_reg_no = #{customerRegNo}
	</script>""")
	int deleteCustomerTermsProfile(TermsRes terms);
	
	@Insert("""<script>
	insert into spt_customer_terms_profile(
			customer_reg_no
		   ,customer_terms_type
		<if test="customerTermsContentRegSeq != null and customerTermsContentRegSeq != ''">
		   ,customer_terms_content_reg_seq
		</if>
		   ,customer_terms_auth_yn
		   ,customer_terms_auth_date
		   ,delete_date
		   ,create_date
		   ,create_id
		   ,update_date
		   ,update_id
		)
		select
			#{customerRegNo}
		   ,#{customerTermsType}
		<if test="customerTermsContentRegSeq != null || customerTermsContentRegSeq != ''">
		   ,#{customerTermsContentRegSeq}
		</if>
		,'Y'
		,sysdate()
	    ,null
        ,sysdate()
        ,#{customerRegNo}
        ,sysdate()
        ,#{customerRegNo}
    from dual
	</script>""")
	int insertMemberTermsProfile(TermsRes terms);

	@Insert("""<script>
	insert into spt_customer_terms_profile(
			customer_reg_no
		   ,customer_terms_type
		<if test="customerTermsContentRegSeq != null and customerTermsContentRegSeq != ''">
		   ,customer_terms_content_reg_seq
		</if>
		   ,customer_terms_auth_yn
		   ,customer_terms_auth_date
		   ,delete_date
		   ,create_date
		   ,create_id
		   ,update_date
		   ,update_id
		)
		values(
			#{customerRegNo}
		   ,#{customerTermsType}
		<if test="customerTermsContentRegSeq != null || customerTermsContentRegSeq != ''">
		   ,#{customerTermsContentRegSeq}
		</if>
		,'Y'
		,sysdate()
	    ,null
        ,sysdate()
        ,#{customerRegNo}
        ,sysdate()
        ,#{customerRegNo}
    )ON DUPLICATE KEY UPDATE
		update_date = sysdate()
		,customer_terms_content_reg_seq = #{customerTermsContentRegSeq}
		,update_id = #{customerRegNo}
	</script>""")
	int insertUpdateMemberTermsProfile(TermsRes terms);

	@Insert("""<script>
		insert into spt_customer_terms_profile_hist(
				customer_terms_seq,             /* 순번 */
				customer_reg_no,                /* 회원OP등록번호 */
				customer_terms_type,            /* 회원동의종류  */
				customer_terms_content_reg_seq, /* 동의서약관내용등록번호 */
				customer_terms_auth_yn,         /* 회원동의여부 */
				customer_terms_auth_date,       /* 회원동의일시 */
				delete_date,                    /* 삭제일시 */
				create_date,                    /* 생성일시 */
				create_id                       /* 생성자 ID */
			)
			select
		  ifnull((
			  select ifnull(max(cast(customer_terms_seq as SIGNED)), 0) + 1
			  from spt_customer_terms_profile_hist aa
			  where a.customer_reg_no = aa.customer_reg_no
		  ), 1),                            /* 순번 */
				a.customer_reg_no,                /* 회원OP등록번호 */
				a.customer_terms_type,            /* 회원동의종류  */
				a.customer_terms_content_reg_seq, /* 동의서약관내용등록번호 */
				a.customer_terms_auth_yn,         /* 회원동의여부 */
				a.customer_terms_auth_date,       /* 회원동의일시 */
				a.delete_date,                    /* 삭제일시 */
				a.create_date,                    /* 생성일시 */
				a.create_id                       /* 생성자 ID */
			from spt_customer_terms_profile a
			where 1=1
			and customer_reg_no = #{customerRegNo} /* 회원OP등록번호 */
			and customer_terms_type = #{customerTermsType} /* 회원OP등록번호 */
	</script>""")
	int insertMemberTermsProfileHist(MemberReq memberReq);

	/**
	 * 회원 탈퇴
	 * */
	@Update("""<script>
        update spt_customer_info_profile set
            customer_reg_status = 'G005004',
            delete_date = sysdate(),  
            update_date = sysdate(),
            update_id = #{customerRegNo}
        where 1=1
        and customer_reg_no = #{customerRegNo}
	</script>""")
	int removeMember(LoginRes loginInfo);

	/**
	 * CI 정보 조회
	 * */
	@Select("""<script>
		  select
            v.customer_reg_no               as customerRegNo,      /* 일반회원검증.회원OP등록번호 */
            v.customer_verify_type          as customerVerifyType, /* 일반회원검증.회원검증종류 */
            v.customer_verify               as customerVerify,     /* 일반회원검증.회원검증값 */
            v.customer_verify_date          as customerVerifyDate, /* 일반회원검증.회원검증일시 */
            v.delete_date                   as deleteDate,         /* 일반회원검증.회원탈퇴일시 */
            v.create_date                   as createDate,         /* 일반회원검증.생성일시 */
            v.create_id                     as createId,           /* 일반회원검증.생성자 ID */
            v.update_date                   as updateDate,         /* 일반회원검증.변경일시 */
            v.update_id                     as udpateId,           /* 일반회원검증.변경자 ID */
            c.customer_name_kor             as customerNameKor     /* 일반회원.회원명 한글 */
        from spt_customer_verify_profile v, spt_customer_info_profile c /* 일반회원검증 */
        where 1=1
        and c.customer_reg_status != 'G005004'
  		and c.delete_date is null
        and c.customer_reg_no = v.customer_reg_no
        and c.customer_reg_no = #{customerRegNo}
        and v.customer_verify_type = 'G007001'
	</script>""")
	MemberVerifyRes selectMemberVerifyProfile(String customerRegNo)

	/**
	 * CI 통한 회원 정보 조회(비회원 인증시 사용)
	 * */
	@Select("""<script>
		SELECT 
			cust.customer_reg_no             	AS customerRegNo, 
			cust.customer_id					AS customerId,
		    cust.customer_reg_status         	AS customerRegStatus, 
		    cust.customer_name_kor           	AS customerNameKor, 
		    cust.customer_withdrawal_proc_yn 	AS customerWithdrawalProcYn, 
		    cust.customer_join_type          	AS customerJoinType, 
		    cust.temporary_member_yn         	AS temporaryMemberYn, 
		    verify.customer_verify           	AS customerVerifyCi
		FROM   spt_customer_info_profile cust 
		JOIN spt_customer_verify_profile verify 
		  ON verify.customer_verify = #{customerVerifyCi} 
		  AND verify.customer_verify_type = 'G007001' 
		  AND cust.customer_reg_no = verify.customer_reg_no
		  AND verify.customer_verify_date is not null
          AND verify.delete_date is null
	</script>""")
	TemporaryMemberInfoRes selecTemporaryMemberInfo(MemberReq memberReq)

	/**
	 * 일반회원 모바일 정보 insert
	 * @param memberReq
	 * @return
	 */
	@Insert("""<script>

		INSERT INTO spt_mobile_info (
		      customer_reg_no,
				  device_type,
				  device_unique_id,
				  <if test="companyCodeId != null">
				  company_code_id,
				  </if>
				  <if test="appKey != null">
				  app_key,
				  </if>
				  create_date,
				  create_id,
				  update_date,
				  update_id
				) values (
				  #{customerRegNo}
				  ,#{deviceType}
				  ,#{deviceUniqueId}
				  <if test="companyCodeId != null">
				  ,#{companyCodeId}
				  </if>
				  <if test="appKey != null">
				  ,#{appKey}
				  </if>
				  ,sysdate()
				  ,#{customerRegNo}
				  ,sysdate()
				  ,#{customerRegNo}

		) ON DUPLICATE KEY UPDATE
				  device_type = #{deviceType}
				  ,device_unique_id = #{deviceUniqueId}
				  <if test="companyCodeId != null">
				  ,company_code_id = #{companyCodeId}
				  </if>
				  <if test="appKey != null">
				  ,app_key = #{appKey}
				  </if>
				  ,update_date = sysdate()
				  ,update_id = #{customerRegNo}

	</script>""")
	int insertSptMobileInfo(MemberReq memberReq)
	
	/**
	 * 일반회원 모바일 정보 히스토리 insert
	 * @param memberReq
	 * @return
	 */
	@Insert("""<script>
		insert into spt_mobile_info_hist
		(
		  customer_reg_no,
		  mobile_info_seq,
		  device_type,
		  device_unique_id,
		  company_code_id,
		  app_key,
		  create_date,
		  create_id,
		  update_date,
		  update_id
		) 
			select
				a.customer_reg_no,
				ifnull((
				    select ifnull(max(cast(mobile_info_seq as SIGNED)), 0) + 1
				    from spt_mobile_info_hist
				    where customer_reg_no = a.customer_reg_no
				), 1),                /* 모바일 정보 히스토리 번호 */
				a.device_type,
				a.device_unique_id,
				a.company_code_id,
				a.app_key,
				a.create_date,
				a.create_id,
				a.update_date,
				a.update_id
			from spt_mobile_info a
			where a.customer_reg_no = #{customerRegNo}
	</script>""")
	int insertSptMobileInfoHist(MemberReq memberReq)
	
	
	/**
	 * ID 중복체크
	 * */
	@Select("""<script>
		  SELECT count(customer_id)
			FROM spt_customer_info_profile
		   WHERE 1 = 1
		     AND customer_id = #{customerId}
	</script>""")
	int selectFindUserId(MemberReq memberReq)

	/**
	 * ID 중복체크
	 * */
	@Select("""<script>
		select 
		  count(cust.customer_reg_no)
		FROM spt_customer_info_profile cust
		join spt_customer_verify_profile verify
		on cust.customer_reg_no = verify.customer_reg_no
		and verify.customer_verify_type = 'G007002'
		and verify.delete_date is null
		where cust.customer_id = #{customerId}
	</script>""")
	int selectFindSignId(MemberReq memberReq)

    /**
     * 모바일 push 메세지 수신 여부 변경
     * */
    @Update("""<script>
            update spt_customer_info_profile set
            customer_mobile_push_yn = #{customerMobilePushYn},  
            update_date = sysdate(),
            update_id = #{customerRegNo}
            where 1=1
            and customer_reg_no = #{customerRegNo}
        </script>""")
    public int changeMobilePush(LoginRes loginInfo)
    
	
	
	
	
	
	/** 탈퇴회원 프로파일 */
	@Insert("""<script>
	INSERT INTO leave_customer_info_profile(
	   customer_reg_no
	  ,customer_id
	  ,customer_password
	  ,customer_temp_password_yn
	  ,customer_chg_password_date
	  ,customer_expire_password_date
	  ,customer_final_password_date
	  ,customer_password_fail_cnt
	  ,customer_reg_status
	  ,customer_step
	  ,customer_name_kor
	  ,customer_name_eng
	  ,customer_phone
	  ,customer_email
	  ,customer_email_auth_yn
	  ,customer_email_reg_date
	  ,customer_birth_day
	  ,customer_sex
	  ,customer_email_receive_yn
	  ,customer_email_receive_date
	  ,customer_mobile_push_yn
	  ,customer_dataset_lock_yn
	  ,customer_reg_date
	  ,delete_date
	  ,integration_account_yn
	  ,customer_withdrawal_proc_yn
	  ,customer_join_type
	  ,temporary_member_yn
	  ,create_date
	  ,create_id
	  ,update_date
	  ,update_id
	)
	select
	   customer_reg_no
	  ,customer_id
	  ,customer_password
	  ,customer_temp_password_yn
	  ,customer_chg_password_date
	  ,customer_expire_password_date
	  ,customer_final_password_date
	  ,customer_password_fail_cnt
	  ,customer_reg_status
	  ,customer_step
	  ,customer_name_kor
	  ,customer_name_eng
	  ,customer_phone
	  ,customer_email
	  ,customer_email_auth_yn
	  ,customer_email_reg_date
	  ,customer_birth_day
	  ,customer_sex
	  ,customer_email_receive_yn
	  ,customer_email_receive_date
	  ,customer_mobile_push_yn
	  ,customer_dataset_lock_yn
	  ,customer_reg_date
	  ,delete_date
	  ,integration_account_yn
	  ,customer_withdrawal_proc_yn
	  ,customer_join_type
	  ,temporary_member_yn
	  ,sysdate()
	  ,#{customerRegNo}
	  ,sysdate()
	  ,#{customerRegNo}
	from spt_customer_info_profile
	WHERE customer_reg_no = #{customerRegNo}
        </script>""")
    int moveMemberInfo(MemberReq memberReq)
    
		
		
	/** 회원 테이블 초기화 */
	@Update("""<script>
	UPDATE spt_customer_info_profile SET
	  customer_reg_status = 'G005004'
	  ,customer_name_kor = #{customerRegNo}
	  ,customer_name_eng = #{customerRegNo}
	  ,customer_phone = #{customerRegNo}
	  ,customer_email = #{customerRegNo}
	  ,customer_birth_day = ''
	  ,customer_sex = ''
	  ,delete_date = sysdate()
	  ,customer_withdrawal_proc_yn = 'Y'
	  ,update_date = sysdate()
	  ,update_id = #{customerRegNo}
	WHERE customer_reg_no = #{customerRegNo}
        </script>""")
    int updateMemberInfo(MemberReq memberReq)
    
	  
	/** 회원 히스토리 테이블 초기화 */
	@Update("""<script>
	UPDATE spt_customer_info_profile_hist SET
	  customer_reg_status = ''
	  ,customer_step = ''
	  ,customer_name_kor = #{customerRegNo}
	  ,customer_name_eng = #{customerRegNo}
	  ,customer_phone = #{customerRegNo}
	  ,customer_email = #{customerRegNo}
	  ,customer_birth_day = ''
	  ,customer_sex = ''
	  ,delete_date = sysdate()
	  ,customer_withdrawal_proc_yn = 'Y'
	  ,create_date = sysdate()
	  ,create_id = #{customerRegNo}
	WHERE customer_reg_no = #{customerRegNo}
        </script>""")
    int updateMemberInfoHist(MemberReq memberReq)
    
	  
	
	/** 탈퇴 회원 검증 */
	@Insert("""<script>
			INSERT INTO leave_customer_verify_profile(
			   customer_reg_no
			  ,customer_verify_type
			  ,customer_verify
			  ,customer_verify_date
			  ,delete_date
			  ,create_date
			  ,create_id
			  ,update_date
			  ,update_id
			)
			SELECT
			   customer_reg_no
			  ,customer_verify_type
			  ,customer_verify
			  ,customer_verify_date
			  ,delete_date
			  ,sysdate()
			  ,#{customerRegNo}
			  ,sysdate()
			  ,#{customerRegNo}
			FROM spt_customer_verify_profile
			WHERE customer_reg_no = #{customerRegNo}
			and customer_verify_type = #{customerVerifyType}
        </script>""")
    int insertLeaveMemberInfo(MemberReq memberReq)
    
	  
	  
	  /** 일반회원 검증 테이블 초기화 */
	@Update("""<script>
	UPDATE spt_customer_verify_profile SET
	  customer_verify = ''
	  ,delete_date = sysdate()
	  ,update_date = sysdate()
	  ,update_id = #{customerRegNo}
	WHERE customer_reg_no = #{customerRegNo}
        </script>""")
    int updateOriginMemberInfo(MemberReq memberReq)
    
	
	/** 일반회원 검증 히스토리 테이블 초기화 */
	@Update("""<script>
	UPDATE spt_customer_verify_profile_hist SET
	  customer_verify = ''
	  ,delete_date = sysdate()
	  ,create_date = sysdate()
	  ,create_id = #{customerRegNo}
	WHERE customer_reg_no = #{customerRegNo}
        </script>""")
    int updateOriginMemberInfoHist(MemberReq memberReq)

	@Select("""<script>
		select count(*) as cnt
		from spt_customer_company_terms_profile
		where customer_reg_no = #{customerRegNo}
		and company_code_id = #{companyCodeId}
		and company_terms_type = #{companyTermsType}
	</script>""")
    int checkCompanyTermsProfile(SptCustomerCompanyTermsProfileRes terms);

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
	int updateCompanyTermsProfile(SptCustomerCompanyTermsProfileRes terms);

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
	int insertCompanyTermsProfile(SptCustomerCompanyTermsProfileRes terms);

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
	int insertCompanyTermsProfileHist(SptCustomerCompanyTermsProfileRes terms);
	
	
	 @Update("""<script>
		update spt_customer_info_profile set
			customer_reg_status = #{customerRegStatus},
			delete_date = sysdate(),
			update_date = sysdate(),
			update_id = #{customerRegNo}
		where 1=1
		and customer_reg_no = #{customerRegNo}
	</script>""")
	int updateSptMbrSecesInfo(MemberReq memberReq);
	
}
