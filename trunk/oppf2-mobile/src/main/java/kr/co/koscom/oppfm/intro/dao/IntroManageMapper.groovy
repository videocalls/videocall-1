package kr.co.koscom.oppfm.intro.dao

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.intro.model.IntroFintechAppReq
import kr.co.koscom.oppfm.intro.model.IntroFintechAppRes
import org.apache.ibatis.annotations.Select

/**
 * ClassName : IntroManageMapper
 * *
 * Description :
 *
 * Created by LSH on 2017. 4. 28..
 */
@Mapper
interface IntroManageMapper {

    @Select("""<script>
        SELECT
            a.app_id           AS appId,
            a.app_name         AS appName,
            a.company_code_id  AS companyCodeId,
            b.company_name_kor AS companyName,
            a.app_category     AS appCategory,
            (SELECT code_name_kor
            FROM com_system_code
            WHERE concat(system_grp_code, system_code) = a.app_category)
            AS appCategoryName,
            a.app_description  AS appDescription,
            a.app_dl_url       AS appDlUrl
        FROM
            (SELECT a.app_id,
                a.app_name,
                a.company_code_id,
                b.app_category,
                b.app_description,
                b.app_dl_url,
                b.exposure_order
            FROM com_app_view a JOIN com_app_info b ON a.app_id = b.app_id
            WHERE a.app_status = 'G022002'                     /*app 상태 (CA 정보)*/
            AND b.exposure_yn = 'Y') a
        JOIN com_company_profile AS b
        ON     a.company_code_id = b.company_code_id
        AND b.delete_date IS NULL
        AND b.exposure_yn = 'Y'
        WHERE 1 = 1
        <if test='searchAppCategory != null'>
            <if test="searchAppCategory != 'all'">
            and a.app_category = #{searchAppCategory}
            </if>
        </if>
        <if test='searchCondition == "all"'>
            and(a.app_name like concat('%', #{searchKeyword}, '%')
                or a.app_description like concat('%', #{searchKeyword}, '%'))
        </if>
        <if test='searchCondition == "appName"'>
            and a.app_name like concat('%', #{searchKeyword}, '%')
        </if>
        <if test='searchCondition == "appDescription"'>
            and a.app_description like concat('%', #{searchKeyword}, '%')
        </if>
         <if test="pageInfo != null">
            limit #{pageInfo.startRowNum},#{pageInfo.endRowNum}
        </if>
    </script>""")
    List<IntroFintechAppRes> introFintechAppList(IntroFintechAppReq introFintechAppReq);

    @Select("""<script>
        SELECT count(1) AS count
        FROM
            (SELECT a.app_id,
                a.app_name,
                a.company_code_id,
                b.app_category,
                b.app_description,
                b.app_dl_url,
                b.exposure_order
            FROM com_app_view a JOIN com_app_info b ON a.app_id = b.app_id
            WHERE a.app_status = 'G022002'                     /*app 상태 (CA 정보)*/
            AND b.exposure_yn = 'Y') a
        JOIN com_company_profile AS b
        ON     a.company_code_id = b.company_code_id
        AND b.delete_date IS NULL
        AND b.exposure_yn = 'Y'
        WHERE 1 = 1
          <if test='searchAppCategory != null'>
            <if test="searchAppCategory != 'all'">
            and a.app_category = #{searchAppCategory}
            </if>
        </if>
        <if test='searchCondition == "all"'>
            and(a.app_name like concat('%', #{searchKeyword}, '%')
                or a.app_description like concat('%', #{searchKeyword}, '%'))
        </if>
        <if test='searchCondition == "appName"'>
            and a.app_name like concat('%', #{searchKeyword}, '%')
        </if>
        <if test='searchCondition == "appDescription"'>
            and a.app_description like concat('%', #{searchKeyword}, '%')
        </if>
         <if test="pageInfo != null">
            limit #{pageInfo.startRowNum},#{pageInfo.endRowNum}
        </if>
    </script>""")
    int introFintechAppCompanyListCount(IntroFintechAppReq introFintechAppReq);



    @Select("""
         SELECT a.app_id            AS appId,
         a.api_id                   AS apiId,
         a.company_code_id          AS companyCodeId,
         b.company_name_kor_alias   AS companyNameKorAlias,
         a.api_title                AS apiTitle
        FROM (
              SELECT a.app_id,
                     a.api_id,
                     b.company_code_id,
                     b.api_title,
                     b.exposure_order
              FROM (SELECT app_id, api_id
                       FROM com_app_apilist_view
                       WHERE 1 = 1 AND app_id = #{appId}) a,
                     com_api_info b
              WHERE     a.api_id = b.api_id
                     AND b.exposure_yn = 'Y'
                     AND b.api_account_yn = 'Y') a
              JOIN com_company_profile AS b
                     ON     a.company_code_id = b.company_code_id
                     AND b.delete_date IS NULL
                     AND b.exposure_yn = 'Y'
        ORDER BY a.app_id, a.company_code_id, a.exposure_order
    """)
    List<IntroFintechAppRes> introFintechAppCompanyList(IntroFintechAppRes introFintechAppReq);


}