package kr.co.koscom.oppfm.intAcnt.dao

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.intAcnt.model.AppAccountProfileRes
import kr.co.koscom.oppfm.intAcnt.model.IntAccountReq
import kr.co.koscom.oppfm.intAcnt.model.IntAccountRes
import kr.co.koscom.oppfm.intAcnt.model.IntAccountTokenRes
import org.apache.ibatis.annotations.Select

/**
 * @author lee
 * @date 2017-05-19
 */
@Mapper
public interface IntAccountMapper {

    @Select("""<script>
       select
        a.app_id                    as appId,
        a.app_name                  as appName,
        c.terms_auth_yn             as termsAuthYn,
        a.subcompany_code_id        as subcompanyCodeId,
        a.pubcompany_code_id        as pubcompanyCodeId,
        pub.company_name_kor_alias  as pubcompanyName,
        pub.company_name_eng_alias  as pubcompanyEngName,
        a.customer_realaccount_no   as customerRealaccountNo,
        a.customer_vtaccount_no     as customerVtaccountNo,
        a.customer_vtaccount_alias  as customerVtaccountAlias,
        a.service_reg_no            as serviceRegNo,
        if(ifnull(b.account_reg_no, 'N') = 'N', 'N', 'Y') as useYn
        from(
        /* 데이터 포멧 */
        select  a.*,
        b.customer_realaccount_no,
        b.customer_vtaccount_no,
        b.customer_vtaccount_alias
        from(
        select  a.customer_reg_no,
        a.service_reg_no,
        a.app_id,
        a.app_name,
        a.subcompany_code_id,
        a.subexposure_order,
        c.api_id,
        b.api_name,
        c.company_code_id       as pubcompany_code_id,
        c.exposure_order        as pubexposure_order
        from(
        select  a.customer_reg_no,
        a.service_reg_no,
        b.app_id,
        b.app_name,
        b.company_code_id   as subcompany_code_id,
        c.exposure_order    as subexposure_order,
        a.api_id
        from(
        select  a.customer_reg_no,
        a.service_reg_no,
        b.app_id,
        b.api_id
        from(
        select  customer_reg_no,
        service_reg_no,
        app_id
        from spt_customer_service_profile
        where customer_reg_no = #{customerRegNo}
        and app_id = #{appId}
        and delete_date is null
        ) a, com_app_apilist_view b
        where a.app_id = b.app_id
        ) a
        left join com_app_view b on a.app_id = b.app_id
        left join com_app_info c on a.app_id = c.app_id
        where b.app_status = 'G022002'        /*app 상태 (CA 정보)*/
        ) a
        left join com_api_view b on a.api_id = b.api_id
        left join com_api_info c on a.api_id = c.api_id
        where c.exposure_yn = 'Y'
        and c.api_account_yn = 'Y'
        ) a,
        (
        select *
        from spt_customer_account_profile
        where customer_reg_no = #{customerRegNo}
        and customer_vtaccount_status = 'G009002'
        ) b
        where a.pubcompany_code_id = b.company_code_id
        ) a
        left join spt_customer_service_account_profile b
        on  a.customer_reg_no = b.customer_reg_no
        and a.service_reg_no = b.service_reg_no
        and a.app_id = b.app_id
        and a.api_id = b.api_id
        and a.customer_realaccount_no = b.customer_realaccount_no
        and b.delete_date is null
        left join (
        select  a.*,
        b.terms_auth_yn
        from(
        select  customer_reg_no,
        service_reg_no,
        app_id,
        terms_reg_no
        from spt_customer_service_profile
        where customer_reg_no = #{customerRegNo}
        and terms_reg_no is not null
        and delete_date is null
        and app_id = #{appId}
        ) a
        left join spt_customer_service_terms_profile b
        on a.customer_reg_no = b.customer_reg_no
        and a.terms_reg_no = b.terms_reg_no
        ) c on a.service_reg_no = c.service_reg_no
        join com_company_profile as pub on a.pubcompany_code_id = pub.company_code_id and pub.delete_date is null and pub.exposure_yn = 'Y'
        where c.terms_auth_yn = 'N'
        order by pub.company_name_kor_alias
    </script>""")
    public List<AppAccountProfileRes> selectIntAccountList(IntAccountReq IntAccountReq);

    @Select("""<script>
       select  access_token as accessToken
             , refresh_token as refreshToken
             , scope as scope
             , token_type as tokenType
             , expires_in as expiresIn
             , use_yn as useYn
             , delete_date as deleteDate
          from spt_customer_account_oauth_token_info
         where 1=1
           and customer_reg_no = #{customerRegNo}
           and use_yn = 'Y'
           and delete_date is null
           limit 1
    </script>""")
    public IntAccountTokenRes selectIntAccountOauthToken(String customerRegNo);

    @Select("""<script>
       select
        a.pubcompany_code_id        as comId,
        pub.company_name_eng_alias  as companyEngName,
        a.customer_vtaccount_no     as vtAccNo
        from(
        /* 데이터 포멧 */
        select  a.*,
        b.customer_realaccount_no,
        b.customer_vtaccount_no,
        b.customer_vtaccount_alias
        from(
        select  a.customer_reg_no,
        a.service_reg_no,
        a.app_id,
        a.app_name,
        a.subcompany_code_id,
        a.subexposure_order,
        c.api_id,
        b.api_name,
        c.company_code_id       as pubcompany_code_id,
        c.exposure_order        as pubexposure_order
        from(
        select  a.customer_reg_no,
        a.service_reg_no,
        b.app_id,
        b.app_name,
        b.company_code_id   as subcompany_code_id,
        c.exposure_order    as subexposure_order,
        a.api_id
        from(
        select  a.customer_reg_no,
        a.service_reg_no,
        b.app_id,
        b.api_id
        from(
        select  customer_reg_no,
        service_reg_no,
        app_id
        from spt_customer_service_profile
        where customer_reg_no = #{customerRegNo}
        and app_id = #{appId}
        and delete_date is null
        ) a, com_app_apilist_view b
        where a.app_id = b.app_id
        ) a
        left join com_app_view b on a.app_id = b.app_id
        left join com_app_info c on a.app_id = c.app_id
        where b.app_status = 'G022002'        /*app 상태 (CA 정보)*/
        /* and c.exposure_yn = 'Y' */
        ) a
        left join com_api_view b on a.api_id = b.api_id
        left join com_api_info c on a.api_id = c.api_id
        where c.exposure_yn = 'Y'
        and c.api_account_yn = 'Y'
        ) a,
        (
        select *
        from spt_customer_account_profile
        where customer_reg_no = #{customerRegNo}
        and customer_vtaccount_status = 'G009002'
        ) b
        where a.pubcompany_code_id = b.company_code_id
        ) a
        left join spt_customer_service_account_profile b
        on  a.customer_reg_no = b.customer_reg_no
        and a.service_reg_no = b.service_reg_no
        and a.app_id = b.app_id
        and a.api_id = b.api_id
        and a.customer_realaccount_no = b.customer_realaccount_no
        and b.delete_date is null
        left join (
        select  a.*,
        b.terms_auth_yn
        from(
        select  customer_reg_no,
        service_reg_no,
        app_id,
        terms_reg_no
        from spt_customer_service_profile
        where customer_reg_no = #{customerRegNo}
        and terms_reg_no is not null
        and delete_date is null
        and app_id = #{appId}
        ) a
        left join spt_customer_service_terms_profile b
        on a.customer_reg_no = b.customer_reg_no
        and a.terms_reg_no = b.terms_reg_no
        ) c on a.service_reg_no = c.service_reg_no
        join com_company_profile as pub on a.pubcompany_code_id = pub.company_code_id and pub.delete_date is null and pub.exposure_yn = 'Y'
        where c.terms_auth_yn = 'N'
          and b.account_reg_no is not null
          <if test="companyCodeId!=0">
          and a.pubcompany_code_id = #{companyCodeId}
          </if>
          <if test="vtAccountNo!=0">
          and a.customer_vtaccount_no = #{vtAccountNo}
          </if>
        order by pub.company_name_kor_alias
    </script>""")
    public List<IntAccountRes> selectFinTechIntAccountList(IntAccountReq IntAccountReq);
}
