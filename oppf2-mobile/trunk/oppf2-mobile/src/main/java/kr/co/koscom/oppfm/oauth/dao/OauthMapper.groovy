package kr.co.koscom.oppfm.oauth.dao

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.oauth.model.OauthAppReq
import kr.co.koscom.oppfm.oauth.model.OauthAppRes
import kr.co.koscom.oppfm.oauth.model.OauthScopeReq
import kr.co.koscom.oppfm.oauth.model.OauthTokenRes
import kr.co.koscom.oppfm.oauth.model.OauthUserReq
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.SelectKey
import org.apache.ibatis.annotations.Update

/**
 * @author lee
 * @date 2017-05-17
 */

@Mapper
public interface OauthMapper {

    @Insert("""<script>
       insert into spt_customer_provider_terms_profile(
        customer_reg_no,
        client_id,
        provider_seq,
        session_id,
        customer_terms_auth_yn,
        customer_terms_auth_date,
        create_date,
        create_id,
        update_date,
        update_id
        )
        VALUES
        (
        #{customerRegNo},
        #{clientId},
        #{providerSeq},
        #{sessionId},
        'Y',
        sysdate(),
        sysdate(),
        #{customerRegNo},
        sysdate(),
        #{customerRegNo}
        )
    </script>""")
    @SelectKey(statement="""<script>
       select  concat(date_format(sysdate(),'%Y%m%d'),
            lpad((
            select ifnull( max(cast(right(provider_seq,4) as signed)), 0)+1
            from spt_customer_provider_terms_profile
            where left(provider_seq, 8) = date_format(sysdate(),'%Y%m%d')
            and customer_reg_no =  #{customerRegNo}
            and client_id =  #{clientId}
            ), 4, '0')) as providerSeq
    </script>""",keyProperty="providerSeq", before=true, resultType=String.class)
    public void insertOauthTermsProfile(OauthScopeReq oauthScopeReq);

    @Insert("""<script>
       insert into spt_customer_provider_scope_profile(
        customer_reg_no,
        client_id,
        provider_seq,
        scope_code,
        scope_use_yn,
        create_date,
        create_id,
        update_date,
        update_id
        )
        VALUES
        (
        #{customerRegNo},
        #{clientId},
        #{providerSeq},
        #{scope},
        'Y',
        sysdate(),
        #{customerRegNo},
        sysdate(),
        #{customerRegNo}
        )
    </script>""")
    public void insertOauthScopeProfile(OauthScopeReq oauthScopeReq);

    @Select("""<script>
       select customer_reg_no as customerRegNo /* 회원 OP 등록 번호 */
         from spt_customer_info_profile
        where customer_id = #{customerId}
          and customer_withdrawal_proc_yn = 'N' /*탈퇴하지 않은 회원*/
         limit 1
    </script>""")
    public String selectCustomerRegNo(String customerId);

    @Update("""<script>
       update spt_customer_account_oauth_token_info
            set delete_date = sysdate()
            where customer_reg_no = #{customerRegNo}
    </script>""")
    public void deleteCustomerAcntOauthToken(OauthTokenRes oauthTokenRes);

    @Insert("""<script>
       insert into spt_customer_account_oauth_token_info(
          customer_reg_no
        , oauth_seq
        , access_token
        , refresh_token
        , scope
        , token_type
        , expires_in
        , use_yn
        , update_date
        , update_id
        , create_date
        , create_id
        )
        values (
         #{customerRegNo}
        ,#{oauthSeq}
        ,#{accessToken}
        ,#{refreshToken}
        ,#{rspScope}
        ,#{tokenType}
        ,#{expiresIn}
        ,'Y'
        ,sysdate()        /* 생성일시 */
        ,#{customerRegNo}  /* 생성자 ID */
        ,sysdate()        /* 변경일시 */
        ,#{customerRegNo}  /* 변경자 ID */
        )
    </script>""")
    @SelectKey(statement="""<script>
       select  concat(date_format(sysdate(),'%Y%m%d'),
            lpad((
            select ifnull( max(cast(right(oauth_seq,4) as signed)), 0)+1
            from spt_customer_account_oauth_token_info
            where customer_reg_no = #{customerRegNo}
            and left(oauth_seq,8) = concat(date_format(sysdate(),'%Y%m%d'))
            ), 4, '0')
            ) as oauthSeq
    </script>""",keyProperty="oauthSeq", before=true, resultType=String.class)
    public void insertCustomerAcntOauthToken(OauthTokenRes oauthTokenRes);

    @Select("""<script>
       select count(*)
        from spt_customer_service_profile
       where customer_reg_no = (
                                 select customer_reg_no
                                   from spt_customer_info_profile
                                   where customer_id = #{userId}
                                     and delete_date is null
                                     and customer_withdrawal_proc_yn = 'N'
                                     and temporary_member_yn = 'N'
                               )
                        and app_id = (
                                      select app_id as appId
                                        from com_app_view a
                                       where a.app_key = #{clientId}
                                         and a.app_status = 'G022002'
                                     )
          and terms_reg_no is not null
          and delete_date is null
    </script>""")
    public int selectCustomerAppCheck(OauthUserReq oauthUserReq);

    @Select("""<script>
       select count(otp.customer_otp_id)
        from spt_customer_info_profile a
                left join (
                            select  sub_a.customer_reg_no,
                                    sub_a.customer_otp_id
                            from spt_customer_otp_profile sub_a ,
                                 spt_customer_info_profile sub_b
                            where customer_otp_status not in('G019003') /*폐기 아님*/
                             and sub_b.customer_id = #{userId}
                             and sub_a.customer_reg_no = sub_b.customer_reg_no
                        ) as otp on a.customer_reg_no = otp.customer_reg_no
       where 1=1
         and a.customer_id = #{userId}
         and otp.customer_otp_id is not null
    </script>""")
    public int selectOtpCheck(String userId);

    @Select("""<script>
          select app_id as appId,
                 app_name as appName,
                 (
                 select company_name_kor_alias
                   from com_company_profile sub
                   where sub.company_code_id = com_app_view.company_code_id
                 ) as companyName
            from com_app_view
           where app_key = #{clientId}
             and app_status = 'G022002'
    </script>""")
    public OauthAppRes selectSvcAppInfo(OauthAppReq oauthAppReq);

}