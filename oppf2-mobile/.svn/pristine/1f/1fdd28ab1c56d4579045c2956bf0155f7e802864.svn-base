package kr.co.koscom.oppfm.commoncode.dao

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.commoncode.model.CommonCodeReq
import kr.co.koscom.oppfm.commoncode.model.CommonCodeRes
import kr.co.koscom.oppfm.commoncode.model.CommonCompanyCodeRes
import kr.co.koscom.oppfm.commoncode.model.CommonCompanyProfileRes
import org.apache.ibatis.annotations.Select

/**
 * CommonCodeMapper
 * <p>
 * Created by Yoojin Han on 2017-05-11.
 */

@Mapper
public interface CommonCodeMapper {

    /* 시스템 그룹 코드 description 조회 */
    @Select("""<script>
	SELECT 
                        system_grp_code_desc                    as systemGrpCodeDesc
	FROM com_system_grp_code 
	WHERE system_grp_code = #{systemGrpCode}
	</script>""")
    String selectSystemGrpCodeDesc(CommonCodeReq commonCodeReq)


    /* 공통 코드 조회 */
    @Select("""<script>
	SELECT 
        system_grp_code       as systemGrpCode,                 /* 코드 그룹 ID  */
        system_code           as systemCode,                    /* 코드 ID       */
        concat(system_grp_code, system_code)        as searchResCode,   /* 검색결과 공통코드(코드그룹ID + 코드ID) */
        code_name_kor         as codeNameKor,                   /* 코드 명 한글  */
        code_name_eng         as codeNameEng,                   /* 코드 명 영문  */
        code_extend1          as codeExtend1,                   /* 확장 코드 1   */
        code_extend2          as codeExtend2,
        code_extend3          as codeExtend3,
        code_extend4          as codeExtend4,
        code_extend5          as codeExtend5

	FROM com_system_code
	WHERE use_yn = 'Y'
	AND DATE_FORMAT(now(), '%Y%m%d') between vld_start_date and vld_expire_date
	AND system_grp_code = #{systemGrpCode}

	ORDER BY code_seq    

	</script>""")
    List<CommonCodeRes> selectCommonCode(CommonCodeReq commonCodeReq)


    /* 시스템 코드 상세 조회 */
    @Select("""<script>
    SELECT 
        system_grp_code       as systemGrpCode,                 /* 코드 그룹 ID  */
        system_code           as systemCode,                    /* 코드 ID       */
        code_name_kor         as codeNameKor,                   /* 코드 명 한글  */
        code_name_eng         as codeNameEng,                   /* 코드 명 영문  */
        code_extend1          as codeExtend1,                   /* 확장 코드 1   */
        code_extend2          as codeExtend2,
        code_extend3          as codeExtend3,
        code_extend4          as codeExtend4,
        code_extend5          as codeExtend5

    FROM com_system_code
    WHERE use_yn = 'Y' 
    AND system_grp_code = #{systemGrpCode}
    AND system_code = #{systemCode}

    </script>""")
    CommonCodeRes selectCommonCodeDetail(CommonCodeReq commonCodeReq)


    /* 기업 코드 조회 */
    @Select("""<script>
	SELECT  
        company_profile_reg_no                   as companyProfileRegNo,
/*       substr(a.company_service_type, 1,4)      as companyType, */
/*       substr(a.company_service_type, 5,3)      as serviceType, */
        company_service_type                     as companyServiceType,
        company_code_id                          as companyCodeId,
        company_name_kor                         as companyNameKor,
        company_name_eng                         as companyNameEng,
        company_name_kor_alias                   as companyNameKorAlias,
        company_name_eng_alias                   as companyNameEngAlias

	FROM com_company_profile a
	WHERE delete_date is null
	AND exposure_yn = 'Y'

    <if test ='searchCompanyServiceType !=null and searchCompanyServiceType != ""'>
        and  ( company_service_type = #{searchCompanyServiceType}
        <if test ='searchCompanyServiceType =="G002002" or searchCompanyServiceType == "G002003"'>
                or company_service_type = "G002001"
        </if>
              )
    </if>

    <if test='companyNameEngAlias != null and companyNameEngAlias != "" '>
        and company_name_eng_alias = #{companyNameEngAlias}
    </if>

	ORDER BY exposure_order, company_name_kor_alias asc    

	</script>""")
    List<CommonCompanyProfileRes> selectCompanyCodeList(CommonCodeReq commonCodeReq)

    /* 기업 정보 조회 */
    @Select("""<script>
	select
            c.company_code_reg_no    as companyCodeRegNo,    /* 기업코드.기업코드등록번호 */
            c.company_code_type      as companyCodeType,     /* 기업코드.기업코드표구분 */
            c.company_code_id        as companyCodeId,       /* 기업코드.기업코드 */
            c.company_code_kind      as companyCodeKind,     /* 기업코드.기업종류 */
            (select code_name_kor from com_system_code
             where c.company_code_kind = system_code) as companyCodeKindNm, /* 기업코드.기업종류명 */
            c.company_name_kor       as companyNameKor,      /* 기업코드.기업이름한글 */
            c.company_name_eng       as companyNameEng,      /* 기업코드.기업이름영문 */
            c.company_name_kor_alias as companyNameKorAlias, /* 기업코드.기업이름Alias한글 */
            c.company_name_eng_alias as companyNameEngAlias /* 기업코드.기업이름Alias영문 */
        from com_company_code c /* 기업코드 */
        where 1=1
        and c.delete_date is null
        and c.company_code_kind = #{companyCodeKind}
	</script>""")
    List<CommonCompanyCodeRes> selectCompanyList(String companyCodeKind)
}
