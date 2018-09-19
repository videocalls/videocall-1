package kr.co.koscom.oppfm.terms.dao

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.terms.model.TermsReq
import kr.co.koscom.oppfm.terms.model.TermsRes
import org.apache.ibatis.annotations.Select

/**
 * ClassName : TermsContentsMapper
 * *
 * Description : 
 *
 * Created by LSH on 2017. 5. 18..
 */
@Mapper
interface TermsContentsMapper {

    @Select("""<script>
            select  
                  (SELECT code_name_kor
                    FROM com_system_code
                   WHERE     code_extend1 = 'Y'
                         AND system_grp_code = left(tc.customer_terms_type, 4)
                         AND system_code = right(tc.customer_terms_type, 3))
                    AS customerTermsTypeName,                     /* 일반회원약관내용.회원동의종류명 */
                 tc.customer_terms_type AS customerTermsType,      /* 일반회원약관내용.회원동의종류 */
                 tc.customer_terms_content_reg_seq AS customerTermsContentRegSeq, /* 일반회원약관내용.동의서약관내용등록번호 */
                 tc.customer_terms_content_ver AS customerTermsContentVer, /* 일반회원약관내용.동의서약관내용버전 */
                 tc.customer_terms_content AS customerTermsContent, /* 일반회원약관내용.동의서약관내용 */
                 tc.customer_email_yn AS customerEmailYn,    /* 일반회원약관내용.동의서email고지여부 */
                 tc.customer_email_date AS customerEmailDate, /* 일반회원약관내용.동의서email고지일자 */
                 tc.create_date AS createDate,                       /* 일반회원약관내용.생성일시 */
                 tc.create_id AS createId,                          /* 일반회원약관내용.생성자ID */
                 tc.update_date AS updateDate,                       /* 일반회원약관내용.변경일시 */
                 tc.update_id AS updateId                           /* 일반회원약관내용.변경자ID */
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
               AND customer_terms_system_kind = #{customerTermsSystemKind}
               AND customer_terms_type in
               <foreach item="item" index="index" collection="searchCustomerTermsTypeList" open="(" separator="," close=")">
                    #{item}
               </foreach>
                group by a.customer_terms_type
            ) a
            left join spt_customer_terms_content_profile tc
            on a.customer_terms_type = tc.customer_terms_type
            and a.customer_terms_content_ver = tc.customer_terms_content_ver
            and tc.customer_terms_system_kind = #{customerTermsSystemKind}
    </script>""")
    List<TermsRes> selectTermsContentsList(TermsReq termsReq);

    @Select("""<script>
           SELECT (SELECT code_name_kor
                      FROM com_system_code
                     WHERE code_extend1 = 'Y'
                       AND system_grp_code = left(tc.customer_terms_type, 4)
                       AND system_code = right(tc.customer_terms_type, 3))
                        AS customerTermsTypeName,                   /* 일반회원약관내용.회원동의종류명 */
                     tc.customer_terms_type          AS customerTermsType, /* 일반회원약관내용.회원동의종류 */
                     tc.customer_terms_content_reg_seq AS customerTermsContentRegSeq, /* 일반회원약관내용.동의서약관내용등록번호 */
                     tc.customer_terms_content_ver   AS customerTermsContentVer, /* 일반회원약관내용.동의서약관내용버전 */
                     tc.customer_terms_content       AS customerTermsContent, /* 일반회원약관내용.동의서약관내용 */
                     tc.customer_email_yn            AS customerEmailYn, /* 일반회원약관내용.동의서email고지여부 */
                     tc.customer_email_date          AS customerEmailDate, /* 일반회원약관내용.동의서email고지일자 */
                     tc.create_date                  AS createDate,    /* 일반회원약관내용.생성일시 */
                     tc.create_id                    AS createId,     /* 일반회원약관내용.생성자ID */
                     tc.update_date                  AS updateDate,    /* 일반회원약관내용.변경일시 */
                     tc.update_id                    AS updateId      /* 일반회원약관내용.변경자ID */
            FROM (  SELECT *
                      FROM spt_customer_terms_content_profile
                     WHERE     1 = 1
						AND customer_terms_content_reg_seq = #{customerTermsContentRegSeq}
						AND customer_terms_type = #{customerTermsType}
						AND customer_terms_system_kind = #{customerTermsSystemKind}
                  ORDER BY customer_terms_type ASC, customer_terms_content_reg_seq DESC)
                 tc
           WHERE 1 = 1
        GROUP BY tc.customer_terms_type
    </script>""")
    TermsRes selectTermsContentDetail(TermsReq termsReq);
}