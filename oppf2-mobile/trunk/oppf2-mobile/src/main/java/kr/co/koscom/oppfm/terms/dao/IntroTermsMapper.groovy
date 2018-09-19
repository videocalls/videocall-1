package kr.co.koscom.oppfm.terms.dao

import org.apache.ibatis.annotations.Select

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.terms.model.IntroTermsComponyRes
import kr.co.koscom.oppfm.terms.model.IntroTermsInfoRes
import kr.co.koscom.oppfm.terms.model.IntroTermsReq


/**
 * @author unionman
 *
 */
@Mapper
public interface IntroTermsMapper {


    /**
     * 기본 서비스 이용약관 조회
     * @return
     */
    @Select("""<script>
            select  
                    'cust'                              as termsType
                    ,customer_terms_type                as companyCodeId
                    ,customer_terms_content_reg_seq     as termsKey
                    ,customer_terms_content             as termsContents
            from spt_customer_terms_content_profile
            where customer_terms_type = 'G008001'
            and customer_terms_system_kind = (
                select concat(system_grp_code, system_code) as system_code
                from com_system_code
                where system_grp_code = 'G003'
                and code_extend1 = 'spt'
            )
            order by customer_terms_content_reg_seq desc
            limit 0, 1    
    </script>""")
    public IntroTermsInfoRes selectTermsServiceContentsDefault(IntroTermsReq termsReq);
    
    /**
     * 업체별 서비스 이용약관 조회
     * @return
     */
    @Select("""<script>
                select 
                        'comp'                                              as termsType
                        ,company_code_id                            as companyCodeId
                        ,company_terms_content_reg_seq   as termsKey
                        ,company_terms_content                 as termsContents
                from spt_customer_company_terms_content_profile
                where company_code_id                        = #{companyCodeId}
                and company_terms_type                      = 'G031001'
                and company_terms_content_reg_seq   = #{termsKey}
            </script>""")
    public IntroTermsInfoRes selectTermsServiceContentsCompony(IntroTermsReq termsReq);
    
    
    /**
     * 약관이 등록된 기업정보 조회
     * @return
     */
    @Select("""<script>
                select 
                        termsType
                        ,companyCodeId
                        ,termsKey
                        ,companyName
                from(
                    select
                             'cust'                                               as termsType
                            ,a.customer_terms_type                              as companyCodeId
                            ,a.customer_terms_content_reg_seq    as termsKey
                            ,(
                                select  com_system_code.code_name_kor as code_name
                                from com_system_code
                                where concat(system_grp_code, system_code) = a.customer_terms_type
                            ) as companyName
                    from spt_customer_terms_content_profile a
                    where customer_terms_type = 'G008001'
                    and customer_terms_system_kind = (
                        select concat(system_grp_code, system_code) as system_code
                        from com_system_code
                        where system_grp_code = 'G003'
                        and code_extend1 = 'spt'
                    )
                    order by customer_terms_content_reg_seq desc
                    limit 0, 1  
                    ) a    
                    union all
                    select  a.termsType, a.companyCodeId, a.termsKey, a.companyName
                    from(
                    select
                             'comp'                                               as termsType
                            ,a.company_code_id                           as companyCodeId
                            ,a.company_terms_content_reg_seq    as termsKey
                            ,b.company_name_kor_alias                as companyName
                    from(
                        /* 기업약관 */
                        select  company_code_id,
                                company_terms_type,
                                company_terms_content_reg_seq,
                                company_terms_content_ver,
                                company_terms_content
                        from spt_customer_company_terms_content_profile
                        where company_terms_type = 'G031001'    /* 기업 서비스 이용약관 */ 
                        and (company_code_id, company_terms_content_ver) in (
                            select  company_code_id, 
                                    max(company_terms_content_ver) as company_terms_content_ver
                            from spt_customer_company_terms_content_profile
                            where company_terms_type = 'G031001'    /* 기업 서비스 이용약관 */
                            group by company_code_id
                        )
                    ) a
                join com_company_profile b on a.company_code_id = b.company_code_id
                order by b.exposure_order asc, b.company_name_kor_alias asc
                ) a     
    </script>""")
    public List<IntroTermsComponyRes> selectTermsServiceComponyList();
        

    

    }
