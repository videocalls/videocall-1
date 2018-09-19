# Host: 10.10.10.100  (Version 5.7.17-ndb-7.5.5-cluster-gpl-log)
# Date: 2017-07-05 10:45:27
# Generator: MySQL-Front 5.4  (Build 4.71) - http://www.mysqlfront.de/

/*!40101 SET NAMES utf8 */;

#
# Procedure "adm_api_traffic_refrash_procedures"
#

DROP PROCEDURE IF EXISTS `adm_api_traffic_refrash_procedures`;
CREATE PROCEDURE `adm_api_traffic_refrash_procedures`(IN yyyymmddFrom varchar(8), IN yyyymmddTo varchar(8))
BEGIN
    
    DECLARE v_from_length int;               /* 파라미터 길이 체크 */
    DECLARE v_to_length int;               /* 파라미터 길이 체크 */
    
    DECLARE v_start_date varchar(20);     /* 기준 start date */
    DECLARE v_end_date varchar(20);       /* 기준 end date */
    DECLARE v_not_date varchar(20);       /* 기준 end date */

    select length(yyyymmddFrom ) into v_from_length  from dual;
    select length(yyyymmddTo   ) into v_to_length  from dual;
    
    if v_from_length <> 8 or v_to_length <> 8  or yyyymmddFrom > yyyymmddTo  then
        select concat(" 파라미터는 최소 yyyymmdd(8 )이어야 합니다. from :", yyyymmddFrom ,"(", v_from_length ,") to :", yyyymmddTo ,"(", v_to_length ,") 혹은 종료일이 시작일 보다 빠름니다.") AS 'DEBUG';
    else
    
        select concat("adm_api_traffic_refrash_procedures 시작") AS 'DEBUG';
        /*
        * 소스 데이터 체크하여 5분단위 traffic data를 집계한다.
        * [start]
        */
        /* 기존 데이터 삭제 */
        select concat(yyyymmddFrom ,'0000') into v_start_date from dual;
        select concat(yyyymmddTo   ,'9999') into v_end_date from dual;

        delete  from adm_api_traffic_min 
                where std_date >=  v_start_date COLLATE utf8_general_ci
                and   std_date <=  v_end_date COLLATE utf8_general_ci
                and   std_date <> concat( date_format(now(), '%Y%m%d%H'),LPAD(CONCAT(Floor(date_format(now(), '%i')/5)*5),2,'0') )
        ; 
        select concat("5분별 데이터 삭제") AS 'DEBUG';

        select date_format(yyyymmddFrom, '%Y-%m-%d 00:00:00') into v_start_date from dual;
        select date_format(yyyymmddTo, '%Y-%m-%d 23:59:59') into v_end_date from dual;

        insert into adm_api_traffic_min(
            std_date,
            api_id,
            app_id,
            app_key,
            api_group,
            api_service,
            response_result_yn,
            api_method,
            api_resource,
            avg_api_duration,
            max_api_duration,
            min_api_duration,
            sum_api_duration,
            cnt_api_duration,
            avg_sif_duration,
            max_sif_duration,
            min_sif_duration,
            sum_sif_duration,
            cnt_sif_duration,
            create_date
        )
        select  max(std_date) as std_date,
                ifnull(a.api_id, '') as api_id,
                ifnull(a.app_id, 0) as app_id,
                ifnull(a.app_key, '') as app_key,
                ifnull(a.api_group, '') as api_group,
                ifnull(a.api_service, '') as api_service,
                ifnull(a.response_result_yn, '') as response_result_yn,
                max(a.api_method) as api_method,
                max(a.api_resource) as api_resource,
                round(sum(a.api_service_duration) / count(*), 3)    as avg_api_duration,
                max(a.api_service_duration)                         as max_api_duration, 
                min(a.api_service_duration)                         as min_api_duration,
                sum(a.api_service_duration)                         as sum_api_duration,
                count(*)                                            as cnt_api_duration,
                round(sum(a.sif_service_duration) / count(*), 3)    as avg_sif_duration,
                max(a.sif_service_duration)                         as max_sif_duration, 
                min(a.sif_service_duration)                         as min_sif_duration,
                sum(a.sif_service_duration)                         as sum_sif_duration,
                count(*)                                            as cnt_sif_duration,
                now()                                               as create_date
        from(
            select  
                    concat( date_format(a.api_response_time, '%Y%m%d%H'),LPAD(CONCAT(Floor(date_format(a.api_response_time, '%i')/5)*5),2,'0') ) as std_date,
                    a.api_id,
                    b.app_id,
                    a.app_key,
                    a.api_group,
                    a.api_service,
                    a.api_method,
                    a.api_resource,
                    a.response_result_yn,
                    a.api_service_duration,
                    a.sif_service_duration
            from ca_gw_api_traffic_view a
            left join com_app_view b on a.app_key = b.app_key
            where api_response_time  >=  v_start_date  COLLATE utf8_general_ci  
            and   api_response_time  <=  v_end_date  COLLATE utf8_general_ci
            and   concat( date_format(a.api_response_time, '%Y%m%d%H'),LPAD(CONCAT(Floor(date_format(a.api_response_time, '%i')/5)*5),2,'0') ) 
                                     not like concat( date_format(now(), '%Y%m%d%H'),LPAD(CONCAT(Floor(date_format(now(), '%i')/5)*5),2,'0') )
        ) a                
        group by a.std_date, a.api_id, a.app_id, a.app_key, a.api_group, a.response_result_yn
        ;
        select concat("5분별 데이터 등록 완료") AS 'DEBUG';
        

        /*
        * 소스 데이터 체크하여 5분단위 traffic data를 집계한다.
        * [end]
        */

        
        /*
        * 시간 별 traffic data를 집계한다.
        * [start]
        */

        select concat(yyyymmddFrom ,'00') into v_start_date from dual;
        select concat(yyyymmddTo   ,'99') into v_end_date from dual;

        /* 기존 데이터 삭제 */
        delete  from adm_api_traffic_hourly 
                where std_date >=  v_start_date COLLATE utf8_general_ci
                and   std_date <=  v_end_date COLLATE utf8_general_ci
                and   std_date not like concat( date_format(now(),'%Y%m%d%H') , '%' )
        ; 
        select concat(" 시간별 데이터 삭제") AS 'DEBUG';

        select concat(yyyymmddFrom ,'0000') into v_start_date from dual;
        select concat(yyyymmddTo   ,'9999') into v_end_date from dual;

        insert into adm_api_traffic_hourly(
            std_date,
            api_id,
            app_id,
            app_key,
            api_group,
            api_service,
            response_result_yn,
            api_method,
            api_resource,
            avg_api_duration,
            max_api_duration,
            min_api_duration,
            sum_api_duration,
            cnt_api_duration,
            avg_sif_duration,
            max_sif_duration,
            min_sif_duration,
            sum_sif_duration,
            cnt_sif_duration,
            create_date
        )
        select  left(std_date, 10) as std_date,
                ifnull(a.api_id, '') as api_id,
                ifnull(a.app_id, 0) as app_id,
                ifnull(a.app_key, '') as app_key,
                ifnull(a.api_group, '') as api_group,
                ifnull(a.api_service, '') as api_service,
                ifnull(a.response_result_yn, '') as response_result_yn,
                max(a.api_method) as api_method,
                max(a.api_resource) as api_resource,
                round(sum(a.sum_api_duration) / sum(a.cnt_api_duration), 3)     as avg_api_duration,
                max(a.max_api_duration)                                         as max_api_duration, 
                min(a.min_api_duration)                                         as min_api_duration,
                sum(a.sum_api_duration)                                         as sum_api_duration,
                sum(a.cnt_api_duration)                                         as cnt_api_duration,
                round(sum(a.sum_sif_duration) / sum(a.cnt_sif_duration), 3)     as avg_sif_duration,
                max(a.max_sif_duration)                                         as max_sif_duration, 
                min(a.min_sif_duration)                                         as min_sif_duration,
                sum(a.sum_sif_duration)                                         as sum_sif_duration,
                sum(a.cnt_sif_duration)                                         as cnt_sif_duration,
                now()                                                           as create_date
        from adm_api_traffic_min a               
        where std_date >=  v_start_date COLLATE utf8_general_ci
        and   std_date <=  v_end_date COLLATE utf8_general_ci
        and   std_date not like concat( date_format(now(),'%Y%m%d%H') , '%' )
        group by left(std_date, 10), a.api_id, a.app_id, a.app_key, a.api_group, a.response_result_yn   
        ;            

        select concat(" 시간별 데이터 등록 완료") AS 'DEBUG';
 
        /*
        * 일자별 traffic data를 집계한다.
        * [start]
        */
        
        select yyyymmddFrom  into v_start_date from dual;
        select yyyymmddTo    into v_end_date from dual;

        /* 기존 데이터 삭제 */
        delete  from adm_api_traffic_daily 
                where std_date >=  v_start_date COLLATE utf8_general_ci
                and   std_date <=  v_end_date COLLATE utf8_general_ci
                and   std_date not like concat( date_format(now(),'%Y%m%d') , '%' )
        ; 
        select concat("일별 데이터 삭제") AS 'DEBUG';

        select concat(yyyymmddFrom ,'00') into v_start_date from dual;
        select concat(yyyymmddTo   ,'99') into v_end_date from dual;

        insert into adm_api_traffic_daily(
            std_date,
            api_id,
            app_id,
            app_key,
            api_group,
            api_service,
            response_result_yn,
            api_method,
            api_resource,
            avg_api_duration,
            max_api_duration,
            min_api_duration,
            sum_api_duration,
            cnt_api_duration,
            avg_sif_duration,
            max_sif_duration,
            min_sif_duration,
            sum_sif_duration,
            cnt_sif_duration,
            create_date
        )
        select  left(std_date, 8)  as std_date,
                ifnull(a.api_id, '') as api_id,
                ifnull(a.app_id, 0) as app_id,
                ifnull(a.app_key, '') as app_key,
                ifnull(a.api_group, '') as api_group,
                ifnull(a.api_service, '') as api_service,
                ifnull(a.response_result_yn, '') as response_result_yn,
                max(a.api_method) as api_method,
                max(a.api_resource) as api_resource,
                round(sum(a.sum_api_duration) / sum(a.cnt_api_duration), 3)     as avg_api_duration,
                max(a.max_api_duration)                                         as max_api_duration, 
                min(a.min_api_duration)                                         as min_api_duration,
                sum(a.sum_api_duration)                                         as sum_api_duration,
                sum(a.cnt_api_duration)                                         as cnt_api_duration,
                round(sum(a.sum_sif_duration) / sum(a.cnt_sif_duration), 3)     as avg_sif_duration,
                max(a.max_sif_duration)                                         as max_sif_duration, 
                min(a.min_sif_duration)                                         as min_sif_duration,
                sum(a.sum_sif_duration)                                         as sum_sif_duration,
                sum(a.cnt_sif_duration)                                         as cnt_sif_duration,
                now()                                                           as create_date
        from adm_api_traffic_hourly a               
        where std_date >=  v_start_date COLLATE utf8_general_ci
        and   std_date <=  v_end_date COLLATE utf8_general_ci
        and   std_date not like concat( date_format(now(),'%Y%m%d') , '%' )
        group by left(std_date, 8), a.api_id, a.app_id, a.app_key, a.api_group, a.response_result_yn 
        ;
        select concat("일별 데이터 등록 완료") AS 'DEBUG';
        /*
        * 일자별 traffic data를 집계한다.
        * [end]
        */
     
 
        /*
        * 월별 traffic data를 집계한다.
        * [start]
        */
        /* 기존 데이터 삭제 */
        select left(yyyymmddFrom ,6) into v_start_date from dual;
        select left(yyyymmddTo   ,6) into v_end_date from dual;
        
        delete  from adm_api_traffic_monthly 
                where std_date >=  v_start_date COLLATE utf8_general_ci
                and   std_date <=  v_end_date COLLATE utf8_general_ci
                and   std_date not like concat( date_format(now(),'%Y%m') , '%' ); 

        select concat("데이터 삭제") AS 'DEBUG';

        select concat(left(yyyymmddFrom ,6) ,'00') into v_start_date from dual;
        select concat(left(yyyymmddTo   ,6) ,'99') into v_end_date from dual;

        insert into adm_api_traffic_monthly(
            std_date,
            api_id,
            app_id,
            app_key,
            api_group,
            api_service,
            response_result_yn,
            api_method,
            api_resource,
            avg_api_duration,
            max_api_duration,
            min_api_duration,
            sum_api_duration,
            cnt_api_duration,
            avg_sif_duration,
            max_sif_duration,
            min_sif_duration,
            sum_sif_duration,
            cnt_sif_duration,
            create_date
        )
        select  left(std_date, 6) as std_date,
                ifnull(a.api_id, '') as api_id,
                ifnull(a.app_id, '') as app_id,
                ifnull(a.app_key, '') as app_key,
                ifnull(a.api_group, '') as api_group,
                ifnull(a.api_service, '') as api_service,
                ifnull(a.response_result_yn, '') as response_result_yn,
                max(a.api_method) as api_method,
                max(a.api_resource) as api_resource,
                round(sum(a.sum_api_duration) / sum(a.cnt_api_duration), 3)     as avg_api_duration,
                max(a.max_api_duration)                                         as max_api_duration, 
                min(a.min_api_duration)                                         as min_api_duration,
                sum(a.sum_api_duration)                                         as sum_api_duration,
                sum(a.cnt_api_duration)                                         as cnt_api_duration,    
                round(sum(a.sum_sif_duration) / sum(a.cnt_sif_duration), 3)     as avg_sif_duration,
                max(a.max_sif_duration)                                         as max_sif_duration, 
                min(a.min_sif_duration)                                         as min_sif_duration,
                sum(a.sum_sif_duration)                                         as sum_sif_duration,
                sum(a.cnt_sif_duration)                                         as cnt_sif_duration,
                now()                                                           as create_date
        from adm_api_traffic_daily a               
        where std_date >=  v_start_date COLLATE utf8_general_ci
        and   std_date <=  v_end_date COLLATE utf8_general_ci
        and   std_date not like concat( date_format(now(),'%Y%m') , '%' )
        group by left(std_date, 6), a.api_id, a.app_id, a.app_key, a.api_group, a.response_result_yn   
        ;
        select concat("월별 집계 종료") AS 'DEBUG';
        
        /*
        * 월별 traffic data를 집계한다.
        * [end]
        */ 
 
 
 
        end if;

END;
