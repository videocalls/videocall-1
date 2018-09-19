package kr.co.koscom.oppfm.terms.dao

import kr.co.koscom.oppfm.app.model.AppCreateReq
import kr.co.koscom.oppfm.app.model.AppTermsPubCompanyProfileReq
import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.terms.model.CommonTermsRes
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 *
 * CommonTermsMapper
 *
 * Created by chungyeol.kim on 2017-05-24.
 */
@Mapper
interface CommonTermsMapper {
    /**
     * (가) 동의서 조회
     * @param customerRegNo
     * @return
     */
    @Select("""<script>
		select
			 a.customer_reg_no    as customerRegNo
			,a.terms_reg_no       as termsRegNo
			,a.terms_auth_type    as termsAuthType
			,a.customer_name      as customerName
			,dec_char_sel(a.customer_email,
							10,
							'ARIA',
							'spt_customer_service_terms_profile',
							'customer_email',
							user(),
							connection_id())              as customerEmail
			,dec_char_sel(a.customer_birth_day,
							10,
							'ARIA',
							'spt_customer_service_terms_profile',
							'customer_birth_day',
							user(),
							connection_id())      as customerBirthDay
			,ifnull(a.terms_policy/12,1) as termsPolicyYear
			,ifnull(date_format(a.terms_chg_date, '%Y%m%d'),date_format(sysdate(),'%Y%m%d')) as termsCreateDate
			,ifnull(date_format(a.terms_start_date,'%Y년%m월%d일'), date_format(sysdate(),'%Y%m%d')) as termsStartDate
			,date_format(a.terms_expire_date,'%Y년%m월%d일')  as termsEndDate
			,a.terms_expire_date as termsExpireDate
			,'G030001'   /*동의 : G030001*/ as termsStatus
			,a.customer_sign_dn   as customerSignDn
			,a.customer_sign_data as customerSignData
			,a.customer_tsa_data  as customerTsaData
			,a.delete_date        as deleteDate
			,dec_char_sel(c.customer_phone,
							10,
							'ARIA',
							'spt_customer_info_profile',
							'customer_phone',
							user(),
							connection_id())  as customerPhone
		from spt_customer_common_terms_profile a
		left join spt_customer_info_profile c on a.customer_reg_no = c.customer_reg_no
		where   a.customer_reg_no = #{customerRegNo}
		and     a.delete_date is null
		limit 0,1
	</script>""")
    CommonTermsRes selectCommonTermsInfo(@Param("customerRegNo") String customerRegNo);

    /**
     * (가)동의서 정보제공 기업정보 조회
     * @param customerRegNo
     * @param termsRegNo
     * @return
     */
    @Select("""<script>
        select    a.pubcompany_name    as pubCompanyName
		        , a.terms_pubcompany_reg_no as termsPubCompanyRegNo
		from 	spt_customer_common_terms_pubcompany_profile a
		left join com_company_profile as b
		on 		a.pubcompany_code_id = b.company_code_id
		where 	a.customer_reg_no = #{customerRegNo}
		and 	a.terms_reg_no = #{termsRegNo}
		order by pubCompanyName
	</script>""")
    List<AppTermsPubCompanyProfileReq> selectCommonTermsPubCompanyList(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo);


    /**
     * (가)동의서 동의 여부 체크
     * @param request
     * @return
     */
	@Select("""<script>
		SELECT
		      count(1)
		FROM spt_customer_common_terms_profile a,
		     spt_customer_common_terms_pubcompany_profile b
		where a.customer_reg_no = b.customer_reg_no
		and a.terms_reg_no = b.terms_reg_no
		and a.delete_date is null
		and b.delete_date is null    
		and a.terms_expire_date>sysdate()
		and a.customer_reg_no = #{customerRegNo}
		<if test='pubCompanyCodeId != null and pubCompanyCodeId!=""'>
         and b.pubcompany_code_id =#{pubCompanyCodeId}
		</if>
	</script>""")
	int selectCheckedCommonTerms(@Param("customerRegNo") String customerRegNo,@Param("pubCompanyCodeId") String pubCompanyCodeId);

    @Select("""<script>
		SELECT
              DATEDIFF(a.terms_expire_date, SYSDATE()) as dateCount
        FROM  spt_customer_common_terms_profile a
        where a.delete_date is null
        and   (date_format(sysdate(), '%Y%m%d') >= date_format(a.terms_expire_date - INTERVAL 1 MONTH, '%Y%m%d'))
        and   a.customer_reg_no = #{customerRegNo}
	</script>""") 
    Map<String,Object> selectExpireDateForCommonTerms(@Param("customerRegNo") String customerRegNo);

    @Select("""<script>
		SELECT
              count(1) as cnt
        FROM  spt_customer_common_terms_profile a
        where a.delete_date is null
        and   (date_format(sysdate(), '%Y%m%d') >= date_format(a.terms_expire_date - INTERVAL 1 MONTH, '%Y%m%d'))
        and   a.customer_reg_no = #{customerRegNo}
	</script>""")
    int selectExpireDateCheck(@Param("customerRegNo") String customerRegNo);

    @Select("""<script>
        select  count(1) as count
        from    spt_customer_verify_profile
        where   customer_reg_no = #{customerRegNo}
        and     customer_verify_type = 'G007002'
    </script>""")
    int selectCheckedCustomerVerify(@Param("customerRegNo") String customerRegNo);

    @Select("""<script>
		select  concat(date_format(sysdate(),'%Y%m%d'),
                    lpad((
                            select ifnull( max(cast(right(terms_reg_no,3) as signed)), 0)+1
                            from spt_customer_common_terms_profile
                            where left(terms_reg_no, 8) = date_format(sysdate(),'%Y%m%d')
                            and customer_reg_no = #{customerRegNo}
                        ), 3, '0')
                    ) as termsRegNo
	</script>""")
    String selectMaxCommonTermsRegNo(@Param("customerRegNo") String customerRegNo);

    @Select("""<script>
		select  concat(date_format(sysdate(),'%Y%m%d'),
                    lpad((
                            select ifnull( max(cast(right(terms_pubcompany_reg_no,3) as signed)), 0)+1
                            from spt_customer_common_terms_pubcompany_profile
                            where left(terms_pubcompany_reg_no, 8) = date_format(sysdate(),'%Y%m%d')
                            and customer_reg_no = #{customerRegNo}
                            and terms_reg_no = #{termsRegNo}
                        ), 3, '0')
                    ) as termsPubcompanyRegNo
	</script>""")
    String selectMaxCommonTermsPubCompanyRegNo(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo);

    @Insert("""<script>
        INSERT INTO spt_customer_common_terms_profile
        (
            customer_reg_no
          , terms_reg_no
          , customer_name
          , customer_email
          , customer_birth_day
          , terms_policy
          , terms_auth_type
          , terms_chg_date
          , terms_start_date
          , terms_expire_date
          , terms_auth_yn
          , terms_file_reg_no
          , terms_file_status
          , customer_sign_dn
          , customer_sign_data
          , customer_tsa_data
          , admin_create_yn
          , create_date
          , create_id
          , update_date
          , update_id
        )
        SELECT  a.customer_reg_no       as customer_reg_no,
                #{termsRegNo}           as terms_reg_no,
                a.customer_name_kor     as customer_name,
                a.customer_email        as customer_email,
                a.customer_birth_day    as customer_birth_day,
                '12'                    as terms_policy,
                'G032001'               as terms_auth_type,
                sysdate()               as terms_chg_date,
                date_format(sysdate(),'%Y%m%d') as terms_start_date,
                date_format(DATE_ADD(sysdate(),INTERVAL + 1 year) - INTERVAL 1 day,'%Y%m%d') as terms_expire_date,
                'N'                     as terms_auth_yn,
                #{termsFileRegNo}       as terms_file_reg_no,
                'G028030'               as terms_file_status,
                #{customerSignDn}       as customer_sign_dn,
                #{customerSignData}     as customer_sign_data,
                #{customerTsaData}      as customer_tsa_data,
                'N'                     as admin_create_yn,
                sysdate()               as create_date,
                #{customerRegNo}        as create_id,
                sysdate()               as update_date,
                #{customerRegNo}        as update_id
        FROM(
            SELECT  customer_reg_no,
            customer_name_kor,
            customer_email,
            customer_birth_day
            FROM spt_customer_info_profile
            WHERE customer_reg_no = #{customerRegNo}
        ) a
    </script>""")
    int insertCustomerCommonTermsProfile(AppCreateReq appCreateReq);

    @Insert("""<script>
        INSERT INTO spt_customer_common_terms_profile_hist
        (
            customer_reg_no
          , terms_reg_no
          , terms_seq
          , customer_name
          , customer_email
          , customer_birth_day
          , terms_policy
          , terms_auth_type
          , terms_chg_date
          , terms_start_date
          , terms_expire_date
          , terms_auth_yn
          , terms_file_reg_no
          , terms_file_status
          , customer_sign_dn
          , customer_sign_data
          , customer_tsa_data
          , admin_create_yn
          , delete_date
          , create_date
          , create_id
          , update_date
          , update_id
        )
        SELECT
            a.customer_reg_no
          , a.terms_reg_no
          , ifnull((
                    select ifnull(max(cast(terms_seq as SIGNED)), 0) + 1
                    from spt_customer_common_terms_profile_hist aa
                    where a.customer_reg_no = aa.customer_reg_no
            ), 1)
          , a.customer_name
          , a.customer_email
          , a.customer_birth_day
          , a.terms_policy
          , a.terms_auth_type
          , a.terms_chg_date
          , a.terms_start_date
          , a.terms_expire_date
          , a.terms_auth_yn
          , a.terms_file_reg_no
          , a.terms_file_status
          , a.customer_sign_dn
          , a.customer_sign_data
          , a.customer_tsa_data
          , a.admin_create_yn
          , a.delete_date
          , a.create_date
          , a.create_id
          , a.update_date
          , a.update_id
        FROM spt_customer_common_terms_profile a
        WHERE a.customer_reg_no = #{customerRegNo}
        AND   a.terms_reg_no = #{termsRegNo}
    </script>""")
    int insertCustomerCommonTermsProfileHist(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo);

    @Insert("""<script>
        INSERT INTO spt_customer_common_terms_pubcompany_profile
        (
            customer_reg_no
          , terms_reg_no
          , terms_pubcompany_reg_no
          , pubcompany_code_id
          , pubcompany_name
          , terms_chg_date
          , create_date
          , create_id
          , update_date
          , update_id
        )
        select  #{customerRegNo}        as customer_reg_no,
                #{termsRegNo}           as terms_reg_no,
                #{termsPubCompanyRegNo}  as terms_pubcompany_reg_no,
                company_code_id         as pubcompany_code_id,
                company_name_kor_alias        as pubcompany_name,
                sysdate()               as terms_chg_date,
                sysdate()               as create_date,
                #{customerRegNo}        as create_id,
                sysdate()               as update_date,
                #{customerRegNo}        as update_id
        from com_company_profile
        where company_code_id = #{pubCompanyCodeId}
        and delete_date is null
        and exposure_yn = 'Y'
    </script>""")
    int insertCustomerCommonTermsPubCompanyProfile(AppTermsPubCompanyProfileReq appTermsPubCompanyProfileReq);

    @Insert("""<script>
        INSERT INTO spt_customer_common_terms_pubcompany_profile_hist
        (
            customer_reg_no
          , terms_reg_no
          , terms_pubcompany_reg_no
          , terms_pubcompany_seq
          , pubcompany_code_id
          , pubcompany_name
          , terms_chg_date
          , delete_date
          , create_date
          , create_id
          , update_date
          , update_id
        )
        SELECT
            a.customer_reg_no
          , a.terms_reg_no
          , a.terms_pubcompany_reg_no
          , ifnull((
                    select ifnull(max(cast(terms_pubcompany_seq as SIGNED)), 0) + 1
                    from spt_customer_common_terms_pubcompany_profile_hist
                    where customer_reg_no = a.customer_reg_no
                    and terms_reg_no = a.terms_reg_no
                    and terms_pubcompany_reg_no = a.terms_pubcompany_reg_no
                ), 1)
          , a.pubcompany_code_id
          , a.pubcompany_name
          , a.terms_chg_date
          , a.delete_date
          , a.create_date
          , a.create_id
          , a.update_date
          , a.update_id
        FROM spt_customer_common_terms_pubcompany_profile a
        WHERE a.customer_reg_no = #{customerRegNo}
        AND   a.terms_reg_no = #{termsRegNo}
        <if test="termsPubCompanyRegNo != null and termsPubCompanyRegNo != ''">
            AND   a.terms_pubcompany_reg_no = #{termsPubCompanyRegNo}
        </if>
    </script>""")
    int insertCustomerCommonTermsPubCompanyProfileHist(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo, @Param("termsPubCompanyRegNo") String termsPubCompanyRegNo);

    @Update("""<script>
        update  spt_customer_common_terms_profile
        set     delete_date = sysdate(),
                update_date = sysdate(),
                update_id = #{customerRegNo}
        WHERE   customer_reg_no = #{customerRegNo}
        AND     terms_reg_no = #{termsRegNo}
        AND     delete_date is null
    </script>""")
    int deleteCustomerCommonTermsProfile(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo);

    @Update("""<script>
        update  spt_customer_common_terms_pubcompany_profile
        set     delete_date = sysdate(),
                update_date = sysdate(),
                update_id = #{customerRegNo}
        where   customer_reg_no = #{customerRegNo}
        and     terms_reg_no = #{termsRegNo}
        and     delete_date is null
    </script>""")
    int deleteCustomerCommonTermsPubCompanyProfile(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo);

    @Select("""<script>
        select
              ccp.company_code_id           as pubCompanyCodeId,       /* 기업프로파일.기업코드 */
              ccp.company_name_kor_alias    as pubCompanyName      /* 기업프로파일.기업이름한글 */
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
        and ccp.company_service_type in ( 'G002001', 'G002002')
        order by ifnull(ccp.exposure_order, 'Z') asc, ccp.company_name_kor_alias asc
    </script>""")
    List<AppTermsPubCompanyProfileReq> selectPubCompanyList();

    @Select("""<script>
        SELECT  customer_name_kor as customerName,
                dec_char_sel(customer_email, 10, 'ARIA', 'spt_customer_info_profile', 'customer_email', user(), connection_id()) as customerEmail,
                date_format(dec_char_sel(customer_birth_day, 10, 'ARIA', 'spt_customer_info_profile', 'customer_birth_day', user(), connection_id()), '%Y년 %m월 %d일') as customerBirthDay,
                dec_char_sel(customer_phone, 10, 'ARIA', 'spt_customer_info_profile', 'customer_phone', user(), connection_id())  as customerPhone,
                date_format(sysdate(), '%Y년 %m월 %d일') as termsCreateDate,
                date_format(sysdate(), '%Y년 %m월 %d일') as termsStartDate,
                date_format(DATE_ADD(sysdate(),INTERVAL + 1 year) - INTERVAL 1 day,'%Y년 %m월 %d일') as termsEndDate
        FROM    spt_customer_info_profile
        WHERE   customer_reg_no = #{customerRegNo}
    </script>""")
    CommonTermsRes selectBaseCommonTerms(@Param("customerRegNo") String customerRegNo);

    @Insert("""<script>
        INSERT INTO spt_customer_verify_profile
        (
            customer_reg_no,
            customer_verify_type,
            customer_verify,
            customer_verify_date,
            create_date,
            create_id,
            update_date,
            update_id
        ) VALUES (
            #{customerRegNo},
            'G007002',
            #{customerSignDn},
            sysdate(),
            sysdate(),
            #{customerRegNo},
            sysdate(),
            #{customerRegNo}
        ) ON DUPLICATE KEY UPDATE
        customer_verify = #{customerSignDn},
        update_date = sysdate(),
        update_id = #{customerRegNo}
    </script>""")
    int insertCustomerVerifyProfile(@Param("customerRegNo") String customerRegNo, @Param("customerSignDn") String customerSignDn);

    @Insert("""<script>
        insert into spt_customer_verify_profile_hist(
                customer_verify_seq,  /* 순번 */
                customer_reg_no,      /* 회원OP등록번호 */
                customer_verify_type, /* 회원검증종류  */
                customer_verify,      /* 회원검증값 */
                customer_verify_date, /* 회원검증일시 */
                delete_date,          /* 삭제일시 */
                create_date,          /* 생성일시 */
                create_id             /* 생성자 ID */
            )
            select
                ifnull((
                    select ifnull(max(cast(customer_verify_seq as SIGNED)), 0) + 1
                    from spt_customer_verify_profile_hist
                    where customer_reg_no = a.customer_reg_no
                ), 1),                /* 순번 */
                customer_reg_no,      /* 회원OP등록번호 */
                customer_verify_type, /* 회원검증종류  */
                customer_verify,      /* 회원검증값 */
                customer_verify_date, /* 회원검증일시 */
                delete_date,          /* 삭제일시 */
                create_date,          /* 생성일시 */
                create_id             /* 생성자 ID */
            from spt_customer_verify_profile a
            where 1=1
            and customer_reg_no = #{customerRegNo} /* 회원OP등록번호 */
            and customer_verify_type = 'G007002' /* 회원검증종류  */
    </script>""")
    int insertCustomerVerifyProfileHist(@Param("customerRegNo") String customerRegNo);
}