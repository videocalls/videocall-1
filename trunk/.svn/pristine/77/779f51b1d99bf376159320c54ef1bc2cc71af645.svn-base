package kr.co.koscom.oppfm.login.dao

import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileRes
import kr.co.koscom.oppfm.terms.model.TermsRes
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.login.model.CertPasswordFailReq
import kr.co.koscom.oppfm.login.model.CertPasswordFailRes
import kr.co.koscom.oppfm.login.model.FindIdReq
import kr.co.koscom.oppfm.login.model.LoginReq
import kr.co.koscom.oppfm.login.model.LoginRes
import kr.co.koscom.oppfm.login.model.ModifyPasswordReq

/**
 *
 * LoginMapper
 *
 * Created by chungyeol.kim on 2017-04-28.
 * * Modify by sh.lee on 2017-05-16.
 */
@Mapper
interface LoginMapper {

    @Select("""<script>
        select   a.customer_reg_no as customerRegNo /* 회원 OP 등록 번호 */
                ,a.customer_join_type as customerJoinType /* 회원 가입 타입 */
                ,a.customer_id as customerId /* 회원 ID */
                ,a.customer_name_kor as customerNameKor /* 회원명 한글 */
                ,a.customer_name_eng as customerNameEng /* 회원명 영문 */
                ,dec_char_sel(a.customer_phone,10,'ARIA','spt_customer_info_profile','customer_phone',user(),connection_id())  as customerPhone
                <if test='customerPassword != null'>
                ,if(a.customer_password = enc_char_ins(#{customerPassword},10,'SHA','spt_customer_info_profile','customer_password',user(),connection_id()), 'Y', 'N') as customerPasswordYn /* 회원 패스워드 유효여부 */
                </if>
                ,a.customer_temp_password_yn as customerTempPasswordYn /* 임시비밀번호여부 */
                ,a.customer_withdrawal_proc_yn as customerWithdrawalProcYn /* 탈퇴처리 여부 */
                ,a.customer_password_fail_cnt as customerPasswordFailCnt /* 비밀번호 실패 카운트 */
                ,if(DATE_FORMAT(now(), '%Y%m%d') <![CDATA[ >=  ]]> a.customer_expire_password_date, 'Y', 'N') as customerExpirePasswordYn /* 비밀번호 변경 예정일 이내 여부 */
                ,if(DATE_FORMAT(now(), '%Y%m%d') <![CDATA[ >=  ]]> a.customer_final_password_date, 'Y', 'N') as customerFinalPasswordYn /* 비밀번호 변경 최종 예정일 이내 여부 */
                ,a.customer_email_auth_yn as customerEmailAuthYn /* 이메일 인증여부 */
                ,a.customer_reg_status as customerRegStatus /* 회원가입 상태 */
                ,if(ifnull(a.delete_date, 'N') = 'N', 'N', 'Y') as deleteYn /* 탈퇴여부 */
                ,a.customer_step as customerStep /* 회원가입스텝 */
                ,a.integration_account_yn as integrationAccountYn /* 통합계좌메뉴 사용여부 */
                ,a.temporary_member_yn as temporaryMemberYn /* 임시회원 여부 */
        from    spt_customer_info_profile a
        where   a.customer_id = #{customerId}
        and customer_withdrawal_proc_yn = 'N' /*탈퇴하지 않은 회원*/
        order by a.customer_reg_no desc /* 최근의 탈퇴회원이나 최근에 가입 및 재가입한 회원 */
        limit 0,1
    </script>""")
    LoginRes selectLoginProfile(LoginReq loginReq);

    @Select("""<script>
        select count(1) as count
        from spt_customer_info_profile
        where customer_id = #{customerId}
    </script>""")
    int selectTest(LoginReq loginReq);

    @Select("""<script>
        select   a.customer_reg_no as customerRegNo /* 회원 OP 등록 번호 */
                ,a.customer_id as customerId /* 회원 ID */
                ,a.customer_name_kor as customerNameKor /* 회원명 한글 */
                ,a.customer_name_eng as customerNameEng /* 회원명 영문 */
                ,dec_char_sel(a.customer_phone,10,'ARIA','spt_customer_info_profile','customer_phone',user(),connection_id())  as customerPhone
                ,if(a.customer_password = enc_char_ins('1234',10,'SHA','spt_customer_info_profile','customer_password',user(),connection_id()), 'Y', 'N') as customerPasswordYn /* 회원 패스워드 유효여부 */
                ,a.customer_temp_password_yn as customerTempPasswordYn /* 임시비밀번호여부 */
                ,a.customer_password_fail_cnt as customerPasswordFailCnt /* 비밀번호 실패 카운트 */
                ,a.customer_withdrawal_proc_yn as customerWithdrawalProcYn /* 탈퇴처리 여부 */
                ,if(DATE_FORMAT(now(), '%Y%m%d') <![CDATA[ >=  ]]> a.customer_expire_password_date, 'Y', 'N') as customerExpirePasswordYn /* 비밀번호 변경 예정일 이내 여부 */
                ,if(DATE_FORMAT(now(), '%Y%m%d') <![CDATA[ >=  ]]> a.customer_final_password_date, 'Y', 'N') as customerFinalPasswordYn /* 비밀번호 변경 최종 예정일 이내 여부 */
                ,a.customer_email_auth_yn as customerEmailAuthYn /* 이메일 인증여부 */
                ,a.customer_reg_status as customerRegStatus /* 회원가입 상태 */
                ,if(ifnull(a.delete_date, 'N') = 'N', 'N', 'Y') as deleteYn /* 탈퇴여부 */
                ,a.customer_step as customerStep /* 회원가입스텝 */
                ,a.integration_account_yn as integrationAccountYn /* 통합계좌메뉴 사용여부 */
                ,a.temporary_member_yn as temporaryMemberYn /* 임시회원 여부 */
        from    spt_customer_info_profile a
        where   a.customer_id = #{customerId}
        and a.customer_withdrawal_proc_yn = 'N' /*탈퇴하지 않은 회원*/
        and a.customer_reg_status = 'G005002'
        order by a.customer_reg_no desc /* 최근의 탈퇴회원이나 최근에 가입 및 재가입한 회원 */
        limit 0,1
    </script>""")
    LoginRes selectTestLoginProfile(LoginReq loginReq);


    @Select("""<script>
           SELECT customer_id as customerId
             FROM spt_customer_info_profile a
            WHERE      a.customer_withdrawal_proc_yn = 'N'
                   AND a.customer_email = enc_char_ins(#{customerEmail},
                                                       10,
                                                       'ARIA',
                                                       'spt_customer_info_profile',
                                                       'customer_email',
                                                       user(),
                                                       connection_id())
                   AND a.customer_withdrawal_proc_yn = 'N' /*탈퇴하지 않은 회원*/
                   AND a.customer_name_kor = #{customerNameKor}
                   AND a.customer_reg_status = 'G005002'
				   AND a.temporary_member_yn = 'N'
            order by a.customer_reg_no desc /* 최근의 탈퇴회원이나 최근에 가입 및 재가입한 회원 */
            limit 0,1
    </script>""")
    String selectFindLoignId(FindIdReq findIdReq);

    @Update("""<script>
        UPDATE spt_customer_info_profile
           SET customer_password =
               enc_char_ins(#{customerPassword},
                               10,
                               'SHA',
                               'spt_customer_info_profile',
                               'customer_password',
                               user(),
                               connection_id()),
               customer_temp_password_yn = 'N',
               customer_chg_password_date = sysdate(),
               customer_expire_password_date =
                  date_format(date_add(sysdate(), INTERVAL #{customerExpirePasswordDate} MONTH),
                              '%Y%m%d'),
               customer_final_password_date =
                  date_format(date_add(sysdate(), INTERVAL #{customerFinalPasswordDate} MONTH),
                              '%Y%m%d'),
               customer_password_fail_cnt = 0,
               customer_otp_fail_cnt = 0,
               customer_reg_status = 'G005002',
               update_date = now(),
               update_id = #{customerRegNo}
         WHERE customer_reg_no = #{customerRegNo}
    </script>""")
    int updatePassword(ModifyPasswordReq modifyPasswordReq);

    @Update("""<script>
        UPDATE spt_customer_info_profile
           SET <if test='customerRegStatus != "G005003"'>
                    customer_password_fail_cnt =
                    if(#{customerPasswordFailType} = 'Y', customer_password_fail_cnt + 1, 0),
               </if>
               <if test='customerPasswordFailType == "Y"'>
                   customer_otp_fail_cnt = 0, /* OTP 인증 실패횟수 */
               </if>
               customer_reg_status = #{customerRegStatus},
               update_date = now(),
               update_id = #{customerRegNo}
         WHERE customer_reg_no = #{customerRegNo}
    </script>""")
    void updateLoginFailCnt(LoginReq loginReq);

    @Insert("""<script>
        INSERT INTO spt_customer_info_profile_hist(customer_reg_no,
                                                   customer_seq,
                                                   customer_id,
                                                   customer_password,
                                                   customer_temp_password_yn,
                                                   customer_chg_password_date,
                                                   customer_expire_password_date,
                                                   customer_final_password_date,
                                                   customer_password_fail_cnt,
                                                   customer_otp_fail_cnt,
                                                   customer_reg_status,
                                                   customer_step,
                                                   customer_name_kor,
                                                   customer_name_eng,
                                                   customer_phone,
                                                   customer_email,
                                                   customer_email_auth_yn,
                                                   customer_email_reg_date,
                                                   customer_birth_day,
                                                   customer_sex,
                                                   customer_email_receive_yn,
                                                   customer_email_receive_date,
                                                   customer_reg_date,
                                                   delete_date,
                                                   create_date,
                                                   create_id)
           SELECT customer_reg_no,
                  ifnull((SELECT ifnull(max(cast(customer_seq AS SIGNED)), 0) + 1
                            FROM spt_customer_info_profile_hist
                           WHERE customer_reg_no = a.customer_reg_no),
                         1),
                  customer_id,
                  customer_password,
                  customer_temp_password_yn,
                  customer_chg_password_date,
                  customer_expire_password_date,
                  customer_final_password_date,
                  customer_password_fail_cnt,
                  customer_otp_fail_cnt,
                  customer_reg_status,
                  customer_step,
                  customer_name_kor,
                  customer_name_eng,
                  customer_phone,
                  customer_email,
                  customer_email_auth_yn,
                  customer_email_reg_date,
                  customer_birth_day,
                  customer_sex,
                  customer_email_receive_yn,
                  customer_email_receive_date,
                  customer_reg_date,
                  delete_date,
                  update_date,
                  update_id
             FROM spt_customer_info_profile a
            WHERE customer_reg_no = #{customerRegNo}
    </script>""")
    void insertLoginHist(LoginReq loginReq);

    /*공인인증서 로그인*/
    @Select("""<script>
  SELECT a.customer_reg_no                            AS customerRegNo /* 회원 OP 등록 번호 */
         ,a.customer_id                                AS customerId /* 회원 ID */
         ,a.customer_name_kor                          AS customerNameKor /* 회원명 한글 */
         ,a.customer_join_type                         AS customerJoinType /* 회원 가입 타입 */
         ,if(DATE_FORMAT(now(), '%Y%m%d') <![CDATA[ >=  ]]> a.customer_expire_password_date, 'Y', 'N') as customerExpirePasswordYn /* 비밀번호 변경 예정일 이내 여부 */
         ,if(DATE_FORMAT(now(), '%Y%m%d') <![CDATA[ >=  ]]> a.customer_final_password_date, 'Y', 'N') as customerFinalPasswordYn /* 비밀번호 변경 최종 예정일 이내 여부 */
         ,a.customer_name_eng                          AS customerNameEng /* 회원명 영문 */
         ,dec_char_sel(a.customer_phone,
                      10,
                      'ARIA',
                      'spt_customer_info_profile',
                      'customer_phone',
                      user(),
                      connection_id())
            AS customerPhone
         ,a.customer_temp_password_yn                  AS customerTempPasswordYn /* 임시비밀번호여부 */
         ,a.customer_withdrawal_proc_yn                AS customerWithdrawalProcYn /* 탈퇴처리 여부 */
         ,a.customer_password_fail_cnt                 AS customerPasswordFailCnt /* 비밀번호 실패 카운트 */
         ,a.customer_email_auth_yn                     AS customerEmailAuthYn /* 이메일 인증여부 */
         ,a.customer_reg_status                        AS customerRegStatus /* 회원가입 상태 */
         ,if(ifnull(a.delete_date, 'N') = 'N', 'N', 'Y') AS deleteYn /* 탈퇴여부 */
         ,b.customer_verify                            AS customerVerify
         ,a.customer_step                              AS customerStep /* 회원가입스텝 */
         ,a.integration_account_yn                     AS integrationAccountYn /* 통합계좌메뉴 사용여부 */
         ,if(b.customer_verify = #{customerVerifyDn}, 'Y', 'N') as customerVerifyYn /* 회원 DN값 유효여부 */
         ,a.temporary_member_yn as temporaryMemberYn /* 임시회원 여부 */
    FROM spt_customer_info_profile a
         LEFT JOIN spt_customer_verify_profile b
            ON     a.customer_reg_no = b.customer_reg_no
               AND b.customer_verify_type = 'G007002'
   WHERE a.customer_id = #{customerId}
   AND b.customer_verify = #{customerVerifyDn}
   AND a.customer_withdrawal_proc_yn = 'N' /*탈퇴하지 않은 회원*/
    </script>""")
    LoginRes selectLoginProfileDn(LoginReq loginReq);

    @Update("""<script>
        UPDATE spt_customer_info_profile
           SET customer_join_type = 'G039003'
         WHERE customer_reg_no = #{customerRegNo}
    </script>""")
    int updateCustomerJoinType(LoginRes resultLoginRes);
    
    /**
     * 사용자 패스워드 실패 카운트 조회
     * @param resultLoginRes
     * @return
     */
    @Update("""<script>
        UPDATE spt_customer_info_profile a, spt_customer_verify_profile b
           SET  a.customer_password_fail_cnt = #{currentTryCount}
        where   a.customer_reg_no       = b.customer_reg_no
        AND  b.customer_verify_type = 'G007002'
        and  b.customer_verify      = #{customerVerifyDn}       
        and     a.customer_withdrawal_proc_yn = 'N' /*탈퇴하지 않은 회원*/;
    </script>""")
    public int updatePasswordFailCnt(CertPasswordFailReq certPasswordFailReq);
    
    /**
     * 사용자 패스워드 실패 카운트 조회
     * @param resultLoginRes
     * @return
     */
    @Select("""<script>
            select  
                a.customer_password_fail_cnt  as passwordFailCnt
            from    spt_customer_info_profile a
                     LEFT JOIN  spt_customer_verify_profile b 
                            ON  a.customer_reg_no       = b.customer_reg_no
                            AND b.customer_verify_type  = 'G007002'
            where   b.customer_verify = #{customerVerifyDn} /*탈퇴하지 않은 회원*/
            and     a.customer_withdrawal_proc_yn = 'N' /*탈퇴하지 않은 회원*/
    </script>""")
    public CertPasswordFailRes selectPasswordFailCnt(CertPasswordFailReq certPasswordFailReq);

    /**
     * 로그인후 이용약관 재동의 여부
     * */
    @Select("""<script>
        select  a.customer_terms_type               as customerTermsType,
                c.code_name_kor                     as customerTermsTypeName,
                a.customer_terms_content_reg_seq    as customerTermsContentRegSeq,
                a.customer_terms_content            as customerTermsContent
        from(
            /* 약관정보 */
            select  a.customer_terms_type,
                    b.customer_terms_content_reg_seq,
                    b.customer_terms_content
            from(
                select  a.customer_terms_type,
                        max(a.customer_terms_content_ver + 0) as customer_terms_content_ver
                from spt_customer_terms_content_profile a
                where customer_terms_type in (
                    select concat(system_grp_code, system_code) as system_code
                    from com_system_code
                    where system_grp_code = 'G008'
                    and use_yn = 'Y'
                    and delete_date is null
                    and code_extend2 = 'Y'
                )
                and customer_terms_system_kind = 'G003002'
                group by a.customer_terms_type
            ) a
            left join spt_customer_terms_content_profile b
            on a.customer_terms_type = b.customer_terms_type
            and a.customer_terms_content_ver = b.customer_terms_content_ver
            and b.customer_terms_system_kind = 'G003002'
        ) a
        left join (
            /* 사용자 약관동의 정보 */
            select *
            from spt_customer_terms_profile
            where customer_reg_no = #{customerRegNo}
            and delete_date is null
            and customer_terms_auth_yn = 'Y'
        ) b
        on a.customer_terms_type = b.customer_terms_type
        and a.customer_terms_content_reg_seq = b.customer_terms_content_reg_seq
        left join (
            select *
            from com_system_code
            where system_grp_code = 'G008'
        ) c on concat(c.system_grp_code, c.system_code) = a.customer_terms_type
        where b.customer_reg_no is null
        order by c.code_seq asc 
    </script>""")
    List<TermsRes> selectTermsResList(LoginRes resultLoginRes);

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
                      max(company_terms_content_ver+0) as company_terms_content_ver
              from spt_customer_company_terms_content_profile
              where company_terms_type = 'G031001'
            group by company_code_id
          )
      ) a
      join (
          select *
          from spt_customer_company_terms_profile
          where customer_reg_no = #{customerRegNo}
          and company_terms_type = 'G031001'
          and delete_date is null
      ) b
      on b.company_code_id = a.company_code_id
      and b.company_terms_type = a.company_terms_type
      and b.company_terms_content_reg_seq != a.company_terms_content_reg_seq
      join com_company_profile c on a.company_code_id = c.company_code_id
      order by c.exposure_order asc, c.company_name_kor_alias asc
    </script>""")
    List<SptCustomerCompanyTermsProfileRes> selectCompanyTermsResList(LoginRes resultLoginRes);
}