package kr.co.koscom.oppfm.app.dao

import kr.co.koscom.oppfm.app.model.AppAccountProfileReq
import kr.co.koscom.oppfm.app.model.AppCreateReq
import kr.co.koscom.oppfm.app.model.AppTermsFileProfileReq
import kr.co.koscom.oppfm.app.model.AppTermsPubCompanyProfileReq
import kr.co.koscom.oppfm.app.model.AppTermsRes
import org.apache.ibatis.annotations.Update


import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

import kr.co.koscom.oppfm.app.model.AppAccountRes
import kr.co.koscom.oppfm.app.model.AppCompanyRes
import kr.co.koscom.oppfm.app.model.AppSearchReq
import kr.co.koscom.oppfm.app.model.AppRes
import kr.co.koscom.oppfm.cmm.annotation.Mapper


/**
 *
 * AppMapper
 *
 * Created by chungyeol.kim on 2017-05-11.
 */

@Mapper
interface AppMapper {
	
	/**
	 * App 소개 목록
	 * @Description: 로그인 전 App목록 조회
	 * @param appReq
	 * @return List<AppRes>
	 */
	@Select("""<script>
	SELECT    c.app_id                     AS appid, 
    	      c.app_name                   AS appname, 
        	  c.app_status                 AS appstatus, 
         	  c.company_code_id            AS companycodeid, 
         	  c.company_name_kor_alias           AS companyname,
              c.app_category               AS appcategory, 
              c.code_name_kor              AS appcategoryname, 
              c.app_description            AS appdescription, 
              IF(Isnull(d.app_id),'N','Y') AS isavailable,
              IF(isnull(e.app_id),'N','Y') AS createdYn
    FROM    ( 
                 SELECT a.app_id, 
                        a.app_name,	 
                        a.a.app_status, 
                        a.company_code_id, 
                        b.company_name_kor_alias,
                        a.app_category,
						a.exposure_order,
                        ( 
                               SELECT code_name_kor 
                               FROM   com_system_code 
                               WHERE  Concat(system_grp_code, system_code) = a.app_category) AS code_name_kor,
                        a.app_description 
                  FROM  ( 
                               SELECT a.app_id, 
                                      a.app_name, 
                                      a.company_code_id, 
                                      a.a.app_status, 
                                      b.app_category, 
                                      b.app_description, 
                                      b.exposure_order 
                               FROM   com_app_view a 
                               JOIN   com_app_info b 
                               ON     a.app_id = b.app_id 
                               WHERE  a.app_status = 'G022002' 
                                      /*app 상태 (CA 정보)*/ 
                               AND    b.exposure_yn = 'Y') a 
                 JOIN   com_company_profile AS b 
                 ON     a.company_code_id = b.company_code_id 
                 AND    b.delete_date IS NULL 
                 AND    b.exposure_yn = 'Y') c 
    LEFT JOIN 
          ( 
            SELECT   a.app_id AS app_id 
            FROM     ( 
                      SELECT a.app_id, 
                             a.api_id, 
                             b.company_code_id 
                      FROM   ( 
                              SELECT app_id, 
                                     api_id 
                              FROM   com_app_apilist_view) a, 
                                          com_api_info b 
                      WHERE  a.api_id = b.api_id 
                      AND    b.exposure_yn = 'Y' 
                      AND    b.api_account_yn = 'Y') a 
             JOIN     com_company_profile AS b 
             ON       a.company_code_id = b.company_code_id 
             AND      b.delete_date IS NULL 
             AND      b.exposure_yn = 'Y' 
             GROUP BY app_id) AS d 
    ON        c.app_id = d.app_id 
    LEFT OUTER JOIN (
      		select a.app_id,
			   		a.create_date,
			   		b.terms_expire_date
			from spt_customer_service_profile a,
			 spt_customer_service_terms_profile b
			where a.customer_reg_no = b.customer_reg_no
			and a.terms_reg_no = b.terms_reg_no
			and a.customer_reg_no = #{customerRegNo}
			and a.delete_date is null
			and a.terms_reg_no is not null
			and b.terms_auth_yn = 'N'
    ) e
    ON e.app_id = c.app_id
    WHERE 1 = 1
	   <if test='searchAppCategory != null and searchAppCategory!="all"'>
            and c.app_category = #{searchAppCategory}
        </if>
        <if test='searchKeyword != "" and searchKeyword!=null'>
            and(
				c.app_name like concat('%', #{searchKeyword}, '%')
                or c.company_name_kor_alias like concat('%', #{searchKeyword}, '%')
				or c.code_name_kor like concat('%', #{searchKeyword}, '%')
				or c.app_id IN (
                                 		 select
                                        		a.app_id                                                                           
                                  		 from
                                       			(select
                                              				a.app_id,
                                              				a.api_id,
                                              				b.api_title,
                                             				b.company_code_id,
                                              				b.exposure_order
                                        			from  (
																select 	app_id,
                                                      					 	api_id 
                                                				 from 	com_app_apilist_view)a, com_api_info b
                                                	where 	 a.api_id = b.api_id
                                                		and	 b.exposure_yn='Y'
                                                		and 	 b.api_account_yn='Y'
												) a
                                  		JOIN  com_company_profile AS b
                                          ON  a.company_code_id = b.company_code_id
                                  		 AND  b.delete_date IS NULL
                                   		 AND  b.exposure_yn = 'Y'
                                   		AND  b.company_name_kor_alias  like concat('%', #{searchKeyword}, '%')
                              	ORDER BY  a.app_id, a.company_code_id, a.exposure_order
                  					)
	)
        </if>
         <if test="pageInfo!=null">
            limit #{pageInfo.startRowNum},#{pageInfo.endRowNum}
        </if>
		order by isavailable desc,
				 c.exposure_order,c.app_name 
    </script>""")
	List<AppRes> selectAppList(AppSearchReq appReq);
		

		/**
		 * App 소개 목록 갯수
		 * @Description: 로그인 전 App목록 총 개수 
		 * @param appReq
		 * @return int
		 */
	@Select("""<script>
    SELECT count(1) AS count
    FROM   ( 
            SELECT      
				  a.app_id, 
                  a.app_name, 
                  a.a.app_status, 
                  a.company_code_id, 
                  b.company_name_kor_alias,
                  a.app_category, 
                 ( 
                  SELECT code_name_kor 
                  FROM   com_system_code 
                  WHERE  Concat(system_grp_code, system_code) = a.app_category) AS code_name_kor,
                  a.app_description 
            FROM  ( 
                   SELECT  a.app_id, 
                           a.app_name, 
                           a.company_code_id, 
                           a.a.app_status, 
                           b.app_category, 
                           b.app_description, 
                           b.exposure_order 
                    FROM   com_app_view a 
                    JOIN   com_app_info b 
                    ON     a.app_id = b.app_id 
                    WHERE  a.app_status = 'G022002' /*app 상태 (CA 정보)*/ 
                    AND    b.exposure_yn = 'Y') a 
             JOIN   com_company_profile AS b 
             ON     a.company_code_id = b.company_code_id 
             AND    b.delete_date IS NULL 
             AND    b.exposure_yn = 'Y') c 
LEFT JOIN 
          ( 
           SELECT   a.app_id AS app_id 
           FROM     ( 
                     SELECT a.app_id, 
                            a.api_id, 
                            b.company_code_id 
                     FROM   ( 
                              SELECT app_id, 
                                     api_id 
                              FROM   com_app_apilist_view) a, 
                              com_api_info b 
                     WHERE  a.api_id = b.api_id 
                     AND    b.exposure_yn = 'Y' 
                     AND    b.api_account_yn = 'Y') a 
           JOIN     com_company_profile AS b 
           ON       a.company_code_id = b.company_code_id 
           AND      b.delete_date IS NULL 
           AND      b.exposure_yn = 'Y' 
           GROUP BY app_id) AS d 
ON        c.app_id = d.app_id 
		WHERE 1 = 1
	   <if test='searchAppCategory != null and searchAppCategory!="all"'>
            and c.app_category = #{searchAppCategory}
        </if>
        <if test='searchKeyword != "" and searchKeyword!=null'>
            and(c.app_name like concat('%', #{searchKeyword}, '%')
                or c.company_name_kor_alias like concat('%', #{searchKeyword}, '%'))
        </if>
    </script>""")
	int selectAppListTotalCount(AppSearchReq appReq);
	
	
	/**
	 * App 소개 상세조회
	 * @Description: 로그인 전 App 상세조회
	 * @param appReq
	 * @return AppDtlRes
	 */
	@Select("""<script>
        SELECT
            a.app_id           AS appId,
            a.app_name         AS appName,
			a.a.app_status	   AS appStatus,
			a.app_dl_url       AS appDlUrl,
            a.company_code_id  AS companyCodeId,
            b.company_name_kor_alias AS companyName,
            a.app_category     AS appCategory,
            (SELECT code_name_kor
            FROM com_system_code
            WHERE concat(system_grp_code, system_code) = a.app_category) AS appCategoryName,
            a.app_description  AS appDescription
        FROM
            (SELECT a.app_id,
                	a.app_name,
                	a.company_code_id,
					a.a.app_status,
                	b.app_category,
                	b.app_description,
                	b.exposure_order,
					b.app_dl_url
               FROM com_app_view a JOIN com_app_info b ON a.app_id = b.app_id
              WHERE a.app_status = 'G022002'                     /*app 상태 (CA 정보)*/
                AND b.exposure_yn = 'Y') a
        JOIN com_company_profile AS b
          ON a.company_code_id = b.company_code_id
         AND b.delete_date IS NULL
         AND b.exposure_yn = 'Y'
        WHERE 1 = 1
        and a.app_id = #{appId}
    </script>""")
	AppRes selectAppDtl(AppSearchReq appReq);
	
	

	/**
	 * App 소개 상세조회
	 * @Description: 로그인 전 App 상세조회-연계서비스 리스트 조회
	 * @param appReq
	 * @return List<AppCompanyRes>
	 */
	@Select("""
         SELECT a.app_id            		AS appId,
        	    a.api_id              		AS apiId,
         		a.company_code_id   		AS companyCodeId,
        	    b.company_name_kor_alias    AS companyName,
       			a.api_title                 AS apiName
           FROM (
              	SELECT  a.app_id,
                        a.api_id,
                        b.company_code_id,
                        b.api_title,
                        b.exposure_order
              	  FROM  (SELECT app_id, api_id
                     	   FROM com_app_apilist_view
                          WHERE  1 = 1 
						    AND  app_id = #{appId}) a, com_api_info b
              	 WHERE     a.api_id = b.api_id
              	   AND 	   b.exposure_yn = 'Y'
                   AND 	   b.api_account_yn = 'Y') a
            JOIN     com_company_profile AS b
              ON     a.company_code_id = b.company_code_id
             AND     b.delete_date IS NULL
             AND     b.exposure_yn = 'Y'
        ORDER BY     a.app_id, a.company_code_id, a.exposure_order
    """)
   List<AppCompanyRes> selectAppCompanyList(AppSearchReq appReq);

   
   /**
	* App소개 목록
	* @Description: 로그인 후 신청한 App리스트 조회
	* @param appReq
	* @return List<AppRes>
	*/
   @Select("""<script>
		SELECT
				a.app_id  AS appID,
				a.app_name AS appName,
				a.app_status AS appStatus,
				a.company_code_id AS companyCodeId,
				a.company_name_kor_alias AS companyName,
				a.app_category AS appCategory,
				a.app_category_name AS appCategoryName,
				a.app_description AS appDescription,
				case when
					(date_format(sysdate(), '%Y%m%d') >= date_format(b.terms_expire_date - INTERVAL 1 MONTH, '%Y%m%d'))
					then
						'Y'
					else
						'N'
					end as reSyncYn
		FROM
			(
				select 	a.app_id,
   						a.create_date,
   						b.terms_expire_date
				from 	spt_customer_service_profile a,
					 	spt_customer_service_terms_profile b
				where 	a.customer_reg_no = b.customer_reg_no
				and 	a.terms_reg_no = b.terms_reg_no
				and 	a.customer_reg_no = #{customerRegNo}
				and 	a.delete_date is null
				and 	a.terms_reg_no is not null
				and 	b.terms_auth_yn = 'N') b
		JOIN
		(
			SELECT
					a.app_id,
					a.app_name,
					a.a.app_status,
					a.company_code_id,
					b.company_name_kor_alias,
					a.app_category ,
					(SELECT code_name_kor
					   FROM com_system_code
					  WHERE concat(system_grp_code, system_code) = a.app_category) AS app_category_name,
					a.app_description,
					a.exposure_order
			FROM
			(
				SELECT
						a.app_id,
						a.app_name,
						a.company_code_id,
						a.a.app_status,
						b.app_category,
						b.app_description,
						b.exposure_order
				FROM   	com_app_view a JOIN com_app_info b ON a.app_id = b.app_id
				WHERE  	a.app_status = 'G022002'                     /*app 상태 (CA 정보)*/
				AND    	b.exposure_yn = 'Y') a
			JOIN com_company_profile AS b
			ON   a.company_code_id = b.company_code_id
			AND  b.delete_date IS NULL
			AND  b.exposure_yn = 'Y'
		)a
		ON a.app_id=b.app_id
		WHERE 1 = 1
		<if test='searchAppCategory != null and searchAppCategory != "all"'>
			and a.app_category = #{searchAppCategory}
		</if>
		<if test='searchKeyword != "" and searchKeyword!=null'>
			and( a.app_name like concat('%', #{searchKeyword}, '%')
			or a.company_name_kor_alias like concat('%', #{searchKeyword}, '%'))
		</if>
		<if test="pageInfo!=null">
			limit #{pageInfo.startRowNum},#{pageInfo.endRowNum}
		</if>
   		order by a.exposure_order, a.app_name
    </script>""")
	List<AppRes> selectMyAppList(AppSearchReq appReq);
   
	
	/**
	 * App 소개 상세조회
	 * @Description: 로그인 후 App 상세조회(reSyncYn 필요여부 포함)
	 * @param appReq
	 * @return AppDtlRes
	 */
	@Select("""<script>
		SELECT
				a.app_id  AS appID,
				b.service_reg_no AS serviceRegNo,
		        b.terms_start_date AS termsStartData,
		        b.terms_expire_date AS termsEndDate,
				a.app_name AS appName,
				a.app_status AS appStatus,
				a.app_dl_url AS appDlUrl,
				a.company_code_id AS companyCodeId,
				a.company_name_kor_alias AS companyName,
				a.app_category AS appCategory,
				a.app_category_name AS appCategoryName,
				a.app_description AS appDescription,
				case when
					(date_format(sysdate(), '%Y%m%d') >= date_format(b.terms_expire_date - INTERVAL 1 MONTH, '%Y%m%d'))
					then
						'Y'
					else
						'N'
					end as reSyncYn
		FROM
			(
				select 	
						a.app_id,
						a.service_reg_no,
						date_format(b.terms_start_date,'%Y-%m-%d')  AS terms_start_date,
						date_format(b.terms_expire_date,'%Y-%m-%d') AS terms_expire_date
				from 	spt_customer_service_profile a,
					 	spt_customer_service_terms_profile b
				where 	a.customer_reg_no = b.customer_reg_no
				and 	a.terms_reg_no = b.terms_reg_no
				and 	a.customer_reg_no = #{customerRegNo}
				and 	a.delete_date is null
				and 	a.terms_reg_no is not null
				and 	b.terms_auth_yn = 'N') b
		JOIN
		(
			SELECT
					a.app_id,
					a.app_name,
					a.a.app_status,
					a.company_code_id,
					a.app_dl_url,
					b.company_name_kor_alias,
					a.app_category ,
					(SELECT code_name_kor
					   FROM com_system_code
					  WHERE concat(system_grp_code, system_code) = a.app_category) AS app_category_name,
					a.app_description
			FROM
			(
				SELECT
						a.app_id,
						a.app_name,
						a.company_code_id,
						a.a.app_status,
						b.app_dl_url,
						b.app_category,
						b.app_description,
						b.exposure_order
				FROM   	com_app_view a JOIN com_app_info b ON a.app_id = b.app_id
				WHERE  	a.app_status = 'G022002'                     /*app 상태 (CA 정보)*/
				AND    	b.exposure_yn = 'Y') a
			JOIN com_company_profile AS b
			ON   a.company_code_id = b.company_code_id
			AND  b.delete_date IS NULL
			AND  b.exposure_yn = 'Y'
		)a
		ON a.app_id=b.app_id
		WHERE b.app_id = #{appId}
		</script>""")
	AppRes selectMyAppDtl(AppSearchReq appReq);
	

	/**
	 * App소개 목록
	 * @Description: 로그인 후 신청한 App리스트 총 갯수 조회
	 * @param appReq
	 * @return int
	 */
	@Select("""<script>
	    SELECT
      		count(1) AS count
		FROM
			(
				select 	a.app_id, b.terms_expire_date
				from 	spt_customer_service_profile a,
					 	spt_customer_service_terms_profile b
				where 	a.customer_reg_no = b.customer_reg_no
				and 	a.terms_reg_no = b.terms_reg_no
				and 	a.customer_reg_no = #{customerRegNo}
				and 	a.delete_date is null
				and 	a.terms_reg_no is not null
				and 	b.terms_auth_yn = 'N') b
		JOIN
		(
			SELECT
					a.app_id,
					a.app_name,
					a.a.app_status,
					a.company_code_id,
					b.company_name_kor_alias,
					a.app_category ,
					(SELECT code_name_kor
					   FROM com_system_code
					  WHERE concat(system_grp_code, system_code) = a.app_category) AS app_category_name,
					a.app_description
			FROM
			(
				SELECT
						a.app_id,
						a.app_name,
						a.company_code_id,
						a.a.app_status,
						b.app_category,
						b.app_description,
						b.exposure_order
				FROM   	com_app_view a JOIN com_app_info b ON a.app_id = b.app_id
				WHERE  	a.app_status = 'G022002'                     /*app 상태 (CA 정보)*/
				AND    	b.exposure_yn = 'Y') a
			JOIN com_company_profile AS b
			ON   a.company_code_id = b.company_code_id
			AND  b.delete_date IS NULL
			AND  b.exposure_yn = 'Y'
		)a
		ON a.app_id=b.app_id
		WHERE 1 = 1
	   <if test='searchAppCategory != null and searchAppCategory!="all"'>
            and a.app_category = #{searchAppCategory}
        </if>
        <if test='searchKeyword != "" and searchKeyword!=null'>
            and( a.app_name like concat('%', #{searchKeyword}, '%')
                or a.company_name_kor_alias like concat('%', #{searchKeyword}, '%'))
        </if>
    </script>""")
	 int selectMyAppListTotalCount(AppSearchReq appReq);
	 
	 /**
	  * App소개 상세조회
	  * @Description: (로그인 후) 신청한 App상세조회-App에 연결된 가상계좌 리스트 조회
	  * @param appReq
	  * @return List<AppAccountRes>
	  */
	@Select("""<script>
        select   a.app_id                                                       AS appId, 
      			 a.api_id                                                       AS apiId, 
       			 a.api_name                                                     AS apiName,
				 a.account_reg_no                                               AS accountRegNo,
				 a.customer_realaccount_no                                      AS customerRealAccountNo,
      			 pub.company_name_kor_alias                                     AS pubCompanyName,
       			 pub.company_code_id                                            AS pubcompanyCodeId,
				 b.customer_vtaccount_no                                        AS customerVtaccountNo, 
       			 b.customer_vtaccount_alias                                     AS customerVtaccountAlias 
		from(
		    select  a.*,
		            b.api_name,
		            c.company_code_id       as pubcompany_code_id,
		            c.exposure_order        as pubexposure_order
		    from(
		        select  a.*,    
		                b.app_name,
   						c.app_description,
                        c.app_category,
		                b.company_code_id   as subcompany_code_id,
		                c.exposure_order    as subexposure_order
		        from(
		            select  a.customer_reg_no,
		                    a.service_reg_no,
		                    a.app_id,
		                    b.api_id,
		                    b.customer_realaccount_no,
							b.account_reg_no,
		                    a.terms_reg_no,
		                    a.terms_auth_yn,
		                    a.terms_start_date,
		                    a.terms_expire_date,
		                    a.terms_auth_date_yn
		            from(
		                select  a.*,
		                        b.terms_auth_yn,
		                        date_format(b.terms_start_date, '%Y-%m-%d') as terms_start_date,
		                        date_format(b.terms_expire_date, '%Y-%m-%d') as terms_expire_date,
		                        case when
		                              /*date_format(sysdate(), '%Y%m%d') between b.terms_start_date and b.terms_expire_date*/
		                               date_format(sysdate(), '%Y%m%d') <![CDATA[ >= ]]> date_format(b.terms_expire_date - INTERVAL 1 MONTH, '%Y%m%d') AND
		                               date_format(sysdate(), '%Y%m%d') <![CDATA[ <= ]]> date_format(b.terms_expire_date, '%Y%m%d')
		                              then 'N'
		                              else 'Y'
		                        end as terms_auth_date_yn
		                from(
		                    select  customer_reg_no,
		                            service_reg_no,
		                            app_id,
		                            terms_reg_no
		                    from spt_customer_service_profile
		                    where customer_reg_no = #{customerRegNo}
		                    and terms_reg_no is not null
		                    and delete_date is null
		                ) a
		                left join spt_customer_service_terms_profile b 
		                on a.customer_reg_no = b.customer_reg_no
		                and a.terms_reg_no = b.terms_reg_no
		            ) a, spt_customer_service_account_profile b
		            where a.customer_reg_no = b.customer_reg_no
		            and a.service_reg_no = b.service_reg_no
		            and a.app_id = b.app_id
		            and b.delete_date is null
		        ) a
		        left join com_app_view b on a.app_id = b.app_id
		        left join com_app_info c on a.app_id = c.app_id
		        where b.app_status = 'G022002'        /*app 상태 (CA 정보)*/
		        /*and c.exposure_yn = 'Y'*/
		    ) a 
		    left join com_api_view b on a.api_id = b.api_id
		    left join com_api_info c on a.api_id = c.api_id 
		    where 1=1 /*c.exposure_yn = 'Y'*/
		    and c.api_account_yn = 'Y'
		) a 
		left join(
		    select *
		    from spt_customer_account_profile
		    where customer_reg_no = #{customerRegNo}
		    and customer_vtaccount_status = 'G009002'
		) b
		on a.pubcompany_code_id = b.company_code_id
		and a.customer_realaccount_no = b.customer_realaccount_no
		join com_company_profile as pub on a.pubcompany_code_id = pub.company_code_id and pub.delete_date is null /*and pub.exposure_yn = 'Y'*/  
		join com_company_profile as sub on a.subcompany_code_id = sub.company_code_id and sub.delete_date is null /*and sub.exposure_yn = 'Y'*/
		WHERE 1 = 1
		<if test='appId!=null'>
            and a.app_id = #{appId}
        </if>
		order by 
            ifnull(sub.exposure_order, 'Z') asc, sub.company_name_kor_alias asc, 
            ifnull(a.subexposure_order, 'Z') asc, a.app_name asc, 
            ifnull(pub.exposure_order, 'Z') asc, pub.company_name_kor_alias asc, 
            ifnull(a.pubexposure_order, 'Z') asc,  a.api_name asc
    </script>""")
	List<AppAccountRes> selectAppAccountList (AppSearchReq appReq);
   
 	
	
	
	
	/**
	 * App신청 목록
	 * @Description: (로그인 후)App 신청목록에서 사용자가 신청할수 있는 App리스트 조회
	 * @param appReq
	 * @return List<AppRes>
	 */
	@Select("""<script>
SELECT a.app_id                       AS appId,
       a.app_name                     AS appName,
       a.app_status                   AS appStatus,
       a.company_code_id              AS companyCodeId,
       a.company_name_kor_alias             AS companyName,
       a.app_category                 AS appCategory,
       a.code_name_kor                AS appCategoryName,
       a.app_description              AS appDescription,
       a.isAvailable				  AS isAvailable
FROM   (SELECT c.app_id, 
               c.app_name, 
               c.app_status, 
               c.company_code_id, 
               c.company_name_kor_alias,
               c.app_category, 
               c.code_name_kor, 
               c.app_description,
			   c.exposure_order,
               IF(Isnull(d.app_id), 'N', 'Y') AS isAvailable 
        FROM   (SELECT a.app_id, 
                       a.app_name, 
                       a.a.app_status, 
                       a.company_code_id, 
                       b.company_name_kor_alias,
                       a.app_category,
					   a.exposure_order,
                       (SELECT code_name_kor 
                        FROM   com_system_code 
                        WHERE  Concat(system_grp_code, system_code) = 
                               a.app_category) 
                       AS 
                               code_name_kor, 
                       a.app_description 
                FROM   (SELECT a.app_id, 
                               a.app_name, 
                               a.company_code_id, 
                               a.a.app_status, 
                               b.app_category, 
                               b.app_description, 
                               b.exposure_order 
                        FROM   com_app_view a 
                               JOIN com_app_info b 
                                 ON a.app_id = b.app_id 
                        WHERE  a.app_status = 'G022002' 
                               /*app 상태 (CA 정보)*/ 
                               AND b.exposure_yn = 'Y') a 
                       JOIN com_company_profile AS b 
                         ON a.company_code_id = b.company_code_id 
                            AND b.delete_date IS NULL 
                            AND b.exposure_yn = 'Y') c 
               JOIN (SELECT a.app_id AS app_id 
                     FROM   (SELECT a.app_id, 
                                    a.api_id, 
                                    b.company_code_id 
                             FROM   (SELECT app_id, 
                                            api_id 
                                     FROM   com_app_apilist_view) a, 
                                    com_api_info b 
                             WHERE  a.api_id = b.api_id 
                                    AND b.exposure_yn = 'Y' 
                                    AND b.api_account_yn = 'Y') a 
                            JOIN com_company_profile AS b 
                              ON a.company_code_id = b.company_code_id 
                                 AND b.delete_date IS NULL 
                                 AND b.exposure_yn = 'Y' 
                     GROUP  BY app_id) AS d 
                 ON c.app_id = d.app_id)a 
WHERE  a.app_id NOT IN (SELECT a.app_id 
                        FROM   (SELECT a.app_id 
                                FROM   (SELECT a.app_id, 
                                               a.company_code_id 
                                        FROM   com_app_view a 
                                               JOIN com_app_info b 
                                                 ON a.app_id = b.app_id 
                                        WHERE  a.app_status = 'G022002' 
                                               /*app 상태 (CA 정보)*/ 
                                               AND b.exposure_yn = 'Y') a 
                                       JOIN com_company_profile AS b 
                                         ON a.company_code_id = 
                                            b.company_code_id 
                                            AND b.delete_date IS NULL 
                                            AND b.exposure_yn = 'Y')a 
                               JOIN (SELECT app_id 
                                     FROM   spt_customer_service_profile 
                                     WHERE  customer_reg_no = #{customerRegNo} 
                                            AND delete_date IS NULL 
                                            AND terms_reg_no IS NOT NULL) b 
                                 ON a.app_id = b.app_id) 
	 <if test='searchAppCategory != null and searchAppCategory!="all"'>
            and a.app_category = #{searchAppCategory}
        </if>
      <if test='searchKeyword != "" and searchKeyword!=null'>
            and(
				a.app_name like concat('%', #{searchKeyword}, '%')
                or a.company_name_kor_alias like concat('%', #{searchKeyword}, '%')
				or a.code_name_kor like concat('%', #{searchKeyword}, '%')
				or a.app_id IN (
                                 		 select
                                        		a.app_id                                                                           
                                  		 from
                                       			(select
                                              				a.app_id,
                                              				a.api_id,
                                              				b.api_title,
                                             				b.company_code_id,
                                              				b.exposure_order
                                        			from  (
																select 	app_id,
                                                      					 	api_id 
                                                				 from 	com_app_apilist_view)a, com_api_info b
                                                	where 	 a.api_id = b.api_id
                                                		and	 b.exposure_yn='Y'
                                                		and 	 b.api_account_yn='Y'
												) a
                                  		JOIN  com_company_profile AS b
                                          ON  a.company_code_id = b.company_code_id
                                  		 AND  b.delete_date IS NULL
                                   		 AND  b.exposure_yn = 'Y'
                                   		AND  b.company_name_kor_alias  like concat('%', #{searchKeyword}, '%')
                              	ORDER BY  a.app_id, a.company_code_id, a.exposure_order
                  					)
				)
         </if>
         <if test="pageInfo!=null">
            limit #{pageInfo.startRowNum},#{pageInfo.endRowNum}
        </if>
		order by a.exposure_order, a.app_name
    </script>""")
	 List<AppRes> selectRequestAppList(AppSearchReq appReq);
	
	 
	 
	 /**
	  * App신청 목록
	  * @Description: (로그인 후)App 신청목록에서 사용자가 신청할수 있는 App리스트 총 갯수 조회
	  * @param appReq
	  * @return int
	  */
	 @Select("""<script>
SELECT count(1) AS count
FROM   (SELECT c.app_id                       AS appId, 
               c.app_name                     AS appName, 
               c.app_status                   AS appStatus, 
               c.company_code_id              AS companyCodeId, 
               c.company_name_kor_alias             AS companyName,
               c.app_category                 AS appCategory, 
               c.code_name_kor                AS appCategoryName, 
               c.app_description              AS appDescription, 
               IF(Isnull(d.app_id), 'N', 'Y') AS isAvailable 
        FROM   (SELECT a.app_id, 
                       a.app_name, 
                       a.a.app_status, 
                       a.company_code_id, 
                       b.company_name_kor_alias,
                       a.app_category, 
                       (SELECT code_name_kor 
                        FROM   com_system_code 
                        WHERE  Concat(system_grp_code, system_code) = 
                               a.app_category) 
                       AS 
                               code_name_kor, 
                       a.app_description 
                FROM   (SELECT a.app_id, 
                               a.app_name, 
                               a.company_code_id, 
                               a.a.app_status, 
                               b.app_category, 
                               b.app_description, 
                               b.exposure_order 
                        FROM   com_app_view a 
                               JOIN com_app_info b 
                                 ON a.app_id = b.app_id 
                        WHERE  a.app_status = 'G022002' 
                               /*app 상태 (CA 정보)*/ 
                               AND b.exposure_yn = 'Y') a 
                       JOIN com_company_profile AS b 
                         ON a.company_code_id = b.company_code_id 
                            AND b.delete_date IS NULL 
                            AND b.exposure_yn = 'Y') c 
               JOIN (SELECT a.app_id AS app_id 
                     FROM   (SELECT a.app_id, 
                                    a.api_id, 
                                    b.company_code_id 
                             FROM   (SELECT app_id, 
                                            api_id 
                                     FROM   com_app_apilist_view) a, 
                                    com_api_info b 
                             WHERE  a.api_id = b.api_id 
                                    AND b.exposure_yn = 'Y' 
                                    AND b.api_account_yn = 'Y') a 
                            JOIN com_company_profile AS b 
                              ON a.company_code_id = b.company_code_id 
                                 AND b.delete_date IS NULL 
                                 AND b.exposure_yn = 'Y' 
                     GROUP  BY app_id) AS d 
                 ON c.app_id = d.app_id)a 
WHERE  a.appid NOT IN (SELECT a.app_id 
                        FROM   (SELECT a.app_id 
                                FROM   (SELECT a.app_id, 
                                               a.company_code_id 
                                        FROM   com_app_view a 
                                               JOIN com_app_info b 
                                                 ON a.app_id = b.app_id 
                                        WHERE  a.app_status = 'G022002' 
                                               /*app 상태 (CA 정보)*/ 
                                               AND b.exposure_yn = 'Y') a 
                                       JOIN com_company_profile AS b 
                                         ON a.company_code_id = 
                                            b.company_code_id 
                                            AND b.delete_date IS NULL 
                                            AND b.exposure_yn = 'Y')a 
                               JOIN (SELECT app_id 
                                     FROM   spt_customer_service_profile 
                                     WHERE  customer_reg_no = #{customerRegNo} 
                                            AND delete_date IS NULL 
                                            AND terms_reg_no IS NOT NULL) b 
                                 ON a.app_id = b.app_id) 
	   <if test='searchAppCategory != null and searchAppCategory!="all"'>
            and a.appCategory = #{searchAppCategory}
        </if>
        <if test='searchKeyword != "" and searchKeyword!=null'>
            and(a.appName like concat('%', #{searchKeyword}, '%')
                or a.companyName like concat('%', #{searchKeyword}, '%'))
        </if>
    </script>""")
	 int selectRequestAppListTotalCount(AppSearchReq appReq);
	

		  
	/**
	* App신청 -연결계좌 선택
	* @Description: (로그인 후)App 신청을 누른뒤 사용자의 전체 가상계좌 목록 출력
	* @param appReq
	* @return List<AppAccountRes>
	*/
	@Select("""<script>
		SELECT
				a.company_name_kor_alias		AS pubCompanyName,
				a.company_code_id               AS pubCompanyCodeId,
				a.customer_vtaccount_no			AS customerVtaccountNo,
				a.customer_vtaccount_alias		AS customerVtaccountAlias,
				a.customer_vtaccount_status		AS customerVtaccountStatus,
				a.customer_realaccount_no       AS customerRealAccountNo,
				IF(Isnull(b.company_code_id), 'false', 'true') AS isavailable,
				b.app_id                        AS appId,
				b.api_id                        AS apiId
		FROM   (SELECT
					  a.customer_reg_no,
					  a.company_code_id,
					  b.company_name_kor_alias,
					  a.customer_vtaccount_no,
					  a.customer_vtaccount_alias,
					  a.customer_vtaccount_status,
					  a.customer_realaccount_type,
					  a.customer_realaccount_no,
					  a.customer_vtaccount_reg_date,
					  a.customer_vtaccount_expire_date
				FROM   (SELECT *
						FROM   spt_customer_account_profile
						WHERE  customer_reg_no = #{customerRegNo}
						AND customer_vtaccount_status = 'G009002'
						AND customer_vtaccount_expire_date IS NULL
						)a
				JOIN com_company_profile AS b
				ON a.company_code_id = b.company_code_id
				)a
		LEFT JOIN (SELECT 	a.app_id            AS app_id,
							a.api_id              AS api_id,
							a.company_code_id ,
							b.company_name_kor_alias    AS companyName,
							a.api_title                 AS apiName
					FROM (
						  SELECT  a.app_id,
								  a.api_id,
								  b.company_code_id,
								  b.api_title,
								  b.exposure_order
		  				  FROM  (SELECT app_id, api_id
								  FROM com_app_apilist_view
								  WHERE  app_id = #{appId}
								  ) a,
								  com_api_info b
						  WHERE    a.api_id = b.api_id
						  AND    b.exposure_yn = 'Y'
						  AND    b.api_account_yn = 'Y'
						) a
					JOIN   com_company_profile AS b
					ON     a.company_code_id = b.company_code_id
					AND    b.delete_date IS NULL
					AND    b.exposure_yn = 'Y'
					ORDER BY     a.app_id, a.company_code_id, a.exposure_order
				) AS b
		ON a.company_code_id = b.company_code_id
	</script>""")
	List<AppAccountRes> selectAccountList(AppSearchReq appReq);

    /**
	 * (나) 동의서 조회
	 * @param customerRegNo
	 * @param appId
	 * @return
     */
	@Select("""<script>
		select
			 a.customer_reg_no    	as customerRegNo
			,a.terms_reg_no       	as termsRegNo
			,a.terms_auth_type    	as termsAuthType
			,e.company_name_kor_alias as subCompanyName
			,e.company_code_id as subCompanyCodeId
			,a.customer_name      	as customerName
			,dec_char_sel(a.customer_email,
							10,
							'ARIA',
							'spt_customer_service_terms_profile',
							'customer_email',
							user(),
							connection_id())              			as customerEmail
			,date_format(dec_char_sel(a.customer_birth_day,
							10,
							'ARIA',
							'spt_customer_service_terms_profile',
							'customer_birth_day',
							user(),
							connection_id()), '%Y년 %m월 %d일')		as customerBirthDay
			,ifnull(a.terms_policy/12,1) as termsPolicyYear
			,ifnull(date_format(a.terms_chg_date, '%Y년 %m월 %d일'),date_format(sysdate(),'%Y년 %m월 %d일')) as termsCreateDate
			,date_format(a.terms_start_date,'%Y년 %m월 %d일') as termsStartDate
			,date_format(a.terms_expire_date,'%Y년 %m월 %d일')  as termsEndDate
			,'G030001'   /*동의 : G030001*/ as termsStatus
			,a.customer_sign_dn   as customerSignDn
			,case a.terms_auth_type
				when 'G032001' then a.customer_sign_data
				else
					dec_char_sel(b.customer_phone,
								10,
								'ARIA',
								'spt_customer_service_ars_profile',
								'customer_phone',
								user(),
								connection_id())
			end as customerSignData
			,case a.terms_auth_type when 'G032001' then a.customer_tsa_data else b.ars_txt_no end as customerTsaData
			,a.delete_date        as deleteDate
			,dec_char_sel(c.customer_phone,
							10,
							'ARIA',
							'spt_customer_info_profile',
							'customer_phone',
							user(),
							connection_id())  as customerPhone
		from spt_customer_service_terms_profile a
		inner join (
				select 	terms_reg_no as terms_reg_no
						,app_id as app_id
				from 	spt_customer_service_profile
				where 	customer_reg_no = #{customerRegNo}
				and 	app_id = #{appId}
				and 	delete_date is null) d
		on 	d.terms_reg_no = a.terms_reg_no
		inner join (
			SELECT
					a.app_id as app_id,
					a.company_code_id as company_code_id,
					c.company_name_kor_alias as company_name_kor_alias
			FROM 		com_app_view a
        	INNER JOIN 	com_app_info b
        	ON 			a.app_id = b.app_id
        	INNER JOIN 	com_company_profile c
        	ON 			c.company_code_id = a.company_code_id
			AND			c.delete_date is null
        	AND 		c.exposure_yn = 'Y'
			WHERE  		a.app_status = 'G022002'                     /*app 상태 (CA 정보)*/
			AND    		b.exposure_yn = 'Y'
		) e on e.app_id = d.app_id
		left join spt_customer_service_ars_profile b on a.customer_reg_no = b.customer_reg_no AND a.terms_reg_no = b.terms_reg_no
		left join spt_customer_info_profile c on a.customer_reg_no = c.customer_reg_no
		where 	a.customer_reg_no = #{customerRegNo}
		limit 0,1
	</script>""")
	AppTermsRes selectAppTermsInfo(@Param("customerRegNo") String customerRegNo, @Param("appId") String appId);

	/**
	 * (나) 동의서 정보제공 기업정보 조회
	 * @param customerRegNo
	 * @param termsRegNo
     * @return
     */
	@Select("""<script>
		select ifnull(b.company_name_kor_alias, a.pubcompany_name)    as pubCompanyName
		from 	spt_customer_service_terms_pubcompany_profile a
		left join com_company_profile as b
		on 		a.pubcompany_code_id = b.company_code_id
		where 	a.customer_reg_no = #{customerRegNo}
		and 	a.terms_reg_no = #{termsRegNo}
		order by pubCompanyName
	</script>""")
	List<String> selectAppTermsPubCompanyList(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo);

	@Select("""<script>
		select ifnull(b.company_name_kor_alias, a.pubcompany_name)    as pubCompanyName,
				a.pubcompany_code_id as pubCompanyCodeId,
				a.customer_reg_no as customerRegNo
		from 	spt_customer_service_terms_pubcompany_profile a
		left join com_company_profile as b
		on 		a.pubcompany_code_id = b.company_code_id
		where 	a.customer_reg_no = #{customerRegNo}
		and 	a.terms_reg_no = #{termsRegNo}
		order by pubCompanyName
	</script>""")
	List<AppTermsPubCompanyProfileReq> selectAppTermsPubCompanyInfoList(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo);

	@Select("""<script>
		SELECT
			dec_char_sel(c.customer_phone,10,'ARIA','spt_customer_info_profile','customer_phone',user(),connection_id())  as customerPhone
		FROM spt_customer_info_profile c
		WHERE c.customer_reg_no = #{customerRegNo}
	</script>""")
	String selectCustomerPhoneNumber(@Param("customerRegNo") String customerRegNo);

	@Select("""<script>
		SELECT
			c.customer_phone  as customerPhone
		FROM spt_customer_info_profile c
		WHERE c.customer_reg_no = #{customerRegNo}
	</script>""")
	String selectEncCustomerPhoneNumber(@Param("customerRegNo") String customerRegNo);

	@Select("""<script>
		select  concat(date_format(sysdate(),'%Y%m%d'), 
						lpad((
								select ifnull( max(cast(right(service_reg_no,3) as signed)), 0)+1
								from spt_customer_service_profile
								where left(service_reg_no, 8) = date_format(sysdate(),'%Y%m%d')
								and customer_reg_no = #{customerRegNo}
							), 3, '0')
						) as serviceRegNo
	</script>""")
	String selectMaxServiceRegNo(@Param("customerRegNo") String customerRegNo);

	@Select("""<script>
		select  concat(date_format(sysdate(),'%Y%m%d'),
                    lpad((
                            select ifnull( max(cast(right(account_reg_no,3) as signed)), 0)+1
                            from spt_customer_service_account_profile
                            where left(account_reg_no, 8) = date_format(sysdate(),'%Y%m%d')
                            and customer_reg_no = #{customerRegNo}
                            and service_reg_no = #{serviceRegNo}
                        ), 3, '0')
                    ) as accountRegNo
	</script>""")
	String selectMaxServiceAccountRegNo(@Param("customerRegNo") String customerRegNo, @Param("serviceRegNo") String serviceRegNo);

	@Select("""<script>
		select  concat(date_format(sysdate(),'%Y%m%d'),
                    lpad((
                            select ifnull( max(cast(right(terms_reg_no,3) as signed)), 0)+1
                            from spt_customer_service_terms_profile
                            where left(terms_reg_no, 8) = date_format(sysdate(),'%Y%m%d')
                            and customer_reg_no = #{customerRegNo}
                        ), 3, '0')
                    ) as termsRegNo
	</script>""")
	String selectMaxTermsRegNo(@Param("customerRegNo") String customerRegNo);

	@Select("""<script>
		select  concat(date_format(sysdate(),'%Y%m%d'),
                    lpad((
                            select ifnull( max(cast(right(terms_pubcompany_reg_no,3) as signed)), 0)+1
                            from spt_customer_service_terms_pubcompany_profile
                            where left(terms_pubcompany_reg_no, 8) = date_format(sysdate(),'%Y%m%d')
                            and customer_reg_no = #{customerRegNo}
                            and terms_reg_no = #{termsRegNo}
                        ), 3, '0')
                    ) as termsPubcompanyRegNo
	</script>""")
	String selectMaxTermsPubCompanyRegNo(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo);

	@Select("""<script>
		select
            concat(date_format(sysdate(),'%Y%m%d'),
                   lpad( (select ifnull( max(cast(right(terms_ars_reg_no,4) as signed)), 0)+1
                          from spt_customer_service_ars_profile where left(terms_ars_reg_no,8) = date_format(sysdate(),'%Y%m%d')
                          and customer_reg_no = #{customerRegNo}
                          )
                          ,4,'0')
                   ) as termsArsRegNo
	</script>""")
	String selectMaxTermsArsRegNo(@Param("customerRegNo") String customerRegNo);

	@Select("""<script>
		select
            concat(date_format(sysdate(),'%Y%m%d'),
                   lpad( (select ifnull( max(cast(right(terms_file_reg_no,3) as signed)), 0)+1
                          from spt_customer_service_terms_file_profile where left(terms_file_reg_no,8) = concat(date_format(sysdate(),'%Y%m%d'))
                          )
                          ,3,'0')
                   ) as termsFileRegNo /* 동의서파일등록번호 */
        from dual
	</script>""")
	String selectMaxTermsFileRegNo();

	@Insert("""<script>
		INSERT INTO spt_customer_service_profile
		(
			customer_reg_no
		  , service_reg_no
		  , app_id
		  , terms_reg_no
		  , create_date
		  , create_id
		  , update_date
		  , update_id
		) VALUES (
			#{customerRegNo}
		  , #{serviceRegNo}
		  , #{appId}
		  , #{termsRegNo}
		  , sysdate()
		  , #{customerRegNo}
		  , sysdate()
		  , #{customerRegNo}
		)
	</script>""")
	int insertCustomerServiceProfile(AppCreateReq appCreateReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_profile_hist
		(
			customer_reg_no
		  , service_reg_no
		  , service_seq
		  , app_id
		  , terms_reg_no
		  , delete_date
		  , create_date
		  , create_id
		)
		SELECT
			a.customer_reg_no
		  , a.service_reg_no
		  , ifnull((
                    select ifnull(max(cast(service_seq as SIGNED)), 0) + 1
                    from spt_customer_service_profile_hist
                    where customer_reg_no = a.customer_reg_no
                    and service_reg_no = a.service_reg_no
                ), 1)
		  , a.app_id
		  , a.terms_reg_no
		  , a.delete_date
		  , sysdate()
		  , a.create_id
		FROM spt_customer_service_profile a
		WHERE a.customer_reg_no = #{customerRegNo}
		<if test="serviceRegNo != null and serviceRegNo != ''">
			AND   a.service_reg_no = #{serviceRegNo}
		</if>
		<if test="termsRegNo != null and termsRegNo != ''">
			AND   a.terms_reg_no = #{termsRegNo}
		</if>
	</script>""")
	int insertCustomerServiceProfileHist(AppCreateReq appCreateReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_account_profile
		(
			customer_reg_no
		  , service_reg_no
		  , account_reg_no
		  , app_id
		  , api_id
		  , customer_realaccount_no
		  , create_date
		  , create_id
		  , update_date
		  , update_id
		) VALUES (
			#{customerRegNo}
		  , #{serviceRegNo}
		  , #{accountRegNo}
		  , #{appId}
		  , #{apiId}
		  , #{customerRealAccountNo}
		  , sysdate()
		  , #{customerRegNo}
		  , sysdate()
		  , #{customerRegNo}
		)
	</script>""")
	int insertCustomerServiceAccountProfile(AppAccountProfileReq appAccountProfileReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_account_profile_hist
		(
			customer_reg_no
		  , service_reg_no
		  , account_reg_no
		  , account_seq
		  , app_id
		  , api_id
		  , customer_realaccount_no
		  , delete_date
		  , create_date
		  , create_id
		)
		SELECT
			a.customer_reg_no
		  , a.service_reg_no
		  , a.account_reg_no
		  , ifnull((
                    select ifnull(max(cast(account_seq as SIGNED)), 0) + 1
                    from spt_customer_service_account_profile_hist
                    where customer_reg_no = a.customer_reg_no
                    and service_reg_no = a.service_reg_no
                    and account_reg_no = a.account_reg_no
                ), 1)
		  , a.app_id
		  , a.api_id
		  , a.customer_realaccount_no
		  , a.delete_date
		  , sysdate()
		  , a.create_id
		FROM spt_customer_service_account_profile a
		WHERE a.customer_reg_no = #{customerRegNo}
		AND   a.service_reg_no = #{serviceRegNo}
		AND   a.account_reg_no = #{accountRegNo}
	</script>""")
	int insertCustomerServiceAccountProfileHist(AppAccountProfileReq appAccountProfileReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_terms_profile
		(
			customer_reg_no
		  , terms_reg_no
		  , subcompany_code_id
		  , subcompany_name
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
		SELECT  a.customer_reg_no,
				#{termsRegNo}           as terms_reg_no,
				b.company_code_id       as subcompany_code_id,
				b.company_name_kor_alias      as subcompany_name,
				a.customer_name_kor     as customer_name,
				a.customer_email        as customer_email,
				a.customer_birth_day    as customer_birth_day,
				#{termsPolicy}          as terms_policy,
				#{termsAuthType}		as terms_auth_type,
				sysdate()				as terms_chg_date,
				date_format(sysdate(),'%Y%m%d')	as terms_start_date,
				<if test="termsExpireDate != null and termsExpireDate != ''">
					#{termsExpireDate}  as terms_expire_date,
				</if>
				<if test="termsExpireDate == null or termsExpireDate == ''">
					date_format(DATE_ADD(sysdate(),INTERVAL + 1 year) - INTERVAL 1 day,'%Y%m%d') as terms_expire_date,
				</if>
				'N'                     as terms_auth_yn,
				#{termsFileRegNo}       as terms_file_reg_no,
				'G028030'      			as terms_file_status,
				#{customerSignDn}       as customer_sign_dn,
				#{customerSignData}		as customer_sign_data,
				#{customerTsaData}		as customer_tsa_data,
				'N'						as admin_create_yn,
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
		LEFT OUTER JOIN (
			SELECT  #{customerRegNo}     as customer_reg_no,
					company_code_id,
					company_name_kor_alias
			FROM com_company_profile
			WHERE company_code_id = #{subCompanyCodeId}
			AND delete_date is null
			AND exposure_yn = 'Y'
		) b ON a.customer_reg_no = b.customer_reg_no
	</script>""")
	int insertCustomerServiceTermsProfile(AppCreateReq appCreateReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_terms_profile_hist
		(
			customer_reg_no
		  , terms_reg_no
		  , terms_seq
		  , subcompany_code_id
		  , subcompany_name
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
		)
		SELECT
			a.customer_reg_no
		  , a.terms_reg_no
		  , ifnull((
				select ifnull(max(cast(terms_seq as SIGNED)), 0) + 1
				from spt_customer_service_terms_profile_hist aa
				where a.customer_reg_no = aa.customer_reg_no
			), 1)
		  , a.subcompany_code_id
		  , a.subcompany_name
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
		  , sysdate()
		  , a.create_id
		FROM spt_customer_service_terms_profile a
		WHERE a.customer_reg_no = #{customerRegNo}
		AND   a.terms_reg_no = #{termsRegNo}
	</script>""")
	int insertCustomerServiceTermsProfileHist(AppCreateReq appCreateReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_terms_pubcompany_profile
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
				sysdate()				as terms_chg_date,
				sysdate()               as create_date,
				#{customerRegNo}        as create_id,
				sysdate()               as update_date,
				#{customerRegNo}        as update_id
		from com_company_profile
		where company_code_id = #{pubCompanyCodeId}
		and delete_date is null
		and exposure_yn = 'Y'
	</script>""")
	int insertCustomerServiceTermsPubCompanyProfile(AppTermsPubCompanyProfileReq appTermsPubCompanyProfileReq);

	@Select("""
		select count(1)
		from spt_customer_service_terms_pubcompany_profile
		where customer_reg_no = #{customerRegNo}
		and terms_reg_no = #{termsRegNo}
		and pubcompany_code_id = #{pubCompanyCodeId}
		and delete_date is null
	""")
	int checkCreatedCustomerServiceTermsPubCompanyProfile(AppTermsPubCompanyProfileReq appTermsPubCompanyProfileReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_terms_pubcompany_profile_hist
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
		)
		SELECT
			a.customer_reg_no
		  , a.terms_reg_no
		  , a.terms_pubcompany_reg_no
		  , ifnull((
                    select ifnull(max(cast(terms_pubcompany_seq as SIGNED)), 0) + 1
                    from spt_customer_service_terms_pubcompany_profile_hist
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
		FROM spt_customer_service_terms_pubcompany_profile a
		WHERE a.customer_reg_no = #{customerRegNo}
		AND   a.terms_reg_no = #{termsRegNo}
		AND   a.terms_pubcompany_reg_no = #{termsPubCompanyRegNo}
	</script>""")
	int insertCustomerServiceTermsPubCompanyProfileHist(AppTermsPubCompanyProfileReq appTermsPubCompanyProfileReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_ars_profile
		(
			customer_reg_no
		  , terms_reg_no
		  , terms_ars_reg_no
		  , customer_phone
		  , ars_record_data
		  , ars_txt_no
		  , ars_tr_cd
		  , ars_rslt_msg
		  , ars_rslt_cd
		  , create_date
		  , create_id
		  , update_date
		  , update_id
		) VALUES (
			#{customerRegNo}
		  , #{termsRegNo}
		  , #{termsArsRegNo}
		  , #{customerPhone}
		  , #{arsRecordData}
		  , #{arsTxtNo}
		  , #{arsTrCd}
		  , #{arsResultMessage}
		  , #{arsResultCd}
		  , sysdate()
		  , #{customerRegNo}
		  , sysdate()
		  , #{customerRegNo}
		)
	</script>""")
	int insertCustomerServiceArsProfile(AppCreateReq appCreateReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_ars_profile_hist
		(
			customer_reg_no
		  , terms_reg_no
		  , terms_ars_seq
		  , terms_ars_reg_no
		  , customer_phone
		  , ars_record_data
		  , ars_txt_no
		  , ars_tr_cd
		  , ars_rslt_msg
		  , ars_rslt_cd
		  , create_date
		  , create_id
		  , update_date
		  , update_id
		)
		SELECT
			a.customer_reg_no
		  , a.terms_reg_no
		  , ifnull((
					select ifnull(max(cast(terms_ars_seq as SIGNED)), 0) + 1
					from spt_customer_service_ars_profile_hist aa
					where a.customer_reg_no = aa.customer_reg_no
							  and a.terms_reg_no = aa.terms_reg_no
							  and a.terms_ars_reg_no = aa.terms_ars_reg_no
				), 1)
		  , a.terms_ars_reg_no
		  , a.customer_phone
		  , a.ars_record_data
		  , a.ars_txt_no
		  , a.ars_tr_cd
		  , a.ars_rslt_msg
		  , a.ars_rslt_cd
		  , a.create_date
		  , a.create_id
		  , a.update_date
		  , a.update_id
		FROM spt_customer_service_ars_profile a
		WHERE a.customer_reg_no = #{customerRegNo}
		AND   a.terms_reg_no = #{termsRegNo}
		AND   a.terms_ars_reg_no = #{termsArsRegNo}
	</script>""")
	int insertCustomerServiceArsProfileHist(AppCreateReq appCreateReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_terms_file_profile
		(
			customer_reg_no
		  , terms_file_reg_no
		  , terms_file_type
		  , terms_file_data
		  , data_hash
		  , create_date
		  , create_id
		  , update_date
		  , update_id
		) VALUES (
			#{customerRegNo}
		  , #{termsFileRegNo}
		  , #{termsFileType}
		  , #{termsFileData}
		  , #{dataHash}
		  , sysdate()
		  , #{customerRegNo}
		  , sysdate()
		  , #{customerRegNo}
		)
	</script>""")
	int insertCustomerServiceTermsFileProfile(AppTermsFileProfileReq appTermsFileProfileReq);

	@Insert("""<script>
		INSERT INTO spt_customer_service_terms_file_profile_hist
		(
			customer_reg_no
		  , terms_file_reg_no
		  , terms_file_type
		  , terms_file_seq
		  , terms_file_data
		  , data_hash
		  , delete_date
		  , create_date
		  , create_id
		)
		SELECT
			a.customer_reg_no
		  , a.terms_file_reg_no
		  , a.terms_file_type
		  , ifnull((
					select ifnull(max(cast(terms_file_seq as SIGNED)), 0) + 1
					from spt_customer_service_terms_file_profile_hist aa
					where a.customer_reg_no = aa.customer_reg_no
				), 1)
		  , a.terms_file_data
		  , a.data_hash
		  , a.delete_date
		  , a.create_date
		  , a.create_id
		FROM spt_customer_service_terms_file_profile a
		WHERE a.customer_reg_no = #{customerRegNo}
		AND   a.terms_file_reg_no = #{termsFileRegNo}
		AND   a.terms_file_type = #{termsFileType}
	</script>""")
	int insertCustomerServiceTermsFileProfileHist(AppTermsFileProfileReq appTermsFileProfileReq);
	
	/**
	 * (나)동의서 동의 여부 체크
	 * @param 
	 * @return
	 */
	@Select("""<script>
		select a.terms_reg_no as termsRegNo
		  from spt_customer_service_terms_profile a
		 inner join spt_customer_service_terms_pubcompany_profile b
			on a.customer_reg_no = b.customer_reg_no
		   and a.terms_reg_no = b.terms_reg_no
		 inner join spt_customer_service_profile c
			on c.customer_reg_no = b.customer_reg_no
		   and c.terms_reg_no = b.terms_reg_no
		 where a.customer_reg_no = #{customerRegNo}
		   and a.delete_date is null
		   and a.terms_expire_date > sysdate()
		   and a.terms_auth_yn = 'N'
		   and b.delete_date is null
		   and c.delete_date is null
		   <if test = "pubCompanyCodeId != null and pubCompanyCodeId != ''">
		   	and b.pubcompany_code_id = #{pubCompanyCodeId}
		   </if>
		   and a.subcompany_code_id = #{subCompanyCodeId}
		 group by a.terms_reg_no
	</script>""")
	String selectCheckedAppTerms(@Param("customerRegNo") String customerRegNo, @Param("pubCompanyCodeId") String pubCompanyCodeId, @Param("subCompanyCodeId") String subCompanyCodeId);

	@Select("""<script>
		select  a.customer_reg_no	as customerRegNo,
				a.service_reg_no	as serviceRegNo,
				a.terms_reg_no		as termsRegNo,
				b.customer_id		as customerId
		from spt_customer_service_profile a, spt_customer_info_profile b
		where a.customer_reg_no = #{customerRegNo}
		and a.app_id = #{appId}
		and a.terms_reg_no is not null
		and a.delete_date is null
		and a.customer_reg_no = b.customer_reg_no
	</script>""")
	AppTermsRes selectCancelAppTerms(@Param("customerRegNo") String customerRegNo, @Param("appId") String appId);

	@Select("""
		select app_id as appId
		  from spt_customer_service_profile
		 where terms_reg_no = #{termsRegNo}
		   and customer_reg_no = #{customerRegNo}
		   and delete_date is null
	""")
	List<String> selectAppCountForTerms(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo);

	@Update("""<script>
		update spt_customer_service_profile
		   set terms_reg_no = #{termsRegNo},
			   update_date = sysdate(),
			   update_id = #{customerRegNo}
		 where customer_reg_no = #{customerRegNo}
		   and app_id = #{appId}
		   and delete_date is null
	</script>""")
	int updateCustomerServiceProfile(@Param("customerRegNo") String customerRegNo, @Param("appId") String appId, @Param("termsRegNo") String termsRegNo);

	@Update("""<script>
		update spt_customer_service_profile
		set
			delete_date = sysdate(),
			update_date = sysdate(),
			update_id = #{customerRegNo}
		where customer_reg_no = #{customerRegNo}
		<if test="serviceRegNo != null and serviceRegNo != ''">
			and service_reg_no = #{serviceRegNo}
		</if>
		<if test="termsRegNo != null and termsRegNo != ''">
			and terms_reg_no = #{termsRegNo}
		</if>
	</script>""")
	int deleteCustomerServiceProfile(@Param("customerRegNo") String customerRegNo, @Param("serviceRegNo") String serviceRegNo, @Param("termsRegNo") String termsRegNo);

	@Select("""<script>
		select service_reg_no as serviceRegNo
		from  spt_customer_service_profile
		where customer_reg_no = #{customerRegNo}
		and   app_id = #{appId}
		and   terms_reg_no = #{termsRegNo}
		and   delete_date is null
	</script>""")
	String selectOldServiceRegNo(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo, @Param("appId") String appId);

	@Update("""<script>
		update spt_customer_service_terms_profile
		set
			delete_date = sysdate(),
			update_date = sysdate(),
			update_id = #{customerRegNo}
		where customer_reg_no = #{customerRegNo}
		and terms_reg_no = #{termsRegNo}
	</script>""")
	int deleteCustomerServiceTermsProfile(@Param("customerRegNo") String customerRegNo, @Param("termsRegNo") String termsRegNo);

	@Select("""<script>
		select service_reg_no as serviceRegNo
		from	spt_customer_service_profile
		where	customer_reg_no = #{customerRegNo}
		and		app_id = #{appId}
		and		delete_date is null
		and		terms_reg_no is not null
	</script>""")
	String selectServiceRegNo(@Param("customerRegNo") String customerRegNo, @Param("appId") String appId);

	@Update("""<script>
		update spt_customer_service_account_profile
		set		delete_date = sysdate(),
				update_date = sysdate(),
				update_id = #{customerRegNo}
		where	customer_reg_no = #{customerRegNo}
		and		service_reg_no = #{serviceRegNo}
		and		account_reg_no = #{accountRegNo}
	</script>""")
	int deleteCustomerServiceAccountProfile(@Param("customerRegNo") String customerRegNo, @Param("serviceRegNo") String serviceRegNo, @Param("accountRegNo") String accountRegNo);
	
    /**
     * 앱 삭제시 로직 추가
     * deleteCustomerServiceAccountProfile 함수에서 account_reg_no 조건만 제거
     */
	@Update("""<script>
	        update spt_customer_service_account_profile
	        set		delete_date = sysdate(),
	        update_date = sysdate(),
	        update_id = #{customerRegNo}
	        where	customer_reg_no = #{customerRegNo}
            and     service_reg_no = #{serviceRegNo}
	        </script>""")
	int deleteCustomerServiceAccountProfile2(@Param("customerRegNo") String customerRegNo, @Param("serviceRegNo") String serviceRegNo);

    @Insert("""<script>
        INSERT INTO spt_customer_service_account_profile_hist
        (
            customer_reg_no
          , service_reg_no
          , account_reg_no
          , account_seq
          , app_id
          , api_id
          , customer_realaccount_no
          , delete_date
          , create_date
          , create_id
        )
        SELECT
            a.customer_reg_no
          , a.service_reg_no
          , a.account_reg_no
          , ifnull((
                    select ifnull(max(cast(account_seq as SIGNED)), 0) + 1
                    from spt_customer_service_account_profile_hist
                    where customer_reg_no = a.customer_reg_no
                    and service_reg_no = a.service_reg_no
                    and account_reg_no = a.account_reg_no
                ), 1)
          , a.app_id
          , a.api_id
          , a.customer_realaccount_no
          , a.delete_date
          , sysdate()
          , a.create_id
        FROM spt_customer_service_account_profile a
        WHERE a.customer_reg_no = #{customerRegNo}
        AND   a.service_reg_no = #{serviceRegNo}
    </script>""")
    int insertCustomerServiceAccountProfileHist2(@Param("customerRegNo") String customerRegNo, @Param("serviceRegNo") String serviceRegNo);

	@Select("""<script>
		SELECT
			a.company_code_id as subCompanyCodeId,
			c.company_name_kor_alias as subCompanyName,
			a.app_name as appName
		FROM 		com_app_view a
		INNER JOIN 	com_app_info b
		ON 			a.app_id = b.app_id
		INNER JOIN 	com_company_profile c
		ON 			c.company_code_id = a.company_code_id
		AND			c.delete_date is null
		AND 		c.exposure_yn = 'Y'
		WHERE  		a.app_status = 'G022002'                     /*app 상태 (CA 정보)*/
		AND    		b.exposure_yn = 'Y'
		AND			a.app_id = #{appId}
	</script>""")
	AppTermsRes selectSubCompanyInfo(@Param("appId") String appId);
}